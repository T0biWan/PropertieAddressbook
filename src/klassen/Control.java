package klassen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Control {
	//Attribute
	PropertieAddressBook							pAddressBook 			= new PropertieAddressBook();
	RandomStringGenerator							rStringGen 				= new RandomStringGenerator();
	ObservableList <ObservableContactDetails>		oList					= FXCollections.observableArrayList();
	
	
	//Konstruktor
	public Control () {
		//AddressBook mit Kontakten füllen
		fuellePropertieAddressBook(25);
		
		//Inhalt von AddressBook an ObservableList übergeben
		fuelleListeMitAddressBookInhalt();
	}
	
	
	//Methoden
	public void fuellePropertieAddressBook (int anzahlKontakte) {
		for(int i = 0; i < anzahlKontakte; i++) {
			ObservableContactDetails	ocd = new ObservableContactDetails(rStringGen.generateRandomString(5), rStringGen.generateRandomString(5), rStringGen.generateRandomString(5), rStringGen.generateRandomString(5), rStringGen.generateRandomString(5));
			pAddressBook.addDetails(ocd);
		}
	}
	
	public void fuelleListeMitAddressBookInhalt() {
		ObservableContactDetails[] kontakte = pAddressBook.search("");
		for(ObservableContactDetails index : kontakte) {
			oList.add(index);
		}
	}
	
}
