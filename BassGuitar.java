import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BassGuitar extends StringInstrument {
	private boolean fretless;
	private final static int MIN_STRINGS_BASS_GUITARS = 4;
	private final static int MAX_STRINGS_BASS_GUITARS = 6;

	public BassGuitar(Scanner scanFromFile) throws IOException, IllegalArgumentException {
		super(scanFromFile);
	
		validateNumOfStrings(this.getNumOfStrings());
		setFretless(scanFromFile); //boolean check in SETTER.
		
	}

	public BassGuitar(double price, String brand, int numOfStrings, boolean isfresless) throws IllegalArgumentException  {
		super(price, brand, numOfStrings);
			setFretless(isfresless);

		
	}
	@Override
	public void setNumOfStrings(int numOfStrings) throws IllegalArgumentException {
	try{
		validateNumOfStrings( numOfStrings);
	}catch (IllegalArgumentException ex){
		throw new IllegalArgumentException(ex.getMessage());
		}
	super.setNumOfStrings(numOfStrings);
	}

	private void validateNumOfStrings(int numOfStrings) throws IllegalArgumentException {
		if (numOfStrings < MIN_STRINGS_BASS_GUITARS || numOfStrings > MAX_STRINGS_BASS_GUITARS) {
			throw new IllegalArgumentException("Bass number of strings is a number between 4 and 6, not " + numOfStrings);
		}
	}

	private void setFretless(Scanner scanFromFile) throws InputMismatchException{
		boolean isfresless = false;
		try{
			isfresless = scanFromFile.nextBoolean();
			scanFromFile.nextLine(); // clean the buffer.
			setFretless(isfresless);
		}catch (InputMismatchException e) {
				throw new IllegalArgumentException("Whether a bass is fretless or not is boolean, any other string than \"True\" or"
					+ " \"False\" is not acceptable");
		}
	}

	public boolean isFretless() {
		return fretless;
	}

	public void setFretless(boolean fretless) {
		this.fretless = fretless;

	}

	@Override
	public boolean equals(Object obj) {
		boolean res = obj instanceof BassGuitar;
		res = res && this.fretless == ((BassGuitar) obj).isFretless();
		return res && super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" Fretless:%4b ", this.isFretless());

	}
}
