package at.mfpjn.workflow.routebuilder;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import java.util.Date;

/**
 * A Camel route that updates from twitter all tweets using having the search
 * term. And post the changes to web-socket, that can be viewed from a web page
 */
public class TwitterRouteBuilder extends RouteBuilder {

	private int port;
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	private String user;
	private String message;
	private int delay = 2;
	private String searchTerm;

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public void setMessage(String m) {
		this.message = m;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String u) {
		this.user = u;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void configure() throws Exception {
		sendTweet(message);
	}
//		@Override
//	    public void configure() throws Exception {
//	        // setup Camel web-socket component on the port we have defined
//	        WebsocketComponent wc = getContext().getComponent("websocket", WebsocketComponent.class);
//	        wc.setPort(port);
//	        // we can serve static resources from the classpath: or file: system
//	        wc.setStaticResources("classpath:.");
//
//	        // setup Twitter component
//	        TwitterComponent tc = getContext().getComponent("twitter", TwitterComponent.class);
//	        tc.setAccessToken(accessToken);
//	        tc.setAccessTokenSecret(accessTokenSecret);
//	        tc.setConsumerKey(consumerKey);
//	        tc.setConsumerSecret(consumerSecret);
//
//	        // poll twitter search for new tweets
//	        fromF("twitter://search?type=polling&delay=%s&keywords=%s", delay, searchTerm)
//	            .to("log:tweet")
//	            // and push tweets to all web socket subscribers on camel-tweet
//	            .to("websocket:camel-tweet?sendToAll=true");
//	        System.out.println("AOK!");
//	    }

	public void sendTweet(String message) {
		// send tweet
		Date now = new Date();
		// message = message + " - at " + now.toString();

		// Tweet with Schick-it tag (length <= 120)
		message = "123456";

		// Tweet without Schick-it tag (length > 120)
		// message =
		// "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

		// Message endpoint, message router, message translator, log, delay, multicast 
		String endpoint = "twitter://timeline/user?" + getUriTokens();
		String emailConfirmation = "smtps://smtp.gmail.com?username=andatu7@gmail.com&password=andatuASE&to=lett.nicolaz@gmail.com";
		from(endpoint).setBody().constant(message).choice()
				.when(body().regex(".{1,120}"))
				.transform(body().append(" sent via Schick-It!"))
				.log(LoggingLevel.INFO, "Tweeting: " + message).to(endpoint).endChoice()
				.otherwise().transform(body()).delay(1000)
				.log(LoggingLevel.INFO, "Tweeting: " + message).to(endpoint);
		//from("direct:foo").setBody().constant(message).to("smtps://smtp.gmail.com?username=andatu7@gmail.com&password=andatuASE&to=lett.nicolas@gmail.com");
		//String subject = "subject";
		//from("direct:a").setHeader("subject", constant(subject)).to("smtps://smtp.gmail.com?username=andatu7@gmail.com&password=andatuASE&to=lett.nicolas@gmail.com");
		
		System.out.println("would like to twwet : " + message);
		System.out.println("by : " + endpoint);
		
		
		from("direct:a").to("string-template:templates/email.tm").to(emailConfirmation);
	}

	protected String getUriTokens() {
		return "consumerKey=" + consumerKey + "&consumerSecret="
				+ consumerSecret + "&accessToken=" + accessToken
				+ "&accessTokenSecret=" + accessTokenSecret + "&user=" + user;
	}
	
}