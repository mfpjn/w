package at.mfpjn.workflow.routebuilder;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Processor;
import at.mfpjn.workflow.controller.FacebookController;

public class SenderRouteBuilder extends RouteBuilder {

	public boolean fb;
	public boolean tw;
	
	
	
	public SenderRouteBuilder(boolean fb, boolean tw) {
		super();
		this.fb = fb;
		this.tw = tw;
	}



	public void configure() throws Exception{

		//why not split in two routs?

        //from("direct:start").to("direct:messages");

		from("direct:start").
		process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.out.println("Message in RecipList: " + exchange.getIn().getBody());
				String recipients = "";
				if(fb == true){
					recipients += "direct:facebookq,";
				}
				if(tw == true){
					recipients += "direct:twitterq,";
				}
				exchange.getIn().setHeader("recipients", recipients);
			}
		})
		.recipientList(header("recipients"));
		
		// test that our route is working
        from("direct:facebookq").routeId("facebookRoute").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.out.println("Facebook queue: "
						+ exchange.getIn().getBody());
				String post = (String) exchange.getIn().getBody();
				FacebookController fc = new FacebookController();
				fc.initFacebook();
				fc.sendPost(post);
			}
		});
        from("direct:twitterq").routeId("twitterRoute").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Twitter queue: " 
                        + exchange.getIn().getBody());
            }
        });
		
	}
}