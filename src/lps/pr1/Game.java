package lps.pr1;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * This class is responsible for controlling the main loop of the adventure
 * game. It creates the Map and the Interpreter and runs the main loop until the
 * user arrives at an exit room. The Game need an InputStream from the player
 * commands are extracted and an OutputStream where the Game is going to write
 * the messages
 */
public class Game {

	/**
	 * Map that contains the rooms
	 */
	protected Map _map;

	/**
	 * The parser responsible for interpreting the user instructions
	 */
	protected Parser _parser;

	private final String GAME_OVER = "GAME OVER\nThank you for playing, goodbye.";
	private final String PROMPT = "> ";
	private final String ERROR_COMMAND = "What?";
	private final String LINE_SEPARATOR = System.getProperty("line.separator");

	private InputStream streamIn;
	private OutputStream streamOut;

	private boolean exitReached;
	private PrintStream ps;

	/**
	 * Application constructor
	 * 
	 * @param in
	 *            Stream that captures the user input
	 * @param out
	 *            Stream that prints the application messages
	 */
	public Game(InputStream in, OutputStream out) {
		streamIn = in;
		streamOut = out;
		if (streamOut != null)
			ps = new PrintStream(streamOut);
		exitReached = false;
	}

	/**
	 * It initializes the application, creating the map and the parser
	 * 
	 * @return true if the application was initialized without errors
	 */
	public boolean init() {
		if (streamIn != null && streamOut != null) {
			_map = new Map(streamOut);
			_parser = new Parser(streamIn);
			return true;
		} else
			return false;
	}

	/**
	 * Main loop of the game
	 */
	public void runGame() {
		_map.initMap();

		while (!exitReached) {
			ps.print(LINE_SEPARATOR + PROMPT);
			Command command = _parser.nextCommand();
			if (command != null) {
				switch (command.getVerb()) {
				case HELP:
					_map.printHelp(_parser.getHelp());
					break;
				case LOOK:
					_map.describeRoom();
					break;
				case QUIT:
					exitReached = true;
					break;
				case GO:
					exitReached = _map.changeRoom(command.getDirection());
					break;
				}
			} else
				ps.println(ERROR_COMMAND);
		}
		ps.println(GAME_OVER);
	}
}
