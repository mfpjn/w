package at.mfpjn.workflow.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(Locale locale, Model model, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/resources/img");
        System.err.println(new FileSystemResource(new File(path)));
        return "home";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
