package lps.pr1;

/**
 * A command contains the information parsed from the input when the player
 * interacts with the game. This class encapsulates a Verb and a Direction (if
 * the Verb is MOVE)
 */
public class Command {

	/**
	 * Last direction parsed from the input stream 
	 */
	protected Parser.Direction dir;
	
	/**
	 * Last verb parsed from the scanner
	 */
	protected Parser.Verb verb;
	
	/**
	 * Constructor for a command. A command always needs a verb
	 * 
	 * @param verb
	 *            The verb contained in the command
	 */
	public Command(Parser.Verb verb) {
		this.verb = verb;
	}

	/**
	 * Get the verb contained in the command
	 * 
	 * @return the verb
	 */
	public Parser.Verb getVerb() {
		return verb;
	}

	/**
	 * Get direction contained in the command
	 * 
	 * @return the direction. Or null, when the command has no direction
	 */
	public Parser.Direction getDirection() {
		return dir;
	}
	
	/**
	 * It sets the direction contained in the command
	 * 
	 * @param dir
	 *            The new direction
	 */
	public void setDirection(Parser.Direction dir) {
		this.dir = dir;
	}

	/**
	 * It sets the verb contained in the command
	 * 
	 * @param verb
	 *            The new verb
	 */
	public void setVerb(Parser.Verb verb) {
		this.verb = verb;
	}
}
