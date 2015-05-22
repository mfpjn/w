//package at.mfpjn.workflow.config;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.camel.CamelContext;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.component.jms.JmsComponent;
//import org.apache.camel.spring.SpringCamelContext;
//import org.apache.camel.spring.javaconfig.Main;
//import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * A simple example router from a file system to an ActiveMQ queue and then to a file system
// */
//@Configuration
//public class MyRouteConfig extends SingleRouteCamelConfiguration implements InitializingBean {
//
//    /**
//     * Allow this route to be run as an application
//     */
//    public static void main(String[] args) throws Exception {
//        new Main().run(args);
//    }
//
//    /**
//     * Returns the CamelContext which support Spring
//     */
//    @Override
//    protected CamelContext createCamelContext() throws Exception {
//        return new SpringCamelContext(getApplicationContext());
//    }
//
//    @Override
//    protected void setupCamelContext(CamelContext camelContext) throws Exception {
//        // setup the ActiveMQ component
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//        connectionFactory.setBrokerURL("vm://localhost.spring.javaconfig?marshal=false&broker.persistent=false&broker.useJmx=false");
//
//        // and register it into the CamelContext
//        JmsComponent answer = new JmsComponent();
//        answer.setConnectionFactory(connectionFactory);
//        camelContext.addComponent("jms", answer);
//    }
//
//
//    public static class SomeBean {
//
//        public void someMethod(String body) {
//            System.out.println("Received: " + body);
//        }
//
//    }
//
//    @Bean
//    @Override
//    public RouteBuilder route() {
//        return new RouteBuilder() {
//            public void configure() {
//                // you can configure the route rule with Java DSL here
//
//                // populate the message queue with some messages
//                from("file:src/data?noop=true").
//                        to("jms:test.MyQueue");
//
//                from("jms:test.MyQueue").
//                        to("file://target/test?noop=true");
//
//                // set up a listener on the file component
//                from("file://target/test?noop=true").
//                        bean(new SomeBean());
//            }
//        };
//    }
//
//    public void afterPropertiesSet() throws Exception {
//        // just to make SpringDM happy do nothing here
//    }
//}