/*It is the class that designed by myself. The reason to create this class is
 * because when i use the move function(at that time) i found i need to return
 * 2 values. Which is not a good style in a function. Then i think, it is a good
 * idea to separate it as a class. It shows a kind of separate algorithm part
 * to help this program. As we all know, algorithm is a important part. So 
 * if the algorithm changed, we can simply change this seciton and the control
 * and we are good.
 * 
 */
import java.util.ArrayList;

/**
 * The Class ReversiMove.
 */
public class ReversiMove {

	/** The value X. */
	private ArrayList<Integer> valueX = new ArrayList<Integer>();
	

	/** The value Y. */
	private ArrayList<Integer> valueY = new ArrayList<Integer>();
	

	/** The x. */
	private int x;

	/** The y. */
	private int y;
	
	/**  The dx is the value that has changed on the X. */
	private int dx;
	
	/**  The dx is the value that has changed on the Y. */
	private int dy;
	
	/**  The id is the feature of the chess. */
	private int id;
	
	/** The board. */
	private int [] [] board;
	
	/** The other is the opposite color of the current chess. */
	private int other;
	
	/**  The end means whether the routine will met a same color chess. */
	private int end=0;
	
	/**  The stop means stop the running when the value is one. */
	private int stop=0;
	
	/** The model. */
	private ReversiModel model = new ReversiModel();
	
	/**
	 * Instantiates a new reversi move.
	 *
	 * @param x the x
	 * @param y the y
	 * @param dx the dx
	 * @param dy the dy
	 * @param id the id
	 * @param model the model
	 */
	public ReversiMove(int x, int y, int dx, int dy ,int id, 
			ReversiModel model) {
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		this.id=id;
		this.model=model;
		
		while (stop!=1) {
			move(model);
		}
		
		
	}
	
	/**
	 * Gets the profit of the current step.Which is the length of the list.
	 *
	 * @return the profit
	 */
	public int getProfit() {
		if (valueX.size()==valueY.size() && end!=0) {
			return valueX.size(); 
		}else {
			return 1000;
		}
		
	}
	
	/**
	 * Move and see what will be met during the routine. It is added to the
	 * list if there is an opposite color chess. Or it will make end become 1
	 * if the same color chess are met.
	 *
	 * @param model the model
	 */
	private void move(ReversiModel model) {
		//move the coordinate and seek for the value.
		x=x+dx;
		y=y+dy;
		
		int cur =model.getElement(x, y);
		// if the input value not meet the requirement of input, it will return
		//100. Then, it is time to jump out of this function.
		if (cur==100 || cur==0) {
			stop=1;
			return;
		}
		
		if (id ==1 ) {
			other=2;
		}else {
			other=1;
		}
		
		//if we found the opposite value in the board, we add it into the list.
		if (cur == other ) {
		
			valueX.add(x);
			valueY.add(y);
			
		}else if (cur == id) {
			end=1;
			stop=1;
		}
		
	}
	
	/**
	 * Gets the value X.
	 *
	 * @return the value X
	 */
	public ArrayList<Integer> getValueX(){
		if (end==1) {
			return valueX;
		}else {
			return new ArrayList<Integer>();
		}
		
	}
	
	/**
	 * Gets the value Y.
	 *
	 * @return the value Y
	 */
	public ArrayList<Integer> getValueY(){
		if (end==1) {
			return valueY;
		}else {
			return new ArrayList<Integer>();
		}
	}
	
	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public int getEnd(){
		return end;
	}
	
	/**
	 * Checks if is legal.
	 *
	 * @return true, if is legal
	 */
	public boolean isLegal() {
		
		if (valueX.size()>0 && valueY.size()>0 && end==1) {
			return true;
		}else {return false;}
	}
}
