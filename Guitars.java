import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Guitars extends StringInstrument {
	private String type;
	private final static int NUM_OF_Acoustic_Guitars_STRINGS = 6;
	private final static int NUM_OF_Classic_Guitars_STRINGS = 6;
	private final static int MIN_NUM_OF_Electric_Guitars_STRINGS = 6;
	private final static int MAX_NUM_OF_Electric_Guitars_STRINGS = 8;
	private final static int DEFULT_NUM_OF_Guitars_STRINGS = 6;
	private enum types {
		Acoustic, Classic, Electric
	}

	public Guitars(Scanner scanFromFile) throws IOException, IllegalArgumentException {
		super(scanFromFile);
		try{
		setType(scanFromFile);
		}catch (InputMismatchException e) {
			throw new IllegalArgumentException("type must be one of the defined types");
		}
	}

	public Guitars(double price, String brand, int numOfStrings, String type) throws IllegalArgumentException {
		super(price, brand, numOfStrings);
		try{
		setType(type);
		} catch (NoSuchElementException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	public Guitars(double price, String brand, String type) throws IllegalArgumentException {
		super(price, brand, DEFULT_NUM_OF_Guitars_STRINGS);
		setType(type);

	}

	@Override
	public void setNumOfStrings(int numOfStrings) throws IllegalArgumentException {
		try {
			validateNumOfStrings(this.getType(), numOfStrings);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
		super.setNumOfStrings(numOfStrings);
	}

	private void setType(Scanner scanFromFile)  {
		if(!scanFromFile.hasNext()){
			throw new NoSuchElementException(" type not entered!");
		}
		String type = scanFromFile.nextLine();
		setType(type);

	}

	private void kindOfType(String typeToCheck) throws IllegalArgumentException {
		boolean typeIsFound = false;
		for (int i = 0; i < types.values().length && typeIsFound == false; i++) {
			if (typeToCheck.compareToIgnoreCase(types.values()[i].name()) == 0) {
				typeIsFound = true;
			}
		}
		if (typeIsFound == false) {
			throw new IllegalArgumentException(typeToCheck + "material is not found in Afeka store.");
		}

	}

	private void validateNumOfStrings(String type, int numOfStrings) throws IllegalArgumentException {
		if (type != null) {
			if (type.compareToIgnoreCase(types.Acoustic.name()) == 0
					&& numOfStrings != NUM_OF_Acoustic_Guitars_STRINGS) {
				throw new IllegalArgumentException("Acoustic Guitars have 6 strings, not " + numOfStrings);
			}
			if (type.compareToIgnoreCase(types.Classic.name()) == 0 && numOfStrings != NUM_OF_Classic_Guitars_STRINGS) {
				throw new IllegalArgumentException("Classic Guitars have 6 strings, not " + numOfStrings);
			}
			if (type.compareToIgnoreCase(types.Electric.name()) == 0
					&& (numOfStrings < MIN_NUM_OF_Electric_Guitars_STRINGS
							|| numOfStrings > MAX_NUM_OF_Electric_Guitars_STRINGS)) {
				throw new IllegalArgumentException(
						"Electric Guitars have a number of strings between 6 to 8, not " + numOfStrings);
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) throws IllegalArgumentException {
		kindOfType(type); // if it is not type from the list throw exception
		validateNumOfStrings(type, this.getNumOfStrings()); // if the type from
															// the list but not
															// match to it's num
															// of strings throw
															// exception
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		boolean res = obj instanceof Guitars;
		res = res && (this.type.compareToIgnoreCase(((Guitars) obj).getType()) == 0);
		return res && super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" Type:%5s ", this.getType());

	}
}
