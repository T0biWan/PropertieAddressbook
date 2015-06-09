package classes;

import java.security.SecureRandom;
import java.util.Random;

import exceptions.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddressBookUI extends Application{

	TableView<ObservableContactDetails> tabelleView = new TableView<>();
	AddressBook buch = new AddressBook();
	
	//DefaultNamen, damit wir Zufallskontakte entwickeln können
	private static char[] VALID_CHARACTERS =
			    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();
	
	public AddressBookUI() {
		// wir füllen unser AdressBuch
		fuelleBuch();
	}
	
	public AddressBookUI(AddressBook buch) {
		// wir füllen unser AdressBuch
		this.buch = buch;
	}

	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Cooles Listen");
 
		this.erstelleTabelle();

		// wir generieren unsere FX Fenster
		Group group = new Group();
		
		group.getChildren().add(new VBox(hLabel("Tabelle"),tabelleView));
		
		Scene scene = new Scene(group, 1200, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Label hLabel(String str) {
		Label label = new Label(str);
	    label.setFont(new Font("Arial", 30));
	    
		return label;
	}
	
	/**
	 * fülle das Addressbook mit Defaultwerten
	 */
	private void fuelleBuch(){

		for(int i = 0; i < 500; i++){

			ObservableContactDetails person = new ObservableContactDetails(
					this.csRandomAlphaNumericString(5), 
					this.csRandomAlphaNumericString(6),
					this.csRandomAlphaNumericString(9),
					this.csRandomAlphaNumericString(12),
					this.csRandomAlphaNumericString(25)
					);	

			try {
				buch.addDetails(person);
			} catch (DuplicateKeyException | InvalidContactException| ParameterStringIsEmptyException e) {
				 e.getMessage();
			}

		}
	}

	/**
	 * Die TableView ist Verwandt mit dem ListView und gehört zu den etwas komplexeren Komponenten. Dank JavaFX wird werden uns 
	 * Eigenschaften wie z.B. Sortierung der der Spalten, Layout etc. von Java abgenommen. Auch bei der TableView sollte 
	 * direkt mit den Objekten gearbeitet werden. Dazu muss auch hier wieder über setCellValueFactory angegeben werden, welche Attribute
	 * unseres Objektes angezeigt werden. Auch hier werden die Daten erst in einer observableArrayList gespeicht und dann der TableView übergeben.
	 */
	
	private void erstelleTabelle() {
		
		try {
			// als erstes holen wir alle Kontakte
			ObservableContactDetails[] personen = buch.search("");
			// wir definieren ein FXCollections.observableArrayList, welches die darzustellenden Daten enthält
			ObservableList<ObservableContactDetails> namen = FXCollections.observableArrayList();
			for(ObservableContactDetails person : personen){
				namen.add(person);
			}
			// wir fügen unsere Daten der observableArrayList in unsere Tabelle hinzu
			tabelleView.setItems(namen);
			
		} catch (ParameterStringIsEmptyException | DetailsNotFoundException e) { e.getMessage();	}

		
		tabelleView.setEditable(true);

		TableColumn<ObservableContactDetails, String> vorname =  new TableColumn<>("Vorname");
		vorname.setCellValueFactory(e -> e.getValue().vornameProperty()); 
		vorname.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<ObservableContactDetails, String> nachname =  new TableColumn<>("Nachname");
		nachname.setCellValueFactory(e -> e.getValue().nachnameProperty());
		nachname.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<ObservableContactDetails, String> telefon =  new TableColumn<>("Telefon");
		telefon.setCellValueFactory(e -> e.getValue().telefonnummerProperty());
		telefon.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<ObservableContactDetails, String> email =  new TableColumn<>("E-Mail");
		email.setCellValueFactory(e -> e.getValue().mailProperty());
		email.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<ObservableContactDetails, String> adresse =  new TableColumn<>("Adresse");
		adresse.setCellValueFactory(e -> e.getValue().adresseProperty());
		adresse.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
        tabelleView.getColumns().addAll(
        		vorname, 
        		nachname, 
        		telefon,
        		email,
        		adresse
        		);

	}


	public static void main(String[] args) {
		launch(args);
	}
	
	private String csRandomAlphaNumericString(int numChars) {
		// bei der Generierung von Zufallszahlen gibt es wie bei Pflanzen einen Samen, der zu Nachkommen führt. 
		// Aus diesem Startwert ermittelt der Zufallszahlengenerator anschließend die folgenden Zahlen durch lineare Kongruenzen.
		// Dadurch sind die Zahlen nicht wirklich zufällig, sondern gehorchen einem mathematischen Verfahren. 
		// Kryptografisch bessere Zufallszahlen liefert die Klasse java.security.SecureRandom, die eine Unterklasse von Random ist.
		SecureRandom srand = new SecureRandom();
		
	    Random rand = new Random();
	    // wir intantiieren uns Array vom Typ char mit der länge der Der Zeichen die unser Default-Wort haben soll
	    char[] buff = new char[numChars];

	    // nun durchlaufen wir eine for-Schleife für jedes Zeichen 
	    for (int i = 0; i < numChars; ++i) {
	      // damit auch bei Zeichenketten länger als 10 Zeichen der Zufall gewährleistet wird setzen wir einen neuen Samen mit Hilfe unseres 
	      // SecureRandom Super Algorithmus....
	      if ((i % 10) == 0) {
	    	  // and here is where the magic happens....
	          rand.setSeed(srand.nextLong()); // 64 bits of random!
	      }
	      // wir kreieren in unseren Array buff ein zufälliges Zeichen mit Hilfe der von Random und unseren Zeichen Array VALID_CHARACTERS.
	      buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
	    }
	    return new String(buff);
	}
}
