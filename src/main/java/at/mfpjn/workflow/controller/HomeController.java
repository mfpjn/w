package at.mfpjn.workflow.controller;

import at.mfpjn.workflow.routebuilder.SenderRouteBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.camel.builder.RouteBuilder;


import javax.jms.ConnectionFactory;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;



@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() throws Exception {
        return "home";
    }

    @RequestMapping(value = "/inputForm")
    public String inputForm() {
        return "inputForm";
    }

    @RequestMapping(value = "/receiverStart")
    public String receiverStart() {
        return "receiverStart";
    }

}
