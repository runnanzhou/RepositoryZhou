/*@author: Runnan Zhou
 * This class is the main class of the program. What it did is to summarize
 * the program behavior. It is like a headquater and assign tasks to each
 * module. The design is based on the Model, Controller,Viewer. It is 
 * complicate program so we need to distribute the task to every module. Thus,
 * it is even more easier to adjust the program behavior.
 * 
 */
import java.util.HashMap;
import java.util.Map;

/**
 * The Class Reversi.
 */
public class Reversi {
	
	/**  This is the hashmap to map the integer to the alphabet. */
	private static Map<String,Integer> in = new HashMap<String,Integer>();
	
	/**
	 * The main method. It is the highly summarize of the whole program. 
	 * Create the module and using their mehtod.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ReversiModel a = new ReversiModel();
		ReversiController b= new ReversiController(a);
		ReversiView c = new ReversiView (b);
		buildMap();
		run(a,b,c);
		
		
		
		
	}
	
	/**
	 * Run is the simply procedural of the program. It can be observed from the
	 * fucntion that the first step is to make sure whether the program is 
	 * going to run. Then, get the input from user. Next,check for the error
	 * and pass the input to the controller and make it change the spot on the
	 * model.Then, the computer give his solution and display to the user. When
	 * The program is available to go to the next step, it always do. If there
	 * is no other space for the user to go, just finish the program.
	 *
	 * @param a model
	 * @param b controller
	 * @param c viewer
	 */
	private static void run(ReversiModel a, ReversiController b, ReversiView c) {
		while (!b.isOver()) {
			int status=0;
			String coordinate=c.getUserInput();
			if (!coordinate.equals("exit")) {
				try {
					int x = Integer.parseInt(coordinate.substring(1,2));
					
				int y =in.get(coordinate.substring(0, 1));
				 status=b.humanTurn
						(x-1,y-1 ); 
				 }catch(NullPointerException e) {  
					 System.out.println("Bad input,null, Plz try again!");
					 run(a,b,c);
					 break;
				 }catch(NumberFormatException e) {
					 System.out.println("Bad input,format, Plz try again!");
					 run(a,b,c);
					 break;
				 }catch(StringIndexOutOfBoundsException e) {
					 System.out.println("Bad input,out, Plz try again!");
					 run(a,b,c);
					 break;
				 }catch(BadInputException e) {
					 System.out.println("Bad input, Plz try again!");
					 run(a,b,c);
					 break;
				 }
				
				
				c.displayBoard();
				b.computerTurn();
				c.computerPrint();
				c.displayBoard();
				
				
			}else {
				break;
			}
			}
			
		
	}
	
	/**
	 * Builds the map. Which mapping the integer to the alphabet.
	 */
	private static void buildMap() {
		 in.put("a", 1);
		 in.put("b", 2);
		 in.put("c", 3);
		 in.put("d", 4);
		 in.put("e", 5);
		 in.put("f", 6);
		 in.put("g", 7);
		 in.put("h", 8);
	}
}
