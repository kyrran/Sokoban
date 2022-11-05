package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sokodan.basic.Location.IteratorPattern.Level;
/**This class is used as testing for level class
 * 
 * @author Kangle Yuan
 *
 */

public class LevelTest {
	
	@Test
	public void testLevel() {
		String levelName = "this is level name";
		int levelIndex = 3;
		List<String> raw_level = new ArrayList<>();
		raw_level.add("1");
		raw_level.add("$");
		raw_level.add(" ");
		raw_level.add("#");
		
		int rows = raw_level.size();
		int columns = raw_level.get(0).trim().length();
		
		Level object = new Level(levelName, levelIndex, raw_level);
		
		assertEquals(levelName, object.getNAME());
		assertEquals(levelIndex, object.getINDEX());
		assertEquals(4, rows);
		assertEquals(1, columns);
	}

}
