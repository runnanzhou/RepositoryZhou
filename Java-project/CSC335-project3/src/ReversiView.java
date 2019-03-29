/*@Author : Runnan Zhou
 * This class is the Viewer class. It take the responsibility of printing out
 * information and getting the user input information from the keyboard. It is 
 * clear that except the viewer class, any other class in the mcv will not be
 * able to print info. So what this class print is meet the requirement of the
 * project specification.
 * 
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * The Class ReversiView.
 */
public class ReversiView {
	
	/** The control class. */
	private ReversiController control;
	
	/** The board. */
	private int [][] board;
	
	
	/** The list. */
	private String[] list = {"a", "b", "c","d","e","f","g","h"};
	
 /**
  * Instantiates a new reversi view. Giving the welcome information at first 
  * time.
  *
  * @param control the control
  */
 public ReversiView(ReversiController control) {
	
	 this.control=control;
	 System.out.println("Welcome to Reversi"); 
	 System.out.println("You are W.");
	 displayBoard();
	 System.out.println();
	 
	
	 
 }
 
 /**
  * Computer print. It is what the computer will print after the computer place
  * in the board.
  */
 public void computerPrint() {
	 String coordinateX = String.valueOf(control.getXC()+1);
	 
	 String coordinateY = list[control.getYC()];
	 System.out.println("The computer places a piece at "+coordinateY+coordinateX);
	 System.out.println();
	 
 }

 /**
  * Gets the user input.
  *
  * @return String the user input
  */
 public String getUserInput() {
	 System.out.println("Where would you like to place your token? ");
	 Scanner reader = new Scanner(System.in);
	 String  input = reader.nextLine(); 

	 return input;
 }
 
 /**
  * Display board. It is an important part of the viewer session. Displaying
  * the board means i need to transfer the data to the things that is visualize.
  * To accomplish this, i use the alphabet and _ to represent chess piece.
  */
 public void displayBoard() {
	 board=control.getBoard();
	 for (int i=0;i<8;i++) {
		 System.out.print(i+1);
		 for (int n=0;n<8;n++) {
			 if (board[i][n]==0) {
				 System.out.print(" "+"_");
			 }else if (board[i][n]==1) {
				 System.out.print(" "+"W");
			 }else {
				 System.out.print(" "+"B");
			 }
		 }
		 System.out.println();
	 }
	 System.out.println("  "+"a b c d e f g h");
	 System.out.println();
	 System.out.println("The score is "+control.getScore());
 }
}
