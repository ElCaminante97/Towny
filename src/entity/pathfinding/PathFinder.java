package entity.pathfinding;

import java.util.ArrayList;
import map.Level;

//pathfinder I found on the internet
public class PathFinder implements AStarHeuristic {
	private final int MAXDIST = 500;
	private ArrayList<Point> closed = new ArrayList<Point>();
	private ArrayList<Point> open = new ArrayList<Point>();
	private Level level;
	private Point[][] nodes;

	public PathFinder(Level level) {
		this.level = level;
		nodes = new Point[level.width][level.height];
		for (int x = 0; x < level.width; x++) {
			for (int y = 0; y < level.height; y++) {
				nodes[x][y] = new Point(x, y);
			}
		}
	}

	public static Path getShortest(Path[] paths) {
		if (paths != null) {
			Path shortest = null;
			for (int i = 0; i < paths.length; i++) {
				if (paths[i] != null) {
					shortest = paths[i];
					break;
				}
			}
			for (Path p : paths) {
				if (p != null && shortest != null)
					if (p.getLength() < shortest.getLength()) {
						shortest = p;
					}

			}
			return shortest;
		}
		return null;
	}

	public Path findPath(int sx, int sy, int tx, int ty) {
		if (sx < 0 || sx > level.width || sy < 0 || sy > level.height || tx < 0 || tx > level.width || ty < 0
				|| ty > level.height) {
			return null;
		}
		if (!level.isWalkAbleTile(tx, ty)){
			return null;

		}
		nodes[tx][ty].setParent(null);
		closed.clear();
		open.clear();
		open.add(nodes[sx][sy]);
		nodes[tx][ty].setParent(null);
		int maxDepth = 0;
		while ((open.size() != 0) && (maxDepth < MAXDIST)) {
			Point current = open.get(0);
			if (current.equals(nodes[tx][ty])) {
				break;
			}
			open.remove(current);
			closed.add(current);
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					if ((x == 0) && (y == 0)) {
						continue;
					}
					if ((x != 0 && y != 0)) { // DIAGONAL
						continue;
					}
					int xp = x + current.x;
					int yp = y + current.y;

					if (isValidLocation(xp, yp)) {
						float nextStepCost = getCost(current.x, current.y, xp, yp);
						Point neighbour = nodes[xp][yp];
						if (nextStepCost < neighbour.cost) {
							if (open.contains(neighbour)) {
								open.remove(neighbour);
							}
							if (closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}
						if (!open.contains(neighbour) && !closed.contains(neighbour)) {
							neighbour.cost = nextStepCost;
							open.add(neighbour);
							neighbour.setParent(current);
						}
					}
				}
			}

		}
		if (nodes[tx][ty].getParent() == null) {
			return null;
		}
		Path path = new Path();
		path.setDest(tx, ty);
		Point target = nodes[tx][ty];
		while (!target.equals(nodes[sx][sy])) {
			path.prependStep(target.x, target.y);
			target = target.getParent();

		}
		path.prependStep(sx, sy);
		return path;

	}

	private boolean isValidLocation(int xp, int yp) {
		if (xp <= 0 || xp >= level.width || yp <= 0 || yp >= level.height) {
			return false;
		}
		return level.isWalkAbleTile(xp, yp);
	}

	public float getCost(int x, int y, int tx, int ty) {
		float dx = tx - x;
		float dy = ty - y;
		float result = (float) (Math.sqrt((dx * dx) + (dy * dy)));
		return result;
	}

}
