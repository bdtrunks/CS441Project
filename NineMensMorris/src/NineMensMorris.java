import javax.swing.JFrame;

public class NineMensMorris {

	public static void main(String[] args) {
		JFrame frame = new JFrame("9 Men's Morris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new NineMensMorrisPanel());
		
		frame.pack();
		frame.setVisible(true);
	}

}
