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

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ReceiverRouteBuilder extends RouteBuilder {

    public boolean fb;
    public boolean tw;
    public String filterString;


    public ReceiverRouteBuilder(boolean fb, boolean tw, String fs) {
        super();
        this.fb = fb;
        this.tw = tw;
        this.filterString = fs;
    }


    public void configure() throws Exception {

        boolean one = false;

        if (fb) {
            from("direct:facebook").
                    to("direct:receiver");
        }

        if (tw) {
            from("direct:twitter").
                    to("direct:receiver");
        }


        from("direct:receiver").
                filter(body().contains(filterString)).
                process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                    }
                }).
                to("direct:filter");


        // test that our route is working
        from("direct:filter").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Receiver queue: "
                        + exchange.getIn().getBody());
            }
        }).to("file:reports");



    }
}