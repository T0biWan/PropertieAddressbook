package klassen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableContactDetails extends ContactDetails {
	// Attribute
	private StringProperty vorname = new SimpleStringProperty();
	private StringProperty nachname = new SimpleStringProperty();
	private StringProperty adresse = new SimpleStringProperty();
	private StringProperty telefonnummer = new SimpleStringProperty();
	private StringProperty mail = new SimpleStringProperty();

	public ObservableContactDetails(String vorname, String nachname, String adresse, String telefonnummer, String mail) {
		setVorname(vorname);
		setNachname(nachname);
		setAdresse(adresse);
		setTelefonnummer(telefonnummer);
		setMail(mail);
	}

	// Standardkonstruktor
	public ObservableContactDetails() {
		this(null, null, null, null, null);
	}

	// Kopierkonstruktor
	public ObservableContactDetails(ObservableContactDetails contact) {
		setVorname(contact.getVorname());
		setNachname(contact.getNachname());
		setAdresse(contact.getAdresse());
		setTelefonnummer(contact.getTelefonnummer());
		setMail(contact.getMail());
	}

	// Getter
	public StringProperty vornameProperty() {
		return vorname;
	}

	public StringProperty nachnameProperty() {
		return nachname;
	}

	public StringProperty adresseProperty() {
		return adresse;
	}

	public StringProperty telefonnummerProperty() {
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

	public String getTelefonnummer() {
		return telefonnummer.get();
	}

	public String getMail() {
		return mail.get();
	}

	// Setter
	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}

	public void setNachname(String nachname) {
		this.nachname.set(nachname);
	}

	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer.set(telefonnummer);
	}

	public void setMail(String mail) {
		this.adresse.set(mail);
	}

	public String toString() {
		return "Klasse: ObservableContactDetails\nKontaktdaten:\nVorname:\t"
				+ getVorname() + "\nNachname:\t" + getNachname()
				+ "\nAdresse:\t" + getAdresse() + "\nTelefonnummer:\t"
				+ getTelefonnummer() + "\nMail:\t\t" + getMail();
	}
}
