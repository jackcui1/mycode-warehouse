package com.emusicstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emusicstore.dao.CartItemDao;
import com.emusicstore.model.Cart;
import com.emusicstore.model.CartItem;
import com.emusicstore.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Override
	public void addCartItem(CartItem cartItem) {
		cartItemDao.addCartItem(cartItem);
		
	}

	@Override
	public void removeCartItem(CartItem cartItem) {
		cartItemDao.removeCartItem(cartItem);
		
	}

	@Override
	public void removeAllCartItem(Cart cart) {
		cartItemDao.removeAllCartItem(cart);
		
	}

	@Override
	public CartItem getCartItemByProductId(int productId) {
		
		return (CartItem)cartItemDao.getCartItemByProductId(productId);
	}

}
