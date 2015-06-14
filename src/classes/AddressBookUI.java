package classes;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import application.Control;


public class AddressBookUI extends Application{

	private TabPane tab = new TabPane();
	private BorderPane pane = new BorderPane();

	public void start(Stage primaryStage) {
		
		

		Button print = new Button("Drucken");
		Button add = new Button("+");
		Label errorText = new Label("");
		errorText.setFont(Font.font("Verdana",15));
		errorText.setTextFill(Color.RED);

		Control control = new Control(print,add,errorText);
		
		Tab listviewTab = new Tab("Liste");
		Tab tableviewTab = new Tab("Tabelle");
		listviewTab.setContent(control.erstelleKontaktListe());
		tableviewTab.setContent(control.erstelleKontaktTabelle());
		
		tab.getTabs().addAll(listviewTab,tableviewTab);
		
		pane.setCenter(tab);
		
		HBox footer = new HBox(10);
		HBox.setHgrow(errorText, Priority.ALWAYS); 
		errorText.setMaxWidth(Double.MAX_VALUE);
		footer.getChildren().addAll(add,errorText,print);
		
		pane.setBottom(footer);

		Scene scene = new Scene(pane, 650, 500);

		primaryStage.setTitle("Address Book");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
