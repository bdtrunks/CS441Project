package ui;

import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import ui.NineMensMorrisPanel.BoardClickListener;

@SuppressWarnings("serial")
public class NineMensMorrisBoardPanel extends JPanel {
	private static final Color GRID_COLOR = Color.black;
	private static final double GRID_STROKE_SIZE = 0.03;
	private static final double GRID_POINT_SIZE = 0.18;
	
	private static final double GAME_PIECE_SIZE = 0.6;
	protected static final Color PLAYER_1_COLOR = Color.decode("#09347A");
	protected static final Color PLAYER_2_COLOR = Color.decode("#F1632A");
	
	private static final double VALID_MOVE_SIZE = 0.3;
	private static final Color VALID_MOVE_COLOR = Color.decode("#13A558");
	
	private static final Collection<Integer> GRID_SQUARES = Arrays.asList(2, 4, 6);
	private static final Collection<Pair<Point>> GRID_LINES = Arrays.asList(
			new Pair<Point>(new Point(-3, 0), new Point(-1, 0)),
			new Pair<Point>(new Point(1, 0), new Point(3, 0)),
			new Pair<Point>(new Point(0, -3), new Point(0, -1)),
			new Pair<Point>(new Point(0, 1), new Point(0, 3)));
	private static final List<Point> GRID_POINTS = Arrays.asList(
			new Point(-3, -3), new Point( 0, -3), new Point( 3, -3),
			new Point(-2, -2), new Point( 0, -2), new Point( 2, -2),
			new Point(-1, -1), new Point( 0, -1), new Point( 1, -1),
			new Point(-3,  0), new Point(-2,  0), new Point(-1,  0),
			new Point( 1,  0), new Point( 2,  0), new Point( 3,  0),
			new Point(-1,  1), new Point( 0,  1), new Point( 1,  1),
			new Point(-2,  2), new Point( 0,  2), new Point( 2,  2),
			new Point(-3,  3), new Point( 0,  3), new Point( 3,  3));
	
	private Collection<Integer> player1PieceIndices = Collections.emptyList();
	private Collection<Integer> player2PieceIndices = Collections.emptyList();
	private Collection<Integer> validMoveIndices	= Collections.emptyList();
	
	private final BoardClickListener boardClickListener;
	
	public NineMensMorrisBoardPanel(BoardClickListener boardClickListener) {
		setBackground(Color.lightGray);
		setPreferredSize(new Dimension(200, 200));
		addMouseListener(new ClickListener(this));
		this.boardClickListener = boardClickListener;
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
		canvas2D.setStroke(new BasicStroke((int)(GRID_STROKE_SIZE * scale)));
		GRID_SQUARES.forEach(s -> drawSquare(canvas2D, center, s, scale));
		GRID_LINES  .forEach(l -> drawLine  (canvas2D, center, new Point(l.first()), l.second(), scale));
		GRID_POINTS .forEach(p -> drawCircle(canvas2D, center, p, GRID_POINT_SIZE, scale));
		
		// draw player pieces
		canvas2D.setColor(PLAYER_1_COLOR);
		player1PieceIndices.forEach(p -> drawGamePiece(canvas2D, center, GRID_POINTS.get(p), scale));
		canvas2D.setColor(PLAYER_2_COLOR);
		player2PieceIndices.forEach(p -> drawGamePiece(canvas2D, center, GRID_POINTS.get(p), scale));
		
		// draw valid moves
		canvas2D.setColor(VALID_MOVE_COLOR);
		validMoveIndices.forEach(m -> drawCircle(canvas2D, center, GRID_POINTS.get(m), VALID_MOVE_SIZE, scale));
	}
	
	public void setPlayer1PieceIndices(Collection<Integer> pieces) {
		this.player1PieceIndices = pieces;
		repaint();
	}
	
	public void setPlayer2PieceIndices(Collection<Integer> pieces) {
		this.player2PieceIndices = pieces;
		repaint();
	}
	
	public void setValidMoveIndices(Collection<Integer> moves) {
		this.validMoveIndices = moves;
		repaint();
	}
	
	private void drawLine(Graphics2D canvas, Point center, Point start, Point end, int scale) {
		canvas.drawLine(center.x + start.x * scale, center.y + start.y * scale, 
						center.x + end.x * scale, center.y + end.y * scale);
	}
	
	private void drawSquare(Graphics2D canvas, Point center, int size, int scale) {
		int scaledSize = (int)(size * scale);
		int half = scaledSize / 2;
		canvas.drawRect(center.x-half, center.y-half, scaledSize, scaledSize);
	}
	
	private void drawCircle(Graphics2D canvas, Point center, Point location, double size, int scale) {
		int scaledSize = (int)(size * scale);
		int half = scaledSize / 2;
		canvas.fillOval(
				(center.x + (location.x * scale)) - half,
				(center.y + (location.y * scale)) - half,
				scaledSize, scaledSize);
	}
	
	private void drawGamePiece(Graphics2D canvas, Point center, Point location, int scale) {
		drawCircle(canvas, center, location, GAME_PIECE_SIZE, scale);
	}
	
	
	class ClickListener implements MouseListener {
		private NineMensMorrisBoardPanel panel;
		
		public ClickListener(NineMensMorrisBoardPanel panel) {
			this.panel = panel;
		}
		
		public void mouseReleased(MouseEvent e) {	
			int width = getWidth(), height = getHeight();
			int scale = Math.min(width, height) / 8;
			int x = Math.round((float)(e.getPoint().x - (width / 2)) / scale);
			int y = Math.round((float)(e.getPoint().y - (height / 2)) / scale);
				
			int gridIndex = NineMensMorrisBoardPanel.GRID_POINTS.indexOf(new Point(x, y));
			if (gridIndex > -1) {
				panel.boardClickListener.boardClicked(gridIndex);
			}
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	}
}
