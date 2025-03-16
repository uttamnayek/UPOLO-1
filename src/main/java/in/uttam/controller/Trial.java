package in.uttam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Trial {

	@GetMapping("/trial")
	public String trial() {
		System.out.println("it is clicked");
		return "calculator";
	}

	@PostMapping("/trial")
	public String postTrial(Model model, @RequestParam("odd") float odd, @RequestParam("even") float even) {
		System.out.println("now post mapping");
		float cgpa = (odd + even) / 2;
		float per = (float) ((cgpa - 0.75) * 10);
		String percentage = String.format("%.2f", per) + "%";
		model.addAttribute("cgpa", cgpa);
		if (per > 0) {
			model.addAttribute("per", percentage);
		}
		System.out.println("odd : " + odd + "  even : " + even);
		System.out.println("cgpa : " + cgpa + "  per : " + percentage);
		return "calculator";
	}
}
