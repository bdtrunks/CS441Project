package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains all Logic for 9 mens morris game.
 * Moving/removing pieces, tracking players and pieces, and finding winner.
 *
 */
public class Logic {
	private List<Point> playerOnePieces;
	private List<Point> playerTwoPieces;
	private List<Point> emptySpaces;
	private int playerTurn;
	private int phase; // phase 1 place pieces, phase 2 move pieces, phase 3
						// remove opponent, phase 4 game over
	private int piecesPlaced;
	private Board board;
	private int playerWon;

	/**
	 * Initialize Logic by starting with player 1 and creating a clear board
	 */
	public Logic() {
		emptySpaces = new LinkedList<>();
		for(int x=0;x<2;x++){
			for(int y=0;y<7;y++){
				Point point = new Point(x,y);
				emptySpaces.add(point);
			}
		}
		playerOnePieces = new LinkedList<Point>();
		playerTwoPieces = new LinkedList<Point>();
		playerTurn = 1;
		phase = 1;
		board = new Board();
		playerWon = 0;
		piecesPlaced = 0;
	}

	/**
	 * Sets the current player
	 */
	public void setPlayer() {
		if (playerTurn == 1) {
			playerTurn = 2;
		} else {
			playerTurn = 1;
		}
	}
	
	public List<Point> getEmptySpaces(){
		return emptySpaces;
	}

	/**
	 * Sets the current phase, 1 for placing, 2 for moving, 3 for removing, 4 for win
	 * @param phase value of phase to set
	 */
	public void setPhase(int phase) {
		this.phase = phase;
	}

	/**
	 * Returns the current board object FOR TESTING
	 * @return game board object
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Sets the given space as belonging to the given player
	 * @param square - row
	 * @param point - column
	 * @param newPlayer - player that will own the space
	 */
	public void setBoardNode(int square, int point, int newPlayer) {
		board.setBoardNode(square, point, newPlayer);
	}

	/**
	 * Returns the current phase of the game
	 * @return current phase
	 */
	public int getPhase() {
		return phase;
	}

	/**
	 * Returns the current player
	 * @return current player
	 */
	public int getPlayer() {
		return playerTurn;
	}

	/**
	 * Returns the number of spaces occupied by player 1
	 * @return number of player 1 spaces
	 */
	public int getPlayerOnePiece() {
		return playerOnePieces.size();
	}
	
	/**
	 * Returns the list of places occupied by player 1
	 * @return player 1 spaces
	 */
	public List<Point> getPlayerOnePieces() {
		return new ArrayList<Point>(playerOnePieces);
	}

	/**
	 * Returns the number of spaces occupied by player 2
	 * @return number of player 2 spaces
	 */
	public int getPlayerTwoPiece() {
		return playerTwoPieces.size();
	}

	/**
	 * Returns the list of places occupied by player 2
	 * @return player 2 spaces
	 */
	public List<Point> getPlayerTwoPieces() {
		return new ArrayList<Point>(playerTwoPieces);
	}

	/**
	 * Adds the given place to the list of spaces occupied by player 1
	 * @param add - point occupied by player 1
	 */
	public void addPlayerOnePieces(Point add) {
		playerOnePieces.add(add);
	}
	/**
	 * Adds the given place to the list of spaces occupied by player 2
	 * @param add - point occupied by player 2
	 */
	public void addPlayerTwoPieces(Point add) {
		playerTwoPieces.add(add);
	}

