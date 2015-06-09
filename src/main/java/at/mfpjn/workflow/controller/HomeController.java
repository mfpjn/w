package at.mfpjn.workflow.controller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.camel.builder.RouteBuilder;

import at.mfpjn.workflow.routebuilder.PostInputRouteBuilder;
import at.mfpjn.workflow.routebuilder.SocMediaRouteBuilder;

import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;



@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(Locale locale, Model model, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/resources/img");
        System.err.println(new FileSystemResource(new File(path)));
        return "home";
    }

    @RequestMapping(value = "/inputForm")
    public String test() {
        return "inputForm";
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String home2(HttpServletRequest request) throws Exception {

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
        
        System.out.println(post + ", " + facebookPost + ", " + twitterPost);
        
        // add our route to the CamelContext

        //context.addRoutes(new PostInputRouteBuilder());

        //start the route jms:messages
        RouteBuilder formToSplitter = new PostInputRouteBuilder();
        context.addRoutes(formToSplitter);

        RouteBuilder route2 = new SocMediaRouteBuilder(facebookBool, twitterBool);

        context.addRoutes(route2);

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
