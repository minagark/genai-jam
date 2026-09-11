public class ValidateISBN {

	private static final int LONG_ISBN_MULTIPLIER = 10;
	private static final int SHORT_ISBN_MULTIPLIER = 11;
	private static final int SHORT_ISBN_LENGTH = 10;
	private static final int LONG_ISBN_LENGTH = 13;

	public boolean checkISBN(String isbn) {

		// *Validate null input to prevent NullPointerException
		if (isbn == null) {
			throw new NumberFormatException("ISBN cannot be null");
		}

		if (isbn.length() == LONG_ISBN_LENGTH) {
			return isThisAValidLongISBN(isbn);
		}
		else if (isbn.length() == SHORT_ISBN_LENGTH) {
			return isThisAValidShortISBN(isbn);			
		}
		throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
	}

	private boolean isThisAValidShortISBN(String isbn) {
		int total = 0;

		for (int i = 0; i < SHORT_ISBN_LENGTH; i++)
		{
			if (!Character.isDigit(isbn.charAt(i))) {
				if (i ==9 && isbn.charAt(i) == 'X') {
					total += 10;
				}
				else {
					throw new NumberFormatException("ISBN-10 can only contain numeric digits (except 'X' at the end)");
				}
			}
			else {
				// *Convert character to digit value (subtract ASCII '0' to get 0-9 instead of 48-57)
				total += (isbn.charAt(i) - '0') * (SHORT_ISBN_LENGTH -i);
			}
		}

		return (total % SHORT_ISBN_MULTIPLIER == 0);
	}

	private boolean isThisAValidLongISBN(String isbn) {
		int total = 0;
		
		for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
			// *Validate that all characters are digits (was missing in original)
			if (!Character.isDigit(isbn.charAt(i))) {
				throw new NumberFormatException("ISBN-13 can only contain numeric digits");
			}
			// *Convert character to digit value (subtract ASCII '0') - same bug as ISBN-10 had
			int digit = isbn.charAt(i) - '0';
			if (i % 2 == 0) {
				total += digit;  // Even positions: add digit value
			}
			else {
				total += digit * 3;  // Odd positions: add digit value × 3
			}
		}
		return (total % LONG_ISBN_MULTIPLIER == 0);
	}
}
