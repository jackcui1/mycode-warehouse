package com.emusicstore.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart implements Serializable {

	private static final long serialVersionUID = 4705768177687620031L;
	
	@Id
	@GeneratedValue
	private int cartId;
	
	@OneToMany(mappedBy="cart",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<CartItem> cartItems;
	
	@OneToOne
	@JoinColumn(name="customerId")
	@JsonIgnore
	private Customer customer;
	
	private double grandTotal;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	/** previous Code
	 * 
	 * private int cartId;
	private Map<String, CartItem> cartItems;
	private double grandTotal;
	private Cart() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = 0;
	}

	public Cart(String cartId) {
		this();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public void addCartItem(CartItem item) {
		String productId = item.getProduct().getProductId();
		if (cartItems.containsKey(productId)) {
			CartItem existingCartItem = cartItems.get(productId);
			existingCartItem.setQuantity(existingCartItem.getQuantity()
					+ item.getQuantity());
			cartItems.put(productId, existingCartItem);
		} else {
			cartItems.put(productId, item);
		}
		updateGrandTatol();
	}

	public void removeCartItem(CartItem item) {
		String productId = item.getProduct().getProductId();
		cartItems.remove(productId);
		updateGrandTatol();
	}

	private void updateGrandTatol() {
		grandTotal=0;
		for(CartItem item:cartItems.values()){
			grandTotal=grandTotal+item.getTotalPrice();
		}
		
	}
	**/
}
