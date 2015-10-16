package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class NineMensMorrisControlsPanel extends JPanel {
	private static final Color[] PLAYER_COLOR = {null, NineMensMorrisBoardPanel.PLAYER_1_COLOR, 
													NineMensMorrisBoardPanel.PLAYER_2_COLOR};
	private JLabel	  playerLabel;
	private JTextArea instructionsTextArea;
	private JButton	  newGameButton;
	
	public NineMensMorrisControlsPanel(ActionListener newGameListener) {
		setPreferredSize(new Dimension(220, 55));
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 0; c.weighty = 0;
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = 1; c.gridheight = 1;
		
		playerLabel = new JLabel("Player 1");
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
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 1; c.weighty = 1;
		c.gridx = 0; c.gridy = 2;
		c.gridwidth = 1; c.gridheight = 1;
		
		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(200, 100));
		newGameButton.setName("newGameButton");
		add(newGameButton, c);
		
		newGameButton.addActionListener(newGameListener);
		
		setPlayerLabel(1);
		setInstructions(1);
	}
	
	public void setPlayerLabel(int player) {
		playerLabel.setText("Player " + player);
		playerLabel.setForeground(PLAYER_COLOR[player]);
	}
	
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
}
