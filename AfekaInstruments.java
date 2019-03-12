import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AfekaInstruments {
	public static void main(String[] args) throws IOException {
		String fileName = gettingFileName();

		getInstumentsFromFile(fileName);

	}

	private static String gettingFileName() {
		File file = new File(" ");
		String fileName = " ";
		Scanner keyboard = new Scanner(System.in); // scanner for file name
		do {
			try {
				System.out.println("Please enter instruments file name / path:");
				fileName = keyboard.nextLine(); // user insert file name
				file = new File(fileName);

				if (!file.exists()) { // if the file name is OK, continue the
										// program otherwise throw Exception and
										// retry enter a file name.
					throw new FileNotFoundException("File Error! Please try again:");
				}

			} catch (FileNotFoundException ex) {
				System.out.println(ex.getMessage());
			}
		} while (!file.exists());
		keyboard.close();
		return fileName;
	}

	private static void getInstumentsFromFile(String file) throws IOException {
		ArrayList<AfekaInstruments> afekaInstrumentsArr = new ArrayList<AfekaInstruments>();
		File file1 = new File(file);
		Scanner fileScanner = new Scanner(file1); // scanner for file

		try {
			// reading from file
							
				addAllInstruments(loadGuitar(fileScanner), afekaInstrumentsArr);
				addAllInstruments(loadBass(fileScanner), afekaInstrumentsArr);
				addAllInstruments(loadFlute(fileScanner), afekaInstrumentsArr);
				addAllInstruments(loadSaxophone(fileScanner), afekaInstrumentsArr);


			System.out.println("Instruments loaded from file successfully!");
			System.out.println();
			System.out.println();
			printInstruments(afekaInstrumentsArr);

			/*-------------------*/
			System.out.println();
			System.out.println(
					"Different Instruments: " + getNumOfDifferentElements(afekaInstrumentsArr));

			System.out.println();

			System.out.println(
					"Most Expensive Instrument:\n" + getMostExpensiveInstrument(afekaInstrumentsArr));
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.exit(0);

		} catch (InputMismatchException e) {
			System.err.println(e.getMessage());
			System.exit(0);
		} catch (NoSuchElementException e) {
			System.err.println("file not completed, there is not what to read");
			System.exit(0);
		}
		fileScanner.close();
	}
	
	private static ArrayList<AfekaInstruments> loadSaxophone(Scanner fileScanner) throws IllegalArgumentException, IOException {
		ArrayList<AfekaInstruments> loadedSaxophoneList = new ArrayList<AfekaInstruments>();
		int numOfElementsFromTheSameInstrument = fileScanner.nextInt();
		fileScanner.nextLine();
		for (int j = 0; j < numOfElementsFromTheSameInstrument && fileScanner.hasNext(); j++) {
			loadedSaxophoneList.add(new Saxophone(fileScanner));

		}
		return loadedSaxophoneList;
	}

	private static ArrayList<AfekaInstruments> loadFlute(Scanner fileScanner) throws IllegalArgumentException, IOException {
		ArrayList<AfekaInstruments> loadedFluteList = new ArrayList<AfekaInstruments>();
		int numOfElementsFromTheSameInstrument = fileScanner.nextInt();
		fileScanner.nextLine();
		for (int j = 0; j < numOfElementsFromTheSameInstrument  && fileScanner.hasNext(); j++) {
			loadedFluteList.add(new Flute(fileScanner));

		}
		return loadedFluteList;
	}

	private static ArrayList<AfekaInstruments> loadBass(Scanner fileScanner) throws IllegalArgumentException, IOException {
		ArrayList<AfekaInstruments> loadedBassGuitarList = new ArrayList<AfekaInstruments>();
		int numOfElementsFromTheSameInstrument = fileScanner.nextInt();
		fileScanner.nextLine();
		for (int j = 0; j < numOfElementsFromTheSameInstrument  && fileScanner.hasNext(); j++) {
			loadedBassGuitarList.add(new BassGuitar(fileScanner));

		}
		return loadedBassGuitarList;
	}

	private static ArrayList<AfekaInstruments> loadGuitar(Scanner fileScanner) throws IllegalArgumentException, IOException {
		ArrayList<AfekaInstruments> loadedGuitarList = new ArrayList<AfekaInstruments>();
		int numOfElementsFromTheSameInstrument = fileScanner.nextInt();
		fileScanner.nextLine();
		for (int j = 0; j < numOfElementsFromTheSameInstrument  && fileScanner.hasNext(); j++) {
			loadedGuitarList.add(new Guitars(fileScanner));

		}
		return loadedGuitarList;
	}

	private double price;
	private String brand;

	public AfekaInstruments(double price, String brand) throws IllegalArgumentException {
		setPrice(price);
		setBrand(brand);
	}

	public AfekaInstruments(Scanner scanFromFile) {
		try {
			setPrice(scanFromFile);
			setBrand(scanFromFile);
		} catch (InputMismatchException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		} catch (NoSuchElementException ex) {
			throw new IllegalArgumentException(" brand name not entered!");
		}
	}

	protected void setBrand(Scanner scanFromFile) {
		this.brand = scanFromFile.nextLine();
	}

	protected void setPrice(Scanner scanFromFile) throws IllegalArgumentException {
		if (!scanFromFile.hasNextInt()|| !scanFromFile.hasNextDouble()){
			throw new InputMismatchException("price must be a double/int");
		}
		double price = scanFromFile.nextDouble();
		scanFromFile.nextLine(); // clean the buffer
		setPrice(price);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) throws IllegalArgumentException {
		if (price >= 0)
			this.price = price;
		else {
			throw new IllegalArgumentException("Price must be a positive number!");
		}
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	protected static void addAllInstruments(ArrayList<AfekaInstruments> list, ArrayList<AfekaInstruments> newList) {
		for (int i = 0; i < list.size(); i++) {
			newList.add(list.get(i));
		}
	}

	protected static void printInstruments(ArrayList<AfekaInstruments> al) {
		if (al.size() < 1) {
			System.out.println("There are no instruments in the store currently");
			System.exit(0);
		}
		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}

	}

	public static AfekaInstruments getMostExpensiveInstrument(ArrayList<AfekaInstruments> al)
			throws IllegalArgumentException {
		AfekaInstruments mostExpensiveInstrument = (AfekaInstruments) al.get(0);
		for (int i = 1; i < al.size(); i++) {
			if (al.get(i).getPrice() > mostExpensiveInstrument.getPrice())
				mostExpensiveInstrument = (AfekaInstruments) al.get(i);
		}
		return mostExpensiveInstrument;
	}

	public static int getNumOfDifferentElements(ArrayList<AfekaInstruments> afekaInstrumentsArrToCount) {
		ArrayList<AfekaInstruments> temp = new ArrayList<AfekaInstruments>();
		addAllInstruments(afekaInstrumentsArrToCount, temp);
		for (int currentIndex = 0; currentIndex < temp.size(); currentIndex++) {
			for (int i = currentIndex + 1; i < temp.size(); i++) {
				if (temp.get(currentIndex).equals(temp.get(i))) {
					i--; // because the instrument has deleted, I need to check
							// the same index witch is the next Instrument.
					temp.remove(i);
				}

			}
		}

		return temp.size();
	}

	@Override
	public boolean equals(Object obj) {
		boolean res = obj instanceof AfekaInstruments;
		res = res && this.getPrice() == ((AfekaInstruments) obj).getPrice();
		res = res && this.getBrand().compareToIgnoreCase(((AfekaInstruments) obj).getBrand()) == 0;
		return res;
	}

	@Override
	public String toString() {
		return String.format("%-10s %-10s | Price: %8.2f,", this.getBrand(), this.getClass().getCanonicalName(),
				this.getPrice());

	}
}
