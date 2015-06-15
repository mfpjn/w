package at.mfpjn.workflow.model;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import org.apache.camel.component.facebook.FacebookComponent;
import org.apache.camel.component.facebook.config.FacebookConfiguration;

/* * * * * * * * * * * * * * * * * *
 * Facebook test profile login:    *
 * username: j20150505@gmail.com   *
 * password: testemail             *
 * * * * * * * * * * * * * * * * * */

public class FacebookModel {

	private Facebook facebook;
	private String appId;
	private String appSecret;
	private String accessToken;

	public FacebookModel() {
		this.appId = "725656577542701";
		this.appSecret = "31ec413453a18aa144db8bc4dc330ace";

		/***** enter access token here: *****/
		this.accessToken = "CAAKTZBxaIii0BAKsnJvDjWNN5HqtjOkWiDV5qP4rkNpoMVrnGEb5FBHoP789AyRuNaUnnTDp7ZC5jrpZBtARqIqdRnhHxQ2vXgXjwKAZBuA7DZBZClBrZAPr3dGO7w2ZAF3pQkhsI9XJqZArGHJHka8bCCXZCqcvt5CuwHIs9vkPDepD1wwYZCU2YmBiyDLRfZAPZCTUZD";
		/************************************/
	}

	public void setFbCamelConfiguration(FacebookComponent fc) {

		FacebookConfiguration fcon = fc.getConfiguration();
		fcon.setOAuthAppId(appId);
		fcon.setOAuthAppSecret(appSecret);
		fcon.setOAuthAccessToken(accessToken);
	}

	public void sendPost(String post) throws Exception {

		facebook.postStatusMessage(post);
	}

    public void setFb4jConfiguration() {

        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(appId, appSecret);
        facebook.setOAuthAccessToken(new AccessToken(accessToken,null));
    }
}

