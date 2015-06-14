
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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import at.mfpjn.workflow.model.Customer;
import at.mfpjn.workflow.model.FacebookModel;
import at.mfpjn.workflow.model.Logging;
import at.mfpjn.workflow.service.CustomerService;

public class SenderRouteBuilder extends RouteBuilder {

	public boolean fb;
	public boolean tw;
	
	@Autowired
	CustomerService customerService;
	
	public SenderRouteBuilder(boolean fb, boolean tw) {
		super();
		this.fb = fb;
		this.tw = tw;
	}



	public void configure() throws Exception{

		//why not split in two routs?

        //from("direct:start").to("direct:messages");

		from("direct:start").wireTap("direct:persistance").
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
        from("direct:facebookq").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Facebook queue: " 
                        + exchange.getIn().getBody());
                String post = (String) exchange.getIn().getBody();
                FacebookModel fc = new FacebookModel();
                fc.setFb4jConfiguration();
                fc.sendPost(post);
            }
        });	
        
        from("direct:persistance").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Persistance queue!!!!!!!!!!!: " 
                        + exchange.getIn().getBody());
                
                               
        		int userId = Integer.parseInt(exchange.getIn().getHeader("myId").toString());
        		                             
                Logging logging = new Logging();
                if (fb == true && tw == true) {
                	logging.setMediaName("Facebook, Twitter");
				}else {
					if(fb == true){
						logging.setMediaName("Facebook");
					}
					if(tw == true){
						logging.setMediaName("Twitter");
					}
				}
                
                logging.setCustomerId(userId);     
                Date dt = new Date();
                SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(dt);
                logging.setPicturePosted(false);
                logging.setPostedAt(currentTime);
                logging.setTextPosted(exchange.getIn().getBody().toString());
                exchange.getIn().setBody("INSERT INTO logging (TextPosted,PostedAt,MediaName,Customer_Id) "
	           			+ "VALUES('" + logging.getTextPosted().toString() + "','"
	           					+ logging.getPostedAt() +"','"
	           					+ logging.getMediaName() +"','"
	           					+ logging.getCustomerId() +"');");
                
            }
        }).to("jdbc:workflowDB"); 
	}
}