import javax.swing.JFrame;

/**
 * Represents a 9 Men's Morris Game.
 * @author Brian Dunn
 *
 */
public class NineMenMorris {

	public static void main(String[] args) {
		JFrame frame = new JFrame("9 Men's Morris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new NineMenMorrisPanel());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
