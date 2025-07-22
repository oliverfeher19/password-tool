package passwords;

/*
	Password tool that:
		generates strong password of desired length
		checks inputed password for strength, suggests improvements, and offers to generate strong password as replacement
		completely testable with JUnit testing 
		save to file option
		AES encryption (coming soon)
 */

public class Home {
	public static void main(String[] args) {
		//call helper class to handle everything
		MenuHandler menu = new MenuHandler();
		menu.runMenu();
	}
}