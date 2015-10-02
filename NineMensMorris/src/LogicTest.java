import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

/**
 * Tests Logic elements of 9 Men Morris game
 * @author Brian Dunn
 *
 */
public class LogicTest {

	//*****************************************NODE TESTS*******************************************
	@Test
	public void NodeCreateTest() {
		boolean nodeTest = true;
		try {
			Node node = new Node();
		} catch (Exception e) {
			System.out.println(e);
			nodeTest = false;
		}
		assertTrue(nodeTest);
	}
	@Test
	public void NodeSetPlayer1Test() {
		Node node = new Node();
		node.setPlayer(1);
		assertEquals(1, node.getPlayer());
	}
	
	@Test
	public void NodeSetPlayer2Test() {
		Node node = new Node();
		node.setPlayer(2);
		assertEquals(2, node.getPlayer());
	}
	
	@Test
	public void NodeSetPlayer0Test() {
		Node node = new Node();
		node.setPlayer(0);
		assertEquals(0, node.getPlayer());
	}

	//***************************************BOARD TESTS*********************************************
	@Test
	public void BoardCreateTest() {
		boolean boardTest = true;
		try {
			Board board = new Board();
		} catch (Exception e) {
			System.out.println(e);
			boardTest = false;
		}
		assertTrue(boardTest);
	}
	
	@Test
	public void BoardNewBoardAllEmptyNodeTest() {
		Board board = new Board();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				assertEquals(0, board.getBoardNode(i,j));
			}
		}
	}
	
	@Test
	public void BoardSetBoardNodePlayer1Test() {
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			Board board = new Board();
			int y = rand.nextInt(8);
			int x = rand.nextInt(3);
			board.setBoardNode(x, y, 1);
			assertEquals(1, board.getBoardNode(x,y));
		}
	}
	
	@Test
	public void BoardSetBoardNodePlayer2Test() {
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			Board board = new Board();
			int y = rand.nextInt(8);
			int x = rand.nextInt(3);
			board.setBoardNode(x, y, 2);
			assertEquals(2, board.getBoardNode(x,y));
		}
	}
	
	@Test
	public void BoardCheckMill1() {
		Board board = new Board();
		board.setBoardNode(0, 0, 1);
		board.setBoardNode(0, 1, 1);
		board.setBoardNode(0, 2, 1);
		assertTrue(board.checkMill(0, 2, 1));
	}
	
	@Test
	public void BoardCheckMill2() {
		Board board = new Board();
		board.setBoardNode(0, 2, 1);
		board.setBoardNode(0, 3, 1);
		board.setBoardNode(0, 4, 1);
		assertTrue(board.checkMill(0, 4, 1));
	}
	
	@Test
	public void BoardCheckMill3() {
		Board board = new Board();
		board.setBoardNode(0, 4, 1);
		board.setBoardNode(0, 5, 1);
		board.setBoardNode(0, 6, 1);
		assertTrue(board.checkMill(0, 6, 1));
	}
	
	@Test
	public void BoardCheckMill4() {
		Board board = new Board();
		board.setBoardNode(0, 6, 1);
		board.setBoardNode(0, 7, 1);
		board.setBoardNode(0, 0, 1);
		assertTrue(board.checkMill(0, 0, 1));
	}
	
	@Test
	public void BoardCheckMill5() {
		Board board = new Board();
		board.setBoardNode(1, 0, 1);
		board.setBoardNode(1, 1, 1);
		board.setBoardNode(1, 2, 1);
		assertTrue(board.checkMill(1, 2, 1));
	}
	
	@Test
	public void BoardCheckMill6() {
		Board board = new Board();
		board.setBoardNode(1, 2, 1);
		board.setBoardNode(1, 3, 1);
		board.setBoardNode(1, 4, 1);
		assertTrue(board.checkMill(1, 4, 1));
	}
	
	@Test
	public void BoardCheckMill7() {
		Board board = new Board();
		board.setBoardNode(1, 4, 1);
		board.setBoardNode(1, 5, 1);
		board.setBoardNode(1, 6, 1);
		assertTrue(board.checkMill(1, 6, 1));
	}
	
	@Test
	public void BoardCheckMill8() {
		Board board = new Board();
		board.setBoardNode(1, 6, 1);
		board.setBoardNode(1, 7, 1);
		board.setBoardNode(1, 0, 1);
		assertTrue(board.checkMill(1, 0, 1));
	}
	
	@Test
	public void BoardCheckMill9() {
		Board board = new Board();
		board.setBoardNode(2, 0, 1);
		board.setBoardNode(2, 1, 1);
		board.setBoardNode(2, 2, 1);
		assertTrue(board.checkMill(2, 2, 1));
	}
	
	@Test
	public void BoardCheckMill10() {
		Board board = new Board();
		board.setBoardNode(2, 2, 1);
		board.setBoardNode(2, 3, 1);
		board.setBoardNode(2, 4, 1);
		assertTrue(board.checkMill(2, 4, 1));
	}
	
	@Test
	public void BoardCheckMill11() {
		Board board = new Board();
		board.setBoardNode(2, 4, 1);
		board.setBoardNode(2, 5, 1);
		board.setBoardNode(2, 6, 1);
		assertTrue(board.checkMill(2, 6, 1));
	}
	
	@Test
	public void BoardCheckMill12() {
		Board board = new Board();
		board.setBoardNode(2, 6, 1);
		board.setBoardNode(2, 7, 1);
		board.setBoardNode(2, 0, 1);
		assertTrue(board.checkMill(2, 0, 1));
	}
	
	@Test
	public void BoardCheckMill13() {
		Board board = new Board();
		board.setBoardNode(0, 1, 1);
		board.setBoardNode(1, 1, 1);
		board.setBoardNode(2, 1, 1);
		assertTrue(board.checkMill(2, 1, 1));
	}
	
	@Test
	public void BoardCheckMill14() {
		Board board = new Board();
		board.setBoardNode(2, 3, 1);
		board.setBoardNode(1, 3, 1);
		board.setBoardNode(0, 3, 1);
		assertTrue(board.checkMill(0, 3, 1));
	}
	
	@Test
	public void BoardCheckMill15() {
		Board board = new Board();
		board.setBoardNode(2, 5, 1);
		board.setBoardNode(1, 5, 1);
		board.setBoardNode(0, 5, 1);
		assertTrue(board.checkMill(0, 5, 1));
	}
	
	@Test
	public void BoardCheckMill16() {
		Board board = new Board();
		board.setBoardNode(2, 7, 1);
		board.setBoardNode(1, 7, 1);
		board.setBoardNode(0, 7, 1);
		assertTrue(board.checkMill(0, 7, 1));
	}
	
	@Test
	public void BoardCheckNotMill() {
		Random rand = new Random();
		for (int i = 0; i < 1000; i++) {
			Board board = new Board();
			int x = rand.nextInt(8);
			int x1 = (x+1)%8;
			int x2 = (x+2)%8;
			int y = rand.nextInt(3);
			int y1 = (y+1)%3;
			int y2 = (y+2)%3;
			board.setBoardNode(y, x, 1);
			board.setBoardNode(y1, x1, 1);
			board.setBoardNode(y2, x2, 1);
			assertFalse(board.checkMill(y2, x2, 1));
		}
	}
	
	//******************************************LOGIC TESTS******************************************
	@Test
	public void LogicCreateTest() {
		boolean pass = true;
		try {
			Logic logic = new Logic();
		} catch (Exception e) {
			System.out.println(e);
			pass = false;
		}
		assertTrue(pass);
	}
	
	@Test
	public void LogicCheckMovesTestFrom00() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 0, 1);
		Collection<Point> moves = logic.checkMoves(0, 0);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0,1));
		expected.add(new Point(0,7));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom01() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 1, 1);
		Collection<Point> moves = logic.checkMoves(0, 1);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0,0));
		expected.add(new Point(0,2));
		expected.add(new Point(1,1));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom02() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 2, 1);
		Collection<Point> moves = logic.checkMoves(0, 2);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0,3));
		expected.add(new Point(0,1));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom03() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 3, 1);
		Collection<Point> moves = logic.checkMoves(0, 3);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0,2));
		expected.add(new Point(0,4));
		expected.add(new Point(1,3));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom04() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 4, 1);
		Collection<Point> moves = logic.checkMoves(0, 4);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0,5));
		expected.add(new Point(0,3));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom05() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 5, 1);
		Collection<Point> moves = logic.checkMoves(0, 5);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0,4));
		expected.add(new Point(0,6));
		expected.add(new Point(1,5));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom06() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 6, 1);
		Collection<Point> moves = logic.checkMoves(0, 6);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0,7));
		expected.add(new Point(0,5));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom07() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 7, 1);
		Collection<Point> moves = logic.checkMoves(0, 7);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0,6));
		expected.add(new Point(0,0));
		expected.add(new Point(1,7));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom10() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 0, 1);
		Collection<Point> moves = logic.checkMoves(1, 0);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1,1));
		expected.add(new Point(1,7));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom11() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 1, 1);
		Collection<Point> moves = logic.checkMoves(1, 1);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1,0));
		expected.add(new Point(1,2));
		expected.add(new Point(2,1));
		expected.add(new Point(0,1));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom12() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 2, 1);
		Collection<Point> moves = logic.checkMoves(1, 2);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1,3));
		expected.add(new Point(1,1));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom13() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 3, 1);
		Collection<Point> moves = logic.checkMoves(1, 3);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1,2));
		expected.add(new Point(1,4));
		expected.add(new Point(2,3));
		expected.add(new Point(0,3));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom14() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 4, 1);
		Collection<Point> moves = logic.checkMoves(1, 4);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1,5));
		expected.add(new Point(1,3));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom15() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 5, 1);
		Collection<Point> moves = logic.checkMoves(1, 5);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1,4));
		expected.add(new Point(1,6));
		expected.add(new Point(2,5));
		expected.add(new Point(0,5));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom16() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 6, 1);
		Collection<Point> moves = logic.checkMoves(1, 6);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1,7));
		expected.add(new Point(1,5));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom17() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 7, 1);
		Collection<Point> moves = logic.checkMoves(1, 7);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1,6));
		expected.add(new Point(1,0));
		expected.add(new Point(2,7));
		expected.add(new Point(0,7));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom20() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 0, 1);
		Collection<Point> moves = logic.checkMoves(2, 0);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2,1));
		expected.add(new Point(2,7));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom21() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 1, 1);
		Collection<Point> moves = logic.checkMoves(2, 1);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2,0));
		expected.add(new Point(2,2));
		expected.add(new Point(1,1));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom22() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 2, 1);
		Collection<Point> moves = logic.checkMoves(2, 2);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2,3));
		expected.add(new Point(2,1));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom23() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 3, 1);
		Collection<Point> moves = logic.checkMoves(2, 3);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2,2));
		expected.add(new Point(2,4));
		expected.add(new Point(1,3));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom24() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 4, 1);
		Collection<Point> moves = logic.checkMoves(2, 4);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2,5));
		expected.add(new Point(2,3));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom25() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 5, 1);
		Collection<Point> moves = logic.checkMoves(2, 5);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2,4));
		expected.add(new Point(2,6));
		expected.add(new Point(1,5));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom26() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 6, 1);
		Collection<Point> moves = logic.checkMoves(2, 6);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2,7));
		expected.add(new Point(2,5));
		assertEquals(expected,moves);
	}
	
	@Test
	public void LogicCheckMovesTestFrom27() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 7, 1);
		Collection<Point> moves = logic.checkMoves(2, 7);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2,6));
		expected.add(new Point(2,0));
		expected.add(new Point(1,7));
		assertEquals(expected,moves);
	}
	
}
