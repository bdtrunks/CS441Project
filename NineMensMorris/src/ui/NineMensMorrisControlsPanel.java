package ui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NineMensMorrisControlsPanel extends JPanel {
	private JLabel playerLabel;
	private JButton newGameButton;
	
	public NineMensMorrisControlsPanel(ActionListener newGameListener) {
		setPreferredSize(new Dimension(220, 55));
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(10, 0, 0, 0);
		c.weightx = 0; c.weighty = 1;
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = 1; c.gridheight = 1;
		
		playerLabel = new JLabel("Player 1");
		playerLabel.setFont(new Font("Helvetica", 0, 40));
		//playerLabel.setAlignment(Label.CENTER);
		playerLabel.setName("playerLabel");
		add(playerLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0, 0, 10, 0);
		c.weightx = 0; c.weighty = 1;
		c.gridx = 0; c.gridy = 2;
		c.gridwidth = 1; c.gridheight = 1;
		
		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(200, 50));
		newGameButton.setName("newGameButton");
		add(newGameButton, c);
		
		newGameButton.addActionListener(newGameListener);
	}
	
	public void setPlayerLabel(int player) {
		playerLabel.setText("Player " + player);
	}
}
