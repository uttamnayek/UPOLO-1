package in.uttam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import in.uttam.entity.User;
import in.uttam.reposetory.UserReposetory;
import in.uttam.service.UserService;
import jakarta.servlet.http.HttpSession;

//@Controller
//public class Delete {
//	@DeleteMapping("/delete/{id}")
//	public String getdel(Model model, HttpSession session, @PathVariable int id) {
//		User details = (User) session.getAttribute("user");
//
//		System.out.println("hi i am uttam.....and you want to delete the account");
//		model.addAttribute("name", details.getName());
//		session.setAttribute("user", details);
//		return "profile";
//	}
//
//}

@Controller
public class Delete {
	
	@Autowired
	private UserService userService;

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id, HttpSession session, Model model) {
        User details = (User) session.getAttribute("user");

        if (details != null && details.getId() == id && userService.deleteAccount(id)) {
            // Perform account deletion logic here
            // For example, call a service to delete the user from the database
            
            // Simulate account deletion
            System.out.println("Account with ID " + id + " is being deleted.");

            // Redirect to profile page or login page after deletion
            session.removeAttribute("user");  // Optionally, remove user from session
            model.addAttribute("message", "Account deleted successfully.");
            return "redirect:/";  // Redirect to login page after deletion
        } else {
            model.addAttribute("message", "Account deletion failed.");
            return "profile";  // Return to profile page if not successful
        }
    }
}
