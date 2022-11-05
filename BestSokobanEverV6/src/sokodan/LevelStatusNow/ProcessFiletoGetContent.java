package sokodan.LevelStatusNow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import sokodan.basic.Info.GameLogger;
import sokodan.basic.Location.IteratorPattern.Level;
/**This class is to process game file to get input.
 * 
 * @author Kangle Yuan
 *
 */
public class ProcessFiletoGetContent {
	/**getter of game name
	 * @param nothing
	 * @return m_mapSetName game name
	 */
	public String getM_mapSetName() {
		return m_mapSetName;
	}
	/**setter of game name
	 * @return nothing
	 * @param m_mapSetName game name
	 */
	public void setM_mapSetName(String m_mapSetName) {
		this.m_mapSetName = m_mapSetName;
	}
	/**getter for total steps in previous level
	 * @param nothing
	 * @return m_previousCount previous level's total steps
	 */
	public int getM_previousCount() {
		return m_previousCount;
	}
	/**setter for total steps in previous level
	 * @return nothing
	 * @param m_previousCount previous level's total steps
	 */
	public void setM_previousCount(int m_previousCount) {
		this.m_previousCount = m_previousCount;
	}

	/**getter of original level index for this level
	 * @param nothing
	 * @return m_leveltoPrint original level index
	 */
	public int getM_leveltoPrint() {
		return m_leveltoPrint;
	}
	/**setter of original level index for this level
	 * @return nothing
	 * @param m_leveltoPrint original level index
	 */
	public void setM_leveltoPrint(int m_leveltoPrint) {
		this.m_leveltoPrint = m_leveltoPrint;
	}

	/**getter of original level index
	 * @param nothing
	 * @return m_arr_indexStored array of original index
	 */
	public int getM_originalSteps() {
		return m_originalSteps;
	}
	/**getter of original level index
	 * @param nothing
	 * @return m_arr_indexStored array of original index
	 */
	public void setM_originalSteps(int m_originalSteps) {
		this.m_originalSteps = m_originalSteps;
	}
	/**getter of number of steps
	 * @param nothing
	 * @return m_movesCount number of movement
	 */
	public int getM_movesCount() {
		return m_movesCount + getM_originalSteps();
	}
	/**setter of number of steps
	 * @return nothing
	 * @param m_movesCount number of movement
	 */
	public void setM_movesCount(int m_movesCount) {
		this.m_movesCount = m_movesCount;
	}
	/**getter of game logger
	 * @param nothing
	 * @return m_logger game logger
	 */
	public GameLogger getM_logger() {
		return m_logger;
	}
	/**setter of game logger
	 * @return nothing
	 * @param m_logger game logger
	 */
	public void setM_logger(GameLogger m_logger) {
		this.m_logger = m_logger;
	}

	/**getter of original level index
	 * @param nothing
	 * @return m_arr_indexStored array of original index
	 */
	public ArrayList<Integer> getM_arr_indexStored() {
		return m_arr_indexStored;
	}
	/**setter of original level index
	 * @return nothing
	 * @param m_arr_indexStored array of original index
	 */
	public void setM_arr_indexStored(ArrayList<Integer> m_arr_indexStored) {
		this.m_arr_indexStored = m_arr_indexStored;
	}

	/**getter of array of all levels
	 * @param nothing
	 * @return m_levels all levels
	 */
	public List<Level> getM_levels() {
		return m_levels;
	}
	/**setter of array of all levels
	 * @return nothing
	 * @param m_levels all levels
	 */
	public void setM_levels(ArrayList<Level> m_levels) {
		this.m_levels = m_levels;
	}


	private int m_previousCount;
	private int m_leveltoPrint;
	private int m_originalSteps;
	private int m_movesCount = 0;
	private GameLogger m_logger;
	private ArrayList<Integer> m_arr_indexStored = new ArrayList<>();
	private List<Level> m_levels;
	private String m_mapSetName;
	public static final int SIZE = 5;
	
	/**
	 * This method is to get the array of all levels after processing every single
	 * line.
	 * 
	 * @param input the input from game file
	 * @return m_levels the array of all levels in this file
	 */
	public List<Level> loadGameFile(InputStream input) {
		List<Level> m_levels = new ArrayList<>(SIZE);
		int levelIndex = 0;
		// m_leveltoPrint =0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
			boolean parsedFirstLevel = false;
			List<String> rawLevel = new ArrayList<>();
			String levelName = "";

			while (true) {
				String line = reader.readLine();

				// Break the loop if EOF is reached
				if (line == null) {
					if (rawLevel.size() != 0) {
						Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
						m_levels.add(parsedLevel);
					}
					break;
				}

				if (line.contains("MapSetName")) {
					m_mapSetName = line.replace("MapSetName: ", "");
					continue;
				}
				if (line.contains("Previous Level Steps in total: ")) {
					m_previousCount = Integer.parseInt(line.replace("Previous Level Steps in total: ", ""));
					setM_previousCount(m_previousCount);
					
					continue;
				}

				if (line.contains("LevelIndex")) {
					m_leveltoPrint = Integer.parseInt(line.replace("LevelIndex: ", ""));
					m_arr_indexStored.add(m_leveltoPrint);
					setM_leveltoPrint(m_leveltoPrint);
					continue;
				}
				if (line.contains("LevelName")) {
					if (parsedFirstLevel) {
						Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
						m_levels.add(parsedLevel);
						rawLevel.clear();
					} else {
						parsedFirstLevel = true;
					}

					levelName = line.replace("LevelName: ", "");
					continue;
				}

				if (line.contains("Original Steps")) {
					m_originalSteps = Integer.parseInt(line.replace("Original Steps: ", ""));
					m_movesCount = m_originalSteps + m_movesCount;
					continue;
				}

				line = line.trim();
				line = line.toUpperCase();
				// If the line contains at least 2 WALLS, add it to the list
				if (line.matches(".*W.*W.*")) {
					rawLevel.add(line);
				}
			}

		} catch (IOException e) {
			m_logger.severe("Error trying to load the game file: " + e);
		} catch (NullPointerException e) {
			m_logger.severe("Cannot open the requested file: " + e);
		}

		return m_levels;
	}

}
