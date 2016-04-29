/*
 * Billing.java
 * 
 * Version : 1.0 Date : 04/29/2016
 * 
 * Revisions : $Log Initial version$
 */

/**
 * This class generates the bill
 * 
 * @author Sanika Kulkarni
 */

public class Billing {
	String shippingAddress;
	String billingAddress;

	void enterShippingDetails() {
		System.out.println("Enter shipping Address");
		shippingAddress = CheckOut.sc.next();
		System.out.println("Enter billing Address");
		billingAddress = CheckOut.sc.next();
	}

	/**
	 * The generateBill method generates a receipt holding customer name,
	 * address and bill
	 *
	 */

	public void generateBill() {
		Cart cart = new Cart();
		System.out.println("\n" + CheckOut.customerName
				+ " - Your Bill Summary");
		System.out.println("Billing Address : " + billingAddress + "\n");
		System.out.println("Shipping Address : " + shippingAddress + "\n");
		cart.printCart();
	}

}
