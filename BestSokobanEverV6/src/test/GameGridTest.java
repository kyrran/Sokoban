package test;

import static org.junit.Assert.*;

import org.junit.Test;

import sokodan.basic.Location.GameObject;
import sokodan.basic.Location.IteratorPattern.GameGrid;
/**This class is used as testing for gamegrid class
 * 
 * @author Kangle Yuan
 *
 */
public class GameGridTest {

	@Test
	public void testGameGrid() {
		int columns = 3;
		int rows = 2;
		GameObject[][] m_gameObjects = new GameObject[columns][rows];
		GameGrid object = new GameGrid(columns,rows);
		assertEquals(rows,object.ROWS);
		assertEquals(columns,object.COLUMNS);
		assertArrayEquals(m_gameObjects,object.getM_gameObjects());
	}
	
	@Test
	public void testVeryLargeGameGrid() {
		int columns = 30;
		int rows = 800;
		GameObject[][] m_gameObjects = new GameObject[columns][rows];
		GameGrid object = new GameGrid(columns,rows);
		assertEquals(rows,object.ROWS);
		assertEquals(columns,object.COLUMNS);
		assertArrayEquals(m_gameObjects,object.getM_gameObjects());
	}

}
