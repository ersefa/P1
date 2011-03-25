package lps.pr1;

import java.io.OutputStream;
import java.io.PrintStream;

import lps.pr1.Parser.Direction;

/**
 * This class represents the map that the player is going to explore. The map is
 * made of places (rooms). We have no more than 20 rooms. It is responsible for
 * controlling where the player is and going from one place to another.
 * Additionally, it uses an OutputStream to print messages about what is
 * happening during the game.
 * 
 * @see PrintStream
 */
public class Map {
	/**
	 * Room where the player stays
	 */
	protected int _currentRoom;

	/**
	 * All the rooms contained in the map. The initial room is always the one
	 * stored in position 0.
	 */
	protected Room _rooms[];

	/**
	 * Array of rooms (20 max.)
	 */
	private final int MAXROOMS = 20;
	private final String WAYCLOSED = "The way is closed in direction ";
	private final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	/**
	 * Stream handling
	 */
	private OutputStream streamOut;
	private PrintStream ps;
		
	/**
	 * Constructor
	 * 
	 * @param out
	 *            The outputstream where the Map is going to flush all the
	 *            information during the game
	 */
	public Map(OutputStream out) {
		streamOut = out;
		_rooms = new Room[MAXROOMS];
		ps = new PrintStream(streamOut);
	}

	/**
	 * It prints the description of the current room on the outputstream
	 */
	public void describeRoom() {
		ps.println(_rooms[_currentRoom].toString());
	}

	/**
	 * It changes the current room according to the given direction. If it
	 * cannot change the room in this direction (there is a wall), then it
	 * writes a warning message on the outputstream. If it has changed the room,
	 * then it writes the room description on the outputstream.
	 * 
	 * @param d
	 *            The direction we want to walk to.
	 * @return true if we have arrived at the exit. False, otherwise.
	 */
	public boolean changeRoom(Parser.Direction d) {
		// If there is a room in the given direction(!isClosed()) && the room is
		// not closed (!=-1)
		if ((!_rooms[_currentRoom].isClosed(d))
				&& (_rooms[_currentRoom].nextRoom(d) != -1)) {
			_currentRoom = _rooms[_currentRoom].nextRoom(d);
			describeRoom();
		} else
			ps.println(WAYCLOSED + d);

		return (_rooms[_currentRoom].isExit());
	}

	/**
	 * It creates the rooms contained in the map, sets the initial room where we
	 * are going to start the game and writes the description from the initial
	 * room on the outputstream
	 * 
	 * @return True if there was no problems during the initialization. False,
	 *         otherwise.
	 */
	public boolean initMap() {
		_currentRoom = createRooms();
		describeRoom();
		return true;
	}

