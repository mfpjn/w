package at.mfpjn.workflow.routebuilder;

import at.mfpjn.workflow.aggregation.ReceiverAggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiverRouteBuilder extends RouteBuilder {

    public boolean fileBool;
    public boolean ftp;
    public boolean csv;
    public String filterString;
    public String recipients = "";

    public ReceiverRouteBuilder(boolean fileBool, boolean ftp, boolean csv, String fs) {
        super();
        this.fileBool = fileBool;
        this.ftp = ftp;
        this.csv = csv;
        this.filterString = fs;
    }

    public void configure() throws Exception {

        from("direct:filter").choice().when(header("Filter").isEqualTo(true))
                .filter(body().contains("Posting")).to("direct:agg")
                .endChoice().when(header("Filter").isEqualTo(false))
                .to("direct:agg").end();

        from("direct:agg")
                .choice()
                .when(header("Aggregate").isEqualTo(true))
                .aggregate(header("SocialNetwork"),
                        new ReceiverAggregationStrategy())
                .completionTimeout(1000)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {

                        Date date = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "YYYY-MM-dd_hh-mm-ss");
                        String datetime = dateFormat.format(date);

                        String filename;
                        String header = exchange.getIn()
                                .getHeader("SocialNetwork").toString();
                        if (header.equals("fb")) {
                            filename = "FacebookAggregatedPosts-" + datetime;
                        } else {
                            filename = "TwitterAggregatedPosts-" + datetime;
                        }
                        exchange.getIn().setHeader("CamelFileName",
                                constant(filename));
                        System.out.println("UUUUUUUUUU: "
                                + exchange.getIn().getBody());
                    }
                }).to("direct:recipient").endChoice()
                .when(header("Aggregate").isEqualTo(false))
                .to("direct:recipients").end();

        from("direct:recipients").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                if (fileBool == true) {
                    recipients += "direct:save2file,";
                }
                if (csv == true) {
                    recipients += "direct:csv,";
                }
                System.out.println("Recipients: " + recipients);

                exchange.getIn().setHeader("recipients", recipients);
            }
        })
                .recipientList(header("recipients"));


      /*  }).multicast()
                .parallelProcessing()
                .to(multicast);
                */

        // test that our route is working
        from("direct:save2file").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Saving to file: "
                        + exchange.getIn().getBody());

            }
        }).to("file:reports");

        from("direct:csv").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Processing to csv: "
                        + exchange.getIn().getBody());

            }
        }).choice()
                .when(header("tw"))
                .to("direct:twItems")
                .when(header("fb"))
                .to("direct:fbItems")
                .otherwise()
                .to("direct:badFiles")
                .end();


        from("direct:twItems")
                .marshal().csv()
                .to("file:reports/twitterCSV");

        from("direct:fbItems")
                .marshal().csv()
                .to("file:reports/facebookCSV");

    }
}

