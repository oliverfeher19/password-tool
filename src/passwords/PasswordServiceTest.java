package passwords;

//testing class for PasswordService.java

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordServiceTest {
	//*******************************************JUnit testing for password creation*******************************************

	@Test
	void testPasswordService_length() {
		int length = 12;
		String password = PasswordService.createPassword(length);
		assertEquals(length, password.length(), "Password length should match requested length");
	}
	
	@Test
	void testPasswordService_minLength() {
		//length with input < 4 should return empty string 
		assertThrows(IllegalArgumentException.class, () -> {
			PasswordService.createPassword(3);
		});
	}

	@Test
	void testPasswordService_containsAllCharacterTypes() {
		//checks if generated password contains all character types
		String password = PasswordService.createPassword(12);
		
		assertTrue(password.matches(".*\\d.*"), "Password should contain at least one digit"); //checks if generated strong password contains at least one integer 
		assertTrue(password.matches(".*[a-z].*"), "Password should contain at least one lower case character"); //checks if generated strong password contains at least one lower case character 
		assertTrue(password.matches(".*[A-Z].*"), "Password should contain at least one upper case character"); //checks if generated strong password contains at least one upper case character 
		assertTrue(password.matches(".*[!@#$%&\\-<>?/|].*"), "Password should contain at least one special charcater"); //checks if generated strong password contains at least one special character
	}
	
	@Test
	void testPasswordService_maxLength() {
		//tests if password generated matches max length password requested 
		int length = 100;
		String password = PasswordService.createPassword(length);
		assertEquals(length, password.length(), "Password length should match requested length");
	}
	
	@Test
	void testPasswordService_exactlyFour() {
		String password = PasswordService.createPassword(4);
		//generated strong password should contain one of each of the following 
		assertTrue(password.matches(".*\\d.*"), "Password should contain at least one digit"); //checks if generated strong password contains at least one integer 
		assertTrue(password.matches(".*[a-z].*"), "Password should contain at least one lower case character"); //checks if generated strong password contains at least one lower case character 
		assertTrue(password.matches(".*[A-Z].*"), "Password should contain at least one upper case character"); //checks if generated strong password contains at least one upper case character 
		assertTrue(password.matches(".*[!@#$%&\\-<>?/|].*"), "Password should contain at least one special charcater"); //checks if generated strong password contains at least one special character
	}
	
	@Test
	void testPasswordService_negativeOrZeroLength() {
		//checks if generator works for zero and negative inputs
		assertThrows(IllegalArgumentException.class, () -> {
			PasswordService.createPassword(-4);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			PasswordService.createPassword(0);
		});
	}
	
	//*******************************************JUnit testing for password checking******************************************* 
	
	@Test
	void testWeakPassword() { //tests a weak password
		String input = "abc";
		PasswordStrengthResult result = PasswordService.checkPassword(input);
		//strength level should match, as well as the suggestions based on strength and password components 
		assertEquals(PasswordStrengthResult.StrengthLevel.WEAK, result.getStrength());
		assertTrue(result.getSuggestions().contains("Add at least 2 digits."));
		assertTrue(result.getSuggestions().contains("Add at least 2 uppercase letters"));
		assertTrue(result.getSuggestions().contains("Add at least 1 special character"));
	}
	
	@Test
	void testModeratePassword() { //tests a moderate password
		String input = "AbcDef1234";
		PasswordStrengthResult result = PasswordService.checkPassword(input);
		//strength level should match, as well as the suggestions based on strength and password components 
		assertEquals(PasswordStrengthResult.StrengthLevel.MODERATE, result.getStrength());
		assertTrue(result.getSuggestions().contains("Add at least 1 special character"));
	}
	
	@Test
	void testStrongPassword() {
		String input = "ABcdE156$agh";
		PasswordStrengthResult result = PasswordService.checkPassword(input);
		//strength level should match, as well as the suggestions based on strength and password components 
		assertEquals(PasswordStrengthResult.StrengthLevel.STRONG, result.getStrength());
		assertTrue(result.getSuggestions().isEmpty()); //no suggestions as password is strong 
	}
	
	@Test
	void testCheckPassword_count() {
		String input = "ABcd123$%";
		PasswordStrengthResult result = PasswordService.checkPassword(input);
		//determines if checker counts contents of password correctly 
		assertEquals(3, result.getDigitCount());
		assertEquals(2, result.getLowerCount());
		assertEquals(2, result.getUpperCount());
		assertEquals(2, result.getSpecialCount());
	}
}






