package at.mfpjn.workflow.model;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

/* * * * * * * * * * * * * * * * * *
 * Facebook test profile login:    *
 * username: j20150505@gmail.com   *
 * password: testemail             *
 * * * * * * * * * * * * * * * * * */

public class TwitterModel {

	public static final String consumerKey = "XhLtFqzkvisnh5vQpU3zdlK7P";
	public static final String consumerSecret = "CBZXM3UjL1Tb6Z6A7ot7vy4SWX3JnLS8mHzfqhwhEadcEGbnK4";
	public static final String accessToken = "3214140528-UfqhFlBsTwElZe1ItXNfJD7FdxBhRyPsmM8qs6l";
	public static final String accessTokenSecret = "U8QAwFW1muOTOQSAt3spO8alUJagslSwUTcdgIp1CCCxx";	

    public TwitterModel() {
    	
    }
    
    public static String getConsumerKey() {
		return consumerKey;
	}

	public static String getConsumerSecret() {
		return consumerSecret;
	}


	public static String getAccessToken() {
		return accessToken;
	}


	public static String getAccessTokenSecret() {
		return accessTokenSecret;
	}


	public static String getUriTokens() {
		return "consumerKey=" + consumerKey + "&consumerSecret="
				+ consumerSecret + "&accessToken=" + accessToken
				+ "&accessTokenSecret=" + accessTokenSecret + "&user=user";
	}
}
