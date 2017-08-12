package com.emusicstore.dao;

import com.emusicstore.model.Cart;
import com.emusicstore.model.CartItem;

public interface CartItemDao {
	void addCartItem(CartItem cartItem);
	void removeCartItem(CartItem cartItem);
	void removeAllCartItem(Cart cart);
	CartItem getCartItemByProductId(int productId);
}
