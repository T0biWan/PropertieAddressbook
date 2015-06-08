package klassen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Control {
	//Attribute
	TableView <ObservableContactDetails> 		tabelleFürKontakte		= new TableView();
	ObservableList <ObservableContactDetails>	kontakte				= FXCollections.observableArrayList();
	
	//konstrukte
	public Control () {
		tabelleFürKontakte.setItems(kontakte);
	}
	
	
}
