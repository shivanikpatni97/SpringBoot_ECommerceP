package com.interior.shivaniInteriorproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.interior.shivaniInteriorproject.model.User;

@Controller
public class LoginController {

	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String processRegister(@ModelAttribute("user") User user) {
	
		
	   // loginService.loginUser(user);
	    return "Succefully logged in";
	}
}
