package application;

/**
 * Enumeration containing all possible pizza sizes for the Pizza class
 */
public enum PizzaSize {
	Personal(8), Small(10), Medium(12), Large(14), ExtraLarge(16), NYStyle(18);
	
	private final int DIAMETER;
	
	private PizzaSize(int diameter) {
		this.DIAMETER = diameter;
		}
	
	/** 
	 * @return the diameter of the pizza as an int
	 */
	public int getDiameter(){
		return this.DIAMETER;
	}
	
	/**
	 * @return the pizza size by name as a String
	 */
	public String getDescription() {
		switch (this.DIAMETER) {
			case 8:
				return "Personal";
			case 10:
				return "Small";
			case 12:
				return "Medium";
			case 14:
				return "Large";
			case 16:
				return "Extra Large";
			case 18:
				return "NY Style";
			default:
				return null;
		}
	}
}
