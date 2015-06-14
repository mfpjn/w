package at.mfpjn.workflow.routebuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import at.mfpjn.workflow.aggregation.ReceiverAggregationStrategy;

public class ReceiverRouteBuilder extends RouteBuilder {

	public boolean fileBool;
	public boolean tw;
	public String filterString;

	public ReceiverRouteBuilder(boolean fileBool, boolean tw, String fs) {
		super();
		this.fileBool = fileBool;
		this.tw = tw;
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
				.to("direct:recipient").end();

		from("direct:recipient").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				String recipients = "";
				if (fileBool == true) {
					recipients += "direct:save2file";
				}
				exchange.getIn().setHeader("recipients", recipients);
			}
		}).recipientList(header("recipients"));

		// test that our route is working
		from("direct:save2file").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.out.println("Saving to file: "
						+ exchange.getIn().getBody());

			}
		}).to("file:reports");

	}
}