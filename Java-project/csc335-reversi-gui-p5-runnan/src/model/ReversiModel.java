package model;

import java.util.Observable;

/**
 * This class is the model of the Reversi game which is to initialize the
 * table and place the tokens. This class used to store the data we need to 
 * use when doing the reversi. It is primarily used as a storage which 
 * provide and accept information from controller or somewhere else. 
 * The data structure we choose is the string array. It is simple but effective
 * because the model is simply as this.The idea is make it as easy as possible.
 * This model has the get and set function whcih is basic in a class.
 * 
 * @author Wen Zhu & Runnan Zhou
 *
 */
public class ReversiModel extends Observable {
	private final int TABLE_SIZE = 8;
	private String[][] table = new String[TABLE_SIZE][TABLE_SIZE];
	private ReversiBoard rboard = new ReversiBoard(this);
	/**
	 * This is the constructor of the model which generate a default table.
	 */
	public ReversiModel() {
		// Initialize the table.
		for(int i = 0; i < TABLE_SIZE; i ++) {
			for(int j = 0; j < TABLE_SIZE; j ++) {
				table[i][j] = "_";
			}
		}
		table[3][3] = "W";
		table[3][4] = "B";
		table[4][3] = "B";
		table[4][4] = "W";
	}

	/**
	 * This method is to place a W token on the table at a specific location.
	 * 
	 * @param row The row of the token need to placed.
	 * @param col The column of the token need to placed.
	 */
	public void placeW(int row, int col) {
		setChanged();
		notifyObservers(rboard);
		table[row][col] = "W";
	}

	/**
	 * This method is to place a B token on the table at a specific location.
	 * 
	 * @param row The row of the token need to placed.
	 * @param col The column of the token need to placed.
	 */
	public void placeB(int row, int col) {
		setChanged();
		notifyObservers(rboard);
		table[row][col] = "B";
	}


	/**
	 * This method is to get a token on the table at a specific location.
	 * 
	 * @param row The row location of the token need to get.
	 * @param col The column location of the token need to get.
	 * @return The token at the given location.
	 */
	public String getTokenAt(int row, int col) {
		String tokenAt = table[row][col];
		return tokenAt;
	}

	/**
	 * This method will get a current table.
	 * 
	 * @return The current table.
	 */
	public String[][] getTable(){
		return table;
	}
	
	/**
	 * This method will get a current table.
	 * 
	 * @return The current table.
	 */
	public void setTable(String[][] newTable){
		for(int i = 0; i < TABLE_SIZE; i ++) {
			for(int j = 0; j < TABLE_SIZE; j ++) {
				table[i][j] = newTable[i][j];
			}
		}
	}

	/**
	 * This method will get the size of the table.
	 * 
	 * @return The size of the table.
	 */
	public int getTableSize() {
		return TABLE_SIZE;
	}

	/**
	 * This method will check if the current table is full or not.
	 * 
	 * @return A boolean variable to represent the status of the table.
	 */
	public boolean isFull() {
		for(int i = 0; i < TABLE_SIZE; i ++) {
			for(int j = 0; j < TABLE_SIZE; j ++) {
				if(table[i][j].equals("_")) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method will set the table according to ReversiBoard. 
	 * @param a The given ReversiBoard.
	 */
	public void getReversi(ReversiBoard a) {
		table = a.getTable();
	}
}
