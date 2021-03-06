package test;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import AI.AI;
import AI.TestAI;
import logic.Board;
import logic.Logic;
import logic.Node;

/**
 * Tests Logic elements of 9 Men Morris game
 * 
 * @author Brian Dunn
 *
 */
public class LogicTest {

	// *****************************************MUTATOR
	// TESTS***************************************
	@Test
	public void mutatorTests() {
		Logic testLogic = new Logic();
		Board expectedBoard = new Board();
		List<Point> expectedOnePieces = new ArrayList<Point>();
		List<Point> expectedTwoPieces = new ArrayList<Point>();
		int nodeCountX;
		int nodeCountY;
		for (nodeCountY = 0; nodeCountY < 3; nodeCountY++) {
			for (nodeCountX = 0; nodeCountX < 8; nodeCountX++) {
				assertEquals(expectedBoard.getBoardNode(nodeCountY, nodeCountX),
						testLogic.getBoard().getBoardNode(nodeCountY, nodeCountX));
			}
		}
		assertEquals(1, testLogic.getPhase());
		assertEquals(1, testLogic.getPlayer());
		testLogic.setPlayer();
		assertEquals(2, testLogic.getPlayer());
		testLogic.setPlayer();
		assertEquals(1, testLogic.getPlayer());
		assertEquals(0, testLogic.getPlayerOnePiece());
		assertEquals(0, testLogic.getPlayerTwoPiece());
		assertEquals(expectedOnePieces, testLogic.getPlayerOnePieces());
		assertEquals(expectedTwoPieces, testLogic.getPlayerTwoPieces());
		testLogic.checkWin();
		assertTrue(testLogic.isLoser(testLogic.getPlayerOnePieces()));
		testLogic.setPlayer();
		testLogic.checkWin();
		assertTrue(testLogic.isLoser(testLogic.getPlayerTwoPieces()));
		assertEquals(1, testLogic.getWinner());

	}

	// *****************************************NODE
	// TESTS*******************************************
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

