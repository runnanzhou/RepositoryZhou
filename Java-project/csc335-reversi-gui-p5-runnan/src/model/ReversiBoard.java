package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/*This class is used to create an instance in the model class. It is used to 
 * compare that if there is a change happened in the model class. Otherwise,
 * it is used to output a file which is called saved.dat which save the 
 * game process to a file and we can reload it anytime we reopen the game. It 
 * is implement with the class serializable which enable us to do so. Give 
 * exception when it is necessary.
 * @author: Runnan Zhou &Wen Zhu

 * 
 */
public class ReversiBoard implements Serializable {


	private static final long serialVersionUID = 1L;
	private String[][] table;
	
	/**
	 * This is the constructor of the ReversiBoard which will initialize with a table.
	 * @param model
	 */
	public ReversiBoard(ReversiModel model) {
		table = model.getTable();
	}

	/**
	 * This method will load the board from the file "save_game.dat"
	 * @exception:FileNotFoundException, IOExepetion ,ClassNotFoundException.
	 * 
	 */
	public void isSave() {
		try{
			FileInputStream fis= new FileInputStream("save_game.dat");
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fis);
			table=(String[][])ois.readObject();
		}catch(FileNotFoundException e) {

		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method will return the table.
	 * @return String [] []table.
	 */
	public String[][] getTable(){
		return table;
	}
}
