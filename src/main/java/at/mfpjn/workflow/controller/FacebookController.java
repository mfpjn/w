package at.mfpjn.workflow.controller;

import org.springframework.stereotype.Controller;

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

@Controller
public class FacebookController {

    private Facebook facebook;

    public FacebookController() {
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



}
