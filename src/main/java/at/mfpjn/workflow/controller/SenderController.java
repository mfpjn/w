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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.mfpjn.workflow.model.Customer;
import at.mfpjn.workflow.model.UserMediaChannelsParameters;
import at.mfpjn.workflow.routebuilder.SenderRouteBuilder;
import at.mfpjn.workflow.routebuilder.StringTemplateRouteBuilder;
import at.mfpjn.workflow.routebuilder.TwitterSenderRouteBuilder;
import at.mfpjn.workflow.service.CustomerService;
import at.mfpjn.workflow.service.UserMediaChannelsParametersService;


@Controller
public class SenderController {

    private final String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
    private final String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
    private String accessToken;//private final String accessToken = "3214140528-UfqhFlBsTwElZe1ItXNfJD7FdxBhRyPsmM8qs6l";
    private String accessTokenSecret;//private final String accessTokenSecret = "U8QAwFW1muOTOQSAt3spO8alUJagslSwUTcdgIp1CCCxx";
    private CamelContext context;
    
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserMediaChannelsParametersService userMediaChannelsParametersService;

    @RequestMapping(value = "/sender", method = RequestMethod.POST)
    public String sender(HttpServletRequest request) throws Exception {
    	Customer currentCustomer = loggedInCustomer();
    	if(null == currentCustomer)
    		return "login";
    	
    	UserMediaChannelsParameters userMediaChannelsParameter = userMediaChannelsParametersService.getTwitterParameter(currentCustomer.getId());
    	
    	if(userMediaChannelsParameter != null){
    		accessToken = userMediaChannelsParameter.getAccessToken();
    		accessTokenSecret = userMediaChannelsParameter.getAccessTokenSecret();
    	}
    	
        // create CamelContext
        context = new DefaultCamelContext();

        // connect to embedded ActiveMQ JMS broker
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        // get post input
        String post = (request.getParameter("postMessage"));

        //post on Facebook?
        String facebookPost = (request.getParameter("postFacebook"));

        boolean facebookBool;
        if (facebookPost != null) {
            facebookBool = true;
        } else {
            facebookBool = false;
        }

        //post on Twitter?
        String twitterPost = (request.getParameter("postTwitter"));

        boolean twitterBool;
        if (twitterPost != null) {
            twitterBool = true;
        } else {
            twitterBool = false;
        }

        // Define routes
        RouteBuilder senderRoute = new SenderRouteBuilder(facebookBool, twitterBool);
        TwitterSenderRouteBuilder twitterRoute = new TwitterSenderRouteBuilder();
        twitterRoute.setAccessToken(accessToken);
        twitterRoute.setAccessTokenSecret(accessTokenSecret);
        twitterRoute.setConsumerKey(consumerKey);
        twitterRoute.setConsumerSecret(consumerSecret);

        StringTemplateRouteBuilder stringTemplateRoute = new StringTemplateRouteBuilder();
        stringTemplateRoute.setRecipientEmail(currentCustomer.getEmail());

        // add routes
        context.addRoutes(senderRoute);
        context.addRoutes(twitterRoute);
        context.addRoutes(stringTemplateRoute);

        context.start();

        ProducerTemplate template = context.createProducerTemplate();

        // start the route and let it do its work
        context.start();

        template.sendBody("direct:start", post);
        template.send("direct:emailConfirmation",
                createConfirmationEmail(currentCustomer.getFirstName(), currentCustomer.getLastName(), post));

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
    
	@RequestMapping(value = "/inputForm")
	public String inputForm() {
		if(null == loggedInCustomer())
    		return "login";
		
		return "inputForm";
	}

    private Customer loggedInCustomer() {
    	String userName;
    	
    	try{
    		userName = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    	}
    	catch(Exception e){
    		return null;
    	}
    	
    	
		Customer currentCustomer = customerService.getUser(userName);
        if (currentCustomer == null ) {
        	return null;
        }
        
        return currentCustomer;
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
