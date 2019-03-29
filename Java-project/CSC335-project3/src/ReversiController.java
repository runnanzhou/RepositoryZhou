/*@Author : Runnan Zhou
 * This class is the most important class in the MVC. Because it take the 
 * responsibility of assigning task to viewer and model. It also changed that
 * 2 section. So, the function in this class is pretty handy to use. It 
 * including the change method to the model and other function help the program 
 * to make the right decision. Like the figureOutComputer, which is make 
 * the decision for the computer to place the chess.
 * 
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class ReversiController.
 */
public class ReversiController {
	
	
	/** The model. */
	private  ReversiModel model = new ReversiModel();
	
	
	/** The yc. */
	private int xc,yc;
	
	/**
	 * Instantiates a new reversi controller.
	 *
	 * @param model :the model
	 */
	public ReversiController (ReversiModel model) {
		this.model=model;
	}
	//get the input coordinate, check if it is legal. If legal, place it. find
	/**
	 * Human turn.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the int 
	 * @throws BadInputException the bad input exception
	 */
	// the suitable element and reverse it.
	public int humanTurn(int x, int y) throws BadInputException {
		if (isLegal(x,y,1)) {place(x,y,1);
		reverseTheElement(x,y,1);return 0;}else {
			throw new BadInputException("Bad Input");
		}
		
	}
	//get the input coordinate, check if it is legal. If legal, place it. find
		/**
	 * Computer turn.
	 */
	// the suitable element and reverse it.
	public void computerTurn() {
		 xc=figureOutComputer().get(0);
		 yc=figureOutComputer().get(1);
		
		if (isLegal(xc,yc,2)) {place(xc,yc,2);
		reverseTheElement(xc,yc,2);}else {return;
		}
	}
	
	/**
	 * Gets the xc.
	 *
	 * @return the xc
	 */
	public int getXC() {
		return xc;
		
	}
	
	/**
	 * Gets the yc.
	 *
	 * @return the yc
	 */
	public int getYC() {
		return yc;
		
	}
	
	/**
	 * Place the chess.
	 *
	 * @param x the x
	 * @param y the y
	 * @param id the id
	 */
	public void place(int x, int y, int id) {
		if (model.getElement(x, y)!=100) {
			model.revise(x, y, id);
		}
	}
	
	/**
	 * Checks if is legal.
	 *
	 * @param x the x
	 * @param y the y
	 * @param id the id
	 * @return true, if is legal
	 */
	public boolean isLegal(int x, int y, int id) {
		int illegal=0;
		if (x<0 || x>7 ||y<0 || y>7||( (id!=1)&&(id!=2))) {
			illegal=1;
			
		}
		if (model.getElement(x, y)!=0) {
			illegal=1;
			
		}
		int flag=0;
		for (int i=-1;i<2;i++) {
			for (int n=-1;n<2;n++) {
				if (i==0 && n==0) {
					continue;
				}
				
				ReversiMove move = new ReversiMove(x,y,i,n,id,model);
				if (move.isLegal()==true) {
					flag=1;
					
				}
			}
		}
		if (flag==0) {
			illegal=1;
			
		}
		if (illegal==0) {
			return true;
		}else {
			
			return false;
		}
	}
	
	/**
	 * Figure out computer's decision. The alogirithnm is pretty simple. Making
	 * the step that will bring the most profit at single step. 
	 *
	 * @return the list
	 */
	public  List<Integer> figureOutComputer() {
		int index=0;
		List<Integer> profit = new ArrayList<Integer>();
		List<Integer> coordinate = new ArrayList<Integer>();
		List<Integer> tempstore = new ArrayList<Integer>();
		for (int j=0;j<7;j++) {
			for (int k=0;k<7;k++) {
				if (model.getElement(j, k)==0) {
					int benefit=0;
					if(isLegal(j,k,2)) {
						
						//temporaily store all the legal solution to the list.
						tempstore.add(j);
						tempstore.add(k);
						//add all the solution's profit to the list.
						for (int i=-1;i<2;i++) {
							for (int n=-1;n<2;n++) {
								 
								if (i==0 && n==0) {
									continue;
								}				
							ReversiMove move = new ReversiMove(j,k,i,n,2,model);
							if (move.isLegal()) {
								benefit+=move.getProfit();
								
							}
							
							
					}
					
					
				}profit.add(benefit);
				
			}
					
		}
				}
			}
			
			//got the best profit index in the list. That is the coordinate of
			//best solution.
			
			index=profit.indexOf(Collections.max(profit));
			
			//got the best solution to the current situation for the computer.
			
			coordinate.add(tempstore.get(index*2));
			coordinate.add(tempstore.get((index*2)+1));
			
			return coordinate;
	}
	
	/**
	 * Reverse the element.
	 *
	 * @param x the x
	 * @param y the y
	 * @param id the id
	 */
	private void reverseTheElement(int x, int y, int id) {
		
		List<Integer> valueX = new ArrayList<Integer>();
		List<Integer> valueY = new ArrayList<Integer>();
		
		for (int i=-1;i<2;i++) {
			for (int n=-1;n<2;n++) {
				if (i==0 && n==0) {
					continue;
				}				
				ReversiMove move = new ReversiMove(x,y,i,n,id,model);
				valueX=move.getValueX();
				valueY=move.getValueY();
				
				
				for (int k=0;k<valueX.size();k++) {
					model.revise(valueX.get(k), valueY.get(k), id);
				}
			}
		}
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public String getScore() {
		int count1=0;
		int count2=0;
		String retVal="";
		for (int i=0;i<7;i++) {
			for (int n=0;n<7;n++) {
				if (model.getElement(i, n)==1) {
					count1+=1;
				}
				if (model.getElement(i, n)==2) {
					count2+=1;
				}
			}
		}
		retVal=String.valueOf(count1)+"-"+String.valueOf(count2);
		return retVal;
	}
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public int[][] getBoard(){
		return model.getBoard();
	}
	
	/**
	 * Checks if is over.
	 *
	 * @return true, if is over
	 */
	public boolean isOver() {
		boolean a =false;
		boolean b =false;
		for (int i=0;i<7;i++) {
			for (int n=0;n<7;n++) {
				if(isLegal(i,n,1)) {
					a=true;
				}
				
				
			}
		}
		return !(a);
	}
}
