package at.mfpjn.workflow.controller;

import javax.servlet.http.HttpServletRequest;

import at.mfpjn.workflow.routebuilder.FacebookRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.camel.component.facebook.FacebookComponent;
import org.apache.camel.component.facebook.config.FacebookConfiguration;

import at.mfpjn.workflow.model.FacebookModel;
import at.mfpjn.workflow.routebuilder.ReceiverRouteBuilder;


@Controller
public class ReceiverController {

    
    @RequestMapping(value = "/receiver")
    public String receiver(HttpServletRequest request) throws Exception {

    	// create CamelContext
        CamelContext context = new DefaultCamelContext();

        // add external components
        FacebookComponent fc = new FacebookComponent();
        context.addComponent("facebook", fc);
        FacebookModel fm = new FacebookModel();
        FacebookConfiguration fcon = fm.getFbCamelConfigiration(fc);

        // add routes
        RouteBuilder facebookRoute = new FacebookRouteBuilder();
        RouteBuilder receiverRoute = new ReceiverRouteBuilder(true, true, "number");
        context.addRoutes(facebookRoute);
        context.addRoutes(receiverRoute);

        //ProducerTemplate template = context.createProducerTemplate();

        // start the route and let it do its work
        context.start();

        /*template.sendBody("direct:facebook", "post number 1");
        template.sendBody("direct:twitter", "post message 2");
        template.sendBody("direct:facebook", "post number 3");
        template.sendBody("direct:facebook", "post number 4");
        template.sendBody("direct:twitter", "post number 5");*/
        
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();
    	
        return "home";
    }
}
