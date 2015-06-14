package klassen;
	
import application.Control;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;



public class View extends Application {
	
	@Override
	public void start(Stage stage) {
		
		BorderPane pane = new BorderPane();
		
		Control control = new Control(pane);

		control.fuelleTabelle();
		
		Scene scene = new Scene(pane, 900, 500);
		
		stage.setTitle("AddressBook");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
