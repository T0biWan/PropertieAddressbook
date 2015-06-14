package application;

import java.security.SecureRandom;
import java.util.Random;

import exceptions.DetailsNotFoundException;
import exceptions.DuplicateKeyException;
import exceptions.InvalidContactException;
import exceptions.ParameterStringIsEmptyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import klassen.AddressBook;
import klassen.ObservableContactDetails;

public class Control {
	//Attribute
	TableView <ObservableContactDetails> 		tabelleFürKontakte		= new TableView();
	ObservableList <ObservableContactDetails>	kontakte				= FXCollections.observableArrayList();
	BorderPane pane;
	AddressBook book = new AddressBook();

	public Control(BorderPane pane){
		this.pane = pane;
		
		fuelleAdressBook();
		
		Button print = new Button("Drucken");
		print.setOnMouseClicked(e-> System.out.println(book));
		Button add = new Button("+");
		
		HBox footer = new HBox(10,add, print ); 
		
		this.pane.setBottom(footer);
	}

	public void fuelleTabelle() {

		ObservableContactDetails[] kontaktListe = null;
		
		try {
			kontaktListe = book.search("");
		} catch (ParameterStringIsEmptyException | DetailsNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ObservableContactDetails contaktDetails : kontaktListe){
			this.kontakte.add(contaktDetails);
		}

		tabelleFürKontakte.setEditable(true);
		
		tabelleFürKontakte.setItems(this.kontakte);
		
		//Spalten erzeugen
		TableColumn<ObservableContactDetails, String> vornameSpalte = new TableColumn<>("Vorname");
		vornameSpalte.setCellValueFactory(new PropertyValueFactory<ObservableContactDetails, String>("vorname"));
		vornameSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<ObservableContactDetails, String> nachnameSpalte = new TableColumn<>("Nachname");
		nachnameSpalte.setCellValueFactory(new PropertyValueFactory<ObservableContactDetails, String>("nachname"));
		nachnameSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<ObservableContactDetails, String> adresseSpalte = new TableColumn<>("Adresse");
		adresseSpalte.setCellValueFactory(new PropertyValueFactory<ObservableContactDetails, String>("adresse"));
		adresseSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<ObservableContactDetails, String> telefonnummerSpalte = new TableColumn<>("Telefonnummer");
		telefonnummerSpalte.setCellValueFactory(new PropertyValueFactory<ObservableContactDetails, String>("telefonnummer"));
		telefonnummerSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn<ObservableContactDetails, String> mailSpalte = new TableColumn<>("E-Mail");
		mailSpalte.setCellValueFactory(new PropertyValueFactory<ObservableContactDetails, String>("mail"));
		mailSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
		
		//Spalten an Tabelle übergeben
		tabelleFürKontakte.getColumns().addAll(vornameSpalte, nachnameSpalte, adresseSpalte, telefonnummerSpalte, mailSpalte);
		this.pane.setCenter(tabelleFürKontakte);
	}
	

	private void fuelleAdressBook() {
		for(int i = 0; i < 5; i++){
			ObservableContactDetails a = new ObservableContactDetails(
					csRandomAlphaNumericString(5), 
					csRandomAlphaNumericString(6),
					csRandomAlphaNumericString(9),
					csRandomAlphaNumericString(12),
					csRandomAlphaNumericString(25)
					);	
			try {
				book.addDetails(a);
			} catch (DuplicateKeyException | InvalidContactException
					| ParameterStringIsEmptyException e) {
				this.generateErrorModal(e.getMessage());
			}
		}
	}

	private static char[] VALID_CHARACTERS =
		    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();

	// cs = cryptographically secure
	public static String csRandomAlphaNumericString(int numChars) {
	    SecureRandom srand = new SecureRandom();
	    Random rand = new Random();
	    char[] buff = new char[numChars];

	    for (int i = 0; i < numChars; ++i) {
	      // reseed rand once you've used up all available entropy bits
	      if ((i % 10) == 0) {
	          rand.setSeed(srand.nextLong()); // 64 bits of random!
	      }
	      buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
	    }
	    return new String(buff);
	}
	
	private void generateErrorModal(String message) {
		System.out.println(message);
	}
}
