package com.interior.shivaniInteriorproject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.interior.shivaniInteriorproject.dto.DesignsDTO;
import com.interior.shivaniInteriorproject.model.Product;
import com.interior.shivaniInteriorproject.service.CategoryService;
import com.interior.shivaniInteriorproject.service.DesignService;

@Controller
public class DesignController {

	@Autowired
	DesignService designService;

	@Autowired
	CategoryService categoryService;

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@GetMapping("/admin/designs")
	public String designs(Model model) {
		model.addAttribute("designs", designService.getAllDesigns());
		return "products";
	}

	@GetMapping("/admin/designs/add")
	public String addDesignsGet(Model model) {
		model.addAttribute("productDTO", new DesignsDTO());
		model.addAttribute("categories", categoryService.getcategoryAll());

		return "productsAdd";
	}

	@PostMapping("/admin/designs/add")
	public String addDesignsPost(@ModelAttribute("productDTO") DesignsDTO productDTO,
			@RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName) throws IOException{

		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			//to create file path and name
			Path fileNameandPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameandPath, file.getBytes());
		}
		else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		designService.addProduct(product);

		return "redirect:/admin/designs";
	}

	@GetMapping("/admin/designs/delete/{id}")
	public String deleteDesignById(@PathVariable long id) {
		designService.removeProductByID(id);
		return "redirect:/admin/designs";
	}

	@GetMapping("/admin/designs/update/{id}")
	public String updateDesignById(@PathVariable long id, Model model) {

        Product product = designService.findDesignById(id).get();
        DesignsDTO productDTO = new DesignsDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

		model.addAttribute("categories", categoryService.getcategoryAll());
    	model.addAttribute("productDTO", productDTO);
		return "productsAdd";
	}
}
