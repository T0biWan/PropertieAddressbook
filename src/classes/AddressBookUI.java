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
import javafx.stage.Stage;
import application.Control;


public class AddressBookUI extends Application{
	
	/* In der TabPane können wir später zwischen unserer List- und TableView switchen */
	private TabPane tab = new TabPane();
	
	/* damit unsere Buttons und Fehlermeldungen unabhängig von den Tabs funktionieren werden diese in außerhalb der TabPane in der BorderPane gesetzt */
	private BorderPane pane = new BorderPane();

	public void start(Stage primaryStage) {

		/* Button für das drucken der Kontakte sowie um neuen Kontakt zu kreieren*/
		Button print = new Button("Drucken");
		Button add = new Button("+");
		
		/* Unsere Fehlermeldungen - wird "leer" initialisiert */
		Label errorText = new Label("");
		errorText.setFont(Font.font("Verdana",15));
		errorText.setTextFill(Color.RED);

		/* Unser Control, der das Befüllen unser Listen und Tabellen übernimmt und die deren Click und Bind Events
		 * Da die Logik von Control übernommen wird müssen auch unsere Buttons und errorText übergeben werden, 
		 * damit wir später darauf zugreifen können.
		 * */
		Control control = new Control(print,add,errorText);
		
		/* Initialisierung der TabPane */
		Tab listviewTab = new Tab("Liste");
		Tab tableviewTab = new Tab("Tabelle");
		/* Control übernimmt hier die Aufgabe des befüllens jedes Tabs */
		listviewTab.setContent(control.erstelleKontaktListe());
		tableviewTab.setContent(control.erstelleKontaktTabelle());
		tab.getTabs().addAll(listviewTab,tableviewTab);
		
		/* die tabs kommen in die Mitte*/
		pane.setCenter(tab);
		
		/* der separate Teil für drucken, neuen Kontakt und Fehlermeldung */
		HBox footer = new HBox(10);
		HBox.setHgrow(errorText, Priority.ALWAYS); 
		errorText.setMaxWidth(Double.MAX_VALUE);
		footer.getChildren().addAll(add,errorText,print);
		
		/* der footer nach unten damit er immer gesehen werden kann */
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
