package klassen;

public class Main {
	public static void main(String[] args) {
		RandomStringGenerator		rsg = new RandomStringGenerator();
		ObservableContactDetails	ocd = new ObservableContactDetails(rsg.generateRandomString(3), rsg.generateRandomString(3), rsg.generateRandomString(3), rsg.generateRandomString(3), rsg.generateRandomString(3));
		System.out.println(ocd);
	}
}
