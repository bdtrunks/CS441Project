package ui;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import AI.AI;
import logic.Logic;

/**
 * Contains the panels for the 9 men morris game board and controls/information
 * @author Owner
 *
 */
@SuppressWarnings("serial")
public class NineMensMorrisPanel extends JPanel {
	private NineMensMorrisBoardPanel boardPanel;
	private NineMensMorrisControlsPanel controlsPanel;
	private Logic logic; private int gameType;
	private AI AI;
	
	//List of all possible game positions
	private static final List<Point> SPOT_INDEX_MAP = Arrays.asList(
			new Point(0, 0), new Point(0, 1), new Point(0, 2),
			new Point(1, 0), new Point(1, 1), new Point(1, 2),
			new Point(2, 0), new Point(2, 1), new Point(2, 2),
			new Point(0, 7), new Point(1, 7), new Point(2, 7),
			new Point(2, 3), new Point(1, 3), new Point(0, 3),
			new Point(2, 6), new Point(2, 5), new Point(2, 4),
			new Point(1, 6), new Point(1, 5), new Point(1, 4),
			new Point(0, 6), new Point(0, 5), new Point(0, 4));
	
	/**
	 * Creates board panel and controls panel objects
	 */
	public NineMensMorrisPanel () {
		this.logic = new Logic();
		this.gameType = 2;
		
		setPreferredSize(new Dimension(720, 500));
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1; c.weighty = 1;
		c.gridx = 0; c.gridy = 0;
		
		boardPanel = new NineMensMorrisBoardPanel(new BoardClickListener(this));
		boardPanel.setName("boardPanel");
		add(boardPanel, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0; c.weighty = 1;
		c.gridx = 1; c.gridy = 0;
		
		controlsPanel = new NineMensMorrisControlsPanel(new NewGameListener(this));
		controlsPanel.setName("controlsPanel");
		add(controlsPanel, c);
	}
	
	/**
	 * Sets the game spaces on the board panel for the given player
	 * @param player - player to set spaces for
	 * @param pieces - all possible game spaces
	 */
	public void setPlayerPieces(int player, List<Point> pieces) {
		Collection<Integer> spots = new LinkedList<>();
		for (Point piece : pieces) {
			spots.add(SPOT_INDEX_MAP.indexOf(piece));
		}
		
		switch (player) {
			case 1:
				this.boardPanel.setPlayer1PieceIndices(spots);
				break;
			case 2:
				this.boardPanel.setPlayer2PieceIndices(spots);
				break;
 		}
	}
	
	/**
	 * Sets what spots on the game board can be moved to
	 * @param moves - collection of valid moves
	 */
	public void setValidMoves(Collection<Point> moves) {
		Collection<Integer> spots = new LinkedList<>();
		for (Point move : moves) {
			spots.add(SPOT_INDEX_MAP.indexOf(move));
		}
		this.boardPanel.setValidMoveIndices(spots);
	}
	
	/**
	 * Listens for mouse clicks on the board panel when placing/moving/removing pieces
	 *
	 */
	public class BoardClickListener {
		private NineMensMorrisPanel panel;
		private Point prevPoint = null;
		
		/**
		 * Sets the click listener to listen for mouse clicks on the game board panel
		 * @param panel - panel to listen for mouse clicks
		 */
		public BoardClickListener(NineMensMorrisPanel panel) {
			this.panel = panel;
		}
		
		/**
		 * Performs actions a spot on the board is clicked based on current game phase and player
		 * @param index - the position of the mouse click
		 */
		public void boardClicked(int index) {
			boolean ai = false;
			Point p = SPOT_INDEX_MAP.get(index);
			
			switch(panel.logic.getPhase()) {
				case 1: // place piece
					if (panel.logic.placePiece(p.x, p.y))
						ai = gameType < 2;
					break;
				case 2: // move piece
					if (prevPoint != null && logic.movePiece(prevPoint.x, prevPoint.y, p.x, p.y)) {
						prevPoint = null;
						panel.setValidMoves(Collections.emptyList());
						panel.boardPanel.setSelectedPiece(null);
						ai = gameType < 2;
					} else {
						prevPoint = p;
						Collection<Point> validMoves = panel.logic.checkMoves(p.x, p.y);
						panel.setValidMoves(validMoves);
						panel.boardPanel.setSelectedPiece(validMoves.size() > 0 ? index : null);
					}
					break;	
				case 3: // remove piece
					if (panel.logic.removePiece(p.x, p.y))
						ai = gameType < 2;
					break;
			}
			
			updateBoard();

			if (ai) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						AI.turn(panel.logic);
						updateBoard();
						if (panel.logic.getPhase() == 3) {
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
							AI.turn(panel.logic);
							updateBoard();
								}
							});
						}
					}
				});
			}
		}
		
		private void updateBoard() {
			panel.setPlayerPieces(1, panel.logic.getPlayerOnePieces());
			panel.setPlayerPieces(2, panel.logic.getPlayerTwoPieces());
			
			panel.controlsPanel.setPlayerLabel((panel.logic.getWinner() > 0) ? logic.getWinner() : logic.getPlayer());
			panel.controlsPanel.setInstructions(logic.getPhase());
			panel.controlsPanel.setPiecesPlaced(logic.getPiecesPlaced());
		}
	}
	
	/**
	 * Listens for the new game button to be pushed
	 *
	 */
	public class NewGameListener {
		private NineMensMorrisPanel panel;
		
		/**
		 * Sets the new game listener to listen for new game button pushes on the control board panel
		 * @param panel - panel to listen for new game button pushes
		 */
		public NewGameListener(NineMensMorrisPanel panel) {
			this.panel = panel;
		}

		/**
		 * When new game button pushed, reset everything
		 */
		public void newGame(int gameType) {
			panel.logic = new Logic();
			panel.gameType = gameType;
			panel.AI = new AI(gameType);
			panel.controlsPanel.setPlayerLabel(logic.getPlayer());
			panel.controlsPanel.setGameMode(gameType);
			panel.controlsPanel.setInstructions(logic.getPhase());
			panel.controlsPanel.setPiecesPlaced(0);
			panel.setPlayerPieces(1, Collections.emptyList());
			panel.setPlayerPieces(2, Collections.emptyList());
			panel.setValidMoves(Collections.emptyList());
		}
	}
}
