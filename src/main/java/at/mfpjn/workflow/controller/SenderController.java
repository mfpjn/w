package at.mfpjn.workflow.controller;

import at.mfpjn.workflow.routebuilder.SenderRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;


@Controller
public class SenderController {

    
    @RequestMapping(value = "/sender", method = RequestMethod.POST)
    public String sender(HttpServletRequest request) throws Exception {

    	// create CamelContext
        CamelContext context = new DefaultCamelContext();
        
        // connect to embedded ActiveMQ JMS broker
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        // get post input
        String messageToPost = (request.getParameter("postMessage"));
        //System.out.print("Enter Post:");
        String post = messageToPost;
        
        // get facebook input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Post to Facebook? (y/n):");
        String facebookPost = br.readLine();
        boolean facebookBool;
        if(facebookPost.equals("y")){
        	facebookBool = true;
        }else{
        	facebookBool = false;
        }
        
        // get twitter input
        System.out.print("Post to Twitter? (y/n):");
        String twitterPost = br.readLine();
        boolean twitterBool;
        if(twitterPost.equals("y")){
        	twitterBool = true;
        }else{
        	twitterBool = false;
        }

        // add routes
        RouteBuilder senderRoute = new SenderRouteBuilder(facebookBool, twitterBool);
        context.addRoutes(senderRoute);

        ProducerTemplate template = context.createProducerTemplate();

        // start the route and let it do its work
        context.start();

        template.sendBody("direct:start", post);

        String statusFormToSplitter = template.requestBody("controlbus:route?routeId=formToSplitter&action=status", null, String.class);

        System.out.println("*** route from Form to Splitter: " + statusFormToSplitter);

        String status2 = template.requestBody("controlbus:route?routeId=route2&action=status", null, String.class);

        System.out.println("=============================ROUTE 2 STATUS: " + status2);
        
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();

        String status3 = template.requestBody("controlbus:route?routeId=route2&action=status", null, String.class);

        System.out.println("=============================ROUTE 2 STATUS: " + status3);
    	
        return "home";
    }
}
