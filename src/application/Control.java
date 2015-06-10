package application;

import java.security.SecureRandom;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import classes.AddressBook;
import classes.ObservableContactDetails;
import exceptions.DetailsNotFoundException;
import exceptions.DuplicateKeyException;
import exceptions.InvalidContactException;
import exceptions.ParameterStringIsEmptyException;

public class Control {
	
	private BorderPane pane;
	private StackPane center = new StackPane();
	private ListView<ObservableContactDetails> liste = new ListView<>();
	private AddressBook buch = new AddressBook();
	//DefaultNamen, damit wir Zufallskontakte entwickeln können
	private static char[] VALID_CHARACTERS =
			    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();
	
	
	public Control(BorderPane pane) {
		this.pane = pane;
		center.getChildren().add(new Text("Kein Kontakt ausgewählt."));
		pane.setCenter(center);
		fuelleBuch();
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
	
	public void erstelleListe() {
		try {
			// als erstes holen wir alle Kontakte
			ObservableContactDetails[] personen = buch.search("");
			// wir definieren ein FXCollections.observableArrayList, welches die darzustellenden Daten enthält
			ObservableList<ObservableContactDetails> namen = FXCollections.observableArrayList();
			for(ObservableContactDetails person : personen){
				namen.add(person);
			}
			// wir fügen unsere Daten der observableArrayList in unsere Liste hinzu
			liste.setItems(namen);
			
		} catch (ParameterStringIsEmptyException | DetailsNotFoundException e) { e.getMessage();}
		
		liste.setCellFactory(c -> new ListCellFactory(center));
		
		liste.setEditable(true);
		
		pane.setLeft(new VBox(10,liste));

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
