package classes;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import application.Control;


public class AddressBookUI extends Application{

	private BorderPane pane = new BorderPane();

	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Cooles Listen");
 
		Control c = new Control(pane);
		
		c.erstelleListe();
		
		pane.setTop(new VBox(hLabel("Liste")));
		pane.setBottom(new Button("Drucken"));
		
		Scene scene = new Scene(pane, 1200, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Label hLabel(String str) {
		Label label = new Label(str);
	    label.setFont(new Font("Arial", 30));
	    
		return label;
	}
	
	/*private void erstelleTabelle() {
		
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

	}*/


	public static void main(String[] args) {
		launch(args);
	}
	
	
}