	/**
	 * Creates all the rooms contained in the map
	 * 
	 * @return The room number where the player starts the game
	 */
	protected int createRooms() {
		// 0: Main Entrance
		_rooms[0] = new Room(
				"MAIN ENTRANCE"
						+ LINE_SEPARATOR
						+ "You are at the School of Computer Science, at Complutense University of Madrid. "
						+ "Completely desperate due to the first Java assignment that you have to finish tomorrow, "
						+ "you are looking for Federico or Guillermo, the lecturers who can help you with it."
						+ LINE_SEPARATOR
						+ "They are your last hope for completing the assignment on time!",
				false);
		// 1: Bulletin board
		_rooms[1] = new Room(
				"BULLETIN BOARD"
						+ LINE_SEPARATOR
						+ "Mmmh... it seems both Federico and Guillermo work in Room 411, on the 4th floor.",
				false);

		// 2: Library
		_rooms[2] = new Room(
				"LIBRARY ENTRANCE"
						+ LINE_SEPARATOR
						+ "Firstly, you can visit the library. Maybe you can find a good book about Java instead of disturbing the lecturers."
						+ LINE_SEPARATOR
						+ "Wait! What the f..., it is their work! isn´t it?... No way, you decide to talk with them.",
				false);

		// 3: Bus (END)
		_rooms[3] = new Room(
				"BUS STOP"
						+ LINE_SEPARATOR
						+ "The bus is coming... and you think: these lecturers are very rude and they are not going to help me"
						+ LINE_SEPARATOR
						+ "Finally, you decide to go back home.", 
				true);

		// 4: Hall
		_rooms[4] = new Room(
				"HALL"
						+ LINE_SEPARATOR
						+ "You have to go to the 4th floor. So you can take the lift... but using the stairs will be better for reducing your belly."
						+ LINE_SEPARATOR
						+ "Another option could be eating something at the canteen: you have a lot of questions for them and should be ready.",
				false);

		// 5: Lift
		_rooms[5] = new Room(
				"LIFT" 
						+ LINE_SEPARATOR
						+ "Buf! You're tired and prefer to take the lift."
						+ LINE_SEPARATOR + "CRAAK ... CRAAK ... CRAAK ... CRAAK"
						+ LINE_SEPARATOR
						+ "The doors are opened. You have arrived at the 4th floor",
				false);

		// 6: Stairs
		_rooms[6] = new Room(
				"STAIRS"
						+ LINE_SEPARATOR
						+ "You think: It's true! I'am fat! I'll take the stairs..."
						+ LINE_SEPARATOR
						+ "1, 2, 3 ... 134, ... 135, ... 136, ..."
						+ "Buf! At last! You have arrived at the 4th floor. Mental note: this is the last time you take the stairs."
						+ LINE_SEPARATOR, false);

		// 7: Canteen (END)
		_rooms[7] = new Room(
				"CANTEEN"
						+ LINE_SEPARATOR
						+ "You decide to eat something first."
						+ LINE_SEPARATOR
						+ "When you go into the canteen, your friends are there... AND THEY ARE PLAYING THE LAST FIFA!!!"
						+ LINE_SEPARATOR
						+ "Ok... only one match before going upstairs. You need some relax..."
						+ LINE_SEPARATOR
						+ "(3 hours later...) Oh sh..!!! It is too late! You run upstairs but the Room 411 is closed."
						+ LINE_SEPARATOR
						+ "Definetly, you are not going to finish the assignment on time :-(",
				true);

		// 8: Fourth floor
		_rooms[8] = new Room(
				"4th FLOOR"
						+ LINE_SEPARATOR
						+ "Ok, you're here and it seems that the Room 411 is open. You drink some water, first (really thirsty!)."
						+ LINE_SEPARATOR
						+ "Well, hopefully the lecturers will help you. That would be very kind.",
				false);

		// 9: Room 411 (END)
		_rooms[9] = new Room(
				"ROOM 411"
						+ LINE_SEPARATOR
						+ "Oh my God! There are 10 people in this room and you don't know how Guillermo or Federico look like."
						+ LINE_SEPARATOR
						+ "\"Excuse me. I'm looking for Guillermo.\""
						+ LINE_SEPARATOR
						+ "(Guillermo) Go away! I cannot help you. It is not office hours. Come back tomorrow... (no matter if it will be too late for your assignment, muahahaha)"
						+ LINE_SEPARATOR
						+ "\"(Ouch!) And... is Federico here?\""
						+ LINE_SEPARATOR
						+ "(Federico) Yes, mate. How can I help you?"
						+ LINE_SEPARATOR
						+ "(1 hour later)... (Federico) and, finally, create the Game object and initialize the Map with THIS source code. It is easy! :-) Do you want me to repeat it from the beginning?"
						+ LINE_SEPARATOR
						+ "Oh... no, thanks a lot :-) (Cool!!! It is done! My first assignment will be on time!)",
				true);

		_rooms[0].setRoom(Direction.NORTH, 2);
		_rooms[0].setRoom(Direction.SOUTH, 3);
		_rooms[0].setRoom(Direction.EAST, 1);
		_rooms[0].setRoom(Direction.WEST, 4);

		_rooms[1].setRoom(Direction.WEST, 0);

		_rooms[2].setRoom(Direction.SOUTH, 0);

		_rooms[4].setRoom(Direction.NORTH, 5);
		_rooms[4].setRoom(Direction.SOUTH, 6);
		_rooms[4].setRoom(Direction.EAST, 0);
		_rooms[4].setRoom(Direction.WEST, 7);

		_rooms[5].setRoom(Direction.SOUTH, 8);
		_rooms[6].setRoom(Direction.NORTH, 8);

		_rooms[8].setRoom(Direction.NORTH, 5);
		_rooms[8].setRoom(Direction.SOUTH, 6);
		_rooms[8].setRoom(Direction.WEST, 9);

		// Initial room is room 0
		return 0;
	}

	/**
	 * It writes on the outputstream the information about the help
	 * 
	 * @param help
	 *            The string that contains the information about the help
	 */
	public void printHelp(java.lang.String help) {
		ps.println(help);
	}
}
