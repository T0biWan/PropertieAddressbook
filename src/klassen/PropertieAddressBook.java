package klassen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import exceptions.DetailsNotFoundException;
import exceptions.DuplicateKeyException;
import exceptions.InvalidContactException;
import exceptions.KeyIsNotInUseException;
import exceptions.ParameterStringIsEmptyException;

public class PropertieAddressBook {
	//Attribute
	//Map in der die ObservableContactDetails gespeichert werden
	private Map<String, ObservableContactDetails> namesMap;
	
	
	//Konstruktoren
	//Kopierkonstruktor
	public PropertieAddressBook(PropertieAddressBook object) {
		namesMap = object.namesMap;		
	}
	
	//Standardkonstruktor
	public PropertieAddressBook() {
		namesMap = new TreeMap<>();
	}
	
	
	//Methoden
	private String getCleanParameter(String key) {
		return key.trim().toLowerCase();
	}
	
	public String generateKey(String name, String lastname, String phone, String mail, String address) {
		name = this.getCleanParameter(name);
		lastname = this.getCleanParameter(lastname);
		phone = (phone.isEmpty()) ? "0000000000" : this.getCleanParameter(phone);
		mail = (mail.isEmpty()) ? "0000000000" : this.getCleanParameter(mail);
		address = (address.isEmpty()) ? "0000000000" : this.getCleanParameter(address);
		return name + "::" + lastname + "::" + phone + "::" + mail + "::"
				+ address;
	}
	
	public String generateKey(ObservableContactDetails details) {
		return this.generateKey(details.getVorname(), details.getNachname(), details.getTelefonnummer(), details.getMail(), details.getAdresse());

	}
	
	public ObservableContactDetails getDetails(String name, String lastname,String phone, String mail, String address) {
		String key = this.generateKey(name, lastname, phone, mail, address);
		return namesMap.get(key);
	}
	
	public ObservableContactDetails[] search(String keyPrefix) {
		keyPrefix = (keyPrefix == null || keyPrefix.isEmpty()) ? "" : keyPrefix.trim().toLowerCase();
		List<ObservableContactDetails> matchedDetails = new ArrayList<>();
		String[] keyPrefixArray = keyPrefix.split(" ");
		for (String prefix : keyPrefixArray) {
			for (String key : namesMap.keySet()) {
				if (key.contains(prefix)) {
					String[] keyArray = key.split("::");
					ObservableContactDetails details = this.getDetails(keyArray[0],keyArray[1], keyArray[2], keyArray[3], keyArray[4]);
					if (!matchedDetails.contains(details)) {
						matchedDetails.add(details);
					}
				}
			}
		}
		return matchedDetails.toArray(new ObservableContactDetails[matchedDetails.size()]);
	}
	
	public ObservableContactDetails getDetails(String key) {
		ObservableContactDetails [] matched = this.search(key);
		return matched[0];
	}
	
	public boolean keyInUse(String key) {
		key = this.getCleanParameter(key);
		return namesMap.containsKey(key);
	}
	
	public int getNumberOfEntries() {
		return namesMap.size();
	}
	
	public void addDetails(ObservableContactDetails details) {
		if (details != null) {
			String key;
				key = this.generateKey(details);
			if (!this.keyInUse(key)){
				namesMap.put(key, details);
			}			
		}
	}
	
	public void removeDetails(String key) {
		namesMap.remove(key);
	}
	
	public void changeDetails(String oldKey, ObservableContactDetails details) {
		if (details != null) {
			oldKey = this.getCleanParameter(oldKey);
			this.removeDetails(oldKey);
			this.addDetails(details);
		}
	}
}
