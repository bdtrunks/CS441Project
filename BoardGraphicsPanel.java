import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Represents the graphical layout of the board. Colors of the squares could possibly
 * change to show valid moves, or images of player pieces placed over them.
 * @author Brian Dunn
 *
 */
@SuppressWarnings("serial")
public class BoardGraphicsPanel extends JPanel {

	public BoardGraphicsPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(500, 500));
	}
	
	
	public void paintComponent(Graphics canvas) {
		super.paintComponent(canvas);
		//middle left
        canvas.fillRect(200, 250, 10, 10);
        canvas.fillRect(125, 250, 10, 10);
        canvas.fillRect(50, 250, 10, 10);
        canvas.drawLine(200, 255, 50, 255);
        //middle right
        canvas.fillRect(300, 250, 10, 10);
        canvas.fillRect(375, 250, 10, 10);
        canvas.fillRect(450, 250, 10, 10);
        canvas.drawLine(300, 255, 450, 255);
        //inner bottom
        canvas.fillRect(200, 325, 10, 10);
        canvas.fillRect(250, 325, 10, 10);
        canvas.fillRect(300, 325, 10, 10);
        canvas.drawLine(200, 330, 300, 330);
        //inner top
        canvas.fillRect(200, 175, 10, 10);
        canvas.fillRect(250, 175, 10, 10);
        canvas.fillRect(300, 175, 10, 10);
        canvas.drawLine(200, 180, 300, 180);
        //middle top
        canvas.fillRect(125, 100, 10, 10);
        canvas.fillRect(250, 100, 10, 10);
        canvas.fillRect(375, 100, 10, 10);
        canvas.drawLine(125, 105, 375, 105);
        //middle bottom
        canvas.fillRect(125, 400, 10, 10);
        canvas.fillRect(250, 400, 10, 10);
        canvas.fillRect(375, 400, 10, 10);
        canvas.drawLine(125, 405, 375, 405);
        //outer bottom
        canvas.fillRect(50, 475, 10, 10);
        canvas.fillRect(250, 475, 10, 10);
        canvas.fillRect(450, 475, 10, 10);
        canvas.drawLine(50, 480, 450, 480);
        canvas.drawLine(455, 475, 455, 25);
        //outer top
        canvas.fillRect(50, 25, 10, 10);
        canvas.fillRect(250, 25, 10, 10);
        canvas.fillRect(450, 25, 10, 10);
        canvas.drawLine(50, 30, 450, 30);
        canvas.drawLine(55, 475, 55, 25);
        
        canvas.drawLine(255, 475, 255, 325);
        canvas.drawLine(255, 175, 255, 25);
        canvas.drawLine(130, 400, 130, 100);
        canvas.drawLine(380, 400, 380, 100);
        canvas.drawLine(205, 175, 205, 325);
        canvas.drawLine(305, 175, 305, 325);
	}
	
}
