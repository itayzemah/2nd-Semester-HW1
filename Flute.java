import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Flute extends WindInstruments {
	private String kind;
	private final String[] kindArr = { "Side Flute", "recorder", "Bass Flute" };

	public Flute(Scanner scanFromFile) throws IOException {
		super(scanFromFile);
		setKind(scanFromFile); // kind is a String, so no exception expected, only validation needed to check.


	}

	public Flute(double price, String brand, String type, String kind) throws IllegalArgumentException {
		super(price, brand, type);
		setKind(kind);
	}

	@Override
	public void setType(String type) throws IllegalArgumentException {
		if( type != null){
		super.setType(type);
		}
	} 
	
	private void setKind(Scanner scanFromFile) {
		String kind = "";
		try{
			 kind = scanFromFile.nextLine();
		} catch (NoSuchElementException ex){
			throw new IllegalArgumentException("type not entered!");
		}

		setKind(kind);
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kindToCheck) {
		boolean isAKind = false;
		for (int i = 0; i < kindArr.length && isAKind == false; i++) {
			if (kindToCheck.compareToIgnoreCase(kindArr[i]) == 0) {
				isAKind = true;
			}
		}

		if (isAKind == false) {
			throw new IllegalArgumentException("The argument " + kindToCheck + " is not accepted kind.");
		}
		this.kind = kindToCheck;
	}

	@Override
	public boolean equals(Object obj) {
		boolean res = obj instanceof Flute;
		res = res && this.kind.compareToIgnoreCase( ((Flute) obj).getKind())==0;
		return res && super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" Type:%6s", this.getKind());

	}
}
