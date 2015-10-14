package ui;

public class Pair<T> {
	private T first, second;
	
	public Pair() {
		this(null, null);
	}
	
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	public T first() {
		return this.first;
	}
	
	public T second() {
		return this.second;
	}
}
