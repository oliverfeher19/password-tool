package passwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Generator {
	
	//define a final random number to be used throughout this class
	private final Random generator = new Random();
	
	public String generatePassword(int length) {
		
		if (length < 4) return "";

        List<Character> passwordChars = new ArrayList<>();

        passwordChars.add(intGenerator());
        passwordChars.add(lowerCharGenerator());
        passwordChars.add(upperCharGenerator());
        passwordChars.add(specialCharGenerator());

        for (int i = 0; i < length - 4; i++) {
            int rand = generator.nextInt(4);
            switch (rand) {
                case 0 -> passwordChars.add(intGenerator());
                case 1 -> passwordChars.add(lowerCharGenerator());
                case 2 -> passwordChars.add(upperCharGenerator());
                case 3 -> passwordChars.add(specialCharGenerator());
            }
        }

        Collections.shuffle(passwordChars);

        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();

	}
	private char intGenerator() { //generates random integer value 
		return (char) generator.nextInt(48, 58); //'0' to '9' 
	}
	private char lowerCharGenerator() { //generates random lower case character value 
		return (char) generator.nextInt(97, 123); //'a' to 'z'
	}
	private char upperCharGenerator() { //generates random upper case character value 
		return (char) generator.nextInt(65, 91); //'A' to 'Z'
	}
	private char specialCharGenerator() { //generates random special character value 
		String specials = "!@#$%&-<>?/|"; //hand picked special characters
		return specials.charAt(generator.nextInt(specials.length()));
	}
}
