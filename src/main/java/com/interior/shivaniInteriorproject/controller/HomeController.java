package com.interior.shivaniInteriorproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.interior.shivaniInteriorproject.service.CategoryService;
import com.interior.shivaniInteriorproject.service.DesignService;

@Controller
public class HomeController {

	@Autowired
	CategoryService categorySerivce;

	@Autowired
	DesignService designService;

	@GetMapping({"/home","/"})
	public String homePage(Model model) {
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories",categorySerivce.getcategoryAll());
		model.addAttribute("products", designService.getAllDesigns());
		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable int id,Model model) {
		model.addAttribute("categories",categorySerivce.getcategoryAll());
		model.addAttribute("products", designService.getAllDesignsByCategory(id));
		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable int id,Model model) {
		model.addAttribute("product",designService.findDesignById(id).get());
		return "viewProduct";
	}
}
