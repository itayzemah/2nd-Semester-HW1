import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WindInstruments extends AfekaInstruments {
	private enum type {
		Wood, Metal, Plastic
	}
	private String Type;

	public WindInstruments(Scanner scanFromFile) throws IOException {
		super(scanFromFile);
		setType(scanFromFile); // type is a String, so no exception expected, only validation needed to check.

	}

	public WindInstruments(double price, String brand, String type) throws IllegalArgumentException {
		super(price, brand);
		setType(type);
	}

	public void setType(Scanner scanFromFile) throws IllegalArgumentException {
		String type = "";
		try{
			type = scanFromFile.nextLine();
		} catch (NoSuchElementException ex){
			throw new IllegalArgumentException("type not entered!");
		}
		setType(type);
	}
	public void setType(String typeToCheck) {
		// type can be only one of the enum types: wood, metal, plastic:
		boolean typeIsFound = false;
		for (int i = 0; i < type.values().length && typeIsFound == false; i++) {
			if (typeToCheck.compareToIgnoreCase(type.values()[i].name()) == 0) {
				typeIsFound = true;
			}
		}
		if(this instanceof Saxophone && (typeToCheck.compareToIgnoreCase("Metal") != 0)){
			throw new IllegalArgumentException("Saxophone must be made from Metal");
		}
		if (typeIsFound == false) {
			throw new IllegalArgumentException("Illegal Type!" + "\"" + typeToCheck + "\"");
		}
		this.Type = typeToCheck.toLowerCase();

	}

	@Override
	public boolean equals(Object obj) {
		boolean res = obj instanceof WindInstruments;
		res = res && (this.Type.compareToIgnoreCase(((WindInstruments) obj).getType()) == 0);
		return res && super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" Made of: %12s|", this.getType());

	}

	public String getType() {
		return Type;
	}



}
