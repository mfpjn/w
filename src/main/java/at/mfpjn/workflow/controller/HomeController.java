package at.mfpjn.workflow.controller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.mfpjn.workflow.routebuilder.PostInputRouteBuilder;
import at.mfpjn.workflow.routebuilder.SocMediaRouteBuilder;

import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
    
    @RequestMapping(value = "/home")
    public String home2() throws Exception {
    	// create CamelContext
        CamelContext context = new DefaultCamelContext();
        
        // connect to embedded ActiveMQ JMS broker
        ConnectionFactory connectionFactory = 
            new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        context.addComponent("jms",
            JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        // get post input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Post:");
        String post = br.readLine();
        System.out.print("Post to Facebook? (y/n):");
        
        // get facebook input
        String facebookPost = br.readLine();
        boolean facebookBool;
        if(facebookPost.equals("y")){
        	facebookBool = true;
        }else{
        	facebookBool = false;
        }
        System.out.print("Post to Twitter? (y/n):");
        
        // get twitter input
        String twitterPost = br.readLine();
        boolean twitterBool;
        if(twitterPost.equals("y")){
        	twitterBool = true;
        }else{
        	twitterBool = false;
        }
        
        System.out.println(post + ", " + facebookPost + ", " + twitterPost);
        
        // add our route to the CamelContext
        context.addRoutes(new PostInputRouteBuilder());
        context.addRoutes(new SocMediaRouteBuilder(facebookBool, twitterBool));

        ProducerTemplate template = context.createProducerTemplate();

        // start the route and let it do its work
        context.start();
        
        template.sendBody("direct:start", post);
        
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();
    	
    	
    	
        return "home";
    }
}
