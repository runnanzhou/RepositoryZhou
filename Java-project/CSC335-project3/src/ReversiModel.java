
/**
 * The Class ReversiModel is use to store the data and serve as the core of 
 * the program. It provide important information for the viewer and controller.
 * Based on the MVC, it is less complicate than the controller because its task
 * is very simple. Store the data.
 */
public class ReversiModel {


	/** The blank. */
	public int BLANK =0;
	

	/** The white. */
	public int WHITE =1;
	
	
	/** The black. */
	public int BLACK =2;
	

	/** The board dimension. */
	public static int BOARD_DIMENSION=8;
	
	
	/** The board. */
	int [] [] board;
	
	/**
	 * Instantiates a new reversi model.
	 */
	public ReversiModel() {
		board = new int [BOARD_DIMENSION][BOARD_DIMENSION];
		initialization(board);
		
	}
	
	/**
	 * Initialization.
	 *
	 * @param board the board
	 */
	private void initialization(int [] [] board) {
		board[3][3]=1;
		board[4][4]=1;
		board[4][3]=2;
		board[3][4]=2;
	}
	
	/**
	 * Gets the element.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the element
	 */
	public int getElement(int x, int y) {
		if (x<=7 && x>=0 && y<=7 && y>=0) {
			return board[x][y];
		}else {
			return 100;
		}
		
	}
	
	/**
	 * Revise.
	 *
	 * @param x the x
	 * @param y the y
	 * @param id the id
	 */
	public void revise(int x, int y, int id) {
		board[x][y]=id;
		
	}
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public int[][] getBoard(){
		return board;
	}
}
 