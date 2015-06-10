//
//package at.mfpjn.workflow.controller;
//
//import at.mfpjn.workflow.routebuilder.TwitterRouteBuilder;
//import org.apache.camel.CamelContext;
//import org.apache.camel.Exchange;
//import org.apache.camel.Message;
//import org.apache.camel.ProducerTemplate;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.impl.DefaultCamelContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//import twitter4j.TwitterFactory;
//import twitter4j.User;
//import twitter4j.auth.AccessToken;
//import twitter4j.auth.RequestToken;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URI;
//
//@Controller
//public class HomePostController {
//	private static String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
//	private static String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
//	private static String accessToken = "";
//	private static String accessTokenSecret = "";
//
//	private Twitter twitter;
//	private RequestToken requestToken;
//	private TwitterRouteBuilder route;
//	private CamelContext camelcontext;
//
//	private User user;
//
//	@RequestMapping(value = "/homePost")
//	public String homePost() throws Exception {
//		twitter = new TwitterFactory().getInstance();
//		twitter.setOAuthConsumer(consumerKey, consumerSecret);
//		requestToken = twitter.getOAuthRequestToken();
//		AccessToken accessToken = null;
//		java.awt.Desktop.getDesktop().browse(
//				new URI(requestToken.getAuthorizationURL()));
//
//		// Ask for user pin
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		while (null == accessToken) {
//			System.out
//					.println("Open the following URL and grant access to your account:");
//			System.out.println(requestToken.getAuthorizationURL());
//			System.out
//					.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
//			String pin = br.readLine();
//			try {
//				if (pin.length() > 0) {
//					accessToken = twitter
//							.getOAuthAccessToken(requestToken, pin);
//				} else {
//					accessToken = twitter.getOAuthAccessToken();
//				}
//			} catch (TwitterException te) {
//				if (401 == te.getStatusCode()) {
//					System.out.println("Unable to get the access token.");
//				} else {
//					te.printStackTrace();
//				}
//			}
//		}
//		storeAccessToken(twitter.verifyCredentials().getId(), accessToken);
//		user = twitter.showUser(twitter.getId());
//
//		// post tweet
//		configureRoute();
//
//		return "homePost";
//	}
//	
////	@RequestMapping(value="/homePost2")
////	public String homePOst2() throws Exception{
////		TwitterRouteBuilder route2 = new TwitterRouteBuilder();
////		route2.setAccessToken("3214140528-UfqhFlBsTwElZe1ItXNfJD7FdxBhRyPsmM8qs6l");
////		route2.setAccessTokenSecret("U8QAwFW1muOTOQSAt3spO8alUJagslSwUTcdgIp1CCCxx");
////		route2.setConsumerKey("XhLtFqzkvisnh5v"); //XhLtFqzkvisnh5vQpU3zdlK7P
////		route2.setConsumerSecret("CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbn");//CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4
////		route2.setSearchTerm("1");
////		route2.setDelay(2);
////		//route2.setPort(9090);
////		CamelContext camelcontext = new DefaultCamelContext();		
////		try {
////			camelcontext.addRoutes(route2);
////		} catch (Exception e) {
////			System.out.println("fail to add route");
////			e.printStackTrace();
////		}
////		try {
////			camelcontext.start();
////		} catch (Exception e) {
////			System.out.println("fail to start camel context");
////			e.printStackTrace();
////		}
////		return "homePost2";
////	}
//
//	private static void storeAccessToken(long useId, AccessToken AC_accessToken) {
//		accessToken = AC_accessToken.getToken();
//		accessTokenSecret = AC_accessToken.getTokenSecret();
//		System.out.println("AccessToken is : " + accessToken);
//		System.out.println("AccessTokenSecret is : " + accessTokenSecret);
//	}
//
//	public void configureRoute() {
//		// test tweet message
//		String message = "Here is my last awesome post!";
//
//		camelcontext = new DefaultCamelContext();
//		ProducerTemplate template = camelcontext.createProducerTemplate();
//
//		route = new TwitterRouteBuilder();
//		route.setAccessToken(accessToken);
//		route.setAccessTokenSecret(accessTokenSecret);
//		route.setConsumerKey(consumerKey);
//		route.setConsumerSecret(consumerSecret);
//		// route.setUser(user.getName());
//		route.setUser("user");
//		route.setMessage(message);
//		try {
//			camelcontext.addRoutes(route);
//			camelcontext.addRoutes(createStringTemplateRouteBuilder());
//		} catch (Exception e) {
//			System.out.println("fail to add route");
//			e.printStackTrace();
//		}
//		try {
//			camelcontext.start();
//		} catch (Exception e) {
//			System.out.println("fail to start camel context");
//			e.printStackTrace();
//		}
//
//		template.send("direct:emailConfirmation",
//				createConfirmationEmail("Nicolas", "Lett", message));
//	}
//
//	protected RouteBuilder createStringTemplateRouteBuilder() throws Exception {
//		return new RouteBuilder() {
//			public void configure() throws Exception {
//				from("direct:emailConfirmation")
//						.to("string-template:templates/email.tm")
//						.to("smtps://smtp.gmail.com?username=andatu7@gmail.com&password=andatuASE&to=lett.nicolas@gmail.com");
//			}
//		};
//	}
//
//	private Exchange createConfirmationEmail(String firstName, String lastName,
//			String postMessage) {
//		Exchange exchange = camelcontext
//				.getEndpoint("direct:emailConfirmation").createExchange();
//		Message msg = exchange.getIn();
//		msg.setHeader("firstName", firstName);
//		msg.setHeader("lastName", lastName);
//		msg.setBody(postMessage);
//		return exchange;
//	}
//}