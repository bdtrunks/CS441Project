package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import ui.NineMensMorrisPanel.NewGameListener;

/**
 * Represents the control panel for 9 men morris game that contains a new game button,
 * a label representing the current player, and game instructions
 *
 */
@SuppressWarnings("serial")
public class NineMensMorrisControlsPanel extends JPanel {
	private static final Color[] PLAYER_COLOR = {null, NineMensMorrisBoardPanel.PLAYER_1_COLOR, 
													NineMensMorrisBoardPanel.PLAYER_2_COLOR};
	private JLabel	  playerLabel, player1PiecesLeft, player2PiecesLeft;
	private JTextArea instructionsTextArea;
	private JButton	  easyButton, hardButton, twoPlayerButton;
	
	/**
	 * Creates the controls panel
	 * @param newGameListener - fires when new game button is hit to start new game
	 */
	public NineMensMorrisControlsPanel(NewGameListener newGameListener) {
		setPreferredSize(new Dimension(220, 0));
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1; c.weighty = 0;
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = 1; c.gridheight = 1;
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridBagLayout());
		
		playerLabel = new JLabel("Player 1");
		playerLabel.setPreferredSize(new Dimension(200, 50));
		playerLabel.setFont(new Font("Helvetica", 0, 40));
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		c.insets = new Insets(10, 10, 10, 10);
		add(playerLabel, c);
		
		instructionsTextArea = new JTextArea(3, 50);
		instructionsTextArea.setEditable(false);
		instructionsTextArea.setLineWrap(true);
		instructionsTextArea.setWrapStyleWord(true);
		instructionsTextArea.setPreferredSize(new Dimension(200, 50));
		c.gridy = 1; c.insets = new Insets(0, 10, 10, 10);
		add(instructionsTextArea, c);
		
		player1PiecesLeft = new JLabel("Player 1 Pieces: 9");
		player1PiecesLeft.setPreferredSize(new Dimension(200, 18));
		player1PiecesLeft.setFont(new Font("Helvetica", 0, 20));
		player1PiecesLeft.setForeground(PLAYER_COLOR[1]);
		player1PiecesLeft.setHorizontalAlignment(SwingConstants.CENTER);
		player1PiecesLeft.setName("player1PiecesLeft");
		c.gridy = 2; c.insets = new Insets(2, 2, 2, 2);
		add(player1PiecesLeft, c);
		
		player2PiecesLeft = new JLabel("Player 2 Pieces: 9");
		player2PiecesLeft.setPreferredSize(new Dimension(200, 18));
		player2PiecesLeft.setFont(new Font("Helvetica", 0, 20));
		player2PiecesLeft.setForeground(PLAYER_COLOR[2]);
		player2PiecesLeft.setHorizontalAlignment(SwingConstants.CENTER);
		player2PiecesLeft.setName("player2PiecesLeft");
		c.gridy = 3;
		add(player2PiecesLeft, c);
		
		JPanel newGamePanel = new JPanel();
		
		newGamePanel.setPreferredSize(new Dimension(200, 134));
		newGamePanel.setBorder(BorderFactory.createTitledBorder(null, "Start New Game", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		
		easyButton = new JButton("Easy");
		easyButton.setPreferredSize(new Dimension(180, 30));
		newGamePanel.add(easyButton);
		
		hardButton = new JButton("Hard");
		hardButton.setPreferredSize(new Dimension(180, 30));
		newGamePanel.add(hardButton);
		
		twoPlayerButton = new JButton("2 Player");
		twoPlayerButton.setPreferredSize(new Dimension(180, 30));
		newGamePanel.add(twoPlayerButton);
		
		newGamePanel.setMinimumSize(new Dimension(200, 134));
		
		c.anchor = GridBagConstraints.SOUTH;
		c.gridy = 4; c.weighty = 1;
		c.insets = new Insets(20, 10, 10, 10);
		c.gridheight = GridBagConstraints.REMAINDER;
		
		add(newGamePanel, c);
		
		easyButton.addActionListener(new NewGameButtonListener(newGameListener, 0));
		hardButton.addActionListener(new NewGameButtonListener(newGameListener, 1));
		twoPlayerButton.addActionListener(new NewGameButtonListener(newGameListener, 2));
		
		setPlayerLabel(1);
		setInstructions(1);
	}
	
	/**
	 * Sets the player label to the current player
	 * @param player - current player
	 */
	public void setPlayerLabel(int player) {
		playerLabel.setText("Player " + player);
		playerLabel.setForeground(PLAYER_COLOR[player]);
	}
	
	public void setPiecesPlaced(int piecesPlaced) {
		player1PiecesLeft.setText("Player 1 Pieces: " + (9 -((int) Math.ceil(((double)piecesPlaced)/2))));
		player2PiecesLeft.setText("Player 2 Pieces: " + (9 - (piecesPlaced/2)));
	}
	
	/**
	 * Shows the instructions for the players given the current phase
	 * @param phase - current game phase
	 */
	public void setInstructions(int phase) {
		switch(phase) {
			case 1: // place piece
				instructionsTextArea.setText("Place a piece on an empty spot on the board.");
				break;
			case 2: // move piece
				instructionsTextArea.setText("Move one of your pieces to an open spot.");
				break;	
			case 3: // remove piece
				instructionsTextArea.setText("Remove one of your opponents pieces.");
				break;
			case 4: // winner
				instructionsTextArea.setText("Is The WINNER!");
				break;
		}
	}
	
	private class NewGameButtonListener implements ActionListener {
		private NewGameListener newGameListener;
		private int gameType;
		
		public NewGameButtonListener(NewGameListener newGameListener, int gameType) {
			this.newGameListener = newGameListener;
			this.gameType = gameType;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			newGameListener.newGame(gameType);
		}
	}
}
