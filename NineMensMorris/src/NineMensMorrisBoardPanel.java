import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class NineMensMorrisBoardPanel extends JPanel {
	private static final Color GRID_COLOR = Color.black;
	private static final double GRID_STROKE_SIZE = 0.004;
	private static final double GRID_POINT_SIZE = 0.18;
	
	private static final Collection<Integer> GRID_SQUARES = Arrays.asList(2, 4, 6);
	private static final Collection<Pair<Point>> GRID_LINES = Arrays.asList(
			new Pair<Point>(new Point(-3, 0), new Point(-1, 0)),
			new Pair<Point>(new Point(1, 0), new Point(3, 0)),
			new Pair<Point>(new Point(0, -3), new Point(0, -1)),
			new Pair<Point>(new Point(0, 1), new Point(0, 3)));
	private static final Collection<Point> GRID_POINTS = Arrays.asList(
			new Point(-3, -3), new Point( 0, -3), new Point( 3, -3),
			new Point(-2, -2), new Point( 0, -2), new Point( 2, -2),
			new Point(-1, -1), new Point( 0, -1), new Point( 1, -1),
			new Point(-3,  0), new Point(-2,  0), new Point(-1,  0),
			new Point( 1,  0), new Point( 2,  0), new Point( 3,  0),
			new Point(-1,  1), new Point( 0,  1), new Point( 1,  1),
			new Point(-2,  2), new Point( 0,  2), new Point( 2,  2),
			new Point(-3,  3), new Point( 0,  3), new Point( 3,  3));
	
	public NineMensMorrisBoardPanel() {
		setBackground(Color.lightGray);
		setPreferredSize(new Dimension(200, 200));
	}
	
	public void paintComponent(Graphics canvas) {
		super.paintComponent(canvas);
		Graphics2D canvas2D = (Graphics2D) canvas;

		int width = getWidth(), height = getHeight();
		int	maxSize = Math.min(width, height);
		int scale = maxSize / 8;
		Point center = new Point(width / 2, height / 2);
		
		// draw board grid
		canvas2D.setColor(GRID_COLOR);
		canvas2D.setStroke(new BasicStroke((int)(GRID_STROKE_SIZE * maxSize)));
		GRID_SQUARES.forEach(s -> drawSquare(canvas2D, center, s, scale));
		GRID_LINES.forEach(l -> drawLine(canvas2D, center, new Point(l.first()), l.second(), scale));
		GRID_POINTS.forEach(p -> drawCircle(canvas2D, center, p, GRID_POINT_SIZE, scale));
	}
	
	private void drawSquare(Graphics2D canvas, Point center, int size, int scale) {
		size *= scale;
		int half = size / 2;
		canvas.drawRect(center.x-half, center.y-half, size, size);
	}
	
	private void drawLine(Graphics2D canvas, Point center, Point start, Point end, int scale) {
		canvas.drawLine(center.x + start.x * scale, center.y + start.y * scale, 
						center.x + end.x * scale, center.y + end.y * scale);
	}
	
	private void drawCircle(Graphics2D canvas, Point center, Point location, double size, int scale) {
		int scaledSize = (int)(size * scale);
		int half = scaledSize / 2;
		canvas.fillOval(
				(center.x + (location.x * scale)) - half,
				(center.y + (location.y * scale)) - half,
				scaledSize, scaledSize);
	}
	
}