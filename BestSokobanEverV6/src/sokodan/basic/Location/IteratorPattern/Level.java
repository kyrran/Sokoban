package sokodan.basic.Location.IteratorPattern;

import java.awt.Point;
import java.util.ArrayList;

import java.util.List;

import sokodan.LevelStatusNow.CurrentStatus;
import sokodan.basic.Location.GameObject;

/**
 * This class is used as iterator pattern adhereed in gamegrid.
 * 
 * @author Kangle Yuan -modified
 *
 */
public final class Level implements Container<GameObject> {
	/**
	 * getter for diamond numbers in total
	 * 
	 * @param nothing
	 * @return m_numberOfDiamond the number of diamond
	 */
	public int getM_numberOfDiamonds() {
		return m_numberOfDiamonds;
	}

	/**
	 * setter for diamond numbers in total
	 * 
	 * @return nothing
	 * @param m_numberOfDiamond the number of diamond
	 */
	public void setM_numberOfDiamonds(int m_numberOfDiamonds) {
		this.m_numberOfDiamonds = m_numberOfDiamonds;
	}

	/**
	 * getter for keeper's position
	 * 
	 * @param nothing
	 * @return m_keeperPosition keeper's posion
	 */
	public Point getM_keeperPosition() {
		return m_keeperPosition;
	}

	/**
	 * setter for keeper's position
	 * 
	 * @return nothing
	 * @param m_keeperPosition keeper's posion
	 */
	public void setM_keeperPosition(Point m_keeperPosition) {
		this.m_keeperPosition = m_keeperPosition;
	}

	/**
	 * getter for original status of the level you are going to reset
	 * 
	 * @param nothing
	 * @return m_level_2 original status of the level you are going to reset
	 */
	public List<String> getM_level_2() {
		return m_level_2;
	}

	/**
	 * setter for original status of the level you are going to reset
	 * 
	 * @return nothing
	 * @param m_level_2 original status of the level you are going to reset
	 */
	public void setM_level_2(List<String> m_level_2) {
		this.m_level_2 = m_level_2;
	}

	/**
	 * getter for name of game
	 * 
	 * @param nothing
	 * @return NAME name of game
	 */
	public String getNAME() {
		return NAME;
	}

	/**
	 * getter for current index
	 * 
	 * @param nothing
	 * @return INDEX current index
	 */
	public int getINDEX() {
		return INDEX;
	}

	/**
	 * getter for object grid
	 * 
	 * @param nothing
	 * @return OBJECTSGRID object grid
	 */
	public GameGrid getOBJECTSGRID() {
		return OBJECTSGRID;
	}

	/**
	 * getter for diamond grid
	 * 
	 * @param nothing
	 * @return OBJECTSGRID diamond grid
	 */
	public GameGrid getDIAMONDSGRID() {
		return DIAMONDSGRID;
	}

	private final String NAME;
	private final GameGrid OBJECTSGRID;
	private final GameGrid DIAMONDSGRID;
	private final int INDEX;
	/** violate with rule 4 */
	private int m_numberOfDiamonds = 0;
	private Point m_keeperPosition = new Point(0, 0);
	private List<String> m_level_2;

