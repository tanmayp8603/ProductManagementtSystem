package com.itvedant.product.service;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.product.dao.AddProductDAO;
import com.itvedant.product.dao.updateProductDAO;
import com.itvedant.product.entity.Product;
import com.itvedant.product.repository.ProductRepository;

import jakarta.persistence.Id;
@Service
public class ProductService 
{
	@Autowired
   ProductRepository productRepository;
	
	public Product createProduct(AddProductDAO addProductDAO) 
	{
		Product product = new Product();
		
		product.setName(addProductDAO.getName());
		product.setPrice(addProductDAO.getPrice());
		product.setQuantity(addProductDAO.getQuantity());
		
		productRepository.save(product);
		return product;
		
	}
	
	public List<Product>readAll()
	{
		List<Product> products = new ArrayList<Product>();
		products = this.productRepository.findAll();
		return products;
	}
	
	public Product readProductById(Integer id)
	{
		Product product = new Product();
		
		product = this.productRepository.findById(id).orElse(null);
		
		return product;
	}
	
	public Product updateProduct(Integer id, updateProductDAO UpdateProductDAO)
	{
		Product product = new Product();
		
		product = this.readProductById(id);
		
		if(UpdateProductDAO.getName() !=null)
		{
			product.setName(UpdateProductDAO.getName());
		}
		if(UpdateProductDAO.getPrice() != null) 
		{
			product.setPrice(UpdateProductDAO.getPrice());
		}
		if(UpdateProductDAO.getQuantity() != null)
		{
			product.setQuantity(UpdateProductDAO.getQuantity());
		}
		
		this.productRepository.save(product);
		
		return product;
	}
	
	public String deleteProduct(Integer id)
	{
		Product product = new Product();
		
		product = this.readProductById(id);
		
		this.productRepository.delete(product);
		
		return "Product Data Deleted";
		
	}
}
