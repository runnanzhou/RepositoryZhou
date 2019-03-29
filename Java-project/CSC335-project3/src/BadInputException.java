/**
 * The Class BadInputException. Creat to catch the unlegal step to place the
 * chess.
 */
public class BadInputException extends Exception {

/**
 * Instantiates a new bad input exception.
 *
 * @param message waring message
 */
public BadInputException(String message) {
	super(message);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	public String toString() {
		return "Bad input! Plz try again!"
				;
	}
}
