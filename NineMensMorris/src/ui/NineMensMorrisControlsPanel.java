package ui;

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
	private JLabel playerLabel;
	private JTextArea instructions;
	private JButton newGameButton;
	
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
		
		instructions = new JTextArea(3, 50);
		instructions.setEditable(false);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		
		instructions.setPreferredSize(new Dimension(200, 50));
		add(instructions, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(10, 10, 10, 10);
		c.weightx = 1; c.weighty = 1;
		c.gridx = 0; c.gridy = 2;
		c.gridwidth = 1; c.gridheight = 1;
		
		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(200, 700));
		newGameButton.setName("newGameButton");
		add(newGameButton, c);
		
		newGameButton.addActionListener(newGameListener);
		
		setPlayerLabel(1);
		setInstructions(1);
	}
	
	public void setPlayerLabel(int player) {
		playerLabel.setText("Player " + player);
	}
	
	public void setInstructions(int phase) {
		switch(phase) {
			case 1: // place piece
				instructions.setText("Place a piece on an empty spot on the board.");
				break;
			case 2: // move piece
				instructions.setText("Move one of your pieces to an open spot.");
				break;	
			case 3: // remove piece
				instructions.setText("Remove one of your opponents pieces.");
				break;
		}
	}
}
