package logic;

/**
 * Represents a 9 men morris game board
 *
 */
public class Board {
	private Node[][] board;

	/**
	 * Create a new game board, initialize all game spaces to unoccupied
	 */
	public Board() {
		board = new Node[3][8];
		int nodeCountX;
		int nodeCountY;
		Node newNode;
		// create Nodes for Board
		for (nodeCountY = 0; nodeCountY < 3; nodeCountY++) {
			for (nodeCountX = 0; nodeCountX < 8; nodeCountX++) {
				newNode = new Node();
				board[nodeCountY][nodeCountX] = newNode;
			}
		}
	}
	
	/**
	 * Sets the given point on the board to the given player
	 * @param square - row
	 * @param point - column
	 * @param newPlayer - player to set space as occupied
	 */
	public void setBoardNode( int square, int point, int newPlayer){
		board[square][point].setPlayer(newPlayer); 
		
	}
	
	/**
	 * Returns the player that is currently occupying that board space
	 * @param square - row
	 * @param point - column
	 * @return player occupying that space
	 */
	public int getBoardNode(int square, int point){
		return board[square][point].getPlayer();
	}
	
	/**
	 * Checks if the given point is within a mill for the given player
	 * @param square - row
	 * @param point - column
	 * @param player - player whose surrounding pieces to check for a mill
	 * @return true if there is a mill, false if not
	 */
	public boolean checkMill(int square, int point, int player){
		if(point%2 == 0){
			if(board[square][point].getPlayer() == player && board[square][(point+1)%8].getPlayer() == player && board[square][(point+2)%8].getPlayer() == player){
				return true;
			}
			if(board[square][point].getPlayer() == player && board[square][(point-1+8)%8].getPlayer() == player && board[square][(point-2+8)%8].getPlayer() == player){
				return true;
			}
		} else{
			if(board[0][point].getPlayer() == player && board[1][point].getPlayer() == player && board[2][point].getPlayer() == player){
				return true;
			}
			if(board[square][point-1].getPlayer() == player && board[square][point].getPlayer() == player && board[square][(point+1)%8].getPlayer() == player){
				return true;
			}	
		}
		return false;
	}
	
}
