package klassen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Control {
	//Attribute
	TableView <ObservableContactDetails> 		tabelleFürKontakte		= new TableView();
	ObservableList <ObservableContactDetails>	kontakte				= FXCollections.observableArrayList();
	
	//konstrukte
	public Control () {
		//Binding
		tabelleFürKontakte.setItems(kontakte);
		
		//Spalten erzeugen
		TableColumn<ObservableContactDetails, String> vornameSpalte = new TableColumn<>("Vorname");
		TableColumn<ObservableContactDetails, String> nachnameSpalte = new TableColumn<>("Nachname");
		TableColumn<ObservableContactDetails, String> adresseSpalte = new TableColumn<>("Adresse");
		TableColumn<ObservableContactDetails, Number> telefonnummerSpalte = new TableColumn<>("Telefonnummer");
		TableColumn<ObservableContactDetails, String> mailSpalte = new TableColumn<>("E-Mail");
		
		//Spalten an Tabelle übergeben
		tabelleFürKontakte.getColumns().addAll(vornameSpalte, nachnameSpalte, adresseSpalte, telefonnummerSpalte, mailSpalte);
	}
	
	
}
