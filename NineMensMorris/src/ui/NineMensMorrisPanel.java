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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import logic.Logic;

@SuppressWarnings("serial")
public class NineMensMorrisPanel extends JPanel {
	private NineMensMorrisBoardPanel boardPanel;
	private NineMensMorrisControlsPanel controlsPanel;
	private Logic logic;
	
	private static final List<Point> SPOT_INDEX_MAP = Arrays.asList(
			new Point(0, 0), new Point(0, 1), new Point(0, 2),
			new Point(1, 0), new Point(1, 1), new Point(1, 2),
			new Point(2, 0), new Point(2, 1), new Point(2, 2),
			new Point(0, 7), new Point(1, 7), new Point(2, 7),
			new Point(2, 3), new Point(1, 3), new Point(0, 3),
			new Point(2, 6), new Point(2, 5), new Point(2, 4),
			new Point(1, 6), new Point(1, 5), new Point(1, 4),
			new Point(0, 6), new Point(0, 5), new Point(0, 4));
	
	public NineMensMorrisPanel () {
		this.logic = new Logic();
		
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
	
	public void setValidMoves(Collection<Point> moves) {
		Collection<Integer> spots = new LinkedList<>();
		for (Point move : moves) {
			spots.add(SPOT_INDEX_MAP.indexOf(move));
		}
		this.boardPanel.setValidMoveIndices(spots);
	}
	
	public class BoardClickListener {
		private NineMensMorrisPanel panel;
		private Point prevPoint;
		
		public BoardClickListener(NineMensMorrisPanel panel) {
			this.panel = panel;
		}
		
		public void boardClicked(int index) {	
			Point p = SPOT_INDEX_MAP.get(index);
			switch(panel.logic.getPhase()) {
				case 1: // place piece
					panel.logic.placePiece(p.x, p.y);
					break;
				case 2: // move piece
					if (prevPoint != null && logic.movePiece(prevPoint.x, prevPoint.y, p.x, p.y)) {
						prevPoint = null;
						panel.setValidMoves(Collections.emptyList());
					} else {
						prevPoint = p;
						panel.setValidMoves(panel.logic.checkMoves(p.x, p.y));
					}
					break;	
				case 3: // remove piece
					panel.logic.removePiece(p.x, p.y);
					break;
			}
			
			panel.setPlayerPieces(1, panel.logic.getPlayerOnePieces());
			panel.setPlayerPieces(2, panel.logic.getPlayerTwoPieces());
			
			panel.controlsPanel.setPlayerLabel((panel.logic.getWinner() > 0) ? logic.getWinner() : logic.getPlayer());
			panel.controlsPanel.setInstructions(logic.getPhase());
		}
	}
	
	class NewGameListener implements ActionListener {
		private NineMensMorrisPanel panel;
		
		public NewGameListener(NineMensMorrisPanel panel) {
			this.panel = panel;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panel.logic = new Logic();
			panel.controlsPanel.setPlayerLabel(logic.getPlayer());
			panel.controlsPanel.setInstructions(logic.getPhase());
			panel.setPlayerPieces(1, Collections.emptyList());
			panel.setPlayerPieces(2, Collections.emptyList());
			panel.setValidMoves(Collections.emptyList());
		}
	}
}
