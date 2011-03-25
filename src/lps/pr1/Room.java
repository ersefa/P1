package lps.pr1;

/**
 * It represents a room in the adventure game. Every room has no more than 4
 * doors (according to the 4 compass directions, North, South, East and West)
 * which can connect to another rooms. Every room has a textual description
 * about itself. This description is displayed when the player runs a LOOK
 * command. Some rooms are exit rooms. When the player arrives a this room, the
 * game finishes.
 */
public class Room {

	/**
	 * Room description.
	 */
	protected String _desc;

	/**
	 * Adjacent rooms (NORTH, EAST, SOUTH, WEST). There are no more than 4
	 * adjacent rooms. If _doors[X] = -1 then there is not any adjacent room in
	 * direction X
	 */
	protected int[] _doors;

	/**
	 * Is it an exit room?
	 */
	protected boolean _exitRoom;

	/**
	 * Maximum number of adjacent rooms. This number is fixed by the number of
	 * directions in the Interpreter.
	 */
	protected int _maxAdjRooms;

	/**
	 * Constructor
	 * 
	 * @param desc
	 *            Room description
	 * @param exitRoom
	 *            If this is an exit room or not
	 */
	public Room(String desc, boolean exitRoom) {
		_desc = desc;
		_exitRoom = exitRoom;
		_maxAdjRooms = 4;
		_doors = new int[_maxAdjRooms];

		// Array initialization
		for (int i = 0; i < _maxAdjRooms; i++) {
			_doors[i] = -1;
		}
	}

	/**
	 * It sets the adjacent rooms for a given direction
	 * 
	 * @param d
	 *            Door direction
	 * @param room
	 *            room number for the given direction
	 */
	public void setRoom(Parser.Direction d, int room) {
		_doors[d.ordinal()] = room;
	}

	/**
	 * Is it an exit room?
	 * 
	 * @return true if it is an exit room
	 */
	public boolean isExit() {
		return _exitRoom;
	}

	/**
	 * It returns true when there is no door in the given direction
	 * 
	 * @param d
	 *            Direction
	 * @return true if there is no room in the given direction. false otherwise
	 */
	public boolean isClosed(Parser.Direction d) {
		return (_doors[(d.ordinal())] == -1);
	}

	/**
	 * It returns the index of the room connected in the given direction. If
	 * there is not any connected room, it returns -1
	 * 
	 * 
	 * @param d
	 *            Direction for checking the room
	 * @return The index of the room connected with this one in the provided
	 *         direction. If the door in this direction is closed (it is not
	 *         connected to any room in this direction) it returns -1
	 */
	public int nextRoom(Parser.Direction d) {
		if (!isClosed(d)) {
			return (_doors[d.ordinal()]);
		}
		return -1;
	}

	/**
	 * It returns the room description
	 * 
	 * @see Object#toString()
	 */
	public String toString() {
		return _desc;
	}
}