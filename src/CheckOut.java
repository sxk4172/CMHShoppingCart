/*
 * CheckOut.java
 * 
 * Version : 1.0 Date : 04/29/2016
 * 
 * Revisions : $Log Initial version$
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * This class, demonstrates the working of a billing procedure at a mart. Each
 * product in a mart belongs to one of the three categories. Products can be
 * called by name, category, price range. Products are added to cart for
 * purchase. Products can also be deleted from the cart.
 * 
 * @author Sanika Kulkarni
 */

public class CheckOut {

	static List<Product> items = new ArrayList<Product>();
	static List<Cart> cartList = new ArrayList<Cart>();
	static Scanner sc = new Scanner(System.in);
	static Product prod;
	static String customerName;

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments
	 */

	public static void main(String args[]) {

		boolean continuePurchase = true;
		System.out.println("Welcome to CMH Shopping!!!");
		System.out.println("Please Enter your name :");
		customerName = sc.next();
		System.out.println("\nHello " + customerName + "!!!\n");

		aadProducts(); // add predefined products to the catalog

		while (continuePurchase) {
			boolean temp = false;
			System.out.println("Catalog : ");

			print(items); // print the catalog

			displayProducts(); // display catalog by name, category or price
								// range

			System.out.println("Add Items to Cart y/n ?");
			String cartItems = sc.next();
			if (cartItems.equalsIgnoreCase("y")) {
				System.out
						.println("Enter Product Number of Item to add to Cart :");
				String prodNumber = sc.next();
				for (int i = 0; i < items.size(); i++) {
					int quantity = 1;

					if ((items.get(i).productNumber).equals(prodNumber
							.toUpperCase())) {// check if entered product number
												// is in the catalog

						temp = true;

						for (int j = 0; j < cartList.size(); j++) { // add
																	// products
																	// to cart

							if ((cartList.get(j).productNumber)
									.equals(prodNumber.toUpperCase())) {
								++quantity; // if product already exists
											// increase count of quantity

								cartList.remove(j); // remove the existing
													// product

							}
						}
						Cart cart = new Cart(items.get(i).productNumber,
								items.get(i).productName,
								items.get(i).productPrice, quantity);
						cartList.add(cart); // add the deleted product with
											// increase in the count of quantity
					}
				}

				if (temp == false) {
					System.out.println("Error enter correct product number");
				}
			} else if (cartItems.equalsIgnoreCase("n")) {
				System.out.println("Continue Shopping y/n?");
				String shopping = sc.next();
				if (shopping.equals("n")) {
					continuePurchase = false;

				} else if (shopping.equals("y")) {
					continuePurchase = true;
				} else {
					System.out.println("Enter y/n only");
				}
			} else {
				System.out.println("Enter y/n only");
			}
		}

		Cart cart = new Cart();
		cart.checkTotal();

		if (Cart.total > 0) { // Print cart and proceed o checkout only if a
								// product is added to the cart

			cart.printCart();
			proceedtoCheckout();
		} else {
			System.out.println("Goodbye!!");
		}

		sc.close();
	}

	/**
	 * The proceedtoCheckout method is called only after a product is added to
	 * the cart. It can add an item, remove an item and then proceed to billing
	 *
	 */

