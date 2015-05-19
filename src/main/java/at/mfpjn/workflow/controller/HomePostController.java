package at.mfpjn.workflow.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import at.mfpjn.workflow.twitter.TwitterRouteBuilder;

@Controller
public class HomePostController {
	private static String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
	private static String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
	private static String accessToken = "";
	private static String accessTokenSecret = "";	

	private Twitter twitter;
	private RequestToken requestToken;
	private TwitterRouteBuilder route;
	
	private User user;

	@RequestMapping(value = "/homePost")
	public String homePost() throws Exception {
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey,
				consumerSecret);
		requestToken = twitter.getOAuthRequestToken();
		AccessToken accessToken = null;
		java.awt.Desktop.getDesktop().browse(
				new URI(requestToken.getAuthorizationURL()));

		// Ask for user pin
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    while (null == accessToken) {
		      System.out.println("Open the following URL and grant access to your account:");
		      System.out.println(requestToken.getAuthorizationURL());
		      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
		      String pin = br.readLine();
		      try{
		         if(pin.length() > 0){
		           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		         }else{
		           accessToken = twitter.getOAuthAccessToken();
		         }
		      } catch (TwitterException te) {
		        if(401 == te.getStatusCode()){
		          System.out.println("Unable to get the access token.");
		        }else{
		          te.printStackTrace();
		        }
		      }
		    }
		storeAccessToken(twitter.verifyCredentials().getId(), accessToken);
		user = twitter.showUser(twitter.getId());
		
		// post tweet
		configureRoute();

		return "homePost";
	}

	private static void storeAccessToken(long useId, AccessToken AC_accessToken) {
		accessToken = AC_accessToken.getToken();
		accessTokenSecret = AC_accessToken.getTokenSecret();
		System.out.println("AccessToken is : " + accessToken);
		System.out.println("AccessTokenSecret is : " + accessTokenSecret);
	}

	public void configureRoute() {
		// test tweet message
		String message = "1";
		
		CamelContext camelcontext = new DefaultCamelContext();
		route = new TwitterRouteBuilder();
		route.setAccessToken(accessToken);
		route.setAccessTokenSecret(accessTokenSecret);
		route.setConsumerKey(consumerKey);
		route.setConsumerSecret(consumerSecret);
		route.setUser(user.getName());
		route.setMessage(message);
		try {
			camelcontext.addRoutes(route);
		} catch (Exception e) {
			System.out.println("fail to add route");
			e.printStackTrace();
		}
		try {
			camelcontext.start();
		} catch (Exception e) {
			System.out.println("fail to start camel context");
			e.printStackTrace();
		}
	}
}