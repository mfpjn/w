package at.mfpjn.workflow.controller;

import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;

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

import at.mfpjn.workflow.routebuilder.SenderRouteBuilder;
import at.mfpjn.workflow.routebuilder.StringTemplateRouteBuilder;
import at.mfpjn.workflow.routebuilder.TwitterSenderRouteBuilder;


@Controller
public class SenderController {
	
	private CamelContext context;
	
	//TODO replace with DB call
	private final String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
	private final String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
	private final String accessToken = "3214140528-UfqhFlBsTwElZe1ItXNfJD7FdxBhRyPsmM8qs6l";
	private final String accessTokenSecret = "U8QAwFW1muOTOQSAt3spO8alUJagslSwUTcdgIp1CCCxx";	
    
    @RequestMapping(value = "/sender", method = RequestMethod.POST)
    public String sender(HttpServletRequest request) throws Exception {

    	// create CamelContext
        context = new DefaultCamelContext();
        
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
        
		// Define routes
        RouteBuilder senderRoute = new SenderRouteBuilder(facebookBool, twitterBool);
        TwitterSenderRouteBuilder twitterRoute = new TwitterSenderRouteBuilder();
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
				createConfirmationEmail("Nicolas", "Lett", post));

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
