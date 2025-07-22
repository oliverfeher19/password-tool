package passwords;

import java.util.Scanner;

public class MenuHandler {
	//global variable
	Scanner scannedInput = new Scanner(System.in);
	
	public void runMenu() {
		boolean running = true;
		//loop to handle continuous inputs until user prompts a stop
		while(running) {
			System.out.println("****************************************************");
			System.out.println("Choose one of the following:\n1. Generate Strong Password (1)\n2. Check Password (2)\n3. Exit (3)");
			int input;
			try {
                input = Integer.parseInt(scannedInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
                continue;
            }
			
			switch(input) {
				case 1: //generate strong password
					System.out.println("Option Number 1 Selected");
					System.out.println("A strong password is between 12-16 letters long, but it can be more.");
					generateStrongPassword(); //call helper method to handle password generation
					break;
				case 2: //check password
					checkPassword(); //call helper method to handle password checking 
					break;
				case 3: //exit
					System.out.println("Exiting Program. Goodbye!");
					running = false;
					break;
				default: //if input is invalid
					System.out.println("Invalid Option. Please choose 1, 2, or 3.");
					break;
			}
		}
		scannedInput.close(); //properly close scanner
	}
	//helper method to generate strong password
	public void generateStrongPassword() {
		int num = 0;
		//loop to make sure length of password is within the allowed parameters
		while(true) {
			System.out.println("Enter Length of Password: ");
			try {
	            num = Integer.parseInt(scannedInput.nextLine());
	            //checks length of password
	            if(num >= 4 && num <= 100) {
					break; //valid input, exit loop
				} else {
					System.out.println("Invalid number. Please enter an integer.");
				}
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number. Try again.");
	        }
		}
		//create string that will store generated strong password created by the helper class PasswordServices
		String generatedPassword = PasswordService.createPassword(num);
		System.out.printf("Generated Strong Password: %s\n", generatedPassword);
		
		System.out.println("Would you like to save this password? (1 = Yes, 2 = No)");
		try {
            int choice = Integer.parseInt(scannedInput.nextLine());
            if (choice == 1) {
            	//helper class saves generated password to a file
            	PasswordStorageService.createFile(generatedPassword);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Returning to menu.");
        }
	}
	//helper method to handle password check
	public void checkPassword() {
		System.out.println("Option Number 2 Selected");
		System.out.println("Enter Password to Check: ");
		String passwordToCheck = scannedInput.nextLine();
		
		//calls logic class to handle checking logic and return object
		PasswordStrengthResult result = PasswordService.checkPassword(passwordToCheck);
		
		//new
		System.out.printf("Password Length: %d\n", result.getLength());
        System.out.printf("Digits: %d, Lowercase: %d, Uppercase: %d, Special: %d\n",
                result.getDigitCount(), result.getLowerCount(),
                result.getUpperCount(), result.getSpecialCount());
        System.out.println("Strength: " + result.getStrength());

        if (!result.getSuggestions().isEmpty()) {
            System.out.println("Suggestions to improve:");
            for (String s : result.getSuggestions()) {
                System.out.println("- " + s);
            }

            System.out.println("Would you like to generate a stronger password? (1 = Yes, 2 = No)");
            try {
                int choice = Integer.parseInt(scannedInput.nextLine());
                if (choice == 1) {
                	//call other helper function to generate strong password if user decides
                	generateStrongPassword();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Returning to menu.");
            }
        }
		//to here
	}
}
