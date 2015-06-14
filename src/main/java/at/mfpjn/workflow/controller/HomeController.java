package at.mfpjn.workflow.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import at.mfpjn.workflow.model.Customer;
import at.mfpjn.workflow.model.UserMediaChannelsParameters;
import at.mfpjn.workflow.service.CustomerService;
import at.mfpjn.workflow.service.UserMediaChannelsParametersService;

@Controller
public class HomeController {

	private final String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
	private final String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
	private Twitter twitter;
	private RequestToken requestToken;
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserMediaChannelsParametersService userMediaChannelsParametersService;

	@RequestMapping(value = "/")
	public String home() throws Exception {
		return "home";
	}

	@RequestMapping(value = "/receiverStart")
	public String receiverStart() {
		return "receiverStart";
	}

	@RequestMapping(value = "/updateTwitterForm")
	public String updateTwitterForm() throws Exception {
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		requestToken = twitter.getOAuthRequestToken();
		java.awt.Desktop.getDesktop().browse(
				new URI(requestToken.getAuthorizationURL()));

		// // Ask for user pin
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		// while (null == accessToken) {
		// System.out
		// .println("Open the following URL and grant access to your account:");
		// System.out.println(requestToken.getAuthorizationURL());
		// System.out
		// .print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
		// String pin = br.readLine();
		// try {
		// if (pin.length() > 0) {
		// accessToken = twitter
		// .getOAuthAccessToken(requestToken, pin);
		// } else {
		// accessToken = twitter.getOAuthAccessToken();
		// }
		// } catch (TwitterException te) {
		// if (401 == te.getStatusCode()) {
		// System.out.println("Unable to get the access token.");
		// } else {
		// te.printStackTrace();
		// }
		// }
		// }
		// storeAccessToken(twitter.verifyCredentials().getId(), accessToken);

		return "updateTwitterForm";
	}

	@RequestMapping(value = "/updateTwitter", method = RequestMethod.POST)
	public String updateTwitter(HttpServletRequest request, Model model) throws TwitterException {
		Customer currentCustomer = loggedInCustomer();
		if (null == currentCustomer)
			return "login";
		
		AccessToken accessToken = null;
		String pin = request.getParameter("twitterPin");

		try {
			if (pin.length() > 0) {
				accessToken = twitter.getOAuthAccessToken(requestToken, pin);
			} else {
				accessToken = twitter.getOAuthAccessToken();
			}
		} catch (TwitterException te) {
			if (401 == te.getStatusCode()) {
				model.addAttribute("error", "The Pin is invalid");
				return "updateTwitterForm";
			} else {
				te.printStackTrace();
			}
		}
		model.addAttribute("success", "Twitter account updated");

		storeAccessToken(currentCustomer, twitter.verifyCredentials().getId(), accessToken);

		return "home";
	}

	private void storeAccessToken(Customer currentCustomer, long useId, AccessToken AC_accessToken) {
		// Get tokens from user
		String accessToken = AC_accessToken.getToken();
		String accessTokenSecret = AC_accessToken.getTokenSecret();
		
		// Store tokens from twitter to database
		UserMediaChannelsParameters userMediaChannelsParameters = new UserMediaChannelsParameters();
		userMediaChannelsParameters.setAccessToken(accessToken);
		userMediaChannelsParameters.setAccessTokenSecret(accessTokenSecret);
		userMediaChannelsParameters.setCustomerId(currentCustomer);
		userMediaChannelsParameters.setMedia("Twitter");
		
		// Clean DB from previous Twitter accounts of the user
		userMediaChannelsParametersService.deleteTwitterParameters(currentCustomer.getId());
		
		userMediaChannelsParametersService.insertParameter(userMediaChannelsParameters);
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
