/*
 * Product.java
 * 
 * Version : 1.0 Date : 04/29/2016
 * 
 * Revisions : $Log Initial version$
 */

/**
 * This class holds details of products
 * 
 * @author Sanika Kulkarni
 */

public class Product {
	String productNumber;
	String productName;
	double productPrice;
	String category;

	public Product(String productNumber, String productName, double productPrice, String category) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.productPrice = productPrice;
		this.category = category;
	}
}
