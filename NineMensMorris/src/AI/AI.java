package AI;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import logic.Board;
import logic.Logic;

public class AI {
	
	public AI() {
		
	}
	
	public boolean turn(Logic logic) {
		if (logic.getPlayer() != 2)
			return false;
		
		List<Point> emptySpaces = logic.getEmptySpaces();
		Board board = logic.getBoard();
		Random rand = new Random();
		//MOVING PIECES PHASE
		if (logic.getPhase() == 2) {
			pause();
			List<Point> aiPieces = logic.getPlayerTwoPieces();
			Point piece, move;
			Collection<Point> places;
			//Move to create mill if possible
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					if (aiPieces.contains(new Point(i,j)) && logic.checkMoves(i, j).size() != 0) {
						places = logic.checkMoves(i, j);
						Point[] moves = (Point[]) places.toArray(new Point[places.size()]);
						for (int k = 0; k < moves.length; k++) {
							int x = moves[k].x;
							int y = moves[k].y;
							board.setBoardNode(i, j, 0);
							board.setBoardNode(x,y,2);
							if (board.checkMill(x,y,2)) {
								board.setBoardNode(i, j, 2);
								board.setBoardNode(x, y, 0);
								System.out.println(x + "," + y);
								logic.movePiece(i, j, x, y);
								return true;
							}
							board.setBoardNode(i, j, 2);
							board.setBoardNode(x, y, 0);
						}
					}
				}
			}
			//Move to block mill if possible
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					if (aiPieces.contains(new Point(i,j)) && logic.checkMoves(i, j).size() != 0) {
						places = logic.checkMoves(i, j);
						Point[] moves = (Point[]) places.toArray(new Point[places.size()]);
						for (int k = 0; k < moves.length; k++) {
							int x = moves[k].x;
							int y = moves[k].y;
							board.setBoardNode(x,y,1);
							if (board.checkMill(x,y,1)) {
								board.setBoardNode(x, y, 0);
								System.out.println(x + "," + y);
								logic.movePiece(i, j, x, y);
								return true;
							}
							board.setBoardNode(x, y, 0);
						}
					}
				}
			}
			//Check to move piece that is within a mill so will create mill on next turn
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					if (aiPieces.contains(new Point(i,j)) && logic.checkMoves(i, j).size() != 0 && board.checkMill(i, j, 2)) {
						places = logic.checkMoves(i, j);
						Point[] moves = (Point[]) places.toArray(new Point[places.size()]);
						for (int k = 0; k < moves.length; k++) {
							int x = moves[k].x;
							int y = moves[k].y;
							System.out.println(x + "," + y);
							logic.movePiece(i, j, x, y);
							return true;
						}
					}
				}
			}
			//Random Move
			do {
				piece = aiPieces.get(rand.nextInt(aiPieces.size()));
				places = logic.checkMoves(piece.x, piece.y);
			} while (places.size() == 0);
			System.out.println("places size = " + places.size());
			move = (Point) places.toArray()[rand.nextInt(places.size())];
			System.out.println(Arrays.toString(places.toArray()));
			
			logic.movePiece(piece.x, piece.y, move.x, move.y);
			return true;
		}
		//PLACING PIECES PHASE
		if (logic.getPhase() == 1) {
			pause();
			//Check all available spaces to see if placing creates mill, if so place there
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					if (emptySpaces.contains(new Point(i,j))) {
						board.setBoardNode(i, j, 2);
						if (board.checkMill(i,j,2)) {
							board.setBoardNode(i, j, 0);
							System.out.println(i + "," + j);
							logic.placePiece(i, j);
							return true;
						}
						board.setBoardNode(i, j, 0);
					}
				}
			}
			//Check all available spaces for possible block of opponent mill
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					if (emptySpaces.contains(new Point(i,j))) {
						board.setBoardNode(i, j, 1);
						if (board.checkMill(i,j,1)) {
							board.setBoardNode(i, j, 0);
							System.out.println(i + "," + j);
							logic.placePiece(i, j);
							return true;
						}
						board.setBoardNode(i, j, 0);
					}
				}
			}
			//Check all available spaces to see if possible to place next to other pieces
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					if (board.getBoardNode(i,j) == 2) {
						if ((j%2 != 0 && (logic.placePiece((i+1)%3,j) || logic.placePiece((i-1+3)%3,j))) || logic.placePiece(i,(j-1+8)%8) || logic.placePiece(i,(j+1)%8)) {
							return true;
						}
					}
				}
			}
			//Random Move
			Point move = emptySpaces.get(rand.nextInt(emptySpaces.size()));
			logic.placePiece(move.x, move.y);
			return true;
		}
		//REMOVING PIECES PHASE
		if (logic.getPhase() == 3) {			
			pause();
			List<Point> playerPieces = logic.getPlayerOnePieces();
			Point remove;
			//Attempt to remove player pieces that are blocking a mill
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					if (playerPieces.contains(new Point(i,j))) {
						board.setBoardNode(i, j, 2);
						if (board.checkMill(i, j, 2)) {
							board.setBoardNode(i, j, 1);
							if (logic.removePiece(i,j))
								return true;
						}
						board.setBoardNode(i, j, 1);
					}
				}
			}
			//Attempt to remove player pieces that are close to forming mill
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 8; j++) {
					if (emptySpaces.contains(new Point(i, j))) {
						board.setBoardNode(i, j, 1);
						if (board.checkMill(i, j, 1)) {
							board.setBoardNode(i, j, 0);
							if ((j%2 != 0 && (logic.removePiece((i+1)%3,j) || logic.removePiece((i-1+3)%3,j))) || logic.removePiece(i,(j-1+8)%8) || logic.removePiece(i,(j+1)%8))
								return true;
						}
						board.setBoardNode(i, j, 0);
					}
				}
			}
			//Random Remove
			do {
				remove = playerPieces.get(rand.nextInt(playerPieces.size()));
				System.out.println("remove = " + remove);
			} while (!logic.removePiece(remove.x, remove.y));
			return true;
		}
		return false;
	}
	
	private void pause() {
		long current = System.currentTimeMillis();
		long end = current + 2000;
		while (current < end) {
			current = System.currentTimeMillis();
		}
	}
	
}
