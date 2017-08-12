package com.emusicstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emusicstore.model.BillingAddress;
import com.emusicstore.model.Customer;
import com.emusicstore.model.ShippingAddress;
import com.emusicstore.service.CustomerService;

@Controller
public class RegisterController {

	@Autowired
	CustomerService customerService;

	@RequestMapping("/register")
	public String registerCustomer(Model model) {
		Customer customer = new Customer();
		BillingAddress billingAddress = new BillingAddress();
		ShippingAddress shippingAddress = new ShippingAddress();

		customer.setBillingAddress(billingAddress);
		customer.setShippingAddress(shippingAddress);

		model.addAttribute("customer", customer);

		return "registerCustomer";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerCustomerPost(
			@Valid @ModelAttribute("customer") Customer customer,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "registerCustomer";
		}

		List<Customer> customerList = customerService.getAllCustomer();

		for (int i = 0; i < customerList.size(); i++) {
			if (customer.getCustomerName()
					.equals(customerList.get(i).getCustomerName())) {
				model.addAttribute("customerNameMsg", "Customer name alread exists.");
				return "registerCustomer";
			}
			
			if (customer.getCustomerEmail().equals(
					customerList.get(i).getCustomerEmail())) {
				model.addAttribute("emailMsg", "Email already exists.");
				return "registerCustomer";
			}
			if (customer.getUserName()
					.equals(customerList.get(i).getUserName())) {
				model.addAttribute("usernameMsg", "Username alread exists.");
				return "registerCustomer";
			}
		}

		customer.setEnabled(true);
		customerService.addCustomer(customer);
		return "registercustomersuccess";
	}
}
