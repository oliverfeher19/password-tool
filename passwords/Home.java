package passwords;

import java.util.Scanner;

/*
more to do with this:
	save to file option (save generated password to a local .txt)
	encrypt saved password (use AES to encrypt/decrypt stored passwords) 
	GUI with JavaFX
	Unit Tests or Automated Testing
	Make it web based (HTML,CSS, JS)
	
	add on GIThub
	
	No real wolrd application (yet)
*/

/*
 Week 1: Code Refactor & Unit Testing 
 	clean code
 	add unit testing
 	make code modular and testable
 Week 2: File Storage + AES Encryption 
 	Save generated passwords locally
 	Secure passwords using AES encryption
 Week 3: GUI with JavaFX
 	Replace CLI with a modern JavaFX GUI
 	Improve usability 
 Week 4: Polish & Publish
 	Final polish for presentation
 	Share publicly for resume, GitHub
 */

public class Home {
	public static void main(String[] args) {
		Scanner scannedInput = new Scanner(System.in);
		boolean running = true;
		
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
				case 1:
					System.out.println("Option Number 1 Selected");
					System.out.println("A strong password is between 12-16 letters long, but it can be more.");
					System.out.println("Enter Length of Password: ");
					int num;
					try {
                        num = Integer.parseInt(scannedInput.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number. Try again.");
                        continue;
                    }
					Generator pGen = new Generator();
					String generatedPassword = pGen.generatePassword(num);
					System.out.printf("Generated Strong Password: %s\n", generatedPassword);
					break;
				case 2:
					System.out.println("Option Number 2 Selected");
					System.out.println("Enter Password to Check: ");
					String passwordToCheck = scannedInput.nextLine();
					Checker pCheck = new Checker();
					PasswordStrengthResult result = pCheck.checkPassword(passwordToCheck);
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
                                System.out.print("Enter desired length: ");
                                int length = Integer.parseInt(scannedInput.nextLine());
                                pGen = new Generator();
                                String newPass = pGen.generatePassword(length);
                                System.out.println("Generated Strong Password: " + newPass);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Returning to menu.");
                        }
                    }
					//to here
					break;
				case 3:
					System.out.println("Exiting Program. Goodbye!");
					running = false;
					break;
				default:
					System.out.println("Invalid Option. Please choose 1, 2, or 3.");
					break;
			}
		}
		scannedInput.close();
	}
}