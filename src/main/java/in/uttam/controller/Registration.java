package in.uttam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.uttam.entity.User;
import in.uttam.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class Registration {
	@Autowired
	private UserService userservice;
	@Autowired
	private EmailSender emailSender;

	@GetMapping("/signup")
	public String getReg(Model model) {
		model.addAttribute("reg", "Registration");
		model.addAttribute("headline", "Registration");
		model.addAttribute("user", new User());
		return "RegLog";
	}

	@PostMapping("/signup")
	public String postReg(@ModelAttribute("user") User user, Model model, HttpSession session) {
		String massage = userservice.regUser(user);
		String massagePass = userservice.passwordLength(user.getPassword());
		if (massage.equalsIgnoreCase("success")) {
			if (massagePass.equalsIgnoreCase("success")) {
				String response = emailSender.sendEmailHtmlAttachment(user);
				System.out.println("Response from email sending : " + response);
				model.addAttribute("name", user.getName());
				session.setAttribute("user", user);
				return "redirect:/profile";
			} else {
				model.addAttribute("reg", "Registration");
				model.addAttribute("headline", "Registration");
				model.addAttribute("error", massagePass);
				model.addAttribute("user", new User());
				return "RegLog";
			}

		} else {
			model.addAttribute("reg", "Registration");
			model.addAttribute("headline", "Registration");
			model.addAttribute("error", massage);
			model.addAttribute("user", new User());
			return "RegLog";
		}

	}
}