	/**
	 * This constructor is to initialise two grids
	 * 
	 * @param levelName  name of this level
	 * @param levelIndex index of this level
	 * @param raw_level  array of all levels
	 * @return nothing
	 */
	public Level(String levelName, int levelIndex, List<String> raw_level) {
		if (CurrentStatus.isDebugActive()) {
			System.out.printf("[ADDING LEVEL] LEVEL [%d]: %s\n", levelIndex, levelName);
		}

		NAME = levelName;
		INDEX = levelIndex;
		int rows = raw_level.size();
		int columns = raw_level.get(0).trim().length();

		OBJECTSGRID = new GameGrid(rows, columns);
		DIAMONDSGRID = new GameGrid(rows, columns);
		setM_numberOfDiamonds(0);
		for (int row = 0; row < raw_level.size(); row++) {

			// Loop over the string one char at a time because it should be the fastest way:
			// http://stackoverflow.com/questions/8894258/fastest-way-to-iterate-over-all-the-chars-in-a-string
			for (int col = 0; col < raw_level.get(row).length(); col++) {
				// The game object is null when the we're adding a floor or a diamond
				GameObject curTile = GameObject.fromChar(raw_level.get(row).charAt(col));

				if (curTile == GameObject.DIAMOND) {
					m_numberOfDiamonds++;
					DIAMONDSGRID.putGameObjectAt(curTile, row, col);
					// curTile = GameObject.FLOOR;
				} else if (curTile == GameObject.KEEPER) {
					m_keeperPosition = new Point(row, col);
				} else if (curTile == GameObject.CRATE_ON_DIAMOND) {
					DIAMONDSGRID.putGameObjectAt(GameObject.DIAMOND, row, col);
					m_numberOfDiamonds++;
				} else if (curTile == GameObject.KEEPER_ON_DIAMOND) {
					m_numberOfDiamonds++;
					m_keeperPosition = new Point(row, col);
					DIAMONDSGRID.putGameObjectAt(GameObject.DIAMOND, row, col);
				}
				OBJECTSGRID.putGameObjectAt(curTile, row, col);
				curTile = null;

			}
		}
		/** deep copy of raw_level */
		m_level_2 = new ArrayList<String>();
		for (String s : raw_level) {
			m_level_2.add(s);
		}

	}

	/**
	 * This method is to reset all strings into original place
	 * 
	 * @param nothing
	 * @return nothing
	 */
	public void reset() {
		for (int row = 0; row < m_level_2.size(); row++) {
			for (int col = 0; col < m_level_2.get(row).length(); col++) {
				GameObject curTile = GameObject.fromChar(m_level_2.get(row).charAt(col));

				if (curTile == GameObject.DIAMOND) {
					/** cannot increment the number of current diamonds */
					DIAMONDSGRID.putGameObjectAt(curTile, row, col);
					// curTile = GameObject.FLOOR;
				} else if (curTile == GameObject.KEEPER) {
					/** cannot create new point for keeper, just a keeper */
					m_keeperPosition.setLocation(row, col);
				} else if (curTile == GameObject.CRATE_ON_DIAMOND) {
					DIAMONDSGRID.putGameObjectAt(GameObject.DIAMOND, row, col);
					// m_numberOfDiamonds++;
				} else if (curTile == GameObject.KEEPER_ON_DIAMOND) {
					m_keeperPosition = new Point(row, col);
					DIAMONDSGRID.putGameObjectAt(GameObject.DIAMOND, row, col);
				}
				OBJECTSGRID.putGameObjectAt(curTile, row, col);
				curTile = null;
			}
		}
	}

	/**
	 * This method is to check if the current level completes
	 * 
	 * @param nothing
	 * @return boolean number that if level completes
	 */
	public boolean isComplete() {
		int cratedDiamondsCount = 0;

		for (int row = 0; row < OBJECTSGRID.ROWS; row++) {
			for (int col = 0; col < OBJECTSGRID.COLUMNS; col++) {
				if ((OBJECTSGRID.getGameObjectAt(col, row) == GameObject.CRATE
						&& DIAMONDSGRID.getGameObjectAt(col, row) == GameObject.DIAMOND)
						|| (OBJECTSGRID.getGameObjectAt(col, row) == GameObject.CRATE_ON_DIAMOND
								&& DIAMONDSGRID.getGameObjectAt(col, row) == GameObject.DIAMOND)) {
					cratedDiamondsCount++;
				}
			}
		}
		boolean complete = (cratedDiamondsCount == m_numberOfDiamonds);
		return complete;
	}

	/**
	 * what the object on this grid, in 2d array, after move
	 * 
	 * @param source where it was
	 * @param delta  movement
	 * @return gameobject what object is
	 */
	public GameObject getTargetObject(Point source, Point delta) {
		/** get the type of object after new move (source+delta) */
		return OBJECTSGRID.getTargetFromSource(source, delta);
	}

