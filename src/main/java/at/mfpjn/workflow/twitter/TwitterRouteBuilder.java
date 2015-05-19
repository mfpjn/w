package at.mfpjn.workflow.twitter;
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


import java.util.Date;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.TwitterComponent;
import org.apache.camel.component.websocket.WebsocketComponent;

/**
 * A Camel route that updates from twitter all tweets using having the search term.
 * And post the changes to web-socket, that can be viewed from a web page
 */
public class TwitterRouteBuilder extends RouteBuilder {

    private int port;
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;
    private String user;
    private String message;

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
    
    public void sendTweet(String message){        
        // send tweet
    	Date now = new Date();
    	message = message +  " - at " + now.toString();
        String endpoint = "twitter://timeline/user?" + getUriTokens();
        from(endpoint).setBody().constant(message).to(endpoint);  
        System.out.println("would like to twwet : "+ message);  	
        System.out.println("by : "+ endpoint);  	
    }
    
    protected String getUriTokens(){
    	return "consumerKey=" + consumerKey
    			+ "&consumerSecret=" + consumerSecret
    			+ "&accessToken=" + accessToken
    			+ "&accessTokenSecret=" + accessTokenSecret
    			+ "&user=" + user;
    }
}