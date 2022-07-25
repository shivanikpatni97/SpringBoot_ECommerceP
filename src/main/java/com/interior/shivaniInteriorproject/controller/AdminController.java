package com.interior.shivaniInteriorproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.interior.shivaniInteriorproject.model.Category;
import com.interior.shivaniInteriorproject.service.CategoryService;

@Controller
public class AdminController {

	@Autowired
	 CategoryService categoryService;
	
	@RequestMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryService.getcategoryAll());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getAddCategories(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postAddCategories(@ModelAttribute("category") Category category)  {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.deleteCategorybyId(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
		Optional<Category> cat = categoryService.getCategoryById(id);
		if(cat.isPresent()) {
			model.addAttribute("category", cat.get());
			return "categoriesAdd";
		}
		else
		{
			return "404";
		}
		
	}
}