	/**
	 * get the game object from object grid at this location
	 * 
	 * @param p point of object
	 * @return gameobject what it is
	 */
	public GameObject getObjectAt(Point p) {
		return OBJECTSGRID.getGameObjectAt(p);
	}

	/**
	 * get the game object from diamond grid at this location
	 * 
	 * @param p point of object
	 * @return gameobject what it is
	 */
	GameObject getObjectAtDiamondGrid(Point p) {
		return DIAMONDSGRID.getGameObjectAt(p);
	}

	/**
	 * put this object into new location(souce with delta), put previous object in
	 * (source+delta) into source
	 * 
	 * @param object   what object is
	 * @param where    it is
	 * @param movement
	 * @return nothing
	 */
	public void moveGameObjectBy(GameObject object, Point source, Point delta) {
		moveGameObjectTo(object, source, GameGrid.translatePoint(source, delta));
	}

	/**
	 * the previous object at destination into source, and put the object into
	 * destination
	 * 
	 * @param object      what it is
	 * @param source      where it is
	 * @param destination whre it goes to
	 */
	public void moveGameObjectTo(GameObject object, Point source, Point destination) {
		/** put object at destination into source x y */
		OBJECTSGRID.putGameObjectAt(getObjectAt(destination), source);
		/** put new object into destination */
		OBJECTSGRID.putGameObjectAt(object, destination);
	}

	/**
	 * put object to somewhere
	 * 
	 * @param object      the object you want to put
	 * @param destination where you want to put
	 * @return nothing
	 */
	public void putObjtoDes(GameObject object, Point destination) {
		OBJECTSGRID.putGameObjectAt(object, destination);
	}

	/**
	 * convert object grid to string
	 * 
	 * @param nothing
	 * @return string of grid
	 */
	@Override
	public String toString() {
		return OBJECTSGRID.toString();
	}

	/**
	 * iterator of grid.
	 * 
	 * @param nothing
	 * @return iterator the iterator
	 */
	@Override
	public Iterator<GameObject> getIterator() {
		return new LevelIterator();
	}

	/**
	 * This class is fot iterator .
	 * 
	 * @author Kangle Yuan -modified
	 *
	 */
	public class LevelIterator implements Iterator<GameObject> {

		int column = 0;
		int row = 0;

		/**
		 * if had next object
		 * 
		 * @param nothing
		 * @return boolean if has next object
		 */
		@Override
		public boolean hasNext() {
			return !(row == OBJECTSGRID.ROWS - 1 && column == OBJECTSGRID.COLUMNS);
		}

		/**
		 * what next object is
		 * 
		 * @param nothing
		 * @return gameobject next object
		 */
		@Override
		public GameObject next() {
			if (column >= OBJECTSGRID.COLUMNS) {
				column = 0;
				row++;
			}

			GameObject object = OBJECTSGRID.getGameObjectAt(column, row);
			GameObject diamond = DIAMONDSGRID.getGameObjectAt(column, row);
			GameObject retObj = object;

			column++;

			if (diamond == GameObject.DIAMOND) {
				if (object == GameObject.CRATE) {
					retObj = GameObject.CRATE_ON_DIAMOND;
				} else if (object == GameObject.FLOOR) {
					retObj = diamond;
				} else if (object == GameObject.KEEPER) {
					retObj = GameObject.KEEPER_ON_DIAMOND;
				} else {
					retObj = object;
				}
			}

			return retObj;
		}

