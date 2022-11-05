package sokodan.basic.Location.IteratorPattern;

import java.awt.Dimension;
import java.awt.Point;

import sokodan.LevelStatusNow.CurrentStatus;
import sokodan.basic.Location.GameObject;

/**
 * This class is to deal with GameGrid.
 * 
 * @author Kangle Yuan -modified
 */

public class GameGrid implements Container<GameObject> {
	/**getter of gameobject 2d array.
	 * @param nothing
	 * @return m_gameObject 2d array  
	 */
	public GameObject[][] getM_gameObjects() {
		return m_gameObjects;
	}
	/**setter of gameobject 2d array.
	 * @return nothing
	 * @param m_gameObject 2d array  
	 */
	public void setM_gameObjects(GameObject[][] m_gameObjects) {
		this.m_gameObjects = m_gameObjects;
	}
	
	public final int COLUMNS;
	public final int ROWS;
	/** violate with rule 4 */
	private GameObject[][] m_gameObjects;


	/**
	 * This constructor is to initialise columns and rows.
	 * 
	 * @param columns number of columns of gamegrid.
	 * @param rows    number of rows of gamegrid.
	 */
	public GameGrid(int columns, int rows) {
		COLUMNS = columns;
		ROWS = rows;
		m_gameObjects = new GameObject[COLUMNS][ROWS];
	}

	/**
	 * iterator of grid.
	 * 
	 * @param nothing
	 * @return iterator the iterator
	 */
	@Override
	public Iterator<GameObject> getIterator() {
		return new GridIterator();
	}

	/**
	 * This class is fot iterator .
	 * 
	 * @author Kangle Yuan -modified
	 *
	 */
	public class GridIterator implements Iterator<GameObject> {
		int row = 0;
		int column = 0;

		/**
		 * if had next object
		 * 
		 * @param nothing
		 * @return boolean if has next object
		 */
		@Override
		public boolean hasNext() {
			return !(row == ROWS && column == COLUMNS);
		}

		/**
		 * what next object is
		 * 
		 * @param nothing
		 * @return gameobject next object
		 */
		@Override
		public GameObject next() {
			if (column >= COLUMNS) {
				column = 0;
				row++;
			}
			return getGameObjectAt(column++, row);
		}
	}

	/**
	 * This method is to translate point is after move, the new point.
	 * 
	 * @param sourceLocation where it was
	 * @param delta          movement
	 * @return Point where it is
	 */
	public static Point translatePoint(Point sourceLocation, Point delta) {
		Point translatedPoint = new Point(sourceLocation);
		translatedPoint.translate((int) delta.getX(), (int) delta.getY());
		return translatedPoint;
	}

	/**
	 * This method is to get dimension of grid.
	 * 
	 * @param nothing
	 * @return dimension the dimension
	 */
	public Dimension getDimension() {
		return new Dimension(COLUMNS, ROWS);
	}

	/**
	 * This method is to get the type of object after new move (source+delta)
	 * 
	 * @param source where it was
	 * @param delta  movement
	 * @return
	 */
	public GameObject getTargetFromSource(Point source, Point delta) {
		return getGameObjectAt(translatePoint(source, delta));
	}

	/**
	 * This method is to get 2d gameobject in a 2d array
	 * 
	 * @param col which col it's at
	 * @param row which row its at
	 * @return nothing
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public GameObject getGameObjectAt(int col, int row) throws ArrayIndexOutOfBoundsException {
		if (isPointOutOfBounds(col, row)) {
			if (CurrentStatus.isDebugActive()) {
				System.out.printf("Trying to get null GameObject from COL: %d  ROW: %d", col, row);
			}
			throw new ArrayIndexOutOfBoundsException("The point [" + col + ":" + row + "] is outside the map.");
		}

		return m_gameObjects[col][row];
	}

	/**
	 * This method is to get gameobject from which point.
	 * 
	 * @param p point of where object at
	 * @return nothing
	 */
	public GameObject getGameObjectAt(Point p) {
		if (p == null) {
			throw new IllegalArgumentException("Point cannot be null.");
		}

		return getGameObjectAt((int) p.getX(), (int) p.getY());
	}

	/**
	 * This method is to remove object from which point,
	 * 
	 * @param position point of where object at
	 * @return boolean is the object on this location is equal.
	 */
	public boolean removeGameObjectAt(Point position) {
		return putGameObjectAt(null, position);
	}

	/**
	 * This method is to put gameobject in x y
	 * 
	 * @param gameObject object to put
	 * @param x          col
	 * @param y          row
	 * @return boolean if they are equal
	 */
	public boolean putGameObjectAt(GameObject gameObject, int x, int y) {
		if (isPointOutOfBounds(x, y)) {
			return false;
		}

		m_gameObjects[x][y] = gameObject;
		return m_gameObjects[x][y] == gameObject;
	}

