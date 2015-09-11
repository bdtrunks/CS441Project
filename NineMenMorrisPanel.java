import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Creates a new Panel that contains all GUI elements of the game
 * @author Brian Dunn
 *
 */
@SuppressWarnings("serial")
public class NineMenMorrisPanel extends JPanel implements ActionListener, MouseListener {
	
	private JRadioButton onePlayerSelect, twoPlayerSelect;
	private JButton newGame;
	private JPanel boardPanel;
	
	public NineMenMorrisPanel () {
		//Graphical Board Representation
		boardPanel = new BoardGraphicsPanel();
		
		//create panel for all options
		JPanel options = new JPanel();
		options.setLayout(new GridLayout(2,1));
		
		//playerSelectPanel houses 1 or 2 player selection buttons
		JPanel playerSelectPanel = new JPanel();
		playerSelectPanel.setBorder(BorderFactory.createTitledBorder("Player Select"));
		playerSelectPanel.setFocusable(false);
		
		onePlayerSelect = new JRadioButton("1 Player");
		onePlayerSelect.setMnemonic(MouseEvent.MOUSE_CLICKED);
		twoPlayerSelect = new JRadioButton("2 Player");
		twoPlayerSelect.setMnemonic(MouseEvent.MOUSE_CLICKED);
		twoPlayerSelect.setSelected(true);
		ButtonGroup player_select = new ButtonGroup();
		player_select.add(onePlayerSelect);
		player_select.add(twoPlayerSelect);
		onePlayerSelect.addActionListener(this);
		twoPlayerSelect.addActionListener(this);
		playerSelectPanel.add(onePlayerSelect);
		playerSelectPanel.add(twoPlayerSelect);
		
		//new game button
		newGame = new JButton("New Game");
		newGame.addActionListener(this);
		
		options.add(playerSelectPanel);
		options.add(newGame);
		
		//add all panels to main panel
		add(boardPanel);
		add(options);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
