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

    public FacebookModel() {
    }

    public void initFacebook() {
        System.out.println("**************in initFacebook");
        facebook = new FacebookFactory().getInstance();

        facebook.setOAuthAppId("725656577542701",
                "31ec413453a18aa144db8bc4dc330ace");
        facebook.setOAuthAccessToken(new AccessToken(
                "CAAKTZBxaIii0BAORVI9rjrBbKbYAGZCB2VTsmVXyZBuRG9mSO3lcp7ElxavpqARWNrKiLB8o6w1pfzrlr0snQfnjwsHGelHG3WnK5cwmd70s6qlkkU5GKYPSZCK3r0LPhj7ZCrvJQQR3KZC55NYvwyIdQ2K8BaAvkm3DmyZBXurMt88f5rjhbBRXNvwqxZARMsKNdnzXPjnCjsrZA3DviN6VM",
                null));
    }

    public void sendPost(String post) throws Exception{
        System.out.println("**************in sendPost");

        facebook.postStatusMessage(post);

    }

    public String receivePost() throws Exception{
        ResponseList<Post> feed = facebook.getHome();
        String first = feed.get(0).getName();
        System.out.println("***************** first: " + first);

        return first;
    }

    public FacebookConfiguration getFbCamelConfigiration(FacebookComponent fc){

        FacebookConfiguration fcon = fc.getConfiguration();
        fcon.setOAuthAppId("725656577542701");
        fcon.setOAuthAppSecret("31ec413453a18aa144db8bc4dc330ace");
        fcon.setOAuthAccessToken("CAAKTZBxaIii0BABzk7mjp6nDNds1emrk7pY4VxT7248SpgI7cIZBXePQIRXZBFhTbOWikdAvbIbExp1XTCybrVlv00DY8TPlyvf6zTmXBSQdLrpQHKzMZA0UYzBG4irs4qlbgDKBn86N8zGkZA5q2LZBs57CEyuFQzZBVFu32D8xBWK2Ch92EAPDODb4ZBus7F2IaZC5zn4IPrS5e8488kbOz");


        return fcon;
    }



}
