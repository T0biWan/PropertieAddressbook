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
	
	private Map<String, TextField> fields = new HashMap<>();
	private TextField nameField = new TextField(), lastnameField = new TextField(), phoneField = new TextField(), emailField = new TextField(), addressField = new TextField();
	
	private AddressBook aBook;
	private ListView<ObservableContactDetails> liste;
	private ObservableContactDetails aktuellerKontakt; 
	private VBox contactBox = new VBox();
	private Label errorText;
	
	public ListCellFactory(AddressBook aBook, ListView<ObservableContactDetails> liste, Label errorText) {
		super();
		this.aBook = aBook;
		this.liste = liste;
		
		fields.put("Vorname",nameField);
		fields.put("Nachname",lastnameField);
		fields.put("Telefon",phoneField);
		fields.put("E-Mail",emailField);
		fields.put("Addresse",addressField);
		
		this.contactBox.visibleProperty().bind(this.editingProperty());
		
		this.errorText = errorText;
	}
	
	@Override
	protected void updateItem(ObservableContactDetails item, boolean empty) {
		super.updateItem(item, empty);
		updateViewMode();
	}

	@Override
	public void startEdit(){
		super.startEdit();
		updateViewMode();
	}
	
	protected void updateViewMode(){
		
		setText(null);
		setGraphic(null);
		
		if(isEditing()){

			Text header = new Text();
			header.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

			if(getItem() != null){

				this.aktuellerKontakt = new ObservableContactDetails(getItem());

				header.textProperty().bind(
						new SimpleStringProperty("Detail von ")
						.concat(getItem().vornameProperty()
								.concat(" ")
								.concat(getItem().nachnameProperty())
								)
						);
				
				nameField.textProperty().bindBidirectional(getItem().vornameProperty());
				lastnameField.textProperty().bindBidirectional(getItem().nachnameProperty());
				phoneField.textProperty().bindBidirectional(getItem().telefonnummerProperty());
				emailField.textProperty().bindBidirectional(getItem().mailProperty());
				addressField.textProperty().bindBidirectional(getItem().adresseProperty());
			}

			Button save = new Button("Speichern");
			Button delete = new Button("Löschen");
			Button cancel = new Button("Abbrechen");
			
			save.setOnAction(e -> commitEdit(speicherKontakt()));
			delete.setOnMouseClicked(e -> commitEdit(loescheKontakt()));
			cancel.setOnMouseClicked(e -> commitEdit(cancelModifikation()));
			
			VBox contacts = new VBox();
			contacts.setPadding(new Insets(10));
			
			for(String label : fields.keySet()){
				contacts.getChildren().add(this.createRowBox(label,fields.get(label)));
			}
			
			HBox confirm = new HBox(10, save, delete,cancel);
			
			this.contactBox = new VBox(header,contacts,confirm);
			this.contactBox.setPadding(new Insets(10));
			
			setGraphic(this.contactBox);
		}else if(getItem() != null){
           setText(getItem().getNachname() + ", " + getItem().getVorname());
		}
	}
	
	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setGraphic(null);
		setText(aktuellerKontakt.getNachname() + ", " + aktuellerKontakt.getVorname()); //setzt den AnzeigeText wieder
	}
	
	private ObservableContactDetails cancelModifikation() {
		return aktuellerKontakt;
	}

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
