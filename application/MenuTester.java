package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuTester extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Menu root = new Menu();
//		primaryStage = root.BuildWindow();
//		Scene scene = new Scene(root, 1170, 750);
//		primaryStage.setTitle("Pizza Order");
//		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
 
