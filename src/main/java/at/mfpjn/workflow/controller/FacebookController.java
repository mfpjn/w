package at.mfpjn.workflow.controller;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PostUpdate;
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
                "CAAKTZBxaIii0BADL8N4lIS4OcXJ1FmUg8zECKWdeMS0FIZCpZCLNst45CALNTiJ0eRtbIfZAZCq7c9K9dZBb7JdRpSC1UZBLgYl0JKTZC6AQPzGDrlu7wq0LxEm0KVZCxrOjeOULEZBxBhCCUfmmKJrZC51OqniGpI67dcQgKHiMykuyNzUnfQk3hdt6iZCll7Prun8Kyyh6v6wDlC00zPqxLlpf",
                null));
    }

    public void sendPost(String post) throws Exception{
        System.out.println("**************in sendPost");

        facebook.postStatusMessage(post);



    }



}
