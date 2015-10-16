package logic;

/**
 * Represents a space on a 9 men morris game board
 *
 */
public class Node {

	private int player; // 0=empty, 1=player1, 2=player2
	
	/**
	 * Initializes a game space to have no player on it
	 */
	public Node(){
		player = 0;
	}
	
	/**
	 * Returns the player currently occupying the game space
	 * @return player occupying space
	 */
	public int getPlayer(){
		return player;
	}
	
	/**
	 * Sets the game space to be occupied by the given player
	 * @param newPlayer - player to occupy game space
	 */
	public void setPlayer(int newPlayer){
		this.player = newPlayer;
	}
}
