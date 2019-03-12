import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.Scanner;

public class Saxophone extends WindInstruments {
	public final String TYPE_OF_SAXOPGONE = "Metal";

	public Saxophone(Scanner scanFromFile) throws IOException {
		super(scanFromFile);

	}

	public Saxophone(double price, String brand, String kind) throws UnexpectedException {
		super(price, brand, kind);

	}

	@Override
	public void setType(String type) throws IllegalArgumentException {
		if (type != null) {
			if (!type.equalsIgnoreCase(TYPE_OF_SAXOPGONE)) {
				throw new IllegalArgumentException("Saxophone must be made from Metal");
			}
			super.setType(type);
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean res = obj instanceof Saxophone;
		return res && super.equals(obj);
	}
}