		public Point getCurrentPosition() {
			return new Point(column, row);
		}
	}
	/**
	 * source code is:
	 * 
	 * package sample;
	 * 
	 * import java.awt.*; import java.util.Iterator; import java.util.List;
	 * 
	 * import static sample.GameGrid.translatePoint;
	 * 
	 * 
	 * public final class Level implements Iterable<GameObject> {
	 * 
	 * private final String name; private final GameGrid objectsGrid; private final
	 * GameGrid diamondsGrid; private final int index; private int numberOfDiamonds
	 * = 0; private Point keeperPosition = new Point(0, 0);
	 * 
	 * public Level(String levelName, int levelIndex, List<String> raw_level) { if
	 * (StartMeUp.isDebugActive()) { System.out.printf("[ADDING LEVEL] LEVEL [%d]:
	 * %s\n", levelIndex, levelName); }
	 * 
	 * name = levelName; index = levelIndex;
	 * 
	 * int rows = raw_level.size(); int columns = raw_level.get(0).trim().length();
	 * 
	 * objectsGrid = new GameGrid(rows, columns); diamondsGrid = new GameGrid(rows,
	 * columns);
	 * 
	 * for (int row = 0; row < raw_level.size(); row++) {
	 * 
	 * // Loop over the string one char at a time because it should be the fastest
	 * way: //
	 * http://stackoverflow.com/questions/8894258/fastest-way-to-iterate-over-all-the-chars-in-a-string
	 * for (int col = 0; col < raw_level.get(row).length(); col++) { // The game
	 * object is null when the we're adding a floor or a diamond GameObject curTile
	 * = GameObject.fromChar(raw_level.get(row).charAt(col));
	 * 
	 * if (curTile == GameObject.DIAMOND) { numberOfDiamonds++;
	 * diamondsGrid.putGameObjectAt(curTile, row, col); curTile = GameObject.FLOOR;
	 * } else if (curTile == GameObject.KEEPER) { keeperPosition = new Point(row,
	 * col); }
	 * 
	 * objectsGrid.putGameObjectAt(curTile, row, col); curTile = null; } } }
	 * 
	 * boolean isComplete() { int cratedDiamondsCount = 0; for (int row = 0; row <
	 * objectsGrid.ROWS; row++) { for (int col = 0; col < objectsGrid.COLUMNS;
	 * col++) { if (objectsGrid.getGameObjectAt(col, row) == GameObject.CRATE &&
	 * diamondsGrid.getGameObjectAt(col, row) == GameObject.DIAMOND) {
	 * cratedDiamondsCount++; } } }
	 * 
	 * return cratedDiamondsCount >= numberOfDiamonds; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * int getIndex() { return index; }
	 * 
	 * Point getKeeperPosition() { return keeperPosition; }
	 * 
	 * GameObject getTargetObject(Point source, Point delta) { return
	 * objectsGrid.getTargetFromSource(source, delta); }
	 * 
	 * GameObject getObjectAt(Point p) { return objectsGrid.getGameObjectAt(p); }
	 * 
	 * void moveGameObjectBy(GameObject object, Point source, Point delta) {
	 * moveGameObjectTo(object, source, translatePoint(source, delta)); }
	 * 
	 * public void moveGameObjectTo(GameObject object, Point source, Point
	 * destination) { objectsGrid.putGameObjectAt(getObjectAt(destination), source);
	 * objectsGrid.putGameObjectAt(object, destination); }
	 * 
	 * @Override public String toString() { return objectsGrid.toString(); }
	 * 
	 * @Override public Iterator<GameObject> iterator() { return new
	 *           LevelIterator(); }
	 * 
	 *           public class LevelIterator implements Iterator<GameObject> {
	 * 
	 *           int column = 0; int row = 0;
	 * 
	 * @Override public boolean hasNext() { return !(row == objectsGrid.ROWS - 1 &&
	 *           column == objectsGrid.COLUMNS); }
	 * 
	 * @Override public GameObject next() { if (column >= objectsGrid.COLUMNS) {
	 *           column = 0; row++; }
	 * 
	 *           GameObject object = objectsGrid.getGameObjectAt(column, row);
	 *           GameObject diamond = diamondsGrid.getGameObjectAt(column, row);
	 *           GameObject retObj = object;
	 * 
	 *           column++;
	 * 
	 *           if (diamond == GameObject.DIAMOND) { if (object ==
	 *           GameObject.CRATE) { retObj = GameObject.CRATE_ON_DIAMOND; } else if
	 *           (object == GameObject.FLOOR) { retObj = diamond; } else { retObj =
	 *           object; } }
	 * 
	 *           return retObj; }
	 * 
	 *           public Point getCurrentPosition() { return new Point(column, row);
	 *           } } }
	 */
}