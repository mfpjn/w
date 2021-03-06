package at.mfpjn.workflow.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.facebook.FacebookComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter4j.TwitterException;
import at.mfpjn.workflow.model.Customer;
import at.mfpjn.workflow.model.FacebookModel;
import at.mfpjn.workflow.model.TwitterModel;
import at.mfpjn.workflow.model.UserMediaChannelsParameters;
import at.mfpjn.workflow.routebuilder.FacebookReceiverRouteBuilder;
import at.mfpjn.workflow.routebuilder.FtpRouteBuilder;
import at.mfpjn.workflow.routebuilder.ReceiverRouteBuilder;
import at.mfpjn.workflow.routebuilder.TwitterReceiverRouteBuilder;
import at.mfpjn.workflow.service.CustomerService;
import at.mfpjn.workflow.service.UserMediaChannelsParametersService;

@Controller
public class ReceiverController {
	private String accessToken; // private final String accessToken =
	// "3214140528-UfqhFlBsTwElZe1ItXNfJD7FdxBhRyPsmM8qs6l";
	private String accessTokenSecret; // private final String accessTokenSecret
	// =
	// "U8QAwFW1muOTOQSAt3spO8alUJagslSwUTcdgIp1CCCxx";

	public ReceiverRouteBuilder receiverRoute;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserMediaChannelsParametersService userMediaChannelsParametersService;

	@RequestMapping(value = "/receiverForm")
	public String home() throws Exception {
		if (null == loggedInCustomer())
			return "login";

		return "receiverForm";
	}

	@RequestMapping(value = "/receiver", method = RequestMethod.POST)
	public String receiver(HttpServletRequest request, Model model)
			throws Exception {

		Customer currentCustomer = loggedInCustomer();
		if (null == currentCustomer)
			return "login";

		UserMediaChannelsParameters userMediaChannelsParameter = userMediaChannelsParametersService
				.getTwitterParameter(currentCustomer.getId());

		if (userMediaChannelsParameter != null) {
			accessToken = userMediaChannelsParameter.getAccessToken();
			accessTokenSecret = userMediaChannelsParameter
					.getAccessTokenSecret();
		}

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

		// Save ShickIT
		String saveSchickit = (request.getParameter("saveSchickit"));
		boolean shickItBool;
		if (saveSchickit != null) {
			shickItBool = true;
		} else {
			shickItBool = false;
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
			twitterReceiverRoute.setConsumerKey(TwitterModel.consumerKey);
			twitterReceiverRoute.setConsumerSecret(TwitterModel.consumerSecret);
			twitterReceiverRoute.setAccessToken(accessToken);
			twitterReceiverRoute.setAccessTokenSecret(accessTokenSecret);
			String userName = getTwitterUserName();
			twitterReceiverRoute.setUser(userName);
			twitterReceiverRoute.setFilter(filterBool);
			twitterReceiverRoute.setAggregate(aggregatorBool);
			context.addRoutes(twitterReceiverRoute);
		}

		receiverRoute = new ReceiverRouteBuilder(saveLocalBool, saveCSVBool,
				filterString, shickItBool);
		context.addRoutes(receiverRoute);

		context.start();
		Thread.sleep(25000);

		// stop the CamelContext
		context.stop();

		if (saveFTPBool) {
			context = new DefaultCamelContext();
			RouteBuilder ftpRouteBuilder = new FtpRouteBuilder();
			context.addRoutes(ftpRouteBuilder);
			context.start();
			Thread.sleep(180000);

			// stop the CamelContext
			context.stop();
		}

		// start the route and let it do its work

		if (shickItBool) {

			String postString = receiverRoute.listofPosts();
			model.addAttribute("postString", postString);

			return "outputPosts";
		}

		return "home";
	}

	private String getTwitterUserName() throws IllegalStateException,
			TwitterException {
		TwitterModel tm = new TwitterModel();
		tm.setAccessToken(accessToken);
		tm.setAccessTokenSecret(accessTokenSecret);
		return tm.getTwitterUserName();
	}
	
	private Customer loggedInCustomer() {
		String userName;

		try {
			userName = ((User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal()).getUsername();
		} catch (Exception e) {
			return null;
		}

		Customer currentCustomer = customerService.getUser(userName);
		if (currentCustomer == null) {
			return null;
		}

		return currentCustomer;
	}

}
