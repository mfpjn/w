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

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import twitter4j.Status;

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
	private Boolean filter;
	private Boolean aggregate;
	
	public Boolean getFilter() {
		return filter;
	}

	public void setFilter(Boolean filter) {
		this.filter = filter;
	}
	
	public Boolean getAggregate() {
		return aggregate;
	}

	public void setAggregate(Boolean aggregate) {
		this.aggregate = aggregate;
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

                        // get message
						String message = status.getText();
						exchange.getIn().setBody(message);
						exchange.getIn().setHeader("SocialNetwork", header("Twitter"));
						exchange.getIn().setHeader("Number of shares", header("test"));
						exchange.getIn().setHeader("Time", header(status.getCreatedAt().toString()));
						exchange.getIn().setHeader("Filter", filter);
						exchange.getIn().setHeader("Aggregate", aggregate);

                        // set filename
                        String filename = "TwitterPost-" + status.getId();
                        exchange.getIn().setHeader("CamelFileName", constant(filename));
					}
				}).to("direct:filter");
	}
}