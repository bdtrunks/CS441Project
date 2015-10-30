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
	private JLabel	  playerLabel;
	private JTextArea instructionsTextArea;
	private JButton	  onePlayerButton, twoPlayerButton;
	
	/**
	 * Creates the controls panel
	 * @param newGameListener - fires when new game button is hit to start new game
	 */
	public NineMensMorrisControlsPanel(NewGameListener newGameListener) {
		setPreferredSize(new Dimension(220, 500));
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 0; c.weighty = 0;
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = 1; c.gridheight = 1;
		
		playerLabel = new JLabel("Player 1");
		playerLabel.setPreferredSize(new Dimension(200, 50));
		playerLabel.setFont(new Font("Helvetica", 0, 40));
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setName("playerLabel");
		add(playerLabel, c);
		
		
		c.weightx = 1; c.weighty = 0;
		c.gridx = 0; c.gridy = 1;
		
		instructionsTextArea = new JTextArea(3, 50);
		instructionsTextArea.setEditable(false);
		instructionsTextArea.setLineWrap(true);
		instructionsTextArea.setWrapStyleWord(true);
		
		instructionsTextArea.setPreferredSize(new Dimension(200, 50));
		add(instructionsTextArea, c);
		
		
		JPanel newGamePanel = new JPanel();
		newGamePanel.setBorder(BorderFactory.createTitledBorder(null, "Start New Game", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		newGamePanel.setPreferredSize(new Dimension(200, 200));
		
		onePlayerButton = new JButton("1 Player");
		onePlayerButton.setPreferredSize(new Dimension(85, 25));
		newGamePanel.add(onePlayerButton);
		
		twoPlayerButton = new JButton("2 Player");
		twoPlayerButton.setPreferredSize(new Dimension(85, 25));
		newGamePanel.add(twoPlayerButton);
		
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 1; c.weighty = 2;
		c.gridx = 0; c.gridy = 2;
		c.gridwidth = 1; c.gridheight = 2;
		
		add(newGamePanel, c);
		
		onePlayerButton.addActionListener(new NewGameButtonListener(newGameListener, true));
		twoPlayerButton.addActionListener(new NewGameButtonListener(newGameListener, false));
		
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
		private boolean useAI;
		
		public NewGameButtonListener(NewGameListener newGameListener, boolean useAI) {
			this.newGameListener = newGameListener;
			this.useAI = useAI;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			newGameListener.newGame(useAI);
		}
	}
}
