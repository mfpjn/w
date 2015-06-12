package at.mfpjn.workflow.model;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import org.apache.camel.component.facebook.config.FacebookConfiguration;
import org.apache.camel.component.facebook.FacebookComponent;

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
        this.accessToken = "CAAKTZBxaIii0BAC5ZAjBRbQpBtInjPWZAIX5RNA8nTikYHzIPpErSXdhFZAXRKKZAHR1LFB6vjUNikth58GHfb0Ue8ZBlbTaJHY10YLdDmfypRXqYqD6Y0bHavClK6L2WG0ZCa2eCNeBEiJDWLDVVg6zJA2vrh5Qm21kW14FBkdp9NXkaPCYdAfhU88a3bqPAOnxbce1pHMMPzJjvFs1hKn";
        /************************************/
    }

    public void setFb4jConfiguration() {

        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(appId,
                appSecret);
        facebook.setOAuthAccessToken(new AccessToken(
                accessToken,null));
    }

    public void setFbCamelConfiguration(FacebookComponent fc){

        FacebookConfiguration fcon = fc.getConfiguration();
        fcon.setOAuthAppId(appId);
        fcon.setOAuthAppSecret(appSecret);
        fcon.setOAuthAccessToken(accessToken);
    }

    public void sendPost(String post) throws Exception{

        facebook.postStatusMessage(post);
    }





}
