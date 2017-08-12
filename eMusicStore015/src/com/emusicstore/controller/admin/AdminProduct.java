package com.emusicstore.controller.admin;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.emusicstore.model.Product;
import com.emusicstore.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminProduct {
	private Path path;

	@Autowired
	private ProductService productService;

	@RequestMapping("/product/addProduct")
	public String addProduct(Model model) {
		Product product = new Product();
		product.setProductCategory("instrument");
		product.setProductStatus("active");
		product.setProductCondition("new");
		model.addAttribute("product", product);
		return "addProduct";
	}

	@RequestMapping(value = "/product/addProduct", method = RequestMethod.POST)
	public String addProductPost(
			@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			return "addProduct";
		}

		productService.addProduct(product);
		// System.out.println("product: " + product);
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext()
				.getRealPath("/");
		path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\"
				+ product.getProductId() + ".png");
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
				System.out.println("file transfer success£º " + path.toString());
			} catch (Exception e) {
				throw new RuntimeException("Product image saving failed.", e);
			}
		}
		return "redirect:/admin/productInventory";
	}

	@RequestMapping("/product/editProduct/{id}")
	public String editProduct(@PathVariable("id") int id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "editProduct";
	}

	@RequestMapping(value = "/product/editProduct", method = RequestMethod.POST)
	public String editProductPost(
			@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			return "editProduct";
		}
		// System.out.println("product: " + product);
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext()
				.getRealPath("/");
		path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\"
				+ product.getProductId() + ".png");
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
				System.out.println("file transfer success£º " + path.toString());
			} catch (Exception e) {
				throw new RuntimeException("Product image saving failed.", e);
			}
		}
		productService.editProduct(product);
		return "redirect:/admin/productInventory";
	}

	@RequestMapping("/product/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") int id,
			HttpServletRequest request) {

		String rootDirectory = request.getSession().getServletContext()
				.getRealPath("/");
		path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + id
				+ ".png");
		if (Files.exists(path)) {
			try {
				Files.delete(path);
				System.out.println("file transfer success£º " + path.toString());
			} catch (Exception e) {
				throw new RuntimeException("Product image saving failed.", e);
			}
		}
		productService.deleteProduct(productService.getProductById(id));
		return "redirect:/admin/productInventory";
	}

}
