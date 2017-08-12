package com.emusicstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.model.Product;
import com.emusicstore.service.ProductService;

//@Service
//public class ProductServiceImpl implements ProductService {
//	
//	@Autowired
//	private ProductDao productDao;
//	
//	@Override
//	public List<Product> getProductList() {
//		
//		return productDao.getProductList();
//	}
//
//	@Override
//	public Product getProductByid(int productId) {
//		return productDao.getProductById(productId);
//	}
//
//	@Override
//	public void addProduct(Product product) {
//		productDao.addProduct(product);
//		
//	}
//
//	@Override
//	public void editProduct(Product product) {
//		productDao.editProduct(product);
//	}
//
//	@Override
//	public void deleteProduct(Product product) {
//		productDao.deleteProduct(product);
//		
//	}
	
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    
    @Override
    public Product getProductById (int productId) {
        return productDao.getProductById(productId);
    }

    public List<Product> getProductList () {
        return productDao.getProductList();
    }

    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    public void editProduct(Product product) {
        productDao.editProduct(product);
    }

    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }
}
