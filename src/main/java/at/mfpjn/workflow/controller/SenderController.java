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
        String post = (request.getParameter("postMessage"));
        //System.out.print("Enter Post:");

        //post on Facebook?
        String facebookPost = (request.getParameter("postFacebook"));

        boolean facebookBool;
        if(facebookPost != null){
            facebookBool = true;
        }else{
            facebookBool = false;
        }

        //post on Twitter?
        String twitterPost = (request.getParameter("postTwitter"));

        boolean twitterBool;
        if(twitterPost != null){
            twitterBool = true;
        }else{
            twitterBool = false;
        }

        //Activate Reader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // add routes
        RouteBuilder senderRoute = new SenderRouteBuilder(facebookBool, twitterBool);
        context.addRoutes(senderRoute);

        ProducerTemplate template = context.createProducerTemplate();

        // start the route and let it do its work
        context.start();

        template.sendBody("direct:start", post);

        String facebookRouteStatus1 = template.requestBody("controlbus:route?routeId=facebookRoute&action=status", null, String.class);

        System.out.println("*** Facebook Route Status (exp. Started): " + facebookRouteStatus1);

        String twitterRouteStatus1 = template.requestBody("controlbus:route?routeId=twitterRoute&action=status", null, String.class);

        System.out.println("*** Twitter Route Status (exp. Started): " + twitterRouteStatus1);
        
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();

        String facebookRouteStatus2 = template.requestBody("controlbus:route?routeId=facebookRoute&action=status", null, String.class);

        System.out.println("*** Facebook Route Status (exp. Stopped): " + facebookRouteStatus2);

        String twitterRouteStatus2 = template.requestBody("controlbus:route?routeId=twitterRoute&action=status", null, String.class);

        System.out.println("*** Twitter Route Status (exp. Stopped): " + twitterRouteStatus2);
    	
        return "home";
    }
}
