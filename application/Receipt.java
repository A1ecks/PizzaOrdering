package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * @author ChristensenKyle
 *
 */
public class Receipt extends BorderPane{
	
	private Pane window;
	private Text receipt;
	private Order order;
	private Customer customer;
	private VBox box;

	public Receipt(Customer customer) {
		this.customer = customer;
	}

	/**
	 * returns the receipt
	 */
	public String toString() {
		if (customer.isDeliver()) {
			return customer.toString() + "\n" + order.toString() + "Order will be ready in 20 minutes.";
		}
		else {
			return customer.toString() + "\n" + order.toString() + "Predicted arrival time is 30 minutes from now.";
		}
	}
	
	/**
	 * processes the GUI for the receipt
	 * @param order
	 */
	public Pane processReceipt(Order order) {
		receipt = new Text(this.toString());
		box = new VBox(receipt);
		
		window = new Pane(receipt, box);
		return window;
	}
}
 