package entity.pathfinding;

public class Point {
	public int x;
	public int y;
	public float cost;
	private Point parent;

	// a point on the map (combination of x and y of a tile)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		cost = 1;
	}

	// rewritten the equals method to return true if the X and Y are the same,
	// because points are not unique
	public boolean equals(Object o) {
		if (o == null)
			return false;
		return this.x == ((Point) o).x && this.y == ((Point) o).y;
	}

	public int hashCode() {
		return x * y;
	}

	//getter
	public Point getParent() {
		return parent;
	}

	//setter
	public void setParent(int x, int y) {
		parent = new Point(x, y);
	}

	public void setParent(Point p) {
		parent = p;
	}

}
