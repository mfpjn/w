package at.mfpjn.workflow.routebuilder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


import at.mfpjn.workflow.model.Logging;

public class PersistanceRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
    	from("direct:persistance").process(new Processor() {
            public void process(Exchange exchange)
                    throws Exception {
            	Logging logging = new Logging();
                logging.setCustomerId(2);
                logging.setMediaName("facebook");
                logging.setPicturePosted(false);
                //logging.setPostedAt(postedAt);
                logging.setTextPosted(exchange.getIn().getBody().toString());            		
            }
            })        
        .to("jpa:at.mfpjn.workflow.model.Logging");
		
	}
	
	
}