	// ***************************************BOARD
	// TESTS*********************************************
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
				assertEquals(0, board.getBoardNode(i, j));
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
			assertEquals(1, board.getBoardNode(x, y));
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
			assertEquals(2, board.getBoardNode(x, y));
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
			int x1 = (x + 1) % 8;
			int x2 = (x + 2) % 8;
			int y = rand.nextInt(3);
			int y1 = (y + 1) % 3;
			int y2 = (y + 2) % 3;
			board.setBoardNode(y, x, 1);
			board.setBoardNode(y1, x1, 1);
			board.setBoardNode(y2, x2, 1);
			assertFalse(board.checkMill(y2, x2, 1));
		}
	}

	// ******************************************LOGIC
	// TESTS******************************************
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
		expected.add(new Point(0, 1));
		expected.add(new Point(0, 7));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom01() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 1, 1);
		Collection<Point> moves = logic.checkMoves(0, 1);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 0));
		expected.add(new Point(0, 2));
		expected.add(new Point(1, 1));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom02() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 2, 1);
		Collection<Point> moves = logic.checkMoves(0, 2);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 3));
		expected.add(new Point(0, 1));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom03() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 3, 1);
		Collection<Point> moves = logic.checkMoves(0, 3);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 2));
		expected.add(new Point(0, 4));
		expected.add(new Point(1, 3));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom04() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 4, 1);
		Collection<Point> moves = logic.checkMoves(0, 4);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 5));
		expected.add(new Point(0, 3));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom05() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 5, 1);
		Collection<Point> moves = logic.checkMoves(0, 5);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 4));
		expected.add(new Point(0, 6));
		expected.add(new Point(1, 5));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom06() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 6, 1);
		Collection<Point> moves = logic.checkMoves(0, 6);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 7));
		expected.add(new Point(0, 5));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom07() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 7, 1);
		Collection<Point> moves = logic.checkMoves(0, 7);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 6));
		expected.add(new Point(0, 0));
		expected.add(new Point(1, 7));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom10() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 0, 1);
		Collection<Point> moves = logic.checkMoves(1, 0);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1, 1));
		expected.add(new Point(1, 7));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom11() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 1, 1);
		Collection<Point> moves = logic.checkMoves(1, 1);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1, 0));
		expected.add(new Point(1, 2));
		expected.add(new Point(2, 1));
		expected.add(new Point(0, 1));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom12() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 2, 1);
		Collection<Point> moves = logic.checkMoves(1, 2);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1, 3));
		expected.add(new Point(1, 1));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom13() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 3, 1);
		Collection<Point> moves = logic.checkMoves(1, 3);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1, 2));
		expected.add(new Point(1, 4));
		expected.add(new Point(2, 3));
		expected.add(new Point(0, 3));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom14() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 4, 1);
		Collection<Point> moves = logic.checkMoves(1, 4);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1, 5));
		expected.add(new Point(1, 3));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom15() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 5, 1);
		Collection<Point> moves = logic.checkMoves(1, 5);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1, 4));
		expected.add(new Point(1, 6));
		expected.add(new Point(2, 5));
		expected.add(new Point(0, 5));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom16() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 6, 1);
		Collection<Point> moves = logic.checkMoves(1, 6);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1, 7));
		expected.add(new Point(1, 5));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom17() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(1, 7, 1);
		Collection<Point> moves = logic.checkMoves(1, 7);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(1, 6));
		expected.add(new Point(1, 0));
		expected.add(new Point(2, 7));
		expected.add(new Point(0, 7));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom20() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 0, 1);
		Collection<Point> moves = logic.checkMoves(2, 0);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2, 1));
		expected.add(new Point(2, 7));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom21() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 1, 1);
		Collection<Point> moves = logic.checkMoves(2, 1);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2, 0));
		expected.add(new Point(2, 2));
		expected.add(new Point(1, 1));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom22() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 2, 1);
		Collection<Point> moves = logic.checkMoves(2, 2);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2, 3));
		expected.add(new Point(2, 1));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom23() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 3, 1);
		Collection<Point> moves = logic.checkMoves(2, 3);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2, 2));
		expected.add(new Point(2, 4));
		expected.add(new Point(1, 3));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom24() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 4, 1);
		Collection<Point> moves = logic.checkMoves(2, 4);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2, 5));
		expected.add(new Point(2, 3));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom25() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 5, 1);
		Collection<Point> moves = logic.checkMoves(2, 5);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2, 4));
		expected.add(new Point(2, 6));
		expected.add(new Point(1, 5));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom26() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 6, 1);
		Collection<Point> moves = logic.checkMoves(2, 6);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2, 7));
		expected.add(new Point(2, 5));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestFrom27() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(2, 7, 1);
		Collection<Point> moves = logic.checkMoves(2, 7);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(2, 6));
		expected.add(new Point(2, 0));
		expected.add(new Point(1, 7));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestWrongPhase() {
		Logic logic = new Logic();
		logic.setPhase(1);
		logic.setBoardNode(0, 0, 1);
		Collection<Point> moves = logic.checkMoves(0, 0);
		Collection<Point> expected = new LinkedList<Point>();
		assertEquals(expected, moves);
	}

	@Test
	public void LogicCheckMovesTestWrongPlayer() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 0, 1);
		logic.setPlayer();
		Collection<Point> moves = logic.checkMoves(0, 0);
		Collection<Point> expected = new LinkedList<Point>();
		assertEquals(expected, moves);
	}

	@Test
	public void playerOneFlyTest03() {
		Logic logic = new Logic();
		logic.setPhase(2);
		logic.setBoardNode(0, 0, 1);
		logic.setBoardNode(0, 2, 1);
		logic.setBoardNode(0, 3, 1);
		logic.setBoardNode(1, 2, 2);
		logic.setBoardNode(1, 4, 2);
		logic.setBoardNode(1, 6, 2);
		logic.setBoardNode(1, 7, 2);
		logic.setBoardNode(2, 1, 2);
		Point add = new Point(0, 0);
		logic.addPlayerOnePieces(add);
		add = new Point(0, 2);
		logic.addPlayerOnePieces(add);
		add = new Point(0, 3);
		logic.addPlayerOnePieces(add);
		add = new Point(1, 2);
		logic.addPlayerTwoPieces(add);
		add = new Point(1, 4);
		logic.addPlayerTwoPieces(add);
		add = new Point(1, 6);
		logic.addPlayerTwoPieces(add);
		add = new Point(1, 7);
		logic.addPlayerTwoPieces(add);
		add = new Point(2, 1);
		logic.addPlayerTwoPieces(add);
		Collection<Point> moves = logic.checkMoves(0, 3);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 1));
		expected.add(new Point(0, 4));
		expected.add(new Point(0, 5));
		expected.add(new Point(0, 6));
		expected.add(new Point(0, 7));
		expected.add(new Point(1, 0));
		expected.add(new Point(1, 1));
		expected.add(new Point(1, 3));
		expected.add(new Point(1, 5));
		expected.add(new Point(2, 0));
		expected.add(new Point(2, 2));
		expected.add(new Point(2, 3));
		expected.add(new Point(2, 4));
		expected.add(new Point(2, 5));
		expected.add(new Point(2, 6));
		expected.add(new Point(2, 7));
		assertEquals(expected, moves);
	}

	@Test
	public void playerTwoFlyTest03() {
		Logic logic = new Logic();
		logic.setPlayer();
		logic.setPhase(2);
		logic.setBoardNode(0, 0, 2);
		logic.setBoardNode(0, 2, 2);
		logic.setBoardNode(0, 3, 2);
		logic.setBoardNode(1, 2, 1);
		logic.setBoardNode(1, 4, 1);
		logic.setBoardNode(1, 6, 1);
		logic.setBoardNode(1, 7, 1);
		logic.setBoardNode(2, 1, 1);
		Point add = new Point(0, 0);
		logic.addPlayerTwoPieces(add);
		add = new Point(0, 2);
		logic.addPlayerTwoPieces(add);
		add = new Point(0, 3);
		logic.addPlayerTwoPieces(add);
		add = new Point(1, 2);
		logic.addPlayerOnePieces(add);
		add = new Point(1, 4);
		logic.addPlayerOnePieces(add);
		add = new Point(1, 6);
		logic.addPlayerOnePieces(add);
		add = new Point(1, 7);
		logic.addPlayerOnePieces(add);
		add = new Point(2, 1);
		logic.addPlayerOnePieces(add);
		Collection<Point> moves = logic.checkMoves(0, 3);
		Collection<Point> expected = new LinkedList<Point>();
		expected.add(new Point(0, 1));
		expected.add(new Point(0, 4));
		expected.add(new Point(0, 5));
		expected.add(new Point(0, 6));
		expected.add(new Point(0, 7));
		expected.add(new Point(1, 0));
		expected.add(new Point(1, 1));
		expected.add(new Point(1, 3));
		expected.add(new Point(1, 5));
		expected.add(new Point(2, 0));
		expected.add(new Point(2, 2));
		expected.add(new Point(2, 3));
		expected.add(new Point(2, 4));
		expected.add(new Point(2, 5));
		expected.add(new Point(2, 6));
		expected.add(new Point(2, 7));
		assertEquals(expected, moves);
	}

	@Test
	public void LogicTestPlacePiece() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 1);
		assertEquals(1, logic.getBoard().getBoardNode(0, 0));
		assertEquals(2, logic.getBoard().getBoardNode(0, 1));
		logic.placePiece(0, 7);
		logic.placePiece(0, 2);
		logic.placePiece(0, 6);
		assertEquals(3, logic.getPhase());
		logic.placePiece(0, 3);
		logic.placePiece(0, 4);
		logic.placePiece(0, 5);
		logic.placePiece(1, 0);
		logic.placePiece(1, 1);
		logic.placePiece(1, 2);
		logic.placePiece(1, 3);
		logic.placePiece(1, 4);
		logic.placePiece(1, 5);
		logic.placePiece(1, 6);
		logic.placePiece(1, 7);
		logic.placePiece(2, 0);
		logic.placePiece(2, 1);
		assertEquals(2, logic.getPhase());

	}

	@Test
	public void LogicTestInvalidPlacePiece() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		assertFalse(logic.placePiece(0, 0));
	}

	@Test
	public void LogicTestPlayerOneMovePiece() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 2);
		logic.placePiece(0, 3);
		logic.placePiece(0, 4);
		logic.placePiece(0, 5);
		logic.placePiece(0, 6);
		logic.placePiece(0, 7);
		logic.placePiece(1, 0);
		logic.placePiece(1, 1);
		logic.placePiece(1, 2);
		logic.placePiece(1, 3);
		logic.placePiece(1, 4);
		logic.placePiece(1, 5);
		logic.placePiece(1, 6);
		logic.placePiece(1, 7);
		logic.placePiece(2, 0);
		logic.placePiece(2, 1);
		logic.placePiece(2, 2);
		assertTrue(logic.movePiece(0, 0, 0, 1));
	}

	@Test
	public void LogicTestPlayerTwoMovePiece() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 2);
		logic.placePiece(0, 3);
		logic.placePiece(0, 4);
		logic.placePiece(0, 5);
		logic.placePiece(0, 6);
		logic.placePiece(0, 7);
		logic.placePiece(1, 1);
		logic.placePiece(1, 0);
		logic.placePiece(1, 2);
		logic.placePiece(1, 3);
		logic.placePiece(1, 4);
		logic.placePiece(1, 5);
		logic.placePiece(1, 6);
		logic.placePiece(1, 7);
		logic.placePiece(2, 0);
		logic.placePiece(2, 1);
		logic.placePiece(2, 2);
		logic.movePiece(0, 0, 0, 1);
		assertTrue(logic.movePiece(2, 2, 2, 3));
	}

	@Test
	public void LogicTestInvalidMovePiece() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 2);
		logic.placePiece(0, 3);
		logic.placePiece(0, 4);
		logic.placePiece(0, 5);
		logic.placePiece(0, 6);
		logic.placePiece(0, 7);
		logic.placePiece(1, 0);
		logic.placePiece(1, 1);
		logic.placePiece(1, 2);
		logic.placePiece(1, 3);
		logic.placePiece(1, 4);
		logic.placePiece(1, 5);
		logic.placePiece(1, 6);
		logic.placePiece(1, 7);
		logic.placePiece(2, 0);
		logic.placePiece(2, 1);
		logic.placePiece(2, 2);
		assertFalse(logic.movePiece(0, 2, 0, 3));
	}

	@Test
	public void LogicTestInvalidMovePiece2() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 2);
		logic.placePiece(0, 3);
		logic.placePiece(0, 4);
		logic.placePiece(0, 5);
		logic.placePiece(0, 6);
		logic.placePiece(0, 7);
		logic.placePiece(1, 0);
		logic.placePiece(1, 1);
		logic.placePiece(1, 2);
		logic.placePiece(1, 3);
		logic.placePiece(1, 4);
		logic.placePiece(1, 5);
		logic.placePiece(1, 6);
		logic.placePiece(1, 7);
		logic.placePiece(2, 0);
		logic.placePiece(2, 1);
		logic.placePiece(2, 2);
		assertFalse(logic.movePiece(0, 0, 2, 3));
	}

	@Test
	public void LogicTestPlayerOneRemovePiecePhase2() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 2);
		logic.placePiece(0, 3);
		logic.placePiece(0, 4);
		logic.placePiece(0, 5);
		logic.placePiece(0, 6);
		logic.placePiece(0, 7);
		logic.placePiece(1, 0);
		logic.placePiece(1, 1);
		logic.placePiece(1, 2);
		logic.placePiece(1, 3);
		logic.placePiece(1, 4);
		logic.placePiece(1, 5);
		logic.placePiece(1, 6);
		logic.placePiece(1, 7);
		logic.placePiece(2, 0);
		logic.placePiece(2, 1);
		logic.placePiece(2, 2);
		assertTrue(logic.removePiece(0, 2));
	}

	@Test
	public void LogicTestPlayerOneRemovePiecePhase1() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 2);
		assertTrue(logic.removePiece(0, 2));
	}

	@Test
	public void LogicTestPlayerTwoRemovePiecePhase2() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 2);
		logic.placePiece(0, 3);
		logic.placePiece(0, 4);
		logic.placePiece(0, 5);
		logic.placePiece(0, 6);
		logic.placePiece(0, 7);
		logic.placePiece(1, 0);
		logic.placePiece(1, 1);
		logic.placePiece(1, 2);
		logic.placePiece(1, 3);
		logic.placePiece(1, 4);
		logic.placePiece(1, 5);
		logic.placePiece(1, 6);
		logic.placePiece(1, 7);
		logic.placePiece(2, 0);
		logic.placePiece(2, 1);
		logic.placePiece(2, 2);
		logic.removePiece(0, 2);
		assertTrue(logic.removePiece(0, 0));
	}

	@Test
	public void LogicTestPlayerTwoRemovePiecePhase1() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 2);
		logic.placePiece(0, 3);
		assertTrue(logic.removePiece(0, 3));
	}

	@Test
	public void LogicTestPlayerOneRemovePiecePhase1Mill() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 6);
		logic.placePiece(0, 1);
		logic.placePiece(0, 5);
		logic.placePiece(0, 3);
		logic.placePiece(1, 0);
		logic.placePiece(1, 2);
		logic.placePiece(0, 4);
		logic.removePiece(1, 2);
		logic.placePiece(0, 2);
		assertFalse(logic.removePiece(0, 6));
	}

	@Test
	public void LogicTestPlayerTwoRemovePiecePhase1Mill() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 6);
		logic.placePiece(0, 1);
		logic.placePiece(0, 5);
		logic.placePiece(0, 3);
		logic.placePiece(1, 0);
		logic.placePiece(1, 2);
		logic.placePiece(0, 4);
		logic.removePiece(1, 2);
		logic.placePiece(0, 2);
		logic.removePiece(0, 6);
		logic.placePiece(0, 6);
		assertFalse(logic.removePiece(0, 0));
	}

	@Test
	public void LogicTestPlayerOneRemovePiecePhase1OnlyMill() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(0, 6);
		logic.placePiece(0, 1);
		logic.placePiece(0, 5);
		logic.placePiece(0, 3);
		logic.placePiece(0, 4);
		logic.removePiece(0, 3);
		logic.placePiece(0, 2);
		assertTrue(logic.removePiece(0, 6));
	}

	@Test
	public void LogicTestPlayerOneRemovePiecePhase1OverlapMills() {
		Logic logic = new Logic();
		logic.placePiece(0, 0);
		logic.placePiece(1, 2);
		logic.placePiece(0, 1);
		logic.placePiece(1, 4);
		logic.placePiece(1, 0);
		logic.placePiece(2, 3);
		logic.placePiece(1, 1);
		logic.placePiece(0, 3);
		logic.placePiece(1, 7);
		logic.placePiece(1, 3);
		logic.removePiece(1, 0);
		logic.placePiece(0, 2);
		assertTrue(logic.removePiece(1, 3));
	}

	@Test
	public void LogicTestPlayerOneRemovePiecePhase2OverlapMills() {
		Logic logic = new Logic();
		logic.placePiece(0, 0); // 1
		logic.placePiece(1, 2); // 2
		logic.placePiece(0, 1); // 1
		logic.placePiece(1, 4); // 2
		logic.placePiece(1, 0); // 1
		logic.placePiece(2, 3); // 2
		logic.placePiece(1, 1); // 1
		logic.placePiece(0, 3); // 2
		logic.placePiece(1, 7); // 1
		logic.placePiece(0, 6); // 2
		logic.placePiece(2, 2); // 1
		logic.placePiece(0, 4); // 2
		logic.placePiece(1, 5); // 1
		logic.placePiece(2, 0); // 2
		logic.placePiece(2, 6); // 1
		logic.placePiece(2, 1); // 2
		logic.placePiece(2, 4); // 1
		logic.placePiece(1, 3); // 2
		logic.removePiece(1, 0); // 2 remove 1
		logic.movePiece(1, 5, 2, 5); // 1
		logic.removePiece(2, 0); // 1 remove 2
		logic.movePiece(0, 6, 0, 5); // 2
		logic.movePiece(2, 5, 1, 5); // 1
		logic.movePiece(0, 5, 0, 6); // 2
		logic.movePiece(1, 5, 2, 5); // 1
		logic.removePiece(2, 1); // 1 remove 2
		logic.movePiece(0, 6, 0, 5); // 2
		logic.movePiece(2, 5, 1, 5); // 1
		logic.movePiece(0, 5, 0, 6); // 2
		logic.movePiece(1, 5, 2, 5); // 1
		logic.removePiece(0, 4); // 1 remove 2
		logic.movePiece(0, 6, 0, 5); // 2
		logic.movePiece(2, 5, 1, 5); // 1
		logic.movePiece(0, 5, 0, 6); // 2
		logic.movePiece(1, 5, 2, 5); // 1
		logic.removePiece(0, 6); // 1 remove 2
		logic.movePiece(0, 3, 0, 4); // 2
		logic.movePiece(2, 5, 1, 5); // 1
		logic.movePiece(0, 4, 0, 3); // 2
		logic.removePiece(0, 0); // 2 remove 1
		logic.movePiece(1, 5, 2, 5); // 1
		assertTrue(logic.removePiece(1, 3)); // 1 remove 2
	}

	@Test
	public void LogicTestLoserNoValidMoves() {
		Logic logic = new Logic();
		Board board = logic.getBoard();
		logic.addPlayerOnePieces(new Point(0, 0));
		board.setBoardNode(0, 0, 1);
		logic.addPlayerOnePieces(new Point(0, 1));
		board.setBoardNode(0, 1, 1);
		logic.addPlayerOnePieces(new Point(1, 0));
		board.setBoardNode(1, 0, 1);
		logic.addPlayerOnePieces(new Point(0, 7));
		board.setBoardNode(0, 7, 1);
		logic.addPlayerTwoPieces(new Point(0, 2));
		board.setBoardNode(0, 2, 2);
		logic.addPlayerTwoPieces(new Point(1, 1));
		board.setBoardNode(1, 1, 2);
		logic.addPlayerTwoPieces(new Point(0, 6));
		board.setBoardNode(0, 6, 2);
		logic.addPlayerTwoPieces(new Point(1, 7));
		board.setBoardNode(1, 7, 2);
		logic.setPhase(2);
		assertTrue(logic.isLoser(logic.getPlayerOnePieces()));
	}

	// -----------AI TESTS---------------------------

	private static final int SEED = 20;

	@Test
	public void AItestHardvsEasy() {
		Logic logic = new Logic();
		AI hard = new AI(1, SEED, false);
		TestAI easy = new TestAI(0, false);
		while (logic.getPhase() != 4) {
			if (logic.getPhase() == 1) {
				easy.turn(logic);
				if (logic.getPhase() == 3)
					easy.turn(logic);
				hard.turn(logic);
				if (logic.getPhase() == 3)
					hard.turn(logic);
			} else if (logic.getPhase() == 2) {
				easy.turn(logic);
				if (logic.getPhase() == 3)
					easy.turn(logic);
				hard.turn(logic);
				if (logic.getPhase() == 3)
					hard.turn(logic);
			}
		}
		assertTrue(logic.getWinner() == 2);
	}

	@Test
	public void AItestHardvsEasyReversed() {
		Logic logic = new Logic();
		AI easy = new AI(0, SEED, false);
		TestAI hard = new TestAI(1, false);
		while (logic.getPhase() != 4) {
			if (logic.getPhase() == 1) {
				hard.turn(logic);
				if (logic.getPhase() == 3)
					hard.turn(logic);
				easy.turn(logic);
				if (logic.getPhase() == 3)
					easy.turn(logic);
			} else if (logic.getPhase() == 2) {
				hard.turn(logic);
				if (logic.getPhase() == 3)
					hard.turn(logic);
				easy.turn(logic);
				if (logic.getPhase() == 3)
					easy.turn(logic);
			}
		}
		assertTrue(logic.getWinner() == 1);
	}

	@Test
	public void AItestMoveCreateMill() {
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.addPlayerTwoPieces(new Point(0, 0));
		logic.addPlayerTwoPieces(new Point(0, 1));
		logic.addPlayerTwoPieces(new Point(0, 3));
		logic.setBoardNode(0, 0, 2);
		logic.setBoardNode(0, 1, 2);
		logic.setBoardNode(0, 3, 2);
		logic.removeEmptySpace(new Point(0, 0));
		logic.removeEmptySpace(new Point(0, 1));
		logic.removeEmptySpace(new Point(0, 3));
		logic.setPiecesPlaced(18);
		logic.setPhase(2);
		logic.setPlayer();
		ai.turn(logic);
		assertTrue(logic.getBoard().checkMill(0, 0, 2));
	}

	@Test
	public void AItestMoveBlockMill() {
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.addPlayerOnePieces(new Point(2, 7));
		logic.addPlayerOnePieces(new Point(0, 7));
		logic.addPlayerTwoPieces(new Point(1, 0));
		logic.setBoardNode(2, 7, 1);
		logic.setBoardNode(0, 7, 1);
		logic.setBoardNode(1, 0, 2);
		logic.removeEmptySpace(new Point(2, 7));
		logic.removeEmptySpace(new Point(0, 7));
		logic.removeEmptySpace(new Point(1, 0));
		logic.setPiecesPlaced(18);
		logic.setPhase(2);
		logic.setPlayer();
		ai.turn(logic);
		assertTrue(logic.getBoard().getBoardNode(1, 7) == 2);
	}

	@Test
	public void AItestMoveWithinMill() {
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.addPlayerTwoPieces(new Point(0, 5));
		logic.addPlayerTwoPieces(new Point(1, 5));
		logic.addPlayerTwoPieces(new Point(2, 5));
		logic.addPlayerTwoPieces(new Point(0, 0));
		logic.setBoardNode(0, 5, 2);
		logic.setBoardNode(1, 5, 2);
		logic.setBoardNode(2, 5, 2);
		logic.setBoardNode(0, 0, 2);
		logic.removeEmptySpace(new Point(0, 5));
		logic.removeEmptySpace(new Point(1, 5));
		logic.removeEmptySpace(new Point(2, 5));
		logic.removeEmptySpace(new Point(0, 0));
		logic.setPiecesPlaced(18);
		logic.setPhase(2);
		logic.setPlayer();
		ai.turn(logic);
		assertFalse(logic.getBoard().checkMill(1, 5, 2));
	}

	@Test
	public void AItestPriorityMoveCreateMill() {
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.addPlayerTwoPieces(new Point(0, 0));
		logic.addPlayerTwoPieces(new Point(0, 1));
		logic.addPlayerTwoPieces(new Point(0, 3));
		logic.addPlayerTwoPieces(new Point(0, 5));
		logic.addPlayerOnePieces(new Point(1, 6));
		logic.addPlayerOnePieces(new Point(1, 4));
		logic.setBoardNode(0, 0, 2);
		logic.setBoardNode(0, 1, 2);
		logic.setBoardNode(0, 3, 2);
		logic.setBoardNode(0, 5, 2);
		logic.setBoardNode(1, 6, 1);
		logic.setBoardNode(1, 4, 1);
		logic.removeEmptySpace(new Point(0, 0));
		logic.removeEmptySpace(new Point(0, 1));
		logic.removeEmptySpace(new Point(0, 3));
		logic.removeEmptySpace(new Point(0, 5));
		logic.removeEmptySpace(new Point(1, 6));
		logic.removeEmptySpace(new Point(1, 4));
		logic.setPiecesPlaced(18);
		logic.setPhase(2);
		logic.setPlayer();
		ai.turn(logic);
		assertTrue(logic.getBoard().checkMill(0, 0, 2));
	}

	@Test
	public void AItestPriorityMoveBlockMill() {
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.addPlayerOnePieces(new Point(0, 0));
		logic.addPlayerOnePieces(new Point(0, 1));
		logic.addPlayerTwoPieces(new Point(0, 3));
		logic.addPlayerTwoPieces(new Point(1, 5));
		logic.addPlayerTwoPieces(new Point(1, 6));
		logic.addPlayerTwoPieces(new Point(1, 4));
		logic.setBoardNode(0, 0, 1);
		logic.setBoardNode(0, 1, 1);
		logic.setBoardNode(0, 3, 2);
		logic.setBoardNode(1, 5, 2);
		logic.setBoardNode(1, 6, 2);
		logic.setBoardNode(1, 4, 2);
		logic.removeEmptySpace(new Point(0, 0));
		logic.removeEmptySpace(new Point(0, 1));
		logic.removeEmptySpace(new Point(0, 3));
		logic.removeEmptySpace(new Point(1, 5));
		logic.removeEmptySpace(new Point(1, 6));
		logic.removeEmptySpace(new Point(1, 4));
		logic.setPiecesPlaced(18);
		logic.setPhase(2);
		logic.setPlayer();
		ai.turn(logic);
		assertTrue(logic.getBoard().getBoardNode(0, 2) == 2);
	}

	@Test
	public void AItestPlacePiece(){
		Logic logic = new Logic();
		AI ai = new AI(1,SEED,false);
		logic.placePiece(1, 1);
		ai.turn(logic);
		assertTrue(logic.getBoard().getBoardNode(1, 7) == 2);
	
	}
	
	@Test
	public void AItestPlacePieceAdjacent(){
		Logic logic = new Logic();
		AI ai = new AI(1,SEED,false);
		logic.placePiece(1, 1);
		ai.turn(logic);
		logic.placePiece(0, 0);
		ai.turn(logic);
		assertTrue(logic.getBoard().getBoardNode(1, 7) == 2);
		assertTrue(logic.getBoard().getBoardNode(2, 7) == 2);
	}

	@Test
	public void AItestPlacePieceMillFormation() {
		Logic logic = new Logic();
		AI ai = new AI(1,SEED,false);
		logic.placePiece(1, 1);
		ai.turn(logic);
		logic.placePiece(0, 0);
		ai.turn(logic);
		logic.placePiece(0, 5);
		ai.turn(logic);
		assertTrue(logic.getBoard().getBoardNode(1, 7) == 2);
		assertTrue(logic.getBoard().getBoardNode(2, 7) == 2);
		assertTrue(logic.getBoard().getBoardNode(0, 7) == 2);
	}

	@Test
	public void AItestPlacePieceMillBlock() {
		Logic logic = new Logic();
		AI ai = new AI(1,SEED,false);
		logic.placePiece(1, 1);
		ai.turn(logic);
		logic.placePiece(0, 1);
		ai.turn(logic);
		assertTrue(logic.getBoard().getBoardNode(1, 7) == 2);
		assertTrue(logic.getBoard().getBoardNode(2, 1) == 2);
	}
	
	@Test
	public void AItestPlacePiecePriority(){
		Logic logic = new Logic();
		AI ai = new AI(1,SEED,false);
		logic.placePiece(1, 1);
		ai.turn(logic);
		logic.placePiece(0, 0);
		ai.turn(logic);
		logic.placePiece(0, 1);
		ai.turn(logic);
		assertTrue(logic.getBoard().getBoardNode(1, 7) == 2);
		assertTrue(logic.getBoard().getBoardNode(2, 7) == 2);
		assertTrue(logic.getBoard().getBoardNode(0, 7) == 2);
	}

	@Test
	public void AItestPlacePieceRandom() {
		Logic logic = new Logic();
		AI ai = new AI(0,SEED,false);
		logic.placePiece(1, 1);
		ai.turn(logic);
		logic.placePiece(0, 1);
		ai.turn(logic);
		assertTrue(logic.getBoard().getBoardNode(1, 7) == 2);
		assertTrue(logic.getBoard().getBoardNode(2, 3) == 2);
	}

	
	// AI Remove Tests
	
	@Test
	public void AItestRemoveBlockingMillCorner(){
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.placePiece(1, 0);
		logic.placePiece(1, 1);
		logic.placePiece(2, 1);
		logic.placePiece(1, 2);
		
		logic.setPhase(3);
		logic.setPlayer();
		
		ai.turn(logic);
		
		assertTrue(logic.getBoard().getBoardNode(1, 0) == 0);
		assertTrue(logic.getBoard().getBoardNode(2, 1) == 1);
	}
	
	@Test
	public void AItestRemoveBlockingMillCenter(){
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.placePiece(1, 1);
		logic.placePiece(1, 0);
		logic.placePiece(2, 1);
		logic.placePiece(1, 2);
		
		logic.setPhase(3);
		logic.setPlayer();
		
		ai.turn(logic);
		
		assertTrue(logic.getBoard().getBoardNode(1, 1) == 0);
		assertTrue(logic.getBoard().getBoardNode(2, 1) == 1);
	}
	
	@Test
	public void AItestRemoveForminggMillCorner(){
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.placePiece(1, 0);
		logic.placePiece(2, 0);
		logic.placePiece(1, 1);
		logic.placePiece(2, 3);
		logic.placePiece(0, 5);
		
		logic.setPhase(3);
		
		ai.turn(logic);
		
		assertTrue(logic.getBoard().getBoardNode(1, 0) == 0 || logic.getBoard().getBoardNode(1, 1) == 0);
		assertTrue(logic.getBoard().getBoardNode(0, 5) == 1);
	}
	
	@Test
	public void AItestRemoveForminggMillCenter(){
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.placePiece(1, 0);
		logic.placePiece(2, 0);
		logic.placePiece(1, 2);
		logic.placePiece(2, 3);
		logic.placePiece(0, 5);
		
		logic.setPhase(3);
		
		ai.turn(logic);
		
		assertTrue(logic.getBoard().getBoardNode(1, 0) == 0 || logic.getBoard().getBoardNode(1, 2) == 0);
		assertTrue(logic.getBoard().getBoardNode(0, 5) == 1);
	}
	
	@Test
	public void AItestRemovePriorityCorner(){
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.placePiece(0, 0);
		logic.placePiece(2, 0);
		logic.placePiece(0, 1);
		logic.placePiece(2, 1);
		logic.placePiece(2, 2);
		
		logic.setPhase(3);
		
		ai.turn(logic);
		
		assertTrue(logic.getBoard().getBoardNode(2, 2) == 0);
		assertTrue(logic.getBoard().getBoardNode(0, 0) == 1 && logic.getBoard().getBoardNode(0, 1) == 1);
	}
	
	@Test
	public void AItestRemovePriorityCenter(){
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.placePiece(0, 0);
		logic.placePiece(2, 0);
		logic.placePiece(0, 2);
		logic.placePiece(2, 2);
		logic.placePiece(2, 1);
		
		logic.setPhase(3);
		
		ai.turn(logic);
		
		assertTrue(logic.getBoard().getBoardNode(2, 1) == 0);
		assertTrue(logic.getBoard().getBoardNode(0, 0) == 1 && logic.getBoard().getBoardNode(0, 2) == 1);
	}
	
	@Test
	public void AItestRemoveRandom(){
		Logic logic = new Logic();
		AI ai = new AI(1, SEED, false);
		logic.placePiece(0, 0);
		logic.placePiece(1, 0);
		logic.placePiece(2, 0);
		
		logic.setPhase(3);
		
		ai.turn(logic);
		
		assertTrue(logic.getBoard().getBoardNode(0, 0) == 0 || logic.getBoard().getBoardNode(2, 0) == 0);
	}
	
	@Test
	public void EasyAItestRemoveRandom(){
		Logic logic = new Logic();
		AI ai = new AI(0, SEED, false);
		logic.placePiece(0, 0);
		logic.placePiece(1, 0);
		logic.placePiece(2, 0);
		
		logic.setPhase(3);
		
		ai.turn(logic);
		
		assertTrue(logic.getBoard().getBoardNode(0, 0) == 0 || logic.getBoard().getBoardNode(2, 0) == 0);
	}
}
