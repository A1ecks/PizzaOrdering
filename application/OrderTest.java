package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderTest extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			Menu order = new Menu();
				order.getOrder().add(new MenuItem(8.99, new Pizza(PizzaSize.Large), "Cheese Pizza"));
				order.getOrder().add(new MenuItem(5.99, new Pizza(PizzaSize.Medium), "Somthin Pizza"));
				order.getOrder().add(new MenuItem(9.99, new Pizza(PizzaSize.NYStyle), "Epic Pizza"));
				order.getOrder().add(new MenuItem(8.99, new Pizza(PizzaSize.Large), "Cheese Pizza"));
				order.getOrder().add(new MenuItem(5.99, new Pizza(PizzaSize.Medium), "Somthin Pizza"));
				order.getOrder().add(new MenuItem(9.99, new Pizza(PizzaSize.NYStyle), "Epic Pizza"));
				order.getOrder().add(new MenuItem(8.99, new Pizza(PizzaSize.Large), "Cheese Pizza"));
				order.getOrder().add(new MenuItem(5.99, new Pizza(PizzaSize.Medium), "Somthin Pizza"));
				order.getOrder().add(new MenuItem(9.99, new Pizza(PizzaSize.NYStyle), "Epic Pizza"));
				order.getOrder().add(new MenuItem(8.99, new Pizza(PizzaSize.Large), "Cheese Pizza"));
				order.getOrder().add(new MenuItem(5.99, new Pizza(PizzaSize.Medium), "Somthin Pizza"));
				order.getOrder().add(new MenuItem(9.99, new Pizza(PizzaSize.NYStyle), "Epic Pizza"));
			String string = order.toString();
			System.out.println(string);
			
			Scene scene = new Scene(order.OrderForm(), 400, 430);
			primaryStage.setTitle("Pizza Order");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
 