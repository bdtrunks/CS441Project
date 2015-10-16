package ui;

/**
 * Pair is used to create the drawing of the game board
 *
 * @param <T> Point
 */
public class Pair<T> {
	private T first, second;
	
	/**
	 * Initialize a pair of null Points
	 */
	public Pair() {
		this(null, null);
	}
	
	/**
	 * Initialize a pair of connected Points
	 * @param first - first Point
	 * @param second - second Point
	 */
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * Return the first Point
	 * @return first Point
	 */
	public T first() {
		return this.first;
	}
	
	/**
	 * Return the second Point
	 * @return second Point
	 */
	public T second() {
		return this.second;
	}
}
