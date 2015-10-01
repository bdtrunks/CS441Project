import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Logic {
	private List<Point> playerOnePieces;
	private List<Point> playerTwoPieces;
	private boolean[][] emptySpaces;
	private int playerTurn;
	private int phase; //phase 1 place pieces, phase 2 move pieces, phase 3 remove opponent
	private Board board;
	
	public Logic(){
		emptySpaces = new boolean[3][8];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				emptySpaces[i][j] = true;
			}
		}
		playerOnePieces = new LinkedList<Point>();
		playerTwoPieces = new LinkedList<Point>();
		playerTurn = 1;
		phase = 1;
		board = new Board();
	}
	
	public Collection<Point> checkMoves(int square, int point){
		Collection<Point> result = new LinkedList<Point>();
		if(phase != 2){
			return result;
		}
		if(board.getBoardNode(square,point) != playerTurn){
			return result;
		}
		
		
		
		
		
		
		
		
		return result;
	}
}
