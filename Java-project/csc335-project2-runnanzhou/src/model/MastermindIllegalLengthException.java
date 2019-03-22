package model;

public class MastermindIllegalLengthException extends Exception {
	/**
	 * 
	 * This class is the exception class. Basically it throw the exception
	 * when the program can't handle it. It extends from the exception class.
	 * The idea of this class is when the leghth is not acceptable. Throw the 
	 * exception
	 *
	 */
	private static final long serialVersionUID = 1L;
	public MastermindIllegalLengthException(String message) {
		//constructor of the class.
		super(message);
	}
	public String toString() {
		return getMessage();
	}
}
