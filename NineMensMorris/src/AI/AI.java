package AI;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import logic.Board;
import logic.Logic;

/**
 * Represents computer player in 9 Men's Morris game
 *
 */
public class AI {

	private Board board;
	private Random rand;
	private List<Point> emptySpaces;
	private Logic logic;
	private int difficulty;
	private boolean pause;

	public AI(int difficulty) {
		pause = true;
		rand = new Random();
		if (difficulty < 0 || difficulty > 1) {
			this.difficulty = 2;
		} else {
			this.difficulty = difficulty; // 0=easy, 1=normal
		}
	}
	
	public AI(int difficulty, int seed, boolean pause) {
		pause = false;
		rand = new Random(seed);
		if (difficulty < 0 || difficulty > 1) {
			this.difficulty = 2;
		} else {
			this.difficulty = difficulty; // 0=easy, 1=normal
		}
	}

	/**
	 * Performs the logic to determine what the computer will do during its turn
	 * 
	 * @param logic
	 *            - game logic
	 * @return true if turn was successful
	 */
	public boolean turn(Logic logic) {
		if (logic.getPlayer() != 2)
			return false;

		this.logic = logic;
		emptySpaces = logic.getEmptySpaces();
		board = logic.getBoard();
		if (pause)
			pause();

		if (difficulty == 1) {
			// MOVING PIECES PHASE
			if (logic.getPhase() == 2 && movePiece())
				return true;

			// PLACING PIECES PHASE
			if (logic.getPhase() == 1 && placePiece())
				return true;

			// REMOVING PIECES PHASE
			if (logic.getPhase() == 3 && removePiece())
				return true;

		}

		if (difficulty == 0) {
			// MOVING PIECES PHASE
			if (logic.getPhase() == 2 && moveRandom())
				return true;

			// PLACING PIECES PHASE
			if (logic.getPhase() == 1 && placeRandom())
				return true;

			// REMOVING PIECES PHASE
			if (logic.getPhase() == 3 && removeRandom())
				return true;

		}

		return false;
	}

	/**
	 * Delays the game, otherwise the computer would move instantly
	 */
	private void pause() {
		long current = System.currentTimeMillis();
		long end = current + 1000;
		while (current < end) {
			current = System.currentTimeMillis();
		}
	}

