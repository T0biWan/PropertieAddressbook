package application;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import classes.AddressBook;
import classes.ObservableContactDetails;
import exceptions.DuplicateKeyException;
import exceptions.InvalidContactException;
import exceptions.KeyIsNotInUseException;
import exceptions.ParameterStringIsEmptyException;

public class ListCellFactory extends ListCell<ObservableContactDetails>{

	/* Variablen die uns im Konstruktor übergeben werden */
	private AddressBook aBook;
	private ListView<ObservableContactDetails> liste;
	private Label errorText;
	
	/* Unsere Felder, die in unserem Formular angezeigt werden */
	private TextField nameField = new TextField(), lastnameField = new TextField(), phoneField = new TextField(), emailField = new TextField(), addressField = new TextField();
	/* für die einfachere Arbeit speichern wir Unsere Felder der Map */
	private Map<String, TextField> fields = new HashMap<>();
	/* unsere FomularBox in der die Daten geändert werden können */
	private VBox contactBox = new VBox();
	/* die Kontaktdaten bevor sie geändert werden - wird bei Abbrüchen gebraucht */
	private ObservableContactDetails aktuellerKontakt; 
	
	public ListCellFactory(AddressBook aBook, ListView<ObservableContactDetails> liste, Label errorText) {
		super();
		this.aBook = aBook;
		this.liste = liste;
		this.errorText = errorText;
		
		/* befülle unsere Map mit den Feldern und als Schlüssel übergeben wir die Namen der Felder 
		 * wie sie dann dem User gezeigt werden sollen*/
		fields.put("Vorname",nameField);
		fields.put("Nachname",lastnameField);
		fields.put("Telefon",phoneField);
		fields.put("E-Mail",emailField);
		fields.put("Addresse",addressField);
		
		// zeigt das Formular nur, wenn das Feld editable ist
		this.contactBox.visibleProperty().bind(this.editingProperty());
	}
	
	/**
	 * Hier überschreiben wir die Methoden der beerbten Klasse ListCell
	 * und greifen direkt in das Verhalten ein wenn ein Element geupdatetet wird (updateItem),
	 * editiert wird (startEdit) oder gecancelt (cancelEdit).
	 * 
	 * */
	
	@Override
	protected void updateItem(ObservableContactDetails item, boolean empty) {
		/* ruft die übergeordnete Methode auf */
		super.updateItem(item, empty);
		
		updateViewMode();
	}

	@Override
	public void startEdit(){
		/* ruft die übergeordnete Methode auf */
		super.startEdit();
		
		updateViewMode();
	}
	
	@Override
	public void cancelEdit() {
		/* ruft die übergeordnete Methode auf */
		super.cancelEdit();
		/* unser Formular wird ausgeblendet und es werden die Daten vor der Veränderung angezeigt */
		setGraphic(null);
		setText(aktuellerKontakt.getNachname() + ", " + aktuellerKontakt.getVorname());
		
		// TODO hier sollten noch die Felder des Formulars zurückgesetzt werden
	}
	
	protected void updateViewMode(){
		/* wir nehmen alles raus */
		setText(null);
		setGraphic(null);
		
		/* und setzen es neu */
		
		// wenn der User im Editiermodus ist (doppelt geklickt)
		if(isEditing()){
			if(getItem() != null){
				// hier werden die "alten" Daten gespeichert für Fälle, in denen wir auf die alten Werte zurückgreifen müssen
				this.aktuellerKontakt = new ObservableContactDetails(getItem());

				// wir übergeben unseren Feldern unsere Properties und binden sie bidirektional, 
				// damit auf eingehende und herausgehende Änderungen reagiert werden kann
				nameField.textProperty().bindBidirectional(getItem().vornameProperty());
				lastnameField.textProperty().bindBidirectional(getItem().nachnameProperty());
				phoneField.textProperty().bindBidirectional(getItem().telefonnummerProperty());
				emailField.textProperty().bindBidirectional(getItem().mailProperty());
				addressField.textProperty().bindBidirectional(getItem().adresseProperty());
				
				// und erstellen unser Formular
				setGraphic(this.createContactBox());
			}
		}else if(getItem() != null){
			/* wir geben in der Liste nur den Nachnamen und Vornamen an */
           setText(getItem().getNachname() + ", " + getItem().getVorname());
		}
	}
	
	private VBox createContactBox(){
		
		// Überschrift mit Namen und Nachnamen der sich ändert wenn in den Felder diese Werte ändert - total cool
		Text header = new Text();
		header.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		
		// muss total umständlich geconcatet werden :S
		header.textProperty().bind(
				new SimpleStringProperty("Detail von ")
				.concat(getItem().vornameProperty()
						.concat(" ")
						.concat(getItem().nachnameProperty())
						)
				);
		// Buttons mit denen der Editirprozess beendet werden kann
		Button save = new Button("Speichern");
		Button delete = new Button("Löschen");
		Button cancel = new Button("Abbrechen");
		
		// commitEdit und cancelEdit signalisieren ListCellFactory das der 
		// Editirprozess beenden wird und rufen zum Schluss Methoden auf
		save.setOnAction(e -> commitEdit(speicherKontakt()));
		delete.setOnMouseClicked(e -> commitEdit(loescheKontakt()));
		cancel.setOnMouseClicked(e -> cancelEdit());
		
		
		//  Wir erstellen unser Formular
		VBox contacts = new VBox();
		contacts.setPadding(new Insets(10));
		
		// Felder mit Label
		for(String label : fields.keySet()){
			contacts.getChildren().add(this.createRowBox(label,fields.get(label)));
		}
		
		HBox confirm = new HBox(10, save, delete,cancel);
		
		this.contactBox = new VBox(header,contacts,confirm);
		this.contactBox.setPadding(new Insets(10));
		
		return this.contactBox;
	}
	
	/*
	 * speichert Änderung des Kontaktes in Addressbook
	 * in der ListView muss nichts geändert werden Dank binding :) 
	 * */
	private ObservableContactDetails speicherKontakt() {
		
		try {
			String key = aBook.generateKey(aktuellerKontakt);
			this.aBook.changeDetails(key, getItem());
			return getItem();
		} catch (DuplicateKeyException | InvalidContactException | KeyIsNotInUseException | ParameterStringIsEmptyException e) {
			generateErrorModal(e.getMessage());
			
		}
		return aktuellerKontakt;
	}
	
	/*
	 * löscht Kontakt aus AddressBook und der ListView 
	 * 
	 * */
	private ObservableContactDetails loescheKontakt() {
		try {
			String key = aBook.generateKey(aktuellerKontakt);
			aBook.removeDetails(key);
			this.liste.getItems().remove(getItem());
		} catch (ParameterStringIsEmptyException | KeyIsNotInUseException e) {
			generateErrorModal(e.getMessage());
		}
		
		return null;
	}

	/* unser Label Felder creater */
	private HBox createRowBox(String label, TextField rowField){
		
		HBox rowBox = new HBox();
		rowBox.setPadding(new Insets(10,10,10,0));
		
		Label rowLabel = new Label(label);
		rowLabel.setMinWidth(100);
		
		rowField.setMinWidth(400);
		rowBox.getChildren().addAll(rowLabel,rowField);
		
		return rowBox;
	}
	
	private void generateErrorModal(String message) {
		this.errorText.setText(message);
	}
}
