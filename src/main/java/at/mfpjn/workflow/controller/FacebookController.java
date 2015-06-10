package at.mfpjn.workflow.controller;

import facebook4j.*;
import facebook4j.auth.AccessToken;
import org.springframework.stereotype.Controller;

import java.net.URL;


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
                "CAAKTZBxaIii0BAO9qB6rLerbSVvtwTw0oDePg9aPIC1FHQECEY8gMTFVVG2IcBnyFiCYaA275fDMUCcZCHI6jcZChWD70r7uyyJdSkT03LLjpEwIuxUAanKZCWZBYy0fZCXqF09zpspZAgFuZCee7g3slhVZBXEeJjSimnaWQJN35OJkdsXlphYpqZAgliBlgyXqqoGAtSDWHYdQmEBUoqSWpn",
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
