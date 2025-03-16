package in.uttam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexForm {

	@PostMapping("/submit")
	public String handleForm(@RequestParam("value") String value) {
		// Process the value
		System.out.println("value is : "+value);
		return "redirect:/login";
	}
}
