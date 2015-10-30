package AI;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import logic.Logic;

public class AI {

	
	
	public AI() {
		
	}
	
	public boolean turn(Logic logic) {
		if (logic.getPlayer() != 2)
			return false;
		
		List<Point> emptySpaces = logic.getEmptySpaces();
		Random rand = new Random();
		
		if (logic.getPhase() == 2) {
			List<Point> aiPieces = logic.getPlayerTwoPieces();
			Point piece, move;
			Collection<Point> places;
			do {
				piece = aiPieces.get(rand.nextInt(aiPieces.size()));
				places = logic.checkMoves(piece.x, piece.y);
			} while (places.size() == 0);
			System.out.println("places size = " + places.size());
			move = (Point) places.toArray()[rand.nextInt(places.size())];
			System.out.println(Arrays.toString(places.toArray()));
			
			pause();
			logic.movePiece(piece.x, piece.y, move.x, move.y);
			return true;
		}
		
		if (logic.getPhase() == 1) {
			Point move = emptySpaces.get(rand.nextInt(emptySpaces.size()));
			
			pause();
			logic.placePiece(move.x, move.y);
			return true;
		}
		
		
		if (logic.getPhase() == 3) {
			List<Point> playerPieces = logic.getPlayerOnePieces();
			Point remove;
			
			pause();
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
