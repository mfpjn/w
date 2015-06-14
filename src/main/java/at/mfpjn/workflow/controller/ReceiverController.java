package at.mfpjn.workflow.controller;

import at.mfpjn.workflow.model.FacebookModel;
import at.mfpjn.workflow.model.TwitterModel;
import at.mfpjn.workflow.routebuilder.FacebookReceiverRouteBuilder;
import at.mfpjn.workflow.routebuilder.FtpRouteBuilder;
import at.mfpjn.workflow.routebuilder.ReceiverRouteBuilder;
import at.mfpjn.workflow.routebuilder.TwitterReceiverRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.facebook.FacebookComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.TwitterException;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ReceiverController {

    // TODO replace with DB call
    private final String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
    private final String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
    private final String accessToken = "3214140528-UfqhFlBsTwElZe1ItXNfJD7FdxBhRyPsmM8qs6l";
    private final String accessTokenSecret = "U8QAwFW1muOTOQSAt3spO8alUJagslSwUTcdgIp1CCCxx";


    @RequestMapping(value = "/receiverForm")
    public String home() throws Exception {
        return "receiverForm";
    }

    @RequestMapping(value = "/receiver", method = RequestMethod.POST)
    public String receiver(HttpServletRequest request) throws Exception {
        // get from Facebook?
        String facebookPost = (request.getParameter("getFacebook"));
        boolean fetchFacebookPosts;
        if (facebookPost != null) {
            fetchFacebookPosts = true;
        } else {
            fetchFacebookPosts = false;
        }

        // get from Twitter?
        String twitterPost = (request.getParameter("getTwitter"));
        boolean fetchTwitterPosts;
        if (twitterPost != null) {
            fetchTwitterPosts = true;
        } else {
            fetchTwitterPosts = false;
        }

        // use Aggregator?
        String aggregator = (request.getParameter("aggregator"));
        boolean aggregatorBool;
        if (aggregator != null) {
            aggregatorBool = true;
        } else {
            aggregatorBool = false;
        }

        // use Filter?
        String filter = (request.getParameter("filter"));
        boolean filterBool;
        if (filter != null) {
            filterBool = true;
        } else {
            filterBool = false;
        }

        // filter sting
        String filterString = (request.getParameter("filterString"));

        // Save to Local
        String saveLocal = (request.getParameter("saveLocal"));
        boolean saveLocalBool;
        if (saveLocal != null) {
            saveLocalBool = true;
        } else {
            saveLocalBool = false;
        }

        // Save to FTP
        String saveFTP = (request.getParameter("saveFTP"));
        boolean saveFTPBool;
        if (saveFTP != null) {
            saveFTPBool = true;
        } else {
            saveFTPBool = false;
        }

        // Save to CSV
        String saveCSV = (request.getParameter("saveCSV"));
        boolean saveCSVBool;
        if (saveCSV != null) {
            saveCSVBool = true;
        } else {
            saveCSVBool = false;
        }

        // Save to DB
        String saveDB = (request.getParameter("saveDB"));
        boolean saveDBBool;
        if (saveDB != null) {
            saveDBBool = true;
        } else {
            saveDBBool = false;
        }

        // create CamelContext
        CamelContext context = new DefaultCamelContext();

        if (fetchFacebookPosts) {
            // add external components
            FacebookComponent fc = new FacebookComponent();
            context.addComponent("facebook", fc);

            // set Facebook component configuration
            FacebookModel fm = new FacebookModel();
            fm.setFbCamelConfiguration(fc);

            FacebookReceiverRouteBuilder facebookRoute = new FacebookReceiverRouteBuilder();
            facebookRoute.setFilter(filterBool);
            facebookRoute.setAggregate(aggregatorBool);
            context.addRoutes(facebookRoute);
        }

        if (fetchTwitterPosts) {
            TwitterReceiverRouteBuilder twitterReceiverRoute = new TwitterReceiverRouteBuilder();
            // TODO replace with db call
            twitterReceiverRoute.setConsumerKey(consumerKey);
            twitterReceiverRoute.setConsumerSecret(consumerSecret);
            twitterReceiverRoute.setAccessToken(accessToken);
            twitterReceiverRoute.setAccessTokenSecret(accessTokenSecret);
            String userName = getTwitterUserName();
            twitterReceiverRoute.setUser(userName);
            twitterReceiverRoute.setFilter(filterBool);
            twitterReceiverRoute.setAggregate(aggregatorBool);
            context.addRoutes(twitterReceiverRoute);
        }

        RouteBuilder receiverRoute = new ReceiverRouteBuilder(saveLocalBool, saveFTPBool, saveCSVBool, filterString);
        context.addRoutes(receiverRoute);

        // start the route and let it do its work
        context.start();
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();

        return "home";
    }

    private String getTwitterUserName() throws IllegalStateException,
            TwitterException {
        TwitterModel tm = new TwitterModel();
        // TODO replace with db call
        tm.setAccessToken(accessToken);
        tm.setAccessTokenSecret(accessTokenSecret);
        tm.setConsumerKey(consumerKey);
        tm.setConsumerSecret(consumerSecret);
        return tm.getTwitterUserName();
    }


    @RequestMapping(value = "/ftp")
    public String ftp(HttpServletRequest request) throws Exception {

        // create CamelContext
        CamelContext context = new DefaultCamelContext();
        context = new DefaultCamelContext();

        RouteBuilder ftpRouteBuilder = new FtpRouteBuilder();
        context.addRoutes(ftpRouteBuilder);

        context.start();

        Thread.sleep(10000);

        context.stop();


        return "home";
    }


}
