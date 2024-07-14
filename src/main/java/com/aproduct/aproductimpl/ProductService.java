package com.aproduct.aproductimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aproduct.model.AProduct;

import com.aproduct.productdao.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository repo;
	@Autowired
	AProduct product;
	public String createProduct(List<AProduct> prod) {
		
		
		
		if(prod.isEmpty()) {
			
			return "Create product Service Failed";
		}
		else {
			
		
			for( AProduct p : prod) {
				
				product = new AProduct(p.getProductID(),p.getProductName(), p.getProductPrice());
				
				repo.save(product);
				
				
			}
			return "Product Creation Successfull";
		}
	}
	
	public List<AProduct> displayAll(){
		
		
		return repo.findAll();
		
	}
	
   public AProduct displayById(long prodID){
		
	   Optional<AProduct> prod = repo.findById(prodID);
	   
	   
	   product = new AProduct(prod.get().getProductID(),prod.get().getProductName(), prod.get().getProductPrice());
		return product;
		
	
   }
   
   public String deleteProd(long prodID) {
	   
	   String response = null;
	   try {
	   repo.deleteById(prodID);
	   response = "Product deleted successfully";
	   }
	   catch(Exception e) {
		   System.out.println("Product doesnot exist");
	   }
	
	   return response;
   }

public String addProductFromManufacturer(List<AProduct> products) {
	
	long price;
	long actualPrice;
	String response = null;
	try {
		for(AProduct prod : products) {
	   
		price=prod.getProductPrice();
		actualPrice=amazonSellingPrice(price);
	    prod.setProductPrice(actualPrice);
	    
		repo.save(prod);
		response="Added Products Successfully!!";
	}}
	catch(Exception e){
		
		response="Exception: "+e;
	}
	return response;
	
	
}

public String updateProduct(AProduct prod) {
	
	try {
		
	
	repo.save(prod);
	return "Product update successfull";
	}catch(Exception e){
		return "Product update unsuccessfull"+e;
	}
	
	
	}

private long amazonSellingPrice(long costPrice) {

	long profit = 0L;
	long sellingPrice = 0L;

	if (costPrice <= 100) {
		profit = ((costPrice * 55) / 100);
		sellingPrice = costPrice + profit;
		System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "60%" + " PROFIT " + profit + " SELLING PRICE" + sellingPrice);
		return sellingPrice;

	} else if (costPrice > 100 && costPrice <= 500) {
		profit = ((costPrice * 48) / 100);
		sellingPrice = costPrice + profit;
		System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "50%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
		return sellingPrice;

	} else if (costPrice > 500 && costPrice <= 1000) {
		profit = ((costPrice * 43) / 100);
		sellingPrice = costPrice + profit;
		System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "40%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
		return sellingPrice;

	} else if (costPrice > 1000 && costPrice <= 2000) {
		profit = ((costPrice * 28) / 100);
		sellingPrice = costPrice + profit;
		System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "30%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
		return sellingPrice;

	} else if (costPrice > 2000 && costPrice <= 4000) {
		profit = ((costPrice * 21) / 100);
		sellingPrice = costPrice + profit;
		System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "20%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
		return sellingPrice;

	} else if (costPrice > 4000 && costPrice <= 7000) {
		profit = ((costPrice * 12) / 100);
		sellingPrice = costPrice + profit;
		System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "10%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
		return sellingPrice;

	} else{
		profit = ((costPrice * 6) / 100);
		sellingPrice = costPrice + profit;
		System.out.println("COST PRICE" + "-" + costPrice + " PROFIT-" + "05%" + " PROFIT - " + profit + " SELLING PRICE - " + sellingPrice);
		return sellingPrice;

	}}
}
