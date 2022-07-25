package com.interior.shivaniInteriorproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.interior.shivaniInteriorproject.model.Role;
import com.interior.shivaniInteriorproject.model.User;
import com.interior.shivaniInteriorproject.repository.IUserRepository;
import com.interior.shivaniInteriorproject.repository.RoleRepository;
import com.interior.shivaniInteriorproject.service.RegistrationService;

@Controller
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping(value = "/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}	
	
	@PostMapping("/register")
	public String processRegister(@ModelAttribute("user") User user, HttpServletRequest httpRequest) throws ServletException{
		String Password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(Password));
		//user.setRoles(roles);
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
	    registrationService.register(user);
	    httpRequest.login(user.getEmail(), Password);
	    return "redirect:/home";
	}

}