	/**
	 * Checks for any available moves for the current player from the given space
	 * @param square - row
	 * @param point - column
	 * @return collection of points containing valid moves
	 */
	public Collection<Point> checkMoves(int square, int point) {
		Collection<Point> result = new LinkedList<Point>();
		if (phase != 2) {
			return result;
		}
		if (board.getBoardNode(square, point) != playerTurn) {
			return result;
		}

		if (checkFly(playerTurn)) {
			Point add;
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 8; y++) {
					if (board.getBoardNode(x, y) == 0) {
						add = new Point(x, y);
						result.add(add);
					}
				}
			}
			return result;
		}

		if (point % 2 == 0) {
			if (board.getBoardNode(square, ((point + 1) % 8)) == 0) {
				Point add = new Point(square, ((point + 1) % 8));
				result.add(add);
			}
			if (board.getBoardNode(square, ((point - 1 + 8) % 8)) == 0) {
				Point add = new Point(square, ((point - 1 + 8) % 8));
				result.add(add);
			}
		} else if (point % 2 == 1 && square == 0) {
			if (board.getBoardNode(square, ((point - 1 + 8) % 8)) == 0) {
				Point add = new Point(square, ((point - 1 + 8) % 8));
				result.add(add);
			}
			if (board.getBoardNode(square, ((point + 1) % 8)) == 0) {
				Point add = new Point(square, ((point + 1) % 8));
				result.add(add);
			}
			if (board.getBoardNode(square + 1, point) == 0) {
				Point add = new Point(square + 1, point);
				result.add(add);
			}

		} else if (point % 2 == 1 && square == 1) {
			if (board.getBoardNode(square, ((point - 1 + 8) % 8)) == 0) {
				Point add = new Point(square, ((point - 1 + 8) % 8));
				result.add(add);
			}
			if (board.getBoardNode(square, ((point + 1) % 8)) == 0) {
				Point add = new Point(square, ((point + 1) % 8));
				result.add(add);
			}
			if (board.getBoardNode(square + 1, point) == 0) {
				Point add = new Point(square + 1, point);
				result.add(add);
			}
			if (board.getBoardNode(square - 1, point) == 0) {
				Point add = new Point(square - 1, point);
				result.add(add);
			}
		} else if (point % 2 == 1 && square == 2) {
			if (board.getBoardNode(square, ((point - 1 + 8) % 8)) == 0) {
				Point add = new Point(square, ((point - 1 + 8) % 8));
				result.add(add);
			}
			if (board.getBoardNode(square, ((point + 1) % 8)) == 0) {
				Point add = new Point(square, ((point + 1) % 8));
				result.add(add);
			}
			if (board.getBoardNode(square - 1, point) == 0) {
				Point add = new Point(square - 1, point);
				result.add(add);
			}
		}
		return result;
	}

	/**
	 * Checks if a player has 3 pieces left, enables that player to move anywhere available
	 * @param player - the player to check
	 * @return true if player can fly, false if not
	 */
	public boolean checkFly(int player) {
		if (player == 1 & playerOnePieces.size() == 3) {
			return true;
		}
		if (player == 2 & playerTwoPieces.size() == 3) {
			return true;
		}
		return false;
	}

	/**
	 * Places pieces on available spaces during phase 1
	 * @param square - row
	 * @param point - column
	 * @return true if placement successful, false if not
	 */
	public boolean placePiece(int square, int point) {
		if (board.getBoardNode(square, point) == 0) {
			board.setBoardNode(square, point, playerTurn);
			Point add = new Point(square, point);
			if (playerTurn == 1) {
				playerOnePieces.add(add);
			} else {
				playerTwoPieces.add(add);
			}
			emptySpaces.remove(add);
			if (board.checkMill(square, point, playerTurn)) {
				phase = 3;
				piecesPlaced++;
			} else {
				setPlayer();
				piecesPlaced++;
				if (piecesPlaced == 18) {
					phase = 2;
					checkWin();
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Attempts to move the current player's piece to a new available space
	 * @param square - current row
	 * @param point - current column
	 * @param moveSquare - row to move to
	 * @param movePoint - column to move to
	 * @return true if move successful, false if not
	 */
	public boolean movePiece(int square, int point, int moveSquare, int movePoint) {
		Collection<Point> moves = checkMoves(square, point);
		if (moves.isEmpty()) {
			return false;
		}
		Point check = new Point(moveSquare, movePoint);
		if (moves.contains(check)) {
			board.setBoardNode(square, point, 0);
			board.setBoardNode(moveSquare, movePoint, playerTurn);
			Point remove = new Point(moveSquare,movePoint);
			if (playerTurn == 1) {
				playerOnePieces.remove(check);
				playerOnePieces.add(remove);
			} else {
				playerTwoPieces.remove(check);
				playerTwoPieces.add(remove);
			}
			emptySpaces.remove(remove);
			emptySpaces.add(check);
			if (board.checkMill(moveSquare, movePoint, playerTurn)) {
				phase = 3;
			} else {
				setPlayer();
				checkWin();
			}
			return true;
		}
		return false;
	}

	/**
	 * Attempts to remove a player's piece from the board
	 * @param square - row
	 * @param point - column
	 * @return true if remove successful, false if not
	 */
	public boolean removePiece(int square, int point) {
		if (playerTurn == 1)
			return remove(square, point, 2, playerTwoPieces);
		else
			return remove(square, point, 1, playerOnePieces);
	}

	/**
	 * Helper method for removePiece that attempts to remove piece of the given player
	 * @param square - row
	 * @param point - column
	 * @param player - player whose piece is to be removed
	 * @param playerPieces - placement of pieces for that player
	 * @return true if remove successful, false if not
	 */
	public boolean remove(int square, int point, int player, List<Point> playerPieces) {
		Point remove = new Point(square, point);
		if (board.getBoardNode(square, point) == player) {
			if (!board.checkMill(square, point, player) || playerPieces.size() <= 3) {
				board.setBoardNode(square, point, 0);
				playerPieces.remove(remove);
				emptySpaces.add(remove);
				setPlayer();

				if (piecesPlaced == 18) {
					phase = 2;
					checkWin();
				} else
					phase = 1;
				return true;
			}

			for (Point test : playerPieces) {
				if (!board.checkMill(test.x, test.y, player)) {
					return false;
				}
			}
			board.setBoardNode(square, point, 0);
			playerPieces.remove(remove);
			setPlayer();

			if (piecesPlaced == 18) {
				phase = 2;
				checkWin();
			} else
				phase = 1;
			return true;

		}
		return false;

	}

	/**
	 * Checks for a win condition (one player less than 3 pieces or no valid moves)
	 */
	public void checkWin() {
		if (playerTurn == 1) {
			if (isLoser(playerOnePieces)) {
				playerWon = 2;
				phase = 4;
			}
		}
		if (playerTurn == 2) {
			if (isLoser(playerTwoPieces)) {
				playerWon = 1;
				phase = 4;
			}
		}
	}

	/**
	 * Helper method for checkWin, tests for less than 3 pieces and no valid moves
	 * @param playerPieces - the player whose pieces are being checked
	 * @return true if given player is a loser, false if not
	 */
	public boolean isLoser(List<Point> playerPieces) {
		if (playerPieces.size() < 3) {
			return true;
		}
		Iterator<Point> iterator = playerPieces.iterator();
		while (iterator.hasNext()) {
			Point check = iterator.next();
			Collection<Point> moves = checkMoves(check.x, check.y);
			if (!moves.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the winner of the game
	 * @return which player won
	 */
	public int getWinner() {
		return playerWon;
	}

}