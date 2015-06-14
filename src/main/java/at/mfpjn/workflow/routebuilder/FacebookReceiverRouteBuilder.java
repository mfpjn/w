package at.mfpjn.workflow.routebuilder;


import facebook4j.Post;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * A Camel route that updates from twitter all tweets using having the search
 * term. And post the changes to web-socket, that can be viewed from a web page
 */
public class FacebookReceiverRouteBuilder extends RouteBuilder {

    String FACEBOOK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private Boolean filter;
    private Boolean aggregate;

    public Boolean getFilter() {
        return filter;
    }

    public void setFilter(Boolean filter) {
        this.filter = filter;
    }

    public Boolean getAggregate() {
        return aggregate;
    }

    public void setAggregate(Boolean aggregate) {
        this.aggregate = aggregate;
    }

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

                        // get message
                        String message = post.getMessage();
                        exchange.getIn().setBody(message);
                        exchange.getIn().setHeader("SocialNetwork", header("fb"));
                        exchange.getIn().setHeader("Number of shares", header(post.getSharesCount().toString()));
                        exchange.getIn().setHeader("Time", header(post.getCreatedTime().toString()));
                        exchange.getIn().setHeader("Filter", filter);
                        exchange.getIn().setHeader("Aggregate", aggregate);

                        // set filename
                        String filename = "FacebookPost-" + post.getId();
                        exchange.getIn().setHeader("CamelFileName", constant(filename));

                    }
                }).to("direct:filter");
    }


}