package application;


/**
 * 
 * @author ChristensenKyle
 * @author Alex Yang
 *
 */
public class Customer {
	
	private String name, address;
	private boolean deliver;
	
	/**
	 * Constructor initializes instance variables with default values
	 */
	public Customer() {
		this.name = "John Doe";
		this.address = "4231 Lipsum St Ipsum, WI 75986";
	}
	
	/**
	 * Method passes bool value and assigns it to bolean deliver
	 * @param bool
	 */
	public void setDeliver(boolean bool) {
		this.deliver = bool;
	}
	
	/**
	 * Method passes name and assigns it to this.name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Method passes in address and assigns it to this.address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = "  " + address;
	}
	
	/**
	 * Returns the order type; Delivery or Takeout
	 * @return String
	 */
	public String getDeliver() {
		if(deliver) {
			return "  Delivery for " + name;
		} else {
			return "  Takeout for " + name;
		}
	}
	
	/** 
	 * Returns whether or not customer wants to have the pizza delivered. if they do, it returns true, otherwise it is false
	 * @return deliver
	 */
	public boolean isDeliver() {
		return deliver;
	}
	
	/**
	 * Method returns instance variable name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Method returns instance variable address
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * @return output the name and address
	 */
	public String toString() {
		String output;
		
		output = this.name + "\n";
		output += this.address + "\n";
		
		return output;
	}

}
