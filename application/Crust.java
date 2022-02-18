package application;

/**
 * 
 * @author Alex Yang
 *
 * Enumeration containing all possible crust types for the Pizza class
 */
public enum Crust {
	Pan("Pan"), HandTossed("Hand-Tossed"), Deep("Deep"), Stuffed("Stuffed");
	
	private final String DESCRIPTION;
	
	private Crust(String desc) {
		this.DESCRIPTION = desc;
	}
	
	/**
	 * @return crust type as String
	 */
	public String getDescription() {
		return this.DESCRIPTION;
	} 
}
