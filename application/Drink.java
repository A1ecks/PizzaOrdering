package application;

public enum Drink {
	CocaCola("Coca Cola"), DietCoke("Diet Coke"), Sprite("Sprite"), DrPepper("Dr. Pepper"), Fanta("Fanta"), IcedTea("Iced Tea"), SweetTea("Sweet Tea"), Water("Water"), AppleJuice("Apple Juice"), OrangeJuice("Orange Juice"), Lemonade("Lemonade"), Milk("Milk");
	
	private final String DESCRIPTION;
	
	private Drink(String desc) {
		this.DESCRIPTION = desc;
	}
	
	/**
	 * @return the Drink description as a String
	 */
	public String getDescription() {
		return this.DESCRIPTION;
	}
} 