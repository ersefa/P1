/**
 * Practica 1 LPS 2010/2011 Grupo 12
 * 
 * @author Jose Luis Garcia Martinez
 * 
 */
package lps.pr1;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Entrance point of the application. It configures and starts the game
 */
public class Main {

	private static InputStream streamIn;
	private static OutputStream streamOut;

	/**
	 * Main method. It initializes and starts the game.
	 * 
	 * @param args
	 *            Console line arguments
	 */
	public static void main(String[] args) {

		/**
		 * Stream Input/Output configuration
		 */
		streamIn = System.in;
		streamOut = System.out;

		Game game = new Game(streamIn, streamOut);
		if (game.init())
			game.runGame();
		else {
			PrintStream ps = new PrintStream(streamOut);
			ps.println("Intitialization Error");
		}
			
	}
}
