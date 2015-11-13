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

/**
 * Contains the graphical representation of the 9 mens morris game board
 *
 */
@SuppressWarnings("serial")
public class NineMensMorrisBoardPanel extends JPanel {
	private static final Color GRID_COLOR = Color.black;
	private static final double GRID_STROKE_SIZE = 0.03;
	private static final double GRID_POINT_SIZE = 0.18;
	
	private static final double GAME_PIECE_SIZE = 0.6;
	protected static final Color PLAYER_1_COLOR = Color.decode("#09347A");
	protected static final Color PLAYER_2_COLOR = Color.decode("#F1632A");
	private static final double PIECE_STROKE_SIZE = 0.06;
	private static final Color PIECE_STROKE_COLOR = Color.WHITE;
	private static final Color SELECTED_PIECE_COLOR = Color.decode("#13A558");
	
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
	private Integer selectedPieceIndex = null;
	
	private final BoardClickListener boardClickListener;
	
	/**
	 * Initializes the game board panel
	 * @param boardClickListener - listens to clicks on the game board panel
	 */
	public NineMensMorrisBoardPanel(BoardClickListener boardClickListener) {
		setBackground(Color.lightGray);
		setPreferredSize(new Dimension(200, 200));
		addMouseListener(new ClickListener(this));
		this.boardClickListener = boardClickListener;
	}
	
	/**
	 * Draws the board grid and the pieces as they are placed/moved
	 */
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
		GRID_POINTS .forEach(p -> drawCircle(canvas2D, center, p, GRID_POINT_SIZE, scale, true));
		
		// draw player pieces
		canvas2D.setStroke(new BasicStroke((int)(PIECE_STROKE_SIZE * scale)));
		player1PieceIndices.forEach(p -> drawGamePiece(canvas2D, center, GRID_POINTS.get(p), scale, PLAYER_1_COLOR));
		player2PieceIndices.forEach(p -> drawGamePiece(canvas2D, center, GRID_POINTS.get(p), scale, PLAYER_2_COLOR));
		
		// draw valid moves
		canvas2D.setColor(VALID_MOVE_COLOR);
		validMoveIndices.forEach(m -> drawCircle(canvas2D, center, GRID_POINTS.get(m), VALID_MOVE_SIZE, scale, true));
	
		// draw selected piece
		if (selectedPieceIndex != null) {
			canvas.setColor(SELECTED_PIECE_COLOR);
			drawCircle(canvas2D, center, GRID_POINTS.get(selectedPieceIndex), GAME_PIECE_SIZE, scale, false);
		}
	}
	
	/**
	 * Draws all of player 1 pieces on game board
	 * @param pieces
	 */
	public void setPlayer1PieceIndices(Collection<Integer> pieces) {
		this.player1PieceIndices = pieces;
		repaint();
	}
	
	/**
	 * Draws all of player 2 pieces on game board
	 * @param pieces
	 */
	public void setPlayer2PieceIndices(Collection<Integer> pieces) {
		this.player2PieceIndices = pieces;
		repaint();
	}
	
	/**
	 * Draws the currently selected piece on game board
	 * @param index
	 */
	public void setSelectedPiece(Integer index) {
		this.selectedPieceIndex = index;
		repaint();
	}
	
	/**
	 * Draws green circles representing valid spaces to move to
	 * @param moves
	 */
	public void setValidMoveIndices(Collection<Integer> moves) {
		this.validMoveIndices = moves;
		repaint();
	}
	
	/**
	 * Draws a line connecting 3 points to create game board grid
	 * @param canvas - game board
	 * @param center - center point
	 * @param start - start point
	 * @param end - end point
	 * @param scale - scale of the line drawn
	 */
	private void drawLine(Graphics2D canvas, Point center, Point start, Point end, int scale) {
		canvas.drawLine(center.x + start.x * scale, center.y + start.y * scale, 
						center.x + end.x * scale, center.y + end.y * scale);
	}
	
	/**
	 * Draws a square representing points on the game board
	 * @param canvas - game board
	 * @param center - center of square
	 * @param size - size of square
	 * @param scale - scale of square
	 */
	private void drawSquare(Graphics2D canvas, Point center, int size, int scale) {
		int scaledSize = (int)(size * scale);
		int half = scaledSize / 2;
		canvas.drawRect(center.x-half, center.y-half, scaledSize, scaledSize);
	}
	
	/**
	 * Draws a circle on the game board
	 * @param canvas - game board
	 * @param center - center of circle
	 * @param location - location of circle
	 * @param size - size of circle
	 * @param scale - scale of circle
	 */
	private void drawCircle(Graphics2D canvas, Point center, Point location, double size, int scale, boolean fill) {
		int scaledSize = (int)(size * scale);
		int half = scaledSize / 2;
		int x = (center.x + (location.x * scale)) - half;
		int y = (center.y + (location.y * scale)) - half;
		
		if (fill) {
			canvas.fillOval(x, y, scaledSize, scaledSize);
		} else {
			canvas.drawOval(x, y, scaledSize, scaledSize);
		}
	}
	
	/**
	 * Draws a game piece onto the given space on the board
	 * @param canvas - game board
	 * @param center - center of space to place piece
	 * @param location - location to place piece
	 * @param scale - scale of piece
	 */
	private void drawGamePiece(Graphics2D canvas, Point center, Point location, int scale, Color color) {
		canvas.setColor(color);
		drawCircle(canvas, center, location, GAME_PIECE_SIZE, scale, true);
		canvas.setColor(PIECE_STROKE_COLOR);
		drawCircle(canvas, center, location, GAME_PIECE_SIZE, scale, false);
	}
	
	/**
	 * Listens for mouse clicks on the game board
	 *
	 */
	class ClickListener implements MouseListener {
		private NineMensMorrisBoardPanel panel;
		
		/**
		 * Sets panel to listen to mouse clicks on
		 * @param panel
		 */
		public ClickListener(NineMensMorrisBoardPanel panel) {
			this.panel = panel;
		}
		
		/**
		 * Only makes changes to game board when mouse button is released
		 */
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
		
		//Not used in this application
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	}
}
