package btn.utils;

public class Vector2i {
	private int x;
	private int y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vector2i subtract(Vector2i v2) {
		return new Vector2i(x - v2.getX(), y - v2.getY());
	}

	public Vector2i add(Vector2i v2) {
		return new Vector2i(x + v2.getX(), y + v2.getY());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof Vector2i)) { 
            return false; 
        }

		Vector2i v2 = (Vector2i) o;
		return x == v2.getX() && y == v2.getY();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
