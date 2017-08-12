package com.emusicstore.controller.admin;

import java.util.List;

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

import com.emusicstore.model.Customer;
import com.emusicstore.service.CustomerService;

@Controller
@RequestMapping("/admin")
public class AdminCustomer {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customer")
	public String customerManagement(Model model){
		
		List<Customer> customerList=customerService.getAllCustomer();
		model.addAttribute("customerList",customerList);
		return "customerManagement";
	}
	
	@RequestMapping("/customer/delete/{id}")
	public String deleteCustomer(@PathVariable("id") int id,
			HttpServletRequest request) {
		customerService.deleteCustomer(customerService.getCustomerById(id));
		return "redirect:/admin/customer";
	}
	
	@RequestMapping("/customer/edit/{id}")
	public String editCustomer(@PathVariable("id") int id, Model model) {
		Customer customer= customerService.getCustomerById(id);
		model.addAttribute("customer", customer);
		return "editCustomer";
	}
	
	@RequestMapping(value = "/customer/edit", method = RequestMethod.POST)
	public String editCustomerPost(
			@Valid @ModelAttribute("customer") Customer customer,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "editCustomer";
		}

		customerService.editCustomer(customer);
		return "redirect:/admin/customer";
	}
}
