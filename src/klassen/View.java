package klassen;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;


public class View extends Application {
	//Attribute
	TableView <ObservableContactDetails> 			tabelleFürKontakte		= new TableView();
	Control control = new Control();
	
	@Override
	public void start(Stage stage) {
		//Binding
		tabelleFürKontakte.setItems(control.oList);
		
		//Spalten erzeugen
		TableColumn<ObservableContactDetails, String> vornameSpalte = new TableColumn<>("Vorname");
		TableColumn<ObservableContactDetails, String> nachnameSpalte = new TableColumn<>("Nachname");
		TableColumn<ObservableContactDetails, String> adresseSpalte = new TableColumn<>("Adresse");
		TableColumn<ObservableContactDetails, String> telefonnummerSpalte = new TableColumn<>("Telefonnummer");
		TableColumn<ObservableContactDetails, String> mailSpalte = new TableColumn<>("E-Mail");
		
		//Spalten an Tabelle übergeben
		tabelleFürKontakte.getColumns().addAll(vornameSpalte, nachnameSpalte, adresseSpalte, telefonnummerSpalte, mailSpalte);
		
		//Spalten mit Inhalt füllen
		vornameSpalte.setCellValueFactory(e -> e.getValue().vornameProperty());
		nachnameSpalte.setCellValueFactory(e -> e.getValue().nachnameProperty());
		adresseSpalte.setCellValueFactory(e -> e.getValue().adresseProperty());
		telefonnummerSpalte.setCellValueFactory(e -> e.getValue().telefonnummerProperty());
		mailSpalte.setCellValueFactory(e -> e.getValue().mailProperty());
		
		//Pane
		BorderPane pane = new BorderPane();
		pane.setLeft(tabelleFürKontakte);
		
		// SCENE
		Scene scene = new Scene(pane);

		// STAGE
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
