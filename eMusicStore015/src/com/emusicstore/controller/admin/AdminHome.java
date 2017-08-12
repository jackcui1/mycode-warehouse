package com.emusicstore.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emusicstore.model.Product;
import com.emusicstore.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminHome {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping
	public String adminPage(){
		return "admin";
	}
	
	@RequestMapping("/productInventory")
	public String productInventory(Model model){
		List<Product> productList=productService.getProductList();
		model.addAttribute("productList",productList);
		return "productInventory";
	}
	
}
