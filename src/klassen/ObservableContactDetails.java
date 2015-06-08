package klassen;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class ObservableContactDetails extends ContactDetails {
	// Attribute
	private StringProperty	vorname;
	private StringProperty	nachname;
	private StringProperty	adresse;
	private IntegerProperty	telefonnummer;
	private StringProperty	mail;
	
	public ObservableContactDetails (String vorname, String nachname, String adresse, int telefonnummer, String mail) {
		setVorname(vorname);
		setNachname(nachname);
		setAdresse(adresse);
		setTelefonnummer(telefonnummer);
		setMail(mail);
	}
	
	//Getter
	public StringProperty vornameProperty() {
		return vorname;
	}
	
	public StringProperty nachnameProperty() {
		return nachname;
	}
	
	public StringProperty adresseProperty() {
		return adresse;
	}
	
	public IntegerProperty telefonnummerProperty() {
		return telefonnummer;
	}
	
	public StringProperty mailProperty() {
		return mail;
	}
	
	public String getVorname() {
		return vorname.get();
	}
	
	public String getNachname() {
		return nachname.get();
	}
	
	public String getAdresse() {
		return adresse.get();
	}
	
	public Integer getTelefonnummer2() {
		return telefonnummer.get();
	}
	
	public String getMail() {
		return mail.get();
	}
	
	//Setter
	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}
	
	public void setNachname(String nachname) {
		this.nachname.set(nachname);
	}
	
	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}
	
	public void setTelefonnummer (int telefonnummer) {
		this.telefonnummer.set(telefonnummer);
	}
	
	public void setMail (String mail) {
		this.adresse.set(mail);
	}
}
