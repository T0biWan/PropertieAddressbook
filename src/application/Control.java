package application;

import java.security.SecureRandom;
import java.util.Random;

import classes.AddressBook;
import classes.ObservableContactDetails;
import exceptions.DetailsNotFoundException;
import exceptions.DuplicateKeyException;
import exceptions.InvalidContactException;
import exceptions.ParameterStringIsEmptyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class Control {
	
	private BorderPane pane;
	private StackPane left = new StackPane();
	private StackPane center = new StackPane();
	
	private AddressBook aBook = new AddressBook();
	
	public Control(BorderPane pane) {
		this.pane = pane;
		
		this.fuelleAddressBook();
		
		this.erstelleKontaktListe();
		this.erstelleKontakt();
	}
	
	private void erstelleKontaktListe() {
		
		ListView<ObservableContactDetails> listView = new ListView<>();
		
		listView.setEditable(true);
		
		ObservableList<ObservableContactDetails> data = FXCollections.observableArrayList();
		
		ObservableContactDetails[] contactDetails = null;
		
		try {
			contactDetails = aBook.search("");
		} catch (DetailsNotFoundException | ParameterStringIsEmptyException e1) {
			System.out.println(e1.getMessage());
		}
		
		for(ObservableContactDetails contact : contactDetails){
			 data.add(contact);
		}
		
		listView.setItems(data);
		
		listView.setCellFactory(c -> new ListCellFactory(center,aBook,listView));

		left.getChildren().add(listView);
		
		pane.setLeft(left);	
	}

	private void erstelleKontakt() {
		/*center.getChildren().add(new Text("Kein Kontakt ausgewählt"));
		pane.setCenter(center);*/
	}
	
	private void fuelleAddressBook() {
		
		for(int i = 0; i < 100; i++){
			ObservableContactDetails a = new ObservableContactDetails(
					i + " " + csRandomAlphaNumericString(5), 
					csRandomAlphaNumericString(6),
					csRandomAlphaNumericString(9),
					csRandomAlphaNumericString(12),
					csRandomAlphaNumericString(25)
					);	
			try {
				aBook.addDetails(a);
			} catch (DuplicateKeyException | InvalidContactException
					| ParameterStringIsEmptyException e) {
				System.out.println(e.getMessage());
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

}