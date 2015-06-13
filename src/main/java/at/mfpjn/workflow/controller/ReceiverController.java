package at.mfpjn.workflow.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.facebook.FacebookComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import twitter4j.TwitterException;
import at.mfpjn.workflow.model.FacebookModel;
import at.mfpjn.workflow.model.TwitterModel;
import at.mfpjn.workflow.routebuilder.FacebookRouteBuilder;
import at.mfpjn.workflow.routebuilder.FtpRouteBuilder;
import at.mfpjn.workflow.routebuilder.ReceiverRouteBuilder;
import at.mfpjn.workflow.routebuilder.TwitterReceiverRouteBuilder;

@Controller
public class ReceiverController {
    
	//TODO replace with DB call
	private final String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
	private final String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
	private final String accessToken = "3214140528-UfqhFlBsTwElZe1ItXNfJD7FdxBhRyPsmM8qs6l";
	private final String accessTokenSecret = "U8QAwFW1muOTOQSAt3spO8alUJagslSwUTcdgIp1CCCxx";
	
    @RequestMapping(value = "/receiver")
    public String receiver(HttpServletRequest request) throws Exception {

    	// create CamelContext
        CamelContext context = new DefaultCamelContext();

        // add external components
        FacebookComponent fc = new FacebookComponent();
        context.addComponent("facebook", fc);

        // set Facebook component configuration
        FacebookModel fm = new FacebookModel();
        fm.setFbCamelConfiguration(fc);

        // add routes
        RouteBuilder facebookRoute = new FacebookRouteBuilder();
        
        String userName = getTwitterUserName();
        
        TwitterReceiverRouteBuilder twitterReceiverRoute = new TwitterReceiverRouteBuilder();
        //TODO replace with db call
        twitterReceiverRoute.setConsumerKey(consumerKey);
        twitterReceiverRoute.setConsumerSecret(consumerSecret);
        twitterReceiverRoute.setAccessToken(accessToken);
        twitterReceiverRoute.setAccessTokenSecret(accessTokenSecret);
        twitterReceiverRoute.setUser(userName);
        
        RouteBuilder receiverRoute = new ReceiverRouteBuilder(true, true, "number");
        context.addRoutes(facebookRoute);
        context.addRoutes(twitterReceiverRoute);
        context.addRoutes(receiverRoute);


        // start the route and let it do its work
        context.start();
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();

        context = new DefaultCamelContext();

        RouteBuilder ftpRouteBuilder = new FtpRouteBuilder();
        context.addRoutes(ftpRouteBuilder);
        context.start();

        Thread.sleep(20000);

        context.stop();


    	
        return "home";
    }

	private String getTwitterUserName() throws IllegalStateException, TwitterException {
        TwitterModel tm = new TwitterModel();
        //TODO replace with db call
        tm.setAccessToken(accessToken);
        tm.setAccessTokenSecret(accessTokenSecret);
        tm.setConsumerKey(consumerKey);
        tm.setConsumerSecret(consumerSecret);
        return tm.getTwitterUserName();
	}
}
