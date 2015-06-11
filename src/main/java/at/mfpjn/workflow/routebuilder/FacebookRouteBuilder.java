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

import at.mfpjn.workflow.model.TwitterModel;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.camel.Processor;

/**
 * A Camel route that updates from twitter all tweets using having the search
 * term. And post the changes to web-socket, that can be viewed from a web page
 */
public class FacebookRouteBuilder extends RouteBuilder {

    String FACEBOOK_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	@Override
	public void configure() throws Exception {

        String since = "RAW("
                + new SimpleDateFormat(FACEBOOK_DATE_FORMAT)
                .format(new Date(System.currentTimeMillis()
                        - TimeUnit.MILLISECONDS.convert(30,
                        TimeUnit.DAYS))) + ")";

        from("facebook://getPosts?" + "reading.since=" + since)
                .process(new Processor() {
                    public void process(Exchange exchange)
                            throws Exception {
                        System.out.println("We just downloaded: ");
                        String custom = exchange.getIn().getBody(
                                String.class);
                        System.out.println("custom: " + custom);
                    }
                }).to("direct:receiver");
	}


}