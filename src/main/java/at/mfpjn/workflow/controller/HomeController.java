package at.mfpjn.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String home() throws Exception {
		return "home";
	}

	@RequestMapping(value = "/receiverStart")
	public String receiverStart() {
		return "receiverStart";
	}

}
