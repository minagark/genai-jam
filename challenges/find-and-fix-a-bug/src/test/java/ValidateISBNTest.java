import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	public void checkAValid10DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449116");
		assertTrue(result,"first value");
		result = validator.checkISBN("0140177396");
		assertTrue(result, "second value");
	}
	
	@Test
	public void checkAValid13DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853260087");
		assertTrue(result,"first value");
		result = validator.checkISBN("9781853267338");
		assertTrue(result, "second value");
	}
	
	@Test
	public void TenDigitISBNNumbersEndingInAnXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}

	@Test
	public void checkAnInvalid10DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}
	
	@Test
	public void checkAnInvalid13DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN("9781853267336");
		assertFalse(result);
	}
	
	@Test
	public void nineDigitISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, 
				() -> {
					validator.checkISBN("123456789");
				});
	}
	
	@Test
	public void nonNumericISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, 
				() -> {
					validator.checkISBN("helloworld");
				});
	}
	
	@Test
	public void nullInputThrowsException() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, 
				() -> {
					validator.checkISBN(null);
				});
	}
	
	@Test
	public void emptyStringIsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, 
				() -> {
					validator.checkISBN("");
				});
	}
	
	@Test
	public void ISBN13WithNonNumericCharactersIsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		assertThrows(NumberFormatException.class, 
				() -> {
					validator.checkISBN("978185326008X");  // Valid format but has X (only allowed in ISBN-10)
				});
	}
	
	@Test
	public void XInWrongPositionForISBN10IsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		// X must be in position 9 (last position), not earlier
		assertThrows(NumberFormatException.class, 
				() -> {
					validator.checkISBN("X123456789");
				});
	}
	
}

