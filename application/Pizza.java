package application;

import java.util.ArrayList;


/**
 * 
 * @author Alex Yang
 *
 */
public class Pizza {
	
	private PizzaSize size;
	private Crust crust;
	private ArrayList<Topping> toppings;
	 
	/**
	 * default constructor
	 */
	public Pizza() {
		this.size = PizzaSize.Small;
		this.crust = Crust.Pan;
		this.toppings = new ArrayList<Topping>();
			this.addTopping(Topping.Cheese);
	}
	
	/**
	 * Initializes only the pizza size
	 * @param size
	 */
	public Pizza(PizzaSize size) {
		this.size = size;
		this.crust = Crust.Pan;
		this.toppings = new ArrayList<Topping>();
			this.addTopping(Topping.Cheese);
	}
	
	/**
	 * Initializes only the pizza size and crust type
	 * @param size
	 * @param crust
	 */
	public Pizza(PizzaSize size, Crust crust) {
		this.size = size;
		this.crust = crust;
		this.toppings = new ArrayList<Topping>();
			this.addTopping(Topping.Cheese);
	}
	
	/**
	 * Initializes all instance variables of the Pizza class
	 * @param crust
	 * @param size
	 * @param toppings
	 */
	public Pizza(PizzaSize size, Crust crust, Topping toppings) {
		this.size = size;
		this.crust = crust;
		this.toppings = new ArrayList<Topping>();
			this.addTopping(Topping.Cheese);
			this.toppings.add(toppings);
	}
	
	/**
	 * Sets the pizza size to the value of the enum parameter
	 * @param size
	 */
	public void setPizzaSize(PizzaSize size) {
		this.size = size;
	}
	
	/**
	 * Sets the crust to the value of the enum parameter
	 * @param crust
	 */
	public void setCrust(Crust crust) {
		this.crust = crust;
	}
	
	/**
	 * Adds the topping to the ArrayList
	 * @param toppings
	 */
	public void addTopping(Topping toppings) {
		this.toppings.add(toppings);
	}
	
	/**
	 * Removes the topping from the ArrayList by the index
	 * @param index
	 */
	public void removeTopping(int index) {
		this.toppings.remove(index);
	}
	
	/**
	 * Gets the size of the pizza
	 * @return pizza size name as a String
	 */
	public PizzaSize getPizzaSize() {
		return this.size;
	}
	
	/**
	 * Gets the crust type on the pizza
	 * @return crust type as a string
	 */
	public Crust getCrust() {
		return this.crust;
	}
	
	/**
	 * Prints all the toppings on the pizza in a nicely formatted list
	 * @return string containing list of toppings
	 */
	public String getToppings() {
		String temp = "";
		
		for(int i = 0; i < this.toppings.size(); i++) {
			int indCh = 0;
			int indXCh = 0;
			
			if (this.toppings.get(i) == Topping.Cheese) {
				indCh = i;
			} else if (this.toppings.get(i) == Topping.ExtraCheese) {
				indXCh = i;
			}
			
			if (this.toppings.get(indCh) == Topping.Cheese && this.toppings.get(indXCh) == Topping.ExtraCheese) {
				this.toppings.remove(indCh);
			}
		}
		
		for(int i = 0; i < this.toppings.size(); i++) {			
			temp += this.toppings.get(i).getDescription() + "\t\t\n" + "\t\t";
		}
		return temp;
	}
	
	/**
	 * Displays the contents of the pizza; size, crust, and topping(s)
	 * @return the pizza as a String
	 */
	public String toString() {
		String temp;
		
		temp  = this.getPizzaSize().getDescription() + " Pizza with ";
		temp += this.getCrust().getDescription() + " Crust, ";
		temp += "Topped with " + this.getToppings();
		
		return temp;
	}
}
