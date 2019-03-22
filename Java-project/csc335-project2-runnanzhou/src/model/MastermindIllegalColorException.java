package model;

public class MastermindIllegalColorException extends Exception {
	/**
	 * This class is the exception class. Basically it throw the exception
	 * when the program can't handle it. It extends from the exception class.
	 * The idea is when the color is not illegal, throw it.
	 */
	private static final long serialVersionUID = 1L;
	public MastermindIllegalColorException(String message) {
		//constructor of the class. inherit from the class "exception".
		super(message);
	}
	public String toString() {
		return getMessage();
	}
}
