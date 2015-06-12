package at.mfpjn.workflow.routebuilder;



import at.mfpjn.workflow.model.TwitterModel;
import facebook4j.Post;
import facebook4j.internal.org.json.JSONObject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.camel.Processor;

/**
 * A Camel route that updates from twitter all tweets using having the search
 * term. And post the changes to web-socket, that can be viewed from a web page
 */
public class FacebookRouteBuilder extends RouteBuilder {

    String FACEBOOK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	@Override
	public void configure() throws Exception {

        String since = "RAW("
                + new SimpleDateFormat(FACEBOOK_DATE_FORMAT)
                .format(new Date(System.currentTimeMillis()
                        - TimeUnit.MILLISECONDS.convert(30,
                        TimeUnit.DAYS))) + ")";

        from("facebook://getPosts?" + "reading.since=" + since)
                .process(new Processor() {
                    public void process(Exchange exchange)
                            throws Exception {

                        Post post = exchange.getIn().getBody(Post.class);

                        String message = post.getMessage();
                        exchange.getIn().setBody(message);
                        exchange.getIn().setHeader("FacebookPost", header("fb"));

                        System.out.println("We just downloaded: " + message);

                    }
                }).to("direct:filter");
	}


}