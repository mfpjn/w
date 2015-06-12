package at.mfpjn.workflow.controller;

import javax.servlet.http.HttpServletRequest;

import at.mfpjn.workflow.routebuilder.FacebookRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.camel.component.facebook.FacebookComponent;

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

        // set Facebook component configuration
        FacebookModel fm = new FacebookModel();
        fm.setFbCamelConfiguration(fc);

        // add routes
        RouteBuilder facebookRoute = new FacebookRouteBuilder();
        RouteBuilder receiverRoute = new ReceiverRouteBuilder(true, true, "number");
        context.addRoutes(facebookRoute);
        context.addRoutes(receiverRoute);


        // start the route and let it do its work
        context.start();
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();

       /* context = new DefaultCamelContext();

        RouteBuilder ftpRouteBuilder = new FtpRouteBuilder();
        context.addRoutes(ftpRouteBuilder);
        context.start();

        Thread.sleep(10000);

        context.stop();
        */

    	
        return "home";
    }
}
