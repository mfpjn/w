package at.mfpjn.workflow.routebuilder;

import at.mfpjn.workflow.aggregation.ReceiverAggregationStrategy;
import at.mfpjn.workflow.aggregation.ReceiverJSPAggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReceiverRouteBuilder extends RouteBuilder {

    public boolean fileBool;
    public boolean csv;
    public String filterString;
    public boolean schickIt;
    public String postString;

    public ReceiverRouteBuilder(boolean fileBool, boolean csv, String fs, boolean schickItBool) {
        super();
        this.fileBool = fileBool;
        this.csv = csv;
        this.filterString = fs;
        this.schickIt = schickItBool;
    }

    public void configure() throws Exception {

        from("direct:filter").choice().when(header("Filter").isEqualTo(true))
                .filter(body().contains(filterString)).to("direct:agg")
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
                        if (header.equals("header{Facebook}")) {
                            filename = "FacebookAggregatedPosts-" + datetime;
                        } else {
                            filename = "TwitterAggregatedPosts-" + datetime;
                        }
                        exchange.getIn().setHeader("CamelFileName",
                                constant(filename));
                    }
                }).to("direct:recipients").endChoice()
                .when(header("Aggregate").isEqualTo(false))
                .to("direct:recipients").end();

        from("direct:recipients").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                String recipients = "";
                if (fileBool == true) {
                    recipients += "direct:save2file,";
                }
                if (csv == true) {
                    recipients += "direct:csv,";
                }
                if (schickIt == true) {
                    recipients += "direct:schickIt,";
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

                Map<String, Object> body = new HashMap<String, Object>();
                body.put("SocialNetwork", exchange.getIn().getHeader("SocialNetwork"));
                body.put("Body", exchange.getIn().getBody(String.class));
                body.put("Time", exchange.getIn().getHeader("Time"));


                String filename = exchange.getIn().getHeader("SocialNetwork") + "-" + exchange.getIn().getMessageId() + ".csv";
                filename = filename.replace("header{", "");
                filename = filename.replace("}", "");
                exchange.getIn().setBody(body);
                exchange.getIn().setHeader("CamelFileName", constant(filename));
            }
        }).marshal().csv()
                .to("file:reports/csv");

        from("direct:schickIt").aggregate(header("SchickItAggregator"),
                new ReceiverJSPAggregationStrategy())
                .completionTimeout(1000)
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("*******SCHICKIT*********" + exchange.getIn().getBody(String.class));
                        postString = (exchange.getIn().getBody(String.class));
                    }
                }).end();

    }

    public String listofPosts() {
        System.out.println("This is the Shit I want= " + postString);
        return postString;
    }
}

