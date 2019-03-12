import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StringInstrument extends AfekaInstruments {
	private int numOfStrings ;
	
	public StringInstrument (Scanner scanFromFile) throws IOException, IllegalArgumentException{
		super(scanFromFile);
		try{
		setNumOfStrings(scanFromFile);
		}catch (InputMismatchException e) {
			throw new IllegalArgumentException("Num of strings must be an integer!");
		} catch (NoSuchElementException ex) {
			throw new IllegalArgumentException(" number ofstrings not entered!");
		}
	}
	public StringInstrument (double price, String brand,int numOfString) throws  IllegalArgumentException {
		super( price, brand);
		setNumOfStrings(numOfString);
	}



	protected  void setNumOfStrings(Scanner scanFromFile) {
		int numOfStrings = scanFromFile.nextInt();
		scanFromFile.nextLine();// clean the buffer
		setNumOfStrings(numOfStrings);
	}




	public int getNumOfStrings() {
		return numOfStrings;
	}

	
	public void setNumOfStrings(int numOfStrings) throws IllegalArgumentException {
		if (numOfStrings <= 0) {
			throw new IllegalArgumentException("num of string can't be 0 or negative");
		}
			this.numOfStrings = numOfStrings; // validation happens in Classes
	}



	@Override
	public boolean equals(Object obj) {
		boolean res= obj instanceof StringInstrument ;
		res = res && this.getNumOfStrings() == ((StringInstrument) obj).getNumOfStrings();
		return res && super.equals(obj);
	}
	
	@Override
	public String toString(){
		return super.toString() + String.format(" Number of strings:%3d|", this.getNumOfStrings());
		
	}

}
