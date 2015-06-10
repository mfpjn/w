package at.mfpjn.workflow.controller;

import at.mfpjn.workflow.routebuilder.SenderRouteBuilder;
import at.mfpjn.workflow.routebuilder.StringTemplateRouteBuilder;
import at.mfpjn.workflow.routebuilder.TwitterRouteBuilder;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
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

	private static String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
	private static String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
	private static String accessToken = "3214140528-UfqhFlBsTwElZe1ItXNfJD7FdxBhRyPsmM8qs6l";
	private static String accessTokenSecret = "U8QAwFW1muOTOQSAt3spO8alUJagslSwUTcdgIp1CCCxx";
	
	private CamelContext context;
    
    @RequestMapping(value = "/sender", method = RequestMethod.POST)
    public String sender(HttpServletRequest request) throws Exception {

    	// create CamelContext
        context = new DefaultCamelContext();
        
        // connect to embedded ActiveMQ JMS broker
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        // get post input
        String messageToPost = (request.getParameter("postMessage"));
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

        // Define routes
        RouteBuilder senderRoute = new SenderRouteBuilder(facebookBool, twitterBool);
        TwitterRouteBuilder twitterRoute = new TwitterRouteBuilder();
        twitterRoute.setAccessToken(accessToken);
        twitterRoute.setAccessTokenSecret(accessTokenSecret);
        twitterRoute.setConsumerKey(consumerKey);
        twitterRoute.setConsumerSecret(consumerSecret);
		// route.setUser(user.getName());
		// route.setUser("user");
		//twitterRoute.setMessage(messageToPost);
        
        StringTemplateRouteBuilder stringTemplateRoute = new StringTemplateRouteBuilder();
        //TODO set logged in user email address 
        stringTemplateRoute.setRecipientEmail("lett.nicolas@gmail.com");
        
        // add routes
        context.addRoutes(senderRoute);
		context.addRoutes(twitterRoute);
		context.addRoutes(stringTemplateRoute);
		
		context.start();

        ProducerTemplate template = context.createProducerTemplate();

        // start the route and let it do its work
        context.start();

        template.sendBody("direct:start", post);
        //TODO set first and last name of logged in user
        template.send("direct:emailConfirmation",
				createConfirmationEmail("Nicolas", "Lett", messageToPost));

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
    
    private Exchange createConfirmationEmail(String firstName, String lastName,
			String postMessage) {
		Exchange exchange = context
				.getEndpoint("direct:emailConfirmation").createExchange();
		Message msg = exchange.getIn();
		msg.setHeader("firstName", firstName);
		msg.setHeader("lastName", lastName);
		msg.setBody(postMessage);
		return exchange;
	}
}
