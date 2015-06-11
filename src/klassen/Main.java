package klassen;

import klassen.ContactDetails;

public class Main {
	

	public static void main(String[] args) {
		//Attribute
		RandomStringGenerator rsg = new RandomStringGenerator();
		
		//Liste mit Inhalt füllen
		for(int i = 0; i < 10; i++){
//			ObservableContactDetails ocb = new ObservableContactDetails(rsg.generateRandomString(5), rsg.generateRandomString(5), rsg.generateRandomString(10), rsg.generateRandomInt(10), rsg.generateRandomString(10));
			String vorname = rsg.generateRandomString(5);
			String nachname = rsg.generateRandomString(5);
			String adresse = rsg.generateRandomString(5);
			int telefonnummer = rsg.generateRandomInt(50000);
			String mail = rsg.generateRandomString(5);
//			ObservableContactDetails ocb = new ObservableContactDetails(vorname, nachname, adresse, telefonnummer, mail);
		}
		
		//View starten
	}

}
