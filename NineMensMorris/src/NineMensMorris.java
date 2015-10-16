import javax.swing.JFrame;

import ui.NineMensMorrisPanel;

/**
 * Represents a 9 Mens Morris game
 *
 */

@SuppressWarnings("serial")
public class NineMensMorris extends JFrame{
	
	/**
	 * Creates the game frame
	 */
	public NineMensMorris() {
		//new JFrame("9 Men's Morris");
		setTitle("9 Men's Morris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(new NineMensMorrisPanel());
		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new NineMensMorris();
	}

}
