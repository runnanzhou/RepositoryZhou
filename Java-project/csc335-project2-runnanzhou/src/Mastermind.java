import java.util.Scanner;

import controller.MastermindController;
import model.MastermindIllegalColorException;
import model.MastermindIllegalLengthException;
import model.MastermindModel;

/**
 * This is the main file of the project. It deal with the model and control. 
 * Displaying the message and take input.  The design is to gather the print
 * and display part in this view part. catch the exception when the user input
 * not meet the requirement. The implementation based on the MCV idea. 
 * @author Runnan Zhou
 *
 */
public class Mastermind {
	//This class is the viewer of the MCV design style.
private static int PLAYS=10; //The times allowed to play.

	public static void main(String[] args) 
			throws MastermindIllegalColorException, 
			MastermindIllegalLengthException {
		//This is the main function of the viewer. 
		//@param: String [] args.
		//@return:
		//@Throw: MastermindIllegalColorException, 
		//MastermindIllegalLengthException
		String original ="ROYGBP";
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
		for (int i=0;i<PLAYS;) {
			//Read up to ten user inputs
			System.out.printf("Enter guess number %d :",i+1);
			Scanner in= new Scanner(System.in);
			String guess =in.nextLine().toUpperCase();
			try {
			for (int n=0;n<guess.length();n++) {
				int count=0;
				for (int k=0;k<original.length();k++) {
					if (guess.charAt(n)==original.charAt(k)) {
						count+=1;
					}
					
				}
				if (count==0) {
					//When the input contains string which is not belong to 
					//the required string. Then throw an exception.
					throw new MastermindIllegalColorException("Illegal Color");
					
				}
			}
			if (guess.length()!=4) {
				guess=in.next();
				//When the input length is not 4. Then throw an exception.
				throw new MastermindIllegalLengthException ("Illegal Length");
				
			}
			//If there is no Exception occur. Then add the times of play. That
			//means, if exception occur, this time play is not count.
			i++;
			if (control.isCorrect(guess)) {
				System.out.println("Congratulations! You win!");
				//  Check whether or not the input is correct (by asking the controller)
				in.close();
				return;
			}else {
				//  If not, display the relevant statistics  (by asking the controller)
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
				// Determine win or loss
				System.out.println("Sorry, you lose -_-!!!");
				in.close();
				
			}
			}catch (MastermindIllegalColorException e){
				System.out.println("Please try again!");
			}catch(MastermindIllegalLengthException e) {
				System.out.println("Please try again!");
			}
			

		}
		
		

	
	}else {
		System.out.println("Okay~~~");
	}
	}
	

}
