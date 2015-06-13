package classes;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import application.Control;


public class AddressBookUI extends Application{

	private BorderPane pane = new BorderPane();
	private BorderPane pane2 = new BorderPane();
	private Stage primaryStage;

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
	
		Scene scene = new Scene(pane, 800, 600);
		Button b = new Button("flip this shit");
		b.setOnMouseClicked((MouseEvent e) -> handleFlip(e,scene));
		pane.setCenter(b);
		
		pane.setTop(new VBox(hLabel("Liste")));


		
		
		
		Scene scene2 = new Scene(pane2, 800, 600);
		Button b2 = new Button("put it back");
		b2.setOnMouseClicked((MouseEvent e) -> handleFlip(e,scene2));
		pane2.setCenter(b2);
		
		pane2.setTop(new VBox(hLabel("Liste2")));
		
		
		
		
		primaryStage.setTitle("Address Book");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void handleFlip(MouseEvent e, Scene scene){
		this.primaryStage.setScene(scene);
	}
	
	private Pane createView(){
		return null;
	}

	private Label hLabel(String str) {
		Label label = new Label(str);
	    label.setFont(new Font("Arial", 30));
	    
		return label;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
}
