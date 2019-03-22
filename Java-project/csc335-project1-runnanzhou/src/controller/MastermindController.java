package controller;
import java.util.ArrayList;
import java.util.List;

import model.MastermindModel;

/**
 * 
 * @author Runnan Zhou
 * This is the controller class. Juding whether is correct or get the 
 * color at some specific place. cooperating with other two class and play an 
 * important role in it.
 */
public class MastermindController {
	
	// Only these methods may be public - you may not create any additional 
	// public methods (and NO public fields)
	private int LENGTH=4;
	private MastermindModel model = new MastermindModel();
	public MastermindController(MastermindModel model) {
		this.model= model;
	}
 

    public boolean isCorrect(String guess) {
    	for (int i=0;i<LENGTH;i++) {
    		if (guess.charAt(i)!=model.getColorAt(i)) {
    			return false;
    		}
    	}
    	return true; //Juding whether the input is correct with the answer.
    }


    public int getRightColorRightPlace(String guess) {
    	int count=0;
    	for (int i=0;i<LENGTH;i++) {
    		if (guess.charAt(i)==model.getColorAt(i)) {
    			count+=1;
    		}
    	}
    	return count; //pick the right color right place. 
    }


    public int getRightColorWrongPlace(String guess) {
    	int count=0;
    	List<Character> used = new ArrayList<Character>();
    	for (int i=0;i<LENGTH;i++) {
    		for (int n=0;n<LENGTH;n++) {
    			if ((guess.charAt(i)==model.getColorAt(n))) {
    				if (!used.contains(guess.charAt(i))) {
    					used.add(guess.charAt(i));
    					if (i!=n) {
    						count+=1;
    					}
    				}
        			
        		}
    		}
    		
    	}
    	return count; //pick the right color wrong place.
    }
    
    // that's it.

}
