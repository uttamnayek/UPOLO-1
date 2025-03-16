package in.uttam.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import in.uttam.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Logout {
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest req) {
		User user = (User) session.getAttribute("user");
		user = null;
		session.invalidate();
		session = req.getSession(false);

		System.out.println("it is logged out");
		return "redirect:/";
	}
}