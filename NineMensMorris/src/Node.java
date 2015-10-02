
public class Node {

	private int player; // 0=empty, 1=player1, 2=player2
	
	public Node(){
		player = 0;
		
	}
	
	public int getPlayer(){
		
		return player;
	}
	
	public void setPlayer(int newPlayer){
		
		player = newPlayer;
	}
}
