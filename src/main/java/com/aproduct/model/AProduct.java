package com.aproduct.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


@Entity
@Component
public class AProduct {

	@Id()
	private long aproductID;
	@Column(name = "aproductname")
	private String productName;
	@Column(name = "aproductprice")
	private long productPrice;
	//Default Constructor
	public AProduct() {
		
	}
	//Parameterized Constructor
	public AProduct(long productID, String productName, long productPrice) {
		super();
		this.aproductID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	public long getProductID() {
		return aproductID;
	}
	public void setProductID(long productID) {
		this.aproductID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public String toString() {
		return "Product [productID=" + aproductID + ", productName=" + productName + ", productPrice=" + productPrice
				+ "]";
	}
	
}
