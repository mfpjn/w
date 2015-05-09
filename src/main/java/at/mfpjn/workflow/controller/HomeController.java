package at.mfpjn.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Locale;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(Locale locale, Model model) throws IOException {
        return "home";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
