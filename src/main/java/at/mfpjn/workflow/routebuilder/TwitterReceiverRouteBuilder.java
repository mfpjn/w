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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import twitter4j.Status;
import twitter4j.Twitter;
import at.mfpjn.workflow.model.TwitterModel;

/**
 * A Camel route that updates from twitter all tweets using having the search
 * term. And post the changes to web-socket, that can be viewed from a web page
 */
public class TwitterReceiverRouteBuilder extends RouteBuilder {

	private String user;
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public void configure() throws Exception {
		
		
		// poll twitter search for new tweets
		from(
				"twitter://timeline/user?type=polling&delay=99999&consumerKey="
						+ consumerKey + "&consumerSecret="
						+ consumerSecret + "&accessToken="
						+ accessToken + "&accessTokenSecret="
						+ accessTokenSecret + "&user=" + user).process(
				new Processor() {
					public void process(Exchange exchange) throws Exception {

						Status status = exchange.getIn().getBody(Status.class);

						String message = status.getText();
						exchange.getIn().setBody(message);
						exchange.getIn().setHeader("SocialNetwork", header("tw"));

						System.out.println("We just downloaded: " + message);

					}
				}).to("direct:filter");
	}

}