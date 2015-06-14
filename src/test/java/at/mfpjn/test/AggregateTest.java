package at.mfpjn.test;

import at.mfpjn.workflow.aggregation.ReceiverAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by george on 2015-06-14.
 */
public class AggregateTest extends CamelTestSupport {

    @Test
    public void testABC() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        // we expect ABC in the published message
        // notice: Only 1 message is expected
        mock.expectedBodiesReceived("A\nB\nC");

        // send the first message
        //template.sendBodyAndHeader("file:inbox", "Hello World", Exchange.FILE_NAME, "hello.txt");

        template.sendBodyAndHeader("direct:start", "A", "myId", 1);
        template.sendBodyAndHeader("direct:start", "B", "myId", 1);
        //template.sendBodyAndHeader("direct:start", "F", "myId", 2);
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
                                // aggregate based on header correlation key
                                // use class MyAggregationStrategy for aggregation
                                // and complete when we have aggregated 3 messages
                        .aggregate(header("myId"), new ReceiverAggregationStrategy()).completionTimeout(1000)
                        // do a little logging for the published message
                        .log("Sending out ${body}")
                                // and send it to the mock
                        .to("mock:result");
            }
        };
    }
}
