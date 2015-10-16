package logic;

import java.awt.Point;
import java.util.ArrayList;
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
						// remove opponent, phase 4 game over
	private int piecesPlaced;
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
		piecesPlaced = 0;
	}

	public void setPlayer() {
		if (playerTurn == 1) {
			playerTurn = 2;
		} else {
			playerTurn = 1;
		}
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public Board getBoard() {
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

	public List<Point> getPlayerOnePieces() {
		return new ArrayList<Point>(playerOnePieces);
	}

	public int getPlayerTwoPiece() {
		return playerTwoPieces.size();
	}

	public List<Point> getPlayerTwoPieces() {
		return new ArrayList<Point>(playerTwoPieces);
	}

	public void addPlayerOnePieces(Point add) {
		playerOnePieces.add(add);
	}

	public void addPlayerTwoPieces(Point add) {
		playerTwoPieces.add(add);
	}

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

	public boolean checkFly(int player) {
		if (player == 1 & playerOnePieces.size() == 3) {
			return true;
		}
		if (player == 2 & playerTwoPieces.size() == 3) {
			return true;
		}
		return false;
	}

	public boolean placePiece(int square, int point) {
		if (board.getBoardNode(square, point) == 0) {
			board.setBoardNode(square, point, playerTurn);
			Point add = new Point(square, point);
			if (playerTurn == 1) {
				playerOnePieces.add(add);
			} else {
				playerTwoPieces.add(add);
			}
			if (board.checkMill(square, point, playerTurn)) {
				phase = 3;
				piecesPlaced++;
			} else {
				setPlayer();
				piecesPlaced++;
				if (piecesPlaced == 18) {
					checkWin();
					phase = 2;
				}
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
			if (playerTurn == 1) {
				playerOnePieces.remove(new Point(square, point));
				playerOnePieces.add(new Point(moveSquare, movePoint));
			} else {
				playerTwoPieces.remove(new Point(square, point));
				playerTwoPieces.add(new Point(moveSquare, movePoint));
			}
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

	public boolean removePiece(int square, int point) {
		if (playerTurn == 1)
			return remove(square, point, 2, playerTwoPieces);
		else
			return remove(square, point, 1, playerOnePieces);
	}

	public boolean remove(int square, int point, int player, List<Point> playerPieces) {
		Point remove = new Point(square, point);
		if (board.getBoardNode(square, point) == player) {
			if (!board.checkMill(square, point, player) || playerPieces.size() <= 3) {
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

	public int getWinner() {
		return playerWon;
	}

}