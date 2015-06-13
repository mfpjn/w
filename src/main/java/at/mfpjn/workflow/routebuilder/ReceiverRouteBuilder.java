package at.mfpjn.workflow.routebuilder;

import org.apache.camel.Exchange;
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

		from("direct:filter").
		// filter(body().contains(filterString)).
				to("direct:agg");


		from("direct:agg")
				.aggregate(header("FacebookPost"),
						new ReceiverAggregationStrategy())
				.completionTimeout(1000).to("direct:recipient");

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