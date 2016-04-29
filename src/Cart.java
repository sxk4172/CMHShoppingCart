/*
 * Cart.java
 * 
 * Version : 1.0 Date : 04/29/2016
 * 
 * Revisions : $Log Initial version$
 */

/**
 * This class contains a cart that holds all the products selected by customer.
 * 
 * @author Sanika Kulkarni
 */

public class Cart {
	String productNumber;
	String productName;
	double productPrice;
	int Quantity;
	static double total = 0;

	public Cart() {

	}

	public Cart(String productNumber, String productName, double productPrice,
			int Quantity) {
		this.productNumber = productNumber;
		this.productName = productName;
		this.productPrice = productPrice;
		this.Quantity = Quantity;
	}

	/**
	 * The checkTotal method calculates the total cost.
	 *
	 */

	void checkTotal() {
		for (int i = 0; i < CheckOut.cartList.size(); i++) {
			double price = (CheckOut.cartList.get(i).productPrice)
					* (CheckOut.cartList.get(i).Quantity);
			CalculateTotal(price);
		}
	}

	/**
	 * The printCart method prints the entire cart and the total cost including
	 * tax
	 *
	 */

	void printCart() {
		total = 0;
		System.out.println("Product Number  |  Name  |  Quantity  |  Price");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < CheckOut.cartList.size(); i++) {
			System.out.print(CheckOut.cartList.get(i).productNumber
					+ "            | ");
			System.out.print(CheckOut.cartList.get(i).productName + "   | ");
			System.out.print(CheckOut.cartList.get(i).Quantity + "   | ");
			double price = (CheckOut.cartList.get(i).productPrice)
					* (CheckOut.cartList.get(i).Quantity);
			System.out.print("$" + price + " | ");
			CalculateTotal(price);
			System.out.println();
		}
		System.out.println("-----------------------------------------------");
		System.out.println("Total : $" + total);
		System.out.println("Tax :   $" + 0.19 * total);
		System.out.println("-----------------------------------------------");
		System.out.println("Cost Price : $" + (total + 0.10 * total));
		total = 0;
	}
	
	/**
	 * The CalculateTotal method calculates the total cost.
	 * 
	 * @param	price	price of each product
	 *
	 */

	private void CalculateTotal(double price) {
		total = total + price;
	}
}
