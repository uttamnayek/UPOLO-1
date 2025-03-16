package in.uttam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class CallAttachment {
	
public String attach(Model model)
{
	model.addAttribute(model);
	return "attachment";
}
}
