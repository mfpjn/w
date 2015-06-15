package at.mfpjn.test;

import at.mfpjn.workflow.aggregation.ReceiverAggregationStrategy;
import at.mfpjn.workflow.aggregation.ReceiverJSPAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by george on 2015-06-14.
 */
public class AggregateJspTest extends CamelTestSupport {

    @Test
    public void testABC() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");


        mock.expectedBodiesReceived("A, B, C");


        template.sendBodyAndHeader("direct:start", "A", "myId", 1);
        template.sendBodyAndHeader("direct:start", "B", "myId", 1);
        template.sendBodyAndHeader("direct:start", "C", "myId", 1);


        assertMockEndpointsSatisfied();


    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        // do a little logging
                        .log("Sending ${body} with correlation key ${header.myId}")

                        .aggregate(header("myId"), new ReceiverJSPAggregationStrategy()).completionTimeout(1000)

                        .log("Sending out ${body}")
                        // send it to the mock
                        .to("mock:result");
            }
        };
    }
}
