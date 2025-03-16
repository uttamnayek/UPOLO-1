package in.uttam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.uttam.entity.User;
import in.uttam.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class Update {
	@Autowired
	private UserService userservice;

	@GetMapping("/update")
	public String getUpdate(Model model) {
		model.addAttribute("user", new User());
		return "update";
	}

	@PostMapping("/update")
	public String postUpdate(@ModelAttribute("user") User user, Model model, HttpSession session,
			@RequestParam("oldPass") String oldP, @RequestParam("newPass") String newP) {
		User details = (User) session.getAttribute("user");

		String pass1 = details.getPassword();

		System.out.println("session : " + pass1);
		System.out.println("website : " + oldP);
		System.out.println("website new password : " + newP);

		if (!newP.equals("")) {
			if (newP.length() > 3) {
				if (pass1.equals(userservice.encPass(oldP))) {
					details.setPassword(newP);
					
					if (userservice.updateUser(details).equalsIgnoreCase("success")) {
						userservice.uLoginUser(details.getUsername(), pass1);
						session.setAttribute("user", userservice.getuser(details.getId()));
						model.addAttribute("name", details.getName());
						
						return "redirect:/profile";
					}

					model.addAttribute("nerror", "some internal problem occure...");
					return "update";
				} else {
					model.addAttribute("oerror", "old password is not matched...!");
					return "update";
				}
			} else {

				model.addAttribute("nerror", "new password should be more than 3 latters...");
				return "update";
			}
		} else {
			model.addAttribute("nerror", "new password can not null...");
			return "update";
		}

//		if(newP.equals(""))
//		{
//			model.addAttribute("error", "new password can not null...");
//			return "update";
//		}
//		
//		else if (newP.length()<=3) {
//			model.addAttribute("error", "new password will be more than 3 latters...");
//			return "update";
//		}
//		
//		else if(newP!=null)
//		{
//			if(pass1.equals(oldP) ) 
//			{
//				model.addAttribute("sucess", "password update successfully done...");
//				return "update";
//			}
//			else 
//			{
//				model.addAttribute("error", "old password is not matched...!");
//				return "update";
//			}
//		}
	}
}