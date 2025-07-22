package passwords;

//testing class for Generator.java

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GeneratorTest {
	
	Generator generator = new Generator();

	@Test
	void testGeneratedPassword_length() { 
		//tests if password generated matches password length requested 
		int length = 12;
		String password = generator.generatePassword(length);
		assertEquals(length, password.length(), "Password length should match requested length");
	}

	@Test
	void testGeneratedPassword_minLength() {
		//length with input < 4 should return empty string 
		assertEquals("", generator.generatePassword(3));
	}
	
	@Test
	void testGeneratedPassword_containsAllCharacterTypes() {
		//checks if generated password contains all required character types
		String password = generator.generatePassword(20);
		
		assertTrue(password.matches(".*\\d.*"), "Password should contain at least one digit"); //checks if generated strong password contains at least one integer 
		assertTrue(password.matches(".*[a-z].*"), "Password should contain at least one lower case character"); //checks if generated strong password contains at least one lower case character 
		assertTrue(password.matches(".*[A-Z].*"), "Password should contain at least one upper case character"); //checks if generated strong password contains at least one upper case character 
		assertTrue(password.matches(".*[!@#$%&\\-<>?/|].*"), "Password should contain at least one special charcater"); //checks if generated strong password contains at least one special character
	}
	
	@Test
	void testGeneratedPassword_maxLength() {
		//tests if password generated matches max length password requested 
		int length = 100;
		String password = generator.generatePassword(length);
		assertEquals(length, password.length(), "Password length should match requested length");
	}
	
	@Test
	void testGeneratedPassword_exactlyFour() {
		String password = generator.generatePassword(4);
		//generated strong password should contain one of each of the following 
		assertTrue(password.matches(".*\\d.*")); //checks if generated strong password contains at least one integer 
		assertTrue(password.matches(".*[a-z].*")); //checks if generated strong password contains at least one lower case character 
		assertTrue(password.matches(".*[A-Z].*")); //checks if generated strong password contains at least one upper case character 
		assertTrue(password.matches(".*[!@#$%&\\-<>?/|].*")); //checks if generated strong password contains at least one special character
	}
	
	@Test
	void testGeneratedPassword_negativeOrZeroLength() {
		//checks if generator works for zero and negative inputs
		assertEquals("", generator.generatePassword(0));
		assertEquals("", generator.generatePassword(-5));
	}
}
