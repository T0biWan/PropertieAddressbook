package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableContactDetails extends ContactDetails {
	
	private StringProperty	vorname = new SimpleStringProperty();
	private StringProperty  nachname = new SimpleStringProperty();
	private StringProperty  adresse = new SimpleStringProperty();
	private StringProperty  telefonnummer = new SimpleStringProperty();
	private StringProperty  mail = new SimpleStringProperty();

	public ObservableContactDetails(){
		super();
	}
	
	public ObservableContactDetails(String vorname, String nachname, String adresse, String telefonnummer, String mail){
		this.setVorname(vorname);
		this.setNachname(nachname);
		this.setAdresse(adresse);
		this.setTelefonnummer(telefonnummer);
		this.setMail(mail);
	}
	
	public ObservableContactDetails(ObservableContactDetails observableContactDetails){
		this.setVorname(observableContactDetails.getVorname());
		this.setNachname(observableContactDetails.getNachname());
		this.setAdresse(observableContactDetails.getAdresse());
		this.setTelefonnummer(observableContactDetails.getTelefonnummer());
		this.setMail(observableContactDetails.getMail());
	}
	
	// GETTER UND SETTER
	
	public StringProperty vornameProperty(){ return vorname;}

	public String getVorname() {
		return vorname.get();
	}

	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}

	public StringProperty nachnameProperty(){ return nachname;}
	
	public String getNachname() {
		return nachname.get();
	}

	public void setNachname(String nachname) {
		this.nachname.set(nachname);
	}
	
	public StringProperty adresseProperty(){ return adresse;}

	public String getAdresse() {
		return adresse.get();
	}

	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}
	
	public StringProperty telefonnummerProperty(){ return telefonnummer;}

	public String getTelefonnummer() {
		return telefonnummer.get();
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer.set(telefonnummer);
	}
	
	public StringProperty mailProperty(){ return mail;}

	public String getMail() {
		return mail.get();
	}

	public void setMail(String mail) {
		this.mail.set(mail);
	}
	
	public String toString(){
		
		StringBuilder s = new StringBuilder("[" + this.getVorname());
		   
		s.append(", " + this.getNachname());
		s.append(", " + this.getAdresse());  
		s.append(", " + this.getTelefonnummer());  
		s.append(", " + this.getMail());  
		s.append("]");
   
		return s.toString();
	}
	
	public static void main(String[] args) {
		ObservableContactDetails ocd = new ObservableContactDetails();
		ocd.setVorname("Robert");
		ocd.setNachname("Dziuba");
		ocd.setAdresse("Mueller str. 142");
		ocd.setTelefonnummer("12345678");
		ocd.setMail("test@test.de");
		
		System.out.println(ocd);
	}
}