	/**
	 * Performs logic to determine what piece the computer should move and where
	 * to move it to
	 * 
	 * @return true if successful move
	 */
	private boolean movePiece() {
		List<Point> aiPieces = logic.getPlayerTwoPieces();
		Point piece, move;
		Collection<Point> places;
		// Move to create mill if possible
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (aiPieces.contains(new Point(i, j)) && logic.checkMoves(i, j).size() != 0) {
					places = logic.checkMoves(i, j);
					Point[] moves = (Point[]) places.toArray(new Point[places.size()]);
					for (int k = 0; k < moves.length; k++) {
						int x = moves[k].x;
						int y = moves[k].y;
						board.setBoardNode(i, j, 0);
						board.setBoardNode(x, y, 2);
						if (board.checkMill(x, y, 2)) {
							board.setBoardNode(i, j, 2);
							board.setBoardNode(x, y, 0);
							logic.movePiece(i, j, x, y);
							return true;
						}
						board.setBoardNode(i, j, 2);
						board.setBoardNode(x, y, 0);
					}
				}
			}
		}
		// Move to block mill if possible
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (aiPieces.contains(new Point(i, j)) && logic.checkMoves(i, j).size() != 0) {
					places = logic.checkMoves(i, j);
					Point[] moves = (Point[]) places.toArray(new Point[places.size()]);
					for (int k = 0; k < moves.length; k++) {
						int x = moves[k].x;
						int y = moves[k].y;
						board.setBoardNode(x, y, 1);
						if (board.checkMill(x, y, 1)) {
							board.setBoardNode(x, y, 0);
							logic.movePiece(i, j, x, y);
							return true;
						}
						board.setBoardNode(x, y, 0);
					}
				}
			}
		}
		// Check to move piece that is within a mill so will create mill on next
		// turn
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (aiPieces.contains(new Point(i, j)) && logic.checkMoves(i, j).size() != 0
						&& board.checkMill(i, j, 2)) {
					places = logic.checkMoves(i, j);
					Point[] moves = (Point[]) places.toArray(new Point[places.size()]);
					for (int k = 0; k < moves.length; k++) {
						int x = moves[k].x;
						int y = moves[k].y;
						if (logic.movePiece(i, j, x, y))
							return true;
					}
				}
			}
		}
		// Random Move
		return moveRandom();
	}

	private boolean moveRandom() {
		List<Point> aiPieces = logic.getPlayerTwoPieces();
		Point piece, move;
		Collection<Point> places;

		do {
			piece = aiPieces.get(rand.nextInt(aiPieces.size()));
			places = logic.checkMoves(piece.x, piece.y);
		} while (places.size() == 0);
		move = (Point) places.toArray()[rand.nextInt(places.size())];
		logic.movePiece(piece.x, piece.y, move.x, move.y);
		return true;

	}

	/**
	 * Performs logic to determine where the computer should place a piece
	 * 
	 * @return true if placement of piece is successful
	 */
	private boolean placePiece() {
		// Check all available spaces to see if placing creates mill, if so
		// place there
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (emptySpaces.contains(new Point(i, j))) {
					board.setBoardNode(i, j, 2);
					if (board.checkMill(i, j, 2)) {
						board.setBoardNode(i, j, 0);
						logic.placePiece(i, j);
						return true;
					}
					board.setBoardNode(i, j, 0);
				}
			}
		}
		// Check all available spaces for possible block of opponent mill
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (emptySpaces.contains(new Point(i, j))) {
					board.setBoardNode(i, j, 1);
					if (board.checkMill(i, j, 1)) {
						board.setBoardNode(i, j, 0);
						logic.placePiece(i, j);
						return true;
					}
					board.setBoardNode(i, j, 0);
				}
			}
		}
		// Check all available spaces to see if possible to place next to other
		// pieces
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.getBoardNode(i, j) == 2) {
					boolean iminus1, iplus1, jminus1, jplus1;
					iminus1 = iplus1 = jminus1 = jplus1 = true;
					if (i == 0)
						iminus1 = false;
					if (i == 2)
						iplus1 = false;
					if (j == 0)
						jminus1 = false;
					if (j == 7)
						jplus1 = false;
					if ((j % 2 != 0
							&& ((iplus1 && logic.placePiece(i + 1, j)) || (iminus1 && logic.placePiece(i - 1, j))))
							|| (jminus1 && logic.placePiece(i, j - 1)) || (jplus1 && logic.placePiece(i, j + 1))) {
						return true;
					}
				}
			}
		}
		// Random Move

		return placeRandom();
	}

	private boolean placeRandom() {
		Point move = emptySpaces.get(rand.nextInt(emptySpaces.size()));
		logic.placePiece(move.x, move.y);
		return true;
	}

	/**
	 * Performs logic for computer to determine what piece to remove
	 * 
	 * @return true if successful removal
	 */
	private boolean removePiece() {
		List<Point> playerPieces = logic.getPlayerOnePieces();
		Point remove;
		// Attempt to remove player pieces that are blocking a mill
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (playerPieces.contains(new Point(i, j))) {
					board.setBoardNode(i, j, 2);
					if (board.checkMill(i, j, 2)) {
						board.setBoardNode(i, j, 1);
						if (logic.removePiece(i, j))
							return true;
					}
					board.setBoardNode(i, j, 1);
				}
			}
		}
		// Attempt to remove player pieces that are close to forming mill
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				if (emptySpaces.contains(new Point(i, j))) {
					board.setBoardNode(i, j, 1);
					if (board.checkMill(i, j, 1)) {
						board.setBoardNode(i, j, 0);
						boolean iminus1, iplus1, jminus1, jplus1;
						iminus1 = iplus1 = jminus1 = jplus1 = true;
						if (i == 0)
							iminus1 = false;
						if (i == 2)
							iplus1 = false;
						if (j == 0)
							jminus1 = false;
						if (j == 7)
							jplus1 = false;
						if ((j % 2 != 0 && ((iplus1 && logic.removePiece(i + 1, j))
								|| (iminus1 && logic.removePiece(i - 1, j))))
								|| (jminus1 && logic.removePiece(i, j - 1) || (jplus1 && logic.removePiece(i, j + 1))))
							return true;
					}
					board.setBoardNode(i, j, 0);
				}
			}
		}
		// Random Remove
		return removeRandom();
	}

	private boolean removeRandom() {
		List<Point> playerPieces = logic.getPlayerOnePieces();
		Point remove;
		do {
			remove = playerPieces.get(rand.nextInt(playerPieces.size()));
		} while (!logic.removePiece(remove.x, remove.y));
		return true;
	}

}
