package com.emusicstore.dao;

import java.util.List;

import com.emusicstore.model.Product;

public interface ProductDao {
	void addProduct(Product product);
	Product getProductById(int id);
	void deleteProduct(Product product);
	void editProduct(Product product);
	List<Product> getProductList();
}
