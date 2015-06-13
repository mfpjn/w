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

import com.sun.xml.bind.v2.model.core.Ref;

public class SocMediaRouteBuilder extends RouteBuilder {

	public boolean fb;
	public boolean tw;
	
	
	
	public SocMediaRouteBuilder(boolean fb, boolean tw) {
		super();
		this.fb = fb;
		this.tw = tw;
	}



	public void configure() throws Exception{
		
		
		from("jms:messages").wireTap("jms:postAudit"). //added wiretap
		process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.out.println("Message in RecipList: " + exchange.getIn().getBody());
				String recipients = "";
				if(fb == true){
					recipients += "jms:facebookq,";
				}
				if(tw == true){
					recipients += "jms:twitterq,";
				}
				exchange.getIn().setHeader("recipients", recipients);
			}
		})
		.recipientList(header("recipients"));
		
		// test that our route is working
        from("jms:facebookq").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Facebook queue: " 
                        + exchange.getIn().getBody());   
            }
        });                
        from("jms:twitterq").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Twitter queue: " 
                        + exchange.getIn().getBody());   
            }
        });
		from("jms:postAudit").process(new Processor(){
			public void process(Exchange exchange) throws Exception{
				System.out.println("Wonder whats here: " + exchange.getIn().getBody());
			}
		});
		from("jms:postAudit").bean(method("partner", "tosql")).to("jdbc:dataSource");
	}
}