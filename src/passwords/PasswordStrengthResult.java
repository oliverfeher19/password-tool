package passwords;

import java.util.List;

//object class

public class PasswordStrengthResult {
	//enumeration defining fixed set of constants for password strength levels
	public enum StrengthLevel { WEAK, MODERATE, STRONG }
	//initialize variables to be used 
	private int length = 0;
	private int digitCount = 0;
	private int lowerCount = 0;
	private int upperCount = 0;
	private int specialCount = 0;
	private StrengthLevel strength = null;
	private List<String> suggestions = null;
	
	
	
	//constructor to set initial values when object is made 
	public PasswordStrengthResult(int length, int digitCount, int lowerCount, int upperCount,
		int specialCount, StrengthLevel strength, List<String> suggestions) {
		this.length = length;
		this.digitCount = digitCount;
		this.lowerCount = lowerCount;
		this.upperCount = upperCount;
		this.specialCount = specialCount;
		this.strength = strength;
		this.suggestions = suggestions;
	}
	
	//getter methods return current value of a specific field in the object
	public int getLength() { 
		return length; 
	}
    public int getDigitCount() { 
    	return digitCount; 
    }
    public int getLowerCount() { 
    	return lowerCount; 
    }
    public int getUpperCount() { 
    	return upperCount; 
    }
    public int getSpecialCount() { 
    	return specialCount; 
    }
    public StrengthLevel getStrength() { 
    	return strength; 
    }
    public List<String> getSuggestions() { 
    	return suggestions; 
    }
}