	/**
	 * This method is to put object at point.
	 * 
	 * @param gameObject
	 * @param p
	 * @return boolean to check if successed
	 */
	public boolean putGameObjectAt(GameObject gameObject, Point p) {
		return p != null && putGameObjectAt(gameObject, (int) p.getX(), (int) p.getY());
	}

	/**
	 * This method is to check is this point is out of bound.
	 * 
	 * @param x col
	 * @param y row
	 * @return boolean is it is out of bound
	 */
	private boolean isPointOutOfBounds(int x, int y) {
		return (x < 0 || y < 0 || x >= COLUMNS || y >= ROWS);
	}

	/**
	 * Convert grid to string.
	 * 
	 * @param nothing
	 * @return string string of grid
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(m_gameObjects.length);

		for (GameObject[] gameObject : m_gameObjects) {
			for (GameObject aGameObject : gameObject) {
				if (aGameObject == null) {
					aGameObject = GameObject.DEBUG_OBJECT;
				}
				sb.append(aGameObject.getCharSymbol());
			}

			sb.append('\n');
		}

		return sb.toString();
	}
	/**
	 * 
	 * source code is :
	 * 
	 * package sample;
	 * 
	 * import java.awt.*; import java.util.Iterator;
	 * 
	 * public class GameGrid implements Iterable {
	 * 
	 * final int COLUMNS; final int ROWS; private GameObject[][] gameObjects;
	 * 
	 * public GameGrid(int columns, int rows) { COLUMNS = columns; ROWS = rows;
	 * 
	 * gameObjects = new GameObject[COLUMNS][ROWS]; }
	 * 
	 * static Point translatePoint(Point sourceLocation, Point delta) { Point
	 * translatedPoint = new Point(sourceLocation); translatedPoint.translate((int)
	 * delta.getX(), (int) delta.getY()); return translatedPoint; }
	 * 
	 * public Dimension getDimension() { return new Dimension(COLUMNS, ROWS); }
	 * 
	 * GameObject getTargetFromSource(Point source, Point delta) { return
	 * getGameObjectAt(translatePoint(source, delta)); }
	 * 
	 * public GameObject getGameObjectAt(int col, int row) throws
	 * ArrayIndexOutOfBoundsException { if (isPointOutOfBounds(col, row)) { if
	 * (StartMeUp.isDebugActive()) { System.out.printf("Trying to get null
	 * GameObject from COL: %d ROW: %d", col, row); } throw new
	 * ArrayIndexOutOfBoundsException("The point [" + col + ":" + row + "] is
	 * outside the map."); }
	 * 
	 * return gameObjects[col][row]; }
	 * 
	 * public GameObject getGameObjectAt(Point p) { if (p == null) { throw new
	 * IllegalArgumentException("Point cannot be null."); }
	 * 
	 * return getGameObjectAt((int) p.getX(), (int) p.getY()); }
	 * 
	 * public boolean removeGameObjectAt(Point position) { return
	 * putGameObjectAt(null, position); }
	 * 
	 * 
	 * public boolean putGameObjectAt(GameObject gameObject, int x, int y) { if
	 * (isPointOutOfBounds(x, y)) { return false; }
	 * 
	 * gameObjects[x][y] = gameObject; return gameObjects[x][y] == gameObject; }
	 * 
	 * public boolean putGameObjectAt(GameObject gameObject, Point p) { return p !=
	 * null && putGameObjectAt(gameObject, (int) p.getX(), (int) p.getY()); }
	 * 
	 * private boolean isPointOutOfBounds(int x, int y) { return (x < 0 || y < 0 ||
	 * x >= COLUMNS || y >= ROWS); }
	 * 
	 * private boolean isPointOutOfBounds(Point p) { return isPointOutOfBounds(p.x,
	 * p.y); }
	 * 
	 * @Override public String toString() { StringBuilder sb = new
	 *           StringBuilder(gameObjects.length);
	 * 
	 *           for (GameObject[] gameObject : gameObjects) { for (GameObject
	 *           aGameObject : gameObject) { if (aGameObject == null) { aGameObject
	 *           = GameObject.DEBUG_OBJECT; }
	 *           sb.append(aGameObject.getCharSymbol()); }
	 * 
	 *           sb.append('\n'); }
	 * 
	 *           return sb.toString(); }
	 * 
	 * @Override public Iterator<GameObject> iterator() { return new GridIterator();
	 *           }
	 * 
	 *           public class GridIterator implements Iterator<GameObject> { int row
	 *           = 0; int column = 0;
	 * 
	 * @Override public boolean hasNext() { return !(row == ROWS && column ==
	 *           COLUMNS); }
	 * 
	 * @Override public GameObject next() { if (column >= COLUMNS) { column = 0;
	 *           row++; } return getGameObjectAt(column++, row); } } }
	 */

}