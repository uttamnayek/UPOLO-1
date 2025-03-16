package in.uttam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.uttam.entity.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class Profile 
{
	@GetMapping("/profile")
	public String openProfile(HttpSession session, Model model) 
	{
		User user = (User) session.getAttribute("user");
		if (user != null) 
		{
			model.addAttribute("user", user);
			return "profile";
		}
		return "redirect:/login";
	}
}

//session.setAttribute("user", details);
//model.addAttribute("user", details);