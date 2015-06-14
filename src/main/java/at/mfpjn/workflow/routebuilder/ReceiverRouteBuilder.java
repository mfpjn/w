package at.mfpjn.workflow.routebuilder;

import at.mfpjn.workflow.aggregation.ReceiverAggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ReceiverRouteBuilder extends RouteBuilder {

    public boolean fileBool;
    public boolean tw;
    public boolean fb;
    public boolean csv;
    public String filterString;
    public String multicast = "";

    public ReceiverRouteBuilder(boolean fileBool, boolean tw, boolean fb, boolean csv, String fs) {
        super();
        this.fileBool = fileBool;
        this.tw = tw;
        this.fb = fb;
        this.csv = csv;
        this.filterString = fs;
    }

    public void configure() throws Exception {

        from("direct:filter").
                // filter(body().contains(filterString)).
                        to("direct:agg");


        from("direct:agg")
                .aggregate(header("SocialNetwork"),
                        new ReceiverAggregationStrategy())
                .completionTimeout(10000).to("direct:multicast");

        from("direct:multicast").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                if (fileBool == true) {
                    multicast += "direct:save2file,";
                }
                if (csv == true) {
                    multicast += "direct:csv";
                }
                System.out.println("Multicast: " + multicast);

            }
        }).multicast()
                .parallelProcessing()
                .to(multicast);

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
                .to("jms:twItems")
                .when(header("fb"))
                .to("jms:fbItems")
                .otherwise()
                .to("jms:badFiles").
                end();


        from("jms:twItems")
                .marshal().csv()
                .to("file:reports/twitterCSV");

        from("jms:fbItems")
                .marshal().csv()
                .to("file:reports/facebookCSV");

    }
}

