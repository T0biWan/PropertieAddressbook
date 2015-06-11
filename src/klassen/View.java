package klassen;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class View extends Application {
	//Attribute
	Control control = new Control();
	
	@Override
	public void start(Stage stage) {
		
		
		// SCENE
		Scene scene = new Scene(control.tabelleFürKontakte);

		// STAGE
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
