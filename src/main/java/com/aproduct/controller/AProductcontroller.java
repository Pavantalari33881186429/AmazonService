package com.aproduct.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aproduct.aproductimpl.ProductService;
import com.aproduct.feign.FiegnConfig;
import com.aproduct.model.AProduct;

//import com.fproduct.feign.FiegnConfig;
//import com.fproduct.fproductimpl.ProductService;
//import com.fproduct.model.FProduct;



	
	@RestController
	public class AProductcontroller {
		
		@Value("${server.port}")
		int port;
		
		@Value("${spring.application.name}")
		String appName;
		
		
		@Autowired
		ProductService prodservice;
		
		@Autowired
		FiegnConfig proxy;
		
		
		@GetMapping("/getAllProductsFromManufacturer")
		public String addProductsfromManufacturer()
		{
			List<AProduct>  products= proxy.getProductDetailsFromProductManufacturer();
			
			String response = prodservice.addProductFromManufacturer(products);
			return response;
			
		}
		
		@PostMapping("/createprod")
		public String addProduct(@RequestBody List<AProduct> productList ) {
			
		  String response = prodservice.createProduct(productList);
			
			return response;
			
			
			
		}
		@GetMapping("/getallprod")
		public List<AProduct> getProducts() {
			
			return prodservice.displayAll();
			
		}
		
		@GetMapping("/getprodbyid/{id}")
	    public  AProduct getProductByID(@PathVariable("id") long prodID) {
			
			return prodservice.displayById(prodID);
		}
		
		
		@PutMapping("/updateprod")
		public String updateProduct(@RequestBody AProduct prod) {
			return prodservice.updateProduct(prod);
		}
		
		
		
		
		@DeleteMapping("/deleteprod/{id}")
		public String deleteProduct(@PathVariable("id") long prodID) {
			
			return prodservice.deleteProd(prodID);
			
		}
		
		
		
	}

