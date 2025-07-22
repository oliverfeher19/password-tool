package passwords;

import java.util.ArrayList;
import java.util.List;

//class is responsible to handle password generation with user input, as well as JUnit testing
//class also handles logic for checking password strength 
public class PasswordService {
	public static String createPassword(int length) {
		if (length < 4 || length > 100) {
			//throws an exception if input is invalid, and stops method immediately
			throw new IllegalArgumentException("Password length must be between 4 and 100.");
		}
		Generator pGenerator = new Generator();
		return pGenerator.generatePassword(length);
	}
	
	//method handles logic for checking a password 
	public static PasswordStrengthResult checkPassword(String password) { 
		int digitCount = 0, lowerCount = 0, upperCount = 0, specialCount = 0;
		
		for(char c : password.toCharArray()) {
			if(Character.isDigit(c)) digitCount++; //updates number of digits (numbers)
			else if(Character.isLowerCase(c)) lowerCount++; //updates lower case letters count
			else if(Character.isUpperCase(c)) upperCount++; //updates upper case letters count
			else specialCount++;
		}
		//create Array List
		List<String> suggestions = new ArrayList<>();
		int score = 0;
		//if number of digits, lower case letters, upper case letters, and special characters is at least 2, increase score
		//else add suggestion when printing feedback to user
		if(digitCount >= 2) score++; else suggestions.add("Add at least 2 digits.");
		if(lowerCount >= 2) score++; else suggestions.add("Add at least 2 lowercase letters");
		if(upperCount >= 2) score++; else suggestions.add("Add at least 2 uppercase letters");
		if(specialCount >= 1) score++; else suggestions.add("Add at least 1 special character");
		//determine strength of password 
		PasswordStrengthResult.StrengthLevel level = switch (score) {
			case 4 -> PasswordStrengthResult.StrengthLevel.STRONG;
			case 3 -> PasswordStrengthResult.StrengthLevel.MODERATE;
			default -> PasswordStrengthResult.StrengthLevel.WEAK;
		};
		
		return new PasswordStrengthResult(password.length(), digitCount, lowerCount, upperCount, specialCount, level, suggestions);
	}
}
