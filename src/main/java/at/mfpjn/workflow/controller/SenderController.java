package at.mfpjn.workflow.controller;

import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.mfpjn.workflow.model.Customer;
import at.mfpjn.workflow.model.TwitterModel;
import at.mfpjn.workflow.routebuilder.PersistanceRouteBuilder;
import at.mfpjn.workflow.routebuilder.SenderRouteBuilder;
import at.mfpjn.workflow.routebuilder.StringTemplateRouteBuilder;
import at.mfpjn.workflow.routebuilder.TwitterRouteBuilder;
import at.mfpjn.workflow.service.CustomerService;


@Controller
public class SenderController {
	
	@Autowired
	CustomerService customerService;
	
	private CamelContext context;
    
    @RequestMapping(value = "/sender", method = RequestMethod.POST)
    public String sender(HttpServletRequest request) throws Exception {

    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = userDetails.getUsername();
		Customer customer = customerService.getUser(name);
                     
		int userId = customer.getId();
        
        // connect to embedded ActiveMQ JMS broker
      	SimpleRegistry registry = new SimpleRegistry();
      	
      	// code to create data source here
      	String url = "com.mysql.jdbc.Driver@localhost:3306:workflow";
      	final DataSource ds = setupDataSource(url);
      	registry.put("workflowDB", ds);
      	
      	// create CamelContext
        context = new DefaultCamelContext(registry);
        
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
        //PersistanceRouteBuilder persistance = new PersistanceRouteBuilder();
        TwitterRouteBuilder twitterRoute = new TwitterRouteBuilder();
        twitterRoute.setAccessToken(TwitterModel.accessToken);
        twitterRoute.setAccessTokenSecret(TwitterModel.accessTokenSecret);
        twitterRoute.setConsumerKey(TwitterModel.consumerKey);
        twitterRoute.setConsumerSecret(TwitterModel.consumerSecret);
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
		//context.addRoutes(persistance);
		//need to add route for db
		context.start();

        ProducerTemplate template = context.createProducerTemplate();

        // start the route and let it do its work
        context.start();

        template.sendBodyAndHeader("direct:start", post, "myId", userId);
        //template.sendBody("direct:start", post);
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
    
    private static DataSource setupDataSource(String connectURI) {
   	 BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/workflow");
        ds.setUsername("root");
        ds.setPassword("0000");
        return ds;
   }
}
