import java.util.Arrays;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NineMensMorrisPanel extends JPanel {
	private NineMensMorrisBoardPanel boardPanel;
	private NineMensMorrisControlsPanel controlsPanel;
	
	public NineMensMorrisPanel () {
		setPreferredSize(new Dimension(720, 500));
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1; c.weighty = 1;
		c.gridx = 0; c.gridy = 0;
		
		boardPanel = new NineMensMorrisBoardPanel(new ClickListener(this));
		add(boardPanel, c);
		
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0; c.weighty = 1;
		c.gridx = 1; c.gridy = 0;
		
		controlsPanel = new NineMensMorrisControlsPanel();
		add(controlsPanel, c);
	}
	
	class ClickListener implements MouseListener {
		private NineMensMorrisPanel panel;
		
		public ClickListener(NineMensMorrisPanel panel) {
			this.panel = panel;
		}
		
		public void mouseClicked(MouseEvent e) {	
			if (e.getButton() == 1) {
				panel.boardPanel.setPlayer1PieceIndices(Arrays.asList(e.getX()));
				System.out.println(e.getX());
			}
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
