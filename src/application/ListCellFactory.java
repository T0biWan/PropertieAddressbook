package application;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import classes.ObservableContactDetails;

public class ListCellFactory extends ListCell<ObservableContactDetails>{
	
	private Map<String, TextField> fields = new HashMap<>();
	private TextField nameField = new TextField(), lastnameField = new TextField(), phoneField = new TextField(), emailField = new TextField(), addressField = new TextField();
	
	private StackPane pane;
	
	public ListCellFactory(StackPane pane) {
		this.pane = pane;
		
		fields.put("Vorname",nameField);
		fields.put("Nachname",lastnameField);
		fields.put("Telefon",phoneField);
		fields.put("E-Mail",emailField);
		fields.put("Addresse",addressField);
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
		VBox contactBox = new VBox();
		contactBox.setPadding(new Insets(45,10,10,20));

		Text header = new Text();
		header.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

		VBox contacts = new VBox();
		contacts.setPadding(new Insets(90,10,10,0));

		setText(null);
		if(isEditing()){
			if(getItem() != null){
				header.setText("Details zu " + getItem().getVorname() + " " + getItem().getNachname());
				
				nameField.setText(getItem().getVorname());
				lastnameField.setText(getItem().getNachname());
				phoneField.setText(getItem().getTelefonnummer());
				emailField.setText(getItem().getMail());
				addressField.setText(getItem().getAdresse());
			}
			
			HBox confirm = new HBox(10);
			confirm.setPadding(new Insets(10));
			confirm.setAlignment(Pos.BASELINE_CENTER);
			
			Button save = new Button("Speichern");
			Button delete = new Button("Löschen");
			
			for(String label : fields.keySet()){
				contacts.getChildren().add(this.createRowBox(label,fields.get(label)));
			}
		
			
			confirm.getChildren().addAll(save, delete);
			

			contactBox.getChildren().addAll(header,contacts,confirm);
			
			FadeTransition ft = new FadeTransition(Duration.millis(500), contactBox);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.play();
			this.pane.getChildren().clear();
			this.pane.getChildren().add(contactBox);
			
			setText(getItem().getNachname() + ", " + getItem().getVorname());
		}else if(getItem() != null){
           setText(getItem().getNachname() + ", " + getItem().getVorname());
		}
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
}
