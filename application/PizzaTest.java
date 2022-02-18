package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Alex Yang
 *
 */
class PizzaTest {
	
	/**
	 * Tests enum methods
	 */
	@Nested
	class testEnums {
		@Test 
		void testTopping() {
			Topping test = Topping.Pepperoni;
			assertEquals("Pepperoni", test.getDescription());
		}
		
		@Test
		void testPizzaSizeDiameter() {
			PizzaSize test = PizzaSize.Small;
			assertEquals(10, test.getDiameter());
		}
		
		@Test
		void testPizzaSizeDescription() {
			PizzaSize test = PizzaSize.Personal;
			assertEquals("Personal", test.getDescription());
		}
		
		@Test
		void testCrust() {
			Crust test = Crust.Deep;
			assertEquals("Deep", test.getDescription());
		}
	}
	
	
	/**
	 * Tests all constructors
	 */
	@Nested
	class testConstructors {
		
		@Test
		void Default_Constructor() {
			Pizza test = new Pizza();
			assertEquals("Small Pizza with Pan Crust, Topped with Cheese" + "\t\t\n" + "\t\t", test.toString());
		}
		
		@Test
		void Size_Only() {
			Pizza test = new Pizza(PizzaSize.Large);
			assertEquals("Large Pizza with Pan Crust, Topped with Cheese" + "\t\t\n" + "\t\t", test.toString());
		}
		
		@Test
		void Size_and_Crust() {
			Pizza test = new Pizza(PizzaSize.NYStyle, Crust.Pan);
			assertEquals("NY Style Pizza with Pan Crust, Topped with Cheese" + "\t\t\n" + "\t\t", test.toString());
		}
		
		@Test
		void Completed_Pizza() {
			Pizza test = new Pizza(PizzaSize.Medium, Crust.Stuffed, Topping.Pepperoni);
			assertEquals("Medium Pizza with Stuffed Crust, Topped with Cheese" + "\t\t\n" + "\t\t" + "Pepperoni" + "\t\t\n" + "\t\t", test.toString());
		}
	}
}
