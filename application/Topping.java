package application;

import java.util.ArrayList;

/**
 * Enumeration containing all possible toppings for the Pizza class
 */
public enum Topping {
	Pepperoni("Pepperoni"), Mushrooms("Mushrooms"), Onions("Onions"), Sausage("Sausage"), Bacon("Bacon"), ExtraCheese("Extra Cheese"), BlackOlives("Black Olives"), GreenPeppers("Green Peppers"), Pineapple("Pineapple"), Spinach("Spinach"), Ham("Ham"), Steak("Steak"), Cheese("Cheese");
	
	private final String DESCRIPTION;
	
	private Topping(String desc) {
		this.DESCRIPTION = desc;
	}
	
	/**
	 * @return the topping description as a String
	 */
	public String getDescription() {
		return this.DESCRIPTION;
	}
}
 