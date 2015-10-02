import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Logic {
	private List<Point> playerOnePieces;
	private List<Point> playerTwoPieces;
	private boolean[][] emptySpaces;
	private int playerTurn;
	private int phase; // phase 1 place pieces, phase 2 move pieces, phase 3
						// remove opponent
	private Board board;
	private int playerWon;

	public Logic() {
		emptySpaces = new boolean[3][8];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				emptySpaces[i][j] = true;
				
			}
		}
		playerOnePieces = new LinkedList<Point>();
		playerTwoPieces = new LinkedList<Point>();
		playerTurn = 1;
		phase = 1;
		board = new Board();
		playerWon = 0;
	}

	private void setPlayer() {
		if (playerTurn == 1) {
			playerTurn = 2;
		} else {
			playerTurn = 1;
		}
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public void setBoardNode(int square, int point, int newPlayer) {
		board.setBoardNode(square, point, newPlayer);
	}

	public int getPhase() {
		return phase;
	}

	public int getPlayer() {
		return playerTurn;
	}

	public int getPlayerOnePiece() {
		return playerOnePieces.size();
	}

	public int getPlayerTwoPiece() {
		return playerTwoPieces.size();
	}

	public Collection<Point> checkMoves(int square, int point) {
		Collection<Point> result = new LinkedList<Point>();
		if (phase != 2) {
			return result;
		}
		if (board.getBoardNode(square, point) != playerTurn) {
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

	public boolean placePiece(int square, int point) {
		if (board.getBoardNode(square, point) == 0) {
			board.setBoardNode(square, point, playerTurn);
			if (board.checkMill(square, point, playerTurn)) {
				// todo
			}
			setPlayer();
			Point add = new Point(square, point);
			if (playerTurn == 1) {
				playerOnePieces.add(add);
			} else {
				playerTwoPieces.add(add);
			}
			return true;
		}
		return false;
	}

	public boolean movePiece(int square, int point, int moveSquare, int movePoint) {
		Collection<Point> moves = checkMoves(square, point);
		if (moves.isEmpty()) {
			return false;
		}
		Point check = new Point(moveSquare, movePoint);
		if (moves.contains(check)) {
			board.setBoardNode(square, point, 0);
			board.setBoardNode(moveSquare, movePoint, playerTurn);
			if (board.checkMill(moveSquare, movePoint, playerTurn)) {
				// todo
			}
			setPlayer();
			if (checkWin()) {
				setPlayer();
				playerWon = playerTurn; 
			}
			return true;
		}

		return false;
	}

	public boolean removePiece(int square, int point) {
		board.setBoardNode(square, point, 0);
		Point remove = new Point(square, point);
		if (playerTurn == 1) {
			playerTwoPieces.remove(remove);
		} else {
			playerOnePieces.remove(remove);
		}
		return false;
	}

	public boolean checkWin() {
		if (playerTurn == 1) {
			if (playerOnePieces.size() < 3) {
				return true;
			}
			Iterator<Point> iterator = playerOnePieces.iterator();
			while (iterator.hasNext()) {
				Point check = iterator.next();
				Collection<Point> moves = checkMoves(check.x, check.y);
				if (!moves.isEmpty()) {
					return false;
				}
			}
			return true;
		}
		if (playerTurn == 2) {
			if (playerTwoPieces.size() < 3) {
				return true;
			}
			Iterator<Point> iterator = playerTwoPieces.iterator();
			while (iterator.hasNext()) {
				Point check = iterator.next();
				Collection<Point> moves = checkMoves(check.x, check.y);
				if (!moves.isEmpty()) {
					return false;
				}
			}
			return true;
		}

		return false;
	}
}
