package at.mfpjn.workflow.controller;

import at.mfpjn.workflow.routebuilder.ReceiverRouteBuilder;
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
public class ReceiverController {

    
    @RequestMapping(value = "/receiver")
    public String receiver(HttpServletRequest request) throws Exception {

    	// create CamelContext
        CamelContext context = new DefaultCamelContext();

        // get post
        FacebookController fc = new FacebookController();
        fc.initFacebook();
        String receivedPost = fc.receivePost();


        // add routes
        RouteBuilder receiverRoute = new ReceiverRouteBuilder(true, true);
        context.addRoutes(receiverRoute);

        ProducerTemplate template = context.createProducerTemplate();

        // start the route and let it do its work
        context.start();

        template.sendBody("direct:facebook", "post number 1");
        template.sendBody("direct:twitter", "post message 2");
        template.sendBody("direct:facebook", "post number 3");
        template.sendBody("direct:facebook", "post number 4");
        template.sendBody("direct:twitter", "post number 5");
        
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();
    	
        return "home";
    }
}
