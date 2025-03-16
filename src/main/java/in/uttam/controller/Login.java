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
public class Login 
{
	@Autowired
	private UserService userservice;
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		System.out.println("someone click at login bottom...........");

		model.addAttribute("log", "Login");
		model.addAttribute("headline", "Login");
		model.addAttribute("user", new User());
		return "RegLog";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute("user") User user,Model model,HttpSession session) 
	{
		
		User details = userservice.loginUser(user.getEmail().toLowerCase(), user.getPassword());
		
		if(details!=null)
		{
//			model.addAttribute("name", details.getName());
			session.setAttribute("user", details);
//			session.wait(0);
//			model.addAttribute("user", details);
//			return "profile";
			return "redirect:/profile";
		}
		
		else
		{
			
			model.addAttribute("log", "Login");
			model.addAttribute("headline", "Login");
			model.addAttribute("error", "email and password is not matched");
			model.addAttribute("user", new User());
			return "RegLog";
		}
	}
	
	//.....................
	@GetMapping("/mlogin")
	public String getUserLogin(Model model) {

		model.addAttribute("mlog", "userLogin");
		model.addAttribute("headline", "Login");
		model.addAttribute("user", new User());
		return "RegLog";
	}

	@PostMapping("/mlogin")
	public String postUserLogin(@ModelAttribute("user") User user,Model model,HttpSession session) 
	{
		
		User details = userservice.uLoginUser(user.getUsername(), user.getPassword());
		
		if(details!=null)
		{
//			model.addAttribute("name", details.getName());
			session.setAttribute("user", details);
			model.addAttribute("user", details);
			
//			return  "profile";
			return "redirect:/profile";
		}
		
		else
		{
			model.addAttribute("mlog", "userLogin");
			model.addAttribute("headline", "Login");
			model.addAttribute("error", "username and password is not matched");
			model.addAttribute("user", new User());
			return "RegLog";
		}
	}

}
