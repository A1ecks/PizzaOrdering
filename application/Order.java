package application;

/**
 * 
 * @author ChristensenKyle
 * @author Alex Yang
 * 
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
 
	public class Order {
	private ArrayList<MenuItem> items;
	private double cash;
	private DecimalFormat df;
	
	/**
	 * Default constructor
	 */
	public Order() {
		df = new DecimalFormat("0.00");
		items = new ArrayList<MenuItem>();
	}
	
	/**
	 * Calculates how much cash the order is and stores the information of what was ordered.
	 * @return cash
	 */
	public String getTotal() {
		this.cash = 0;
		for (int i = 0; i < items.size(); i++) {
			this.cash += (items.get(i).getPrice() * 1.03);
		}
		return df.format(cash);
	}
	
	/**
	 * Returns the items purchased along with the total cash
	 */
	public String toString() {
		this.cash = 0;
		String result = "";
		for (int i = 0; i < items.size(); i++) {
			result += items.get(i).toString();
			this.cash += (items.get(i).getPrice() * 1.03);
		}
		return result + "Total: \t\t$"+ df.format(cash) + "\n";
	}
	
	/**
	 * Adds a menu item to the list of things ordered.
	 * @param menuItem
	 */
	public void add(MenuItem menuItem) {
		this.items.add(menuItem);
	}

	/**
	 * @return the items
	 */
	public ArrayList<MenuItem> getItems() {
		return this.items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<MenuItem> items) {
		this.items = items;
	}

}
