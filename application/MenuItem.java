/**
 * 
 */
package application;

import java.text.DecimalFormat; 

/**
 * 
 * @author Alex Yang
 *
 */
public class MenuItem{
	
	private String desc;
	private double price;
	private Pizza pizza;
	private Drink drink;
	
	/**
	 * Constructs a MenuItem with an assigned Pizza value
	 * @param price
	 * @param pizza
	 */
	public MenuItem(double price, Pizza pizza, String desc) {
		this.desc = desc;
		this.price = price;
		this.pizza = pizza;
		this.drink = null;
	}
	
	/**
	 * Constructs a MenuItem with an assigned Drink value
	 * @param price
	 * @param drink
	 */
	public MenuItem(double price, Drink drink, String desc) {
		this.desc = desc;
		this.price = price;
		this.pizza = null;
		this.drink = drink;
	}
	
	/**
	 * Sets the Pizza value of the MenuItem by passing in a Pizza enumeration
	 * @param pizza
	 */
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	/**
	 * Returns the Pizza value of the MenuItem
	 * @return pizza
	 */
	public Pizza getPizza() {
		return this.pizza;
	}
	
	/**
	 * Sets the Drink value of the MenuItem by passing in a Drink enumeration
	 * @param drink
	 */
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	
	/**
	 * Returns the Drink value of the MenuItem
	 * @return
	 */
	public Drink getDrink() {
		return this.drink;
	}
	
	/**
	 * Sets the price of the MenuItem by passing in a double
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Returns the price of the MenuItem
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets the description of the MenuItem by passing in a String
	 * @param desc
	 */
	public void setDescription(String desc) {
		this.desc = desc;
	}
	
	/**
	 * Returns the crust of the pizza object 
	 * @return price
	 */
	public String getDescription() {
		return desc;
	}
	
	/**
	 * Sets the crust of the Pizza object
	 * @param crust
	 */
	public void setCrust(Crust crust) {
		this.pizza.setCrust(crust);
	}
	
	/**
	 * Sets the size of the Pizza object
	 * @param size
	 */
	public void setSize(PizzaSize size) {
		this.pizza.setPizzaSize(size);
	}
	
	/**
	 * Returns the contents of the class as a String
	 * @return temp
	 */
	public String toString() {
		String temp;
		DecimalFormat df = new DecimalFormat("0.00");	
			temp = this.desc + "\t";
			temp += "$" + df.format(this.getPrice()) + "\n";
		return temp;
	}
}