	private static void proceedtoCheckout() {
		int choice = 0;
		boolean temp = true;
		while (temp) {
			System.out
					.println("\nEnter 1 to add Item\nEnter 2 to remove item\nEnter 3 to proceed to checkout");
			if (sc.hasNextInt()) {
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					boolean productCheck = true;

					displayProducts();// display catalog by name, category or
										// price
										// range

					System.out
							.println("Enter Product Number of Item to add to Cart :");
					String prodNumber = sc.next();
					for (int i = 0; i < items.size(); i++) {
						int quantity = 1;

						if ((items.get(i).productNumber).equals(prodNumber
								.toUpperCase())) { // check if entered product
													// number is in catalog

							productCheck = false;

							for (int j = 0; j < cartList.size(); j++) {
								if ((cartList.get(j).productNumber)
										.equals(prodNumber.toUpperCase())) { // add
																				// product
																				// to
																				// cart

									++quantity; // if product already exists
												// increase count of quantity

									cartList.remove(j); // remove the existsing
														// product
								}
							}
							Cart cart = new Cart(items.get(i).productNumber,
									items.get(i).productName,
									items.get(i).productPrice, quantity);

							cartList.add(cart); // add deleted product with
												// increase
												// in product quantity

						}
					}
					if (productCheck == true) {
						System.out.println("Enter Correct Number!!");
					} else {
						System.out.println("\nCart Modified:\n");
						Cart c = new Cart();
						c.printCart();
					}
					break;
				case 2:
					boolean check = true;
					System.out
							.println("Enter Product number of item to be removed");
					String productNum = sc.next();
					for (int j = 0; j < cartList.size(); j++) {

						if (cartList.get(j).productNumber.equals(productNum)) {// check
																				// if
																				// entered
																				// product
																				// number
																				// is
																				// in
																				// the
																				// cart

							check = false;

							if (cartList.get(j).Quantity > 1) { // check if more
																// than one
																// quantity
																// of a product

								cartList.get(j).Quantity = (cartList.get(j).Quantity) - 1; // decrease
																							// quantity
																							// if
																							// more
																							// than
																							// one

							} else {

								cartList.remove(j); // remove product if
													// quantity is
													// one

							}
						}
					}
					if (check == true) {
						System.out.println("Enter Correct Number!!");
					} else {
						System.out.println("\nCart Modified:\n");
						Cart cartModified = new Cart();
						cartModified.printCart();
					}
					break;
				case 3:
					System.out.println("\nFinal Cart:\n");
					Cart cartPrint = new Cart();

					cartPrint.checkTotal(); // check if cart has atleast one
											// product

					if (Cart.total > 0) {
						cartPrint.printCart();
						System.out
								.println("Do you want to Proceed to checkout?");
						String checkOut = sc.next();
						if (checkOut.equalsIgnoreCase("y")) {
							Billing billing = new Billing();
							billing.enterShippingDetails();
							billing.generateBill();
							temp = false;
						} else if (checkOut.equalsIgnoreCase("n")) {
							temp = true;
						} else {
							System.out.println("Enter y/n only!!!");
						}
					} else {
						System.out.println("Cart Empty");
					}

					break;
				default:
					System.out.println("Enter 1 , 2 or 3 only");
				}
			} else {
				System.out.println("You enetered a non-numeric field");
				sc.next();
				continue;
			}
		}
	}

	/**
	 * The displayProducts method is called only after a product is added to the
	 * catalog. Products can be viewed by name, category and price range
	 *
	 */

	private static void displayProducts() {
		String category;
		int choice = 0;
		boolean temp = true;
		while (temp) {
			System.out.println("Enter 1 to Display Items by Name");
			System.out.println("Enter 2 to Display Items by Category");
			System.out.println("Enter 3 to Display Items by Price Range");

			if (sc.hasNextInt()) {
				choice = sc.nextInt();

				switch (choice) {
				case 1:

					List<Product> nameList = new ArrayList<Product>(items); // display
																			// products
																			// in
																			// ascending
																			// order
																			// of
																			// name

					if (nameList.size() > 0) {
						Collections.sort(nameList, new Comparator<Product>() {
							@Override
							public int compare(Product o1, Product o2) {
								return o1.productName.compareTo(o2.productName);
							}
						});
					}
					print(nameList);
					temp = false;
					break;
				case 2:
					int categoryChoice = 0;
					boolean temp1 = true;
					while (temp1) {
						System.out
								.println("\nEnter 1 for Food\nEnter 2 for Clothing\nEnter 3 for Utilities");

						if (sc.hasNextInt()) {
							categoryChoice = sc.nextInt();

							if (categoryChoice == 1) {
								category = "Food";
								printCategory(category);
								temp1 = false;
							} else if (categoryChoice == 2) {
								category = "Clothing";
								printCategory(category);
								temp1 = false;
							} else if (categoryChoice == 3) {
								category = "Utilities";
								printCategory(category);
								temp1 = false;
							} else {
								System.out
										.println("Error entered wrong number");
							}
							temp = false;
						} else {
							System.out
									.println("You enetered a non-numeric value");
							sc.next();
							continue;
						}
					}
					break;
				case 3:
					int priceFrom = 0;
					int priceTo = 0;
					System.out.println("Enter Price Range from :");
					if (sc.hasNextInt()) {
						priceFrom = sc.nextInt();
					} else {
						System.out
								.println("You have entered a non numeric field value");
						sc.next();
						continue;
					}
					System.out.println("Enter Price Range To :");
					if (sc.hasNextInt()) {
						priceTo = sc.nextInt();
					} else {
						System.out
								.println("You have entered a non numeric field value");
						sc.next();
						continue;
					}
					temp = printPriceRange(priceFrom, priceTo);
					break;

				default:
					System.out.println("Enter 1, 2 or 3 only!!");
					break;
				}
			} else {
				System.out.println("You entered a non numeric field");
				sc.next();
				continue;
			}
		}
	}

	/**
	 * The printPriceRange method displays products belonging to a particular
	 * price range
	 * 
	 * @param priceFrom
	 *            start of price range
	 * @param priceTo
	 *            end of price range
	 *
	 */

	private static boolean printPriceRange(int priceFrom, int priceTo) {
		boolean temp = true;
		System.out.println("Product Number  |  Name  |  Price");
		for (int i = 0; i < items.size(); i++) {
			if ((items.get(i).productPrice) >= priceFrom
					&& (items.get(i).productPrice) <= priceTo) {
				temp = false;
				System.out.print(items.get(i).productNumber + "            | ");
				System.out.print(items.get(i).productName + "   | ");
				System.out.print("$" + items.get(i).productPrice + " | ");
				System.out.println();
			}
		}
		if (temp == true) {
			System.out.println("No Product in this Price range");
		}
		System.out.println("------------------------------------");
		return temp;
	}

	/**
	 * The aadProducts method adds products to a catalog. These products have a
	 * fixed price, Product number, product name and the category to which they
	 * belong
	 * 
	 */

	private static void aadProducts() {
		prod = new Product("P001", "Eggs", 4.47, "Food");
		items.add(prod);
		prod = new Product("P002", "Bags", 15.97, "Clothing");
		items.add(prod);
		prod = new Product("P003", "Milk", 5.67, "Food");
		items.add(prod);
		prod = new Product("P004", "Dresses", 19.96, "Clothing");
		items.add(prod);
		prod = new Product("P005", "Tables", 21.17, "Utilities");
		items.add(prod);
		prod = new Product("P006", "Shirts", 16.98, "Clothing");
		items.add(prod);
		prod = new Product("P007", "Bread", 3.28, "Food");
		items.add(prod);
		prod = new Product("P008", "Chair", 19.84, "Utilities");
		items.add(prod);
		prod = new Product("P009", "Lamp", 8.57, "Utilities");
		items.add(prod);
		prod = new Product("P010", "Apples", 9.96, "Food");
		items.add(prod);
	}

	/**
	 * The printCategory method displays only those products that belong to a
	 * category
	 * 
	 * @param category
	 *            category to which a product belongs
	 * 
	 */

	private static void printCategory(String category) {
		System.out.println("Product Number  |  Name  |  Price");
		for (int i = 0; i < items.size(); i++) {
			if ((items.get(i).category).equals(category)) {
				System.out.print(items.get(i).productNumber + "            | ");
				System.out.print(items.get(i).productName + "   | ");
				System.out.print("$" + items.get(i).productPrice + " | ");
				System.out.println();
			}
		}
		System.out.println("------------------------------------");
	}

	/**
	 * The print method displays all the products in a catalog
	 * 
	 * @param items
	 *            list that contains all products and its description
	 * 
	 */

	private static void print(List<Product> items) {
		System.out.println("Product Number  |  Name  |  Price");
		System.out.println("------------------------------------");
		for (int i = 0; i < items.size(); i++) {
			System.out.print(items.get(i).productNumber + "            | ");
			System.out.print(items.get(i).productName + "   | ");
			System.out.print("$" + items.get(i).productPrice + " | ");
			System.out.println();
		}
		System.out.println("------------------------------------");
	}

}
