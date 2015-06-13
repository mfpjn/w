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
import org.apache.camel.component.twitter.TwitterComponent;

public class TwitterRouteBuilder extends RouteBuilder {

	private int port;
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	private String user = "user";
	private String message;
	private int delay = 2;
	private String searchTerm;

	public String getMessage() {
		return message;
	}

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
		sendTweet();
	}

	// @Override
	// public void configure() throws Exception {
	// // setup Camel web-socket component on the port we have defined
	// WebsocketComponent wc = getContext().getComponent("websocket",
	// WebsocketComponent.class);
	// wc.setPort(port);
	// // we can serve static resources from the classpath: or file: system
	// wc.setStaticResources("classpath:.");
	//}

	public void sendTweet() {
		// Tweet with Schick-it tag (length <= 120)
		// message = "123456";

		// Tweet without Schick-it tag (length > 120)
		// message =
		// "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

		// Message endpoint, message router, message translator, log, delay, multicast
		// String endpoint = "twitter://timeline/user?" + getUriTokens();

		String endpoint = "twitter://timeline/user?" + "consumerKey=" + consumerKey + "&consumerSecret="
				+ consumerSecret + "&accessToken=" + accessToken
				+ "&accessTokenSecret=" + accessTokenSecret + "&user=" + user;

		from("direct:twitterq")
		  .setHeader("CamelTwitterKeywords", header("bar")).choice()
			.when(body().regex(".{1,120}"))
			.transform(body().append(" sent via Schick-It!"))
			.log(LoggingLevel.INFO, "Tweeting: " + body()).to(endpoint)
			.endChoice()
			.otherwise().transform(body()).delay(1000)
			.log(LoggingLevel.INFO, "Tweeting: " + body()).to(endpoint);
		
		 // setup Twitter component
		 TwitterComponent tc = getContext().getComponent("twitter",
		 TwitterComponent.class);
		 tc.setAccessToken(accessToken);
		 tc.setAccessTokenSecret(accessTokenSecret);
		 tc.setConsumerKey(consumerKey);
		 tc.setConsumerSecret(consumerSecret);
	}
}