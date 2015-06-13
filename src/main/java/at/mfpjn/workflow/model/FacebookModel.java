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
                "CAAKTZBxaIii0BALTiRfakIAjomecW3E8NyN7uWV6bt8cRr5CixMHGDWPYwZAVsK4ksXJuPXj7xhLYxI8EZC8UVOi2BqgW4Hn67BpFk31ZAvybO5o0KgQgx9KxgNTgGLHN5SppP0YzuSOZCdMVsFqCbFubve9Th5cbgineSHy2Kh2ubmAOF5dknJCgQnirjQzVtDz4A5PZBOQ8eS8rQSwZBqQDMjr8CXsi4ZD",
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
        fcon.setOAuthAccessToken("CAAKTZBxaIii0BALTiRfakIAjomecW3E8NyN7uWV6bt8cRr5CixMHGDWPYwZAVsK4ksXJuPXj7xhLYxI8EZC8UVOi2BqgW4Hn67BpFk31ZAvybO5o0KgQgx9KxgNTgGLHN5SppP0YzuSOZCdMVsFqCbFubve9Th5cbgineSHy2Kh2ubmAOF5dknJCgQnirjQzVtDz4A5PZBOQ8eS8rQSwZBqQDMjr8CXsi4ZD");


        return fcon;
    }



}
