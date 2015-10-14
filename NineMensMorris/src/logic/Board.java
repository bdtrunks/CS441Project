package logic;

public class Board {
	private Node[][] board;

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
	
	public void setBoardNode( int square, int point, int newPlayer){
		board[square][point].setPlayer(newPlayer); 
		
	}
	
	public int getBoardNode(int square, int point){
		return board[square][point].getPlayer();
	}
	
	public boolean checkMill(int square, int point, int player){
		if(point%2 == 0){
			if(board[square][point].getPlayer() == player && board[square][(point+1)%8].getPlayer() == player && board[square][(point+2)%8].getPlayer() == player){
				return true;
			}
			if(board[square][point].getPlayer() == player && board[square][(point-1+8)%8].getPlayer() == player && board[square][(point-2+8)%8].getPlayer() == player){
				return true;
			}
		}
		else{
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
