package model;

/**This is the model calss which mainly task is to generate the random 
 * answer of the problem. and a test model input which can make the answer by
 *  the tester.The design is to gather the answer part to this class.
 *  the implementation is based on the MCV idea.
 * 
 * @author Runnan Zhou
 *
 */
public class MastermindModel {
	//private variable(s) to store the answer
	private int LENGTH=4;
	private int NUM=6;
	private String finalAnswer="";
	private String elements = "ROYGBP";
	private int position;
	// Only these methods may be public - you may not create any additional 
	// public methods (and NO public fields)
    public MastermindModel() { 
    	// TODO Make the answer 
    	for (int i=0;i<LENGTH;i++) {
    		position=(int)(Math.random()*NUM);
    		finalAnswer+=elements.substring(position,position+1);
    	}
    	
    }
    
    /**
     * This method is a special constructor to allow us to use JUnit to test our model.
     * 
     * Instead of creating a random solution, it allows us to set the solution from a 
     * String parameter.
     * 
     * 
     * @param answer A string that represents the four color solution
     */
    public MastermindModel(String answer) {
    	//Constuctor of the class with parameter. User can define answer in this
    	//way.
    	//@param:String answer
    	
    	finalAnswer= answer;
    	// TODO Take answer and somehow store it as your answer. Make sure the getColorAt method 
    	// still works
    }


    public char getColorAt(int index) {
          /* Return color at position index as a char
           (first converted if stored as a number) 
           @param:int index
           @return char finalAnswer.charAt(index);
           
           */
    	
    	
    	return finalAnswer.charAt(index); //Just returning something to make sure the code compiles
    }


}
