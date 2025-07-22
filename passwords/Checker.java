package passwords;

import java.util.ArrayList;
import java.util.List;

public class Checker {
	public PasswordStrengthResult checkPassword(String password) {
		int digitCount = 0, lowerCount = 0, upperCount = 0, specialCount = 0;
		
		for(char c : password.toCharArray()) {
			if(Character.isDigit(c)) digitCount++;
			else if(Character.isLowerCase(c)) lowerCount++;
			else if(Character.isUpperCase(c)) upperCount++;
			else specialCount++;
		}
		
		List<String> suggestions = new ArrayList<>();
		int score = 0;
		if(digitCount >= 2) score++; else suggestions.add("Add at least 2 digits.");
		if(lowerCount >= 2) score++; else suggestions.add("Add at least 2 lowercase letters");
		if(upperCount >= 2) score++; else suggestions.add("Add at least 2 uppercase letters");
		if(specialCount >= 1) score++; else suggestions.add("Add at least 1 special character");
		
		PasswordStrengthResult.StrengthLevel level = switch (score) {
			case 4 -> PasswordStrengthResult.StrengthLevel.STRONG;
			case 3 -> PasswordStrengthResult.StrengthLevel.MODERATE;
			default -> PasswordStrengthResult.StrengthLevel.WEAK;
		};
		
		return new PasswordStrengthResult(password.length(), digitCount, lowerCount, upperCount, specialCount, level, suggestions);
		
	}
}
