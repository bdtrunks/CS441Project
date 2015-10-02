import javax.swing.JFrame;

@SuppressWarnings("serial")
public class NineMensMorris extends JFrame{
	
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
