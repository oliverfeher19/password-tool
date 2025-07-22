package passwords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//class saves password securely to local file

public class PasswordStorageService {
	//creates file to store generated password in 
	public static void createFile(String password) {
		try {
			File myObj = new File("password.txt");
			if(myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists");
			}
		} catch(IOException e) {
			System.out.println("An error occured while creating your file.");
			e.printStackTrace();
		}
		savePasswordToFile(password);
	}
	//method writes password to created file 
	public static void savePasswordToFile(String password) {
		try {
			FileWriter myWriter = new FileWriter("password.txt");
			myWriter.write(password);
			myWriter.close();
			System.out.println("Successfully saved password to file.");
		} catch(IOException e) {
			System.out.println("An error occured while saving your password.");
			e.printStackTrace();
		}
	}
}
