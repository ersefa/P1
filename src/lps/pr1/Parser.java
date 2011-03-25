package lps.pr1;

import java.io.InputStream;
import java.util.Scanner;

/**
 * The parser is in charge of parsing the user input in order to generate a
 * command for the application. </BR> Up to now, the valid commands are:
 * 
 * <li>HELP</li> <li>LOOK</li> <li>GO { NORTH | SOUTH | EAST | WEST }</li> <li>
 * QUIT</li>
 * 
 * @see Command
 * @see Scanner
 */
public class Parser {

	private Command command;

	private InputStream streamIn;
	private Scanner reader;
	private Scanner readerTemp;

	/**
	 * It represents the compass directions: North, east, south and west.
	 */
	public enum Direction {
		NORTH, EAST, SOUTH, WEST;
	}

	/**
	 * Commands recognized by the application (help, go, look and quit)
	 */
	public enum Verb {
		/**
		 * Metacommand for asking for help about how to use the application
		 */
		HELP,

		/**
		 * Movement command. It allows the player to navigate in the map
		 */
		GO,

		/**
		 * Command for observing the room where the player currently stays
		 */
		LOOK,

		/**
		 * Metacommand for finishing the game
		 */
		QUIT;
	}

	/**
	 * Create a new input stream
	 * 
	 * @param in
	 *            Input stream
	 */
	public Parser(InputStream in) {
		streamIn = in;
		reader = new Scanner(streamIn).useDelimiter("\\n");
	}

	/**
	 * Parse the next command in the input stream. It returns a command if the
	 * input is parsed correctly
	 * 
	 * @return A configured command if we have found a correct command.
	 *         Otherwise, it returns null.
	 */
	public Command nextCommand() {
		if (reader.hasNext()) {
			// Put one line in a temp Scanner
			readerTemp = new Scanner(reader.next());
			String firstCommand = readerTemp.next();
			firstCommand = firstCommand.toUpperCase();

			// If invalid argument in switch, throws exception (valueOf())
			try {
				switch (Verb.valueOf(firstCommand)) {
				case HELP:
				case LOOK:
				case QUIT:
					command = new Command(Verb.valueOf(firstCommand));
					break;
				case GO:
					if (readerTemp.hasNext()) {
						String secondCommand = readerTemp.next();
						secondCommand = secondCommand.toUpperCase();
						command = new Command(Verb.valueOf(firstCommand));
						command.setDirection(Direction.valueOf(secondCommand));
					} else
						return null;
				}
				return command;
			} catch (IllegalArgumentException e) {
				return null;
			}
		} else
			return null;
	}

	/**
	 * It returns information about all the commands that the application
	 * understands
	 * 
	 * @return A string with the information about all the available commands
	 */
	public String getHelp() {
		return "You are lost. You are alone. You wander\n"
				+ "Your command words are:\n" + "  HELP\n" + "  LOOK\n"
				+ "  GO { NORTH | SOUTH | EAST | WEST }\n" + "  QUIT";
	}
}
