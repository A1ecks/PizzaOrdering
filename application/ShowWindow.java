package application;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * @author ChristensenKyle
 * @author Alex Yang
 *
 */
public class ShowWindow extends Application {
	private Menu menu;
	private AudioInputStream audioIS;
	private AudioFormat audioFmt;
	private File file;
	private Clip clip;
	private DataLine.Info dlInfo;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			menu = new Menu();
			Scene scene1 = new Scene(menu.CustomerForm(), 400, 130);
			Scene scene2 = new Scene(menu.MainMenu(), 1170, 750);

			
			menu.getSwitch(2).setOnAction( e -> {	
				primaryStage.setScene(new Scene(menu.OrderForm(), 400, 480));
				menu.getSwitch(4).setOnAction(e2 -> {
					primaryStage.setScene(new Scene(menu.Receipt(), 400, 480));
					try {
						file = new File("ow.wav");
						audioIS = AudioSystem.getAudioInputStream(file);
						audioFmt = audioIS.getFormat();
						dlInfo = new Info(Clip.class, audioFmt);
						clip = (Clip)AudioSystem.getLine(dlInfo);
						clip.open(audioIS);
						clip.start();
					} catch (Exception error) { System.out.println("Exception thrown"); }
				});
				menu.getSwitch(3).setOnAction(e1 -> {
					primaryStage.setScene(scene2);
				});
			});
			menu.getSwitch(1).setOnAction( e -> {	
				menu.UpdateCustomer();
				primaryStage.setScene(scene2);
			});
			menu.getSwitch(0).setOnAction(e -> {
				primaryStage.setScene(scene1);
			});
			
			primaryStage.setTitle("Pizza Order");
			primaryStage.setScene(scene1);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
 