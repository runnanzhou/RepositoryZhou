import java.util.Scanner;

import controller.MastermindController;
import model.MastermindModel;

/**
 * This is the main file of the project. It deal with the model and control. 
 * Displaying the message and take input. 
 * @author Runnan Zhou
 *
 */
public class Mastermind {
private static int PLAYS=10;
	public static void main(String[] args) {
		// This class represents the view, it should be how uses play the game
		System.out.println("Welcome to Mastermind!");
		Scanner reader = new Scanner(System.in);
		System.out.println("Would you like to play?");
		String  input = reader.nextLine();
		
		
		if (input.toLowerCase().equals("yes")) {
			
		
		
			
		// TODO while the user wants to play:
		MastermindModel model = new MastermindModel();
		// TODO Construct the model (whose constructor builds the secret answer)
		MastermindController control = new MastermindController(model);
		// TODO Construct the controller, passing in the model
		for (int i=0;i<PLAYS;i++) {
			//Read up to ten user inputs
			System.out.printf("Enter guess number %d :",i+1);
			Scanner in= new Scanner(System.in);
			String guess =in.nextLine().toUpperCase();
			if (guess.length()!=4) {
				
				System.out.println("Error!Please enter a 4-size String");
				guess=in.next();
			}
			if (control.isCorrect(guess)) {
				System.out.println("Congratulations! You win!");
				// TODO Check whether or not the input is correct (by asking the controller)
				in.close();
				return;
			}else {
				// TODO If not, display the relevant statistics  (by asking the controller)
				int a=control.getRightColorRightPlace(guess);
				int b=control.getRightColorWrongPlace(guess);
				System.out.printf("Colors in the correct place :%d ",
						a);
				System.out.println();
				
				System.out.printf("Colors correct but in wrong position: %d",
						b);
				System.out.println();
			}
			if (i==9) {
				// TODO Determine win or loss
				System.out.println("Sorry, you lose -_-!!!");
				in.close();
				
			}

		}
		

	
	}else {
		System.out.println("Okay~~~");
	}
	}
	

}
