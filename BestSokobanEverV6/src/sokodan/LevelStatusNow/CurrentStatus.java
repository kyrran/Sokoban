package sokodan.LevelStatusNow;

import java.awt.Point;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

import sokodan.MVCHighScore.MVCpopup;
import sokodan.basic.Info.GameLogger;
import sokodan.basic.Location.GameObject;
import sokodan.basic.Location.IteratorPattern.GameGrid;
import sokodan.basic.Location.IteratorPattern.Level;

/**
 * This class is to track the current status after every single movement.
 * 
 * @author Kangle Yuan -modified
 */
public class CurrentStatus {
	/**
	 * getter of obj for process input file
	 * 
	 * @param nothing
	 * @return m_obj obj for processFiletoGetContent
	 */
	public ProcessFiletoGetContent getObj() {
		return m_obj;
	}

	/**
	 * setter of obj for process input file
	 * 
	 * @return nothing
	 * @param m_obj obj for processFiletoGetContent
	 */
	public void setObj(ProcessFiletoGetContent obj) {
		this.m_obj = obj;
	}

	/**
	 * getter of name of game
	 * 
	 * @param nothing
	 * @return GAME_NAME name of game
	 */
	public static String getGameName() {
		return GAME_NAME;
	}

	/**
	 * getter of original level index
	 * 
	 * @param nothing
	 * @return m_arr_indexStored array of original index
	 */
	public static boolean isDebugActive() {
		return m_debug;
	}

	/**
	 * getter of game logger
	 * 
	 * @param nothing
	 * @return m_logger game logger
	 */
	public static GameLogger getM_logger() {
		return m_logger;
	}

	/**
	 * setter of game logger
	 * 
	 * @return nothing
	 * @param m_logger game logger
	 */
	public static void setM_logger(GameLogger m_logger) {
		CurrentStatus.m_logger = m_logger;
	}

	/**
	 * getter of object for debug
	 * 
	 * @param nothing
	 * @return m_debug debug message
	 */
	public static boolean isM_debug() {
		return m_debug;
	}

	/**
	 * setter of object for debug
	 * 
	 * @return nothing
	 * @param m_debug debug message
	 */
	public static void setM_debug(boolean m_debug) {
		CurrentStatus.m_debug = m_debug;
	}

	/**
	 * getter of current level
	 * 
	 * @param nothing
	 * @return m_currentLevel current level
	 */
	public Level getM_currentLevel() {
		return m_currentLevel;
	}

	/**
	 * setter of current level
	 * 
	 * @return nothing
	 * @param m_currentLevel current level
	 */
	public void setM_currentLevel(Level m_currentLevel) {
		this.m_currentLevel = m_currentLevel;
	}

	/**
	 * getter of array of all levels
	 * 
	 * @param nothing
	 * @return m_levels all levels
	 */
	public List<Level> getM_levels() {
		return m_levels;
	}

	/**
	 * setter of array of all levels
	 * 
	 * @return nothing
	 * @param m_levels all levels
	 */
	public void setM_levels(List<Level> m_levels) {
		this.m_levels = m_levels;
	}

	/**
	 * getter of game complete status
	 * 
	 * @param nothing
	 * @return m_gameComplete if game complete
	 */
	public boolean isM_gameComplete() {
		return m_gameComplete;
	}

	/**
	 * setter of game complete status
	 * 
	 * @return nothing
	 * @param m_gameComplete if game complete
	 */
	public void setM_gameComplete(boolean m_gameComplete) {
		this.m_gameComplete = m_gameComplete;
	}

	/**
	 * getter of number of steps
	 * 
	 * @param nothing
	 * @return m_movesCount number of movement
	 */
	public int getM_movesCount() {
		return m_movesCount;
	}

	/**
	 * setter of number of steps
	 * 
	 * @return nothing
	 * @param m_movesCount number of movement
	 */
	public void setM_movesCount(int m_movesCount) {
		this.m_movesCount = m_movesCount;
	}
	
	/**
	 * getter for number of accumulated steps when you finish all levels.
	 * 
	 * @param nothing
	 * @return m_steptotal total steps
	 */
	public int getStepsTotal() {
		return m_stepsTotal;
	}

	/**
	 * setter for every object
	 * 
	 * @param steptotal number of accumulated steps
	 * @return nothing
	 */
	public void setStepsTotal(int stepsTotal) {
		this.m_stepsTotal = stepsTotal;
	}

	private ProcessFiletoGetContent m_obj = new ProcessFiletoGetContent();
	public static final String GAME_NAME = "BestSokobanEverV6";
	public static GameLogger m_logger;
	private static boolean m_debug = true;
	private Level m_currentLevel;
	private List<Level> m_levels;
	private boolean m_gameComplete = false;
	private int m_movesCount;
	private int m_stepsTotal = 0;

	/**
	 * This is a construction to edit original input and process it to be modified
	 * array, and also to get currentlevel to track the current status.
	 * 
	 * @param input input from game file
	 * @return nothing
	 */

	public CurrentStatus(InputStream input) {
		try {

			m_logger = new GameLogger();
			// m_levels = loadGameFile(input);
			m_levels = m_obj.loadGameFile(input);
			m_currentLevel = getNextLevel();
			m_movesCount = m_obj.getM_movesCount();
			m_stepsTotal = m_obj.getM_previousCount();
		} catch (IOException x) {
			System.out.println("Cannot create m_logger.");
		} catch (NoSuchElementException e) {
			m_logger.warning("Cannot load the default save file: " + e.getStackTrace());
		}
	}

	/**
	 * This method is to track every single move and once current level finishes
	 * generate a dialogue and proceed to next level
	 * 
	 * @param delta
	 * @return nothing
	 */

	public void move(Point delta) {
		if (isM_gameComplete()) {
			return;
		}
		// push bo to left

		// where is keeper
		Point keeperPosition = m_currentLevel.getM_keeperPosition();
		GameObject keeper = m_currentLevel.getObjectAt(keeperPosition);

		// object on the keeper's left
		Point targetObjectPoint = GameGrid.translatePoint(keeperPosition, delta);
		GameObject keeperTarget = m_currentLevel.getObjectAt(targetObjectPoint);

		// object on diamond grid under keeper's position
		GameGrid diamondgrid = m_currentLevel.getDIAMONDSGRID();
		GameObject isdiamond = diamondgrid.getGameObjectAt(keeperPosition);

		if (CurrentStatus.isDebugActive()) {

			System.out.println("Current level state:");
			System.out.println(m_currentLevel.toString());
			/** where the keeper was before you move to the new location */
			System.out.println("Keeper pos: " + keeperPosition);
			System.out.println("Movement source obj: " + keeper);
			/** what the object was on the location you goes to now */
			System.out.printf("Target object: %s at [%s]", keeperTarget, targetObjectPoint);
		}

		boolean keeperMoved = false;
		/** what the object was on the location you goes to now */
		switch (keeperTarget) {

		case WALL:
			break;

		case CRATE:
			// System.out.println(m_currentLevel.getDIAMONDSGRID());
			// object on crate's left
			GameObject crateTarget = m_currentLevel.getTargetObject(targetObjectPoint, delta);

			if (crateTarget == GameObject.WALL || crateTarget == GameObject.CRATE_ON_DIAMOND
					|| crateTarget == GameObject.CRATE) {
				break;
			} else if (crateTarget == GameObject.FLOOR) {
				if (isdiamond == GameObject.DIAMOND || isdiamond == GameObject.CRATE_ON_DIAMOND) {
					// keeper move forward
					// crate move forward
					m_currentLevel.moveGameObjectBy(keeperTarget, targetObjectPoint, delta);
					m_currentLevel.moveGameObjectBy(GameObject.KEEPER, keeperPosition, delta);
					// object on where keeper was is diamond
					m_currentLevel.putObjtoDes(GameObject.DIAMOND, keeperPosition);
					keeperMoved = true;
					break;
				}
				// move crate to left
				m_currentLevel.moveGameObjectBy(keeperTarget, targetObjectPoint, delta);
				m_currentLevel.moveGameObjectBy(GameObject.KEEPER, keeperPosition, delta);
				keeperMoved = true;
				break;
			} else if (crateTarget == GameObject.DIAMOND) {
				// for loading saved file, at this time, D shows up
				// exchange C and D
				m_currentLevel.moveGameObjectBy(keeperTarget, targetObjectPoint, delta);
				// let d at the new positon to be floor
				m_currentLevel.putObjtoDes(GameObject.FLOOR, targetObjectPoint);
				// echange floor and keeper
				m_currentLevel.moveGameObjectBy(GameObject.KEEPER, keeperPosition, delta);
				keeperMoved = true;
				break;
			}

		case FLOOR:
			// Point destination = GameGrid.translatePoint(targetObjectPoint, delta);
			// if the position keeper at is a diamond in diamond grid
			if (isdiamond == GameObject.DIAMOND) {
				// to move forward is keeper
				m_currentLevel.moveGameObjectBy(GameObject.KEEPER, keeperPosition, delta);
				// cencel current keeper
				m_currentLevel.putObjtoDes(GameObject.DIAMOND, keeperPosition);
				// set where keeper was into diamond
				keeperMoved = true;
				break;

			}
			if (keeper == GameObject.KEEPER_ON_DIAMOND) {
				m_currentLevel.moveGameObjectBy(GameObject.KEEPER, keeperPosition, delta);
				keeperMoved = true;
				m_currentLevel.putObjtoDes(GameObject.DIAMOND, keeperPosition);
				m_currentLevel.getDIAMONDSGRID().putGameObjectAt(GameObject.DIAMOND, keeperPosition);
				break;
			}
			m_currentLevel.moveGameObjectBy(keeper, keeperPosition, delta);
			keeperMoved = true;
			break;
		case CRATE_ON_DIAMOND:
			// whats on goal's left
			GameObject goalTarget = m_currentLevel.getTargetObject(targetObjectPoint, delta);
			Point newP = GameGrid.translatePoint(targetObjectPoint, delta);
			if (goalTarget != GameObject.FLOOR) {
				break;
			}
			// OS CP
			// put keeper to this goal position
			m_currentLevel.putObjtoDes(GameObject.KEEPER_ON_DIAMOND, targetObjectPoint);
			// cancel prevous keeper
			m_currentLevel.putObjtoDes(GameObject.FLOOR, keeperPosition);
			// put crate to newP
			m_currentLevel.putObjtoDes(GameObject.CRATE, newP);

			keeperMoved = true;// put keeper on this left position
			break;
		case DIAMOND:

			Point newKeeperPosition = GameGrid.translatePoint(keeperPosition, delta);
			// move keeper to this diamond
			m_currentLevel.putObjtoDes(GameObject.KEEPER_ON_DIAMOND, newKeeperPosition);
			// cancel the current keeper
			m_currentLevel.putObjtoDes(GameObject.FLOOR, keeperPosition);
			keeperMoved = true;
			break;

		default:
			m_logger.severe("The object to be moved was not a recognised GameObject.");
			throw new AssertionError("This should not have happened. Report this problem to the developer.");
		}

		if (keeperMoved) {
			keeperPosition.translate((int) delta.getX(), (int) delta.getY());
			// System.out.println(m_movesCount);
			m_movesCount++;
			if (m_currentLevel.isComplete()) {
				generatePopUp(m_currentLevel);
				m_currentLevel = getNextLevel();
			}
		}
	}
	

	/**
	 * This method is to change current level status after movement.
	 * 
	 * @return nothing
	 * @param delta  where it should go
	 * @param delta2 on opposite position
	 */
	public void undomove(Point delta, Point delta2) {
		if (m_gameComplete) {
			return;
		}

		// keeper goes to left, we want to drag it to right
		Point keeperPosition = m_currentLevel.getM_keeperPosition();
		GameObject keeper = m_currentLevel.getObjectAt(keeperPosition);
		// whats on keeper's right
		Point backObjectPoint = GameGrid.translatePoint(keeperPosition, delta);
		GameObject keeperTarget = m_currentLevel.getObjectAt(backObjectPoint);

		// what on keeper's left
		Point lastObjectPoint = GameGrid.translatePoint(keeperPosition, delta2);
		GameObject lastimpact = m_currentLevel.getObjectAt(lastObjectPoint);

		if (CurrentStatus.isDebugActive()) {
			System.out.println("Current level state:");
			System.out.println(m_currentLevel.toString());
			/** where the keeper was before you move to the new location */
			System.out.println("Keeper pos: " + keeperPosition);
			System.out.println("Movement source obj: " + keeper);
			/** what the object was on the location you goes to now */
			System.out.printf("Target object: %s at [%s]", keeperTarget, backObjectPoint);
		}

		boolean keeperMoved = false;
		// whats on right
		switch (keeperTarget) {
		case WALL:
			break;

		default:
			// whats on the left
			switch (lastimpact) {

			case WALL:
				m_currentLevel.moveGameObjectBy(keeper, keeperPosition, delta);
				keeperMoved = true;
				break;

			case FLOOR:
				if (keeperTarget == GameObject.DIAMOND) {
					// CDS -> CP s moved to right want to go left, last impact is right delta2
					m_currentLevel.putObjtoDes(GameObject.FLOOR, keeperPosition);
					m_currentLevel.putObjtoDes(GameObject.KEEPER_ON_DIAMOND, backObjectPoint);
					keeperMoved = true;
					break;
				}
				m_currentLevel.moveGameObjectBy(GameObject.KEEPER, keeperPosition, delta);
				keeperMoved = true;
				break;

			case CRATE:
				// CP -> OS
				// move keeper to right
				m_currentLevel.moveGameObjectBy(GameObject.KEEPER, keeperPosition, delta);
				keeperMoved = true;
				// move crate to right
				m_currentLevel.moveGameObjectBy(lastimpact, lastObjectPoint, delta);
				break;

			case CRATE_ON_DIAMOND:
				// put diamond on this left position
				m_currentLevel.putObjtoDes(GameObject.DIAMOND, lastObjectPoint);
				// move crate to right
				m_currentLevel.moveGameObjectBy(lastimpact, lastObjectPoint, delta);
				// move keeper to right
				m_currentLevel.moveGameObjectBy(GameObject.KEEPER, keeperPosition, delta);
				keeperMoved = true;
				break;

			default:
				m_logger.severe("The object to be moved was not a recognised GameObject.");
				throw new AssertionError("This should not have happened. Report this problem to the developer.");
			}
		}
		if (keeperMoved) {
			keeperPosition.translate((int) delta.getX(), (int) delta.getY());
			m_movesCount--;
		}
	}

	/**
	 * This method is to generate high-score pop-up
	 * 
	 * @return nothing
	 * @param m_currentLevel current level you are at
	 */
	public void generatePopUp(Level m_currentLevel) {
		try {
			int i = m_currentLevel.getINDEX();

			if (m_obj.getM_arr_indexStored().size() != 0 && i >= 1) {
				int p = m_obj.getM_arr_indexStored().get(i - 1);

				if (p > i) {
					i = p;
				}
			}
			m_stepsTotal += m_movesCount;
			new MVCpopup().writetoFile(i, getM_movesCount(), m_logger.getdate());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (isDebugActive()) {
			System.out.println("Level complete!");
		}
	}

	/**
	 * This method is to get the layout of next level
	 * 
	 * @param nothing
	 * @return nothing
	 */
	public Level getNextLevel() {
		if (m_currentLevel == null) {
			return m_levels.get(0);
		}

		int m_currentLevelIndex = m_currentLevel.getINDEX();
		if (m_currentLevelIndex < m_levels.size()) {
			/** shouldn't increment 1 */
			setM_movesCount(0);
			return m_levels.get(m_currentLevelIndex);
		}

		m_gameComplete = true;
		return null;
	}

	/**
	 * This method is to get the original state of current level
	 * 
	 * @param index which level are you in
	 * @return level the original state of current level
	 */
	public Level getOneLevel(int index) {
		if (m_currentLevel == null) {
			return m_levels.get(0);
		}
		m_movesCount = 0;
		m_levels.get(index).reset();
		return m_levels.get(index);
	}

	/**
	 * This method is to get specific level under specific index in array.
	 * 
	 * @param index the index in array
	 * @return level the level you need
	 */
	public Level getSpecificLevel(int index) {
		if (m_currentLevel == null) {
			return m_levels.get(0);
		}
		return m_levels.get(index);
	}

	/**
	 * This method is to get the name of specific level.
	 * 
	 * @param index the index in array
	 * @return string name of this level
	 */
	public String getLevelName(int index) {
		return m_levels.get(index).getNAME();
	}

	/**
	 * this method is to get the opposite debug status
	 * 
	 * @param nothing
	 * @return boolean opposite debug status
	 */
	public void toggleDebug() {
		m_debug = !m_debug;
	}
	/**
	 * source code is: package sample;
	 * 
	 * import javafx.scene.input.KeyCode; import javafx.scene.media.MediaPlayer;
	 * 
	 * import javax.sound.sampled.LineUnavailableException; import java.awt.*;
	 * import java.io.*; import java.util.ArrayList; import java.util.List; import
	 * java.util.NoSuchElementException;
	 * 
	 * public class StartMeUp {
	 * 
	 * public static final String GAME_NAME = "BestSokobanEverV6"; public static
	 * GameLogger logger; private static boolean debug = false; private Level
	 * currentLevel; private String mapSetName; private List<Level> levels; private
	 * boolean gameComplete = false; private int movesCount = 0; private MediaPlayer
	 * player;
	 * 
	 * public StartMeUp(InputStream input, boolean production) { try { logger = new
	 * GameLogger(); levels = loadGameFile(input); currentLevel = getNextLevel();
	 * 
	 * if (production) { createPlayer(); } } catch (IOException x) {
	 * System.out.println("Cannot create logger."); } catch (NoSuchElementException
	 * e) { logger.warning("Cannot load the default save file: " +
	 * e.getStackTrace()); } catch (LineUnavailableException e) {
	 * logger.warning("Cannot load the music file: " + e.getStackTrace()); } }
	 * 
	 * public static boolean isDebugActive() { return debug; }
	 * 
	 * public int getMovesCount() { return movesCount; }
	 * 
	 * public String getMapSetName() { return mapSetName; }
	 * 
	 * public void handleKey(KeyCode code) { switch (code) { case UP: move(new
	 * Point(-1, 0)); break;
	 * 
	 * case RIGHT: move(new Point(0, 1)); break;
	 * 
	 * case DOWN: move(new Point(1, 0)); break;
	 * 
	 * case LEFT: move(new Point(0, -1)); break;
	 * 
	 * default: // TODO: implement something funny. }
	 * 
	 * if (isDebugActive()) { System.out.println(code); } }
	 * 
	 * public void move(Point delta) { if (isGameComplete()) { return; }
	 * 
	 * Point keeperPosition = currentLevel.getKeeperPosition(); GameObject keeper =
	 * currentLevel.getObjectAt(keeperPosition); Point targetObjectPoint =
	 * GameGrid.translatePoint(keeperPosition, delta); GameObject keeperTarget =
	 * currentLevel.getObjectAt(targetObjectPoint);
	 * 
	 * if (StartMeUp.isDebugActive()) { System.out.println("Current level state:");
	 * System.out.println(currentLevel.toString()); System.out.println("Keeper pos:
	 * " + keeperPosition); System.out.println("Movement source obj: " + keeper);
	 * System.out.printf("Target object: %s at [%s]", keeperTarget,
	 * targetObjectPoint); }
	 * 
	 * boolean keeperMoved = false;
	 * 
	 * switch (keeperTarget) {
	 * 
	 * case WALL: break;
	 * 
	 * case CRATE:
	 * 
	 * GameObject crateTarget = currentLevel.getTargetObject(targetObjectPoint,
	 * delta); if (crateTarget != GameObject.FLOOR) { break; }
	 * 
	 * currentLevel.moveGameObjectBy(keeperTarget, targetObjectPoint, delta);
	 * currentLevel.moveGameObjectBy(keeper, keeperPosition, delta); keeperMoved =
	 * true; break;
	 * 
	 * case FLOOR: currentLevel.moveGameObjectBy(keeper, keeperPosition, delta);
	 * keeperMoved = true; break;
	 * 
	 * default: logger.severe("The object to be moved was not a recognised
	 * GameObject."); throw new AssertionError("This should not have happened.
	 * Report this problem to the developer."); }
	 * 
	 * if (keeperMoved) { keeperPosition.translate((int) delta.getX(), (int)
	 * delta.getY()); movesCount++; if (currentLevel.isComplete()) { if
	 * (isDebugActive()) { System.out.println("Level complete!"); }
	 * 
	 * currentLevel = getNextLevel(); } } }
	 * 
	 * public List<Level> loadGameFile(InputStream input) { List<Level> levels = new
	 * ArrayList<>(5); int levelIndex = 0;
	 * 
	 * try (BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(input))) { boolean parsedFirstLevel = false; List<String>
	 * rawLevel = new ArrayList<>(); String levelName = "";
	 * 
	 * while (true) { String line = reader.readLine();
	 * 
	 * // Break the loop if EOF is reached if (line == null) { if (rawLevel.size()
	 * != 0) { Level parsedLevel = new Level(levelName, ++levelIndex, rawLevel);
	 * levels.add(parsedLevel); } break; }
	 * 
	 * if (line.contains("MapSetName")) { mapSetName = line.replace("MapSetName: ",
	 * ""); continue; }
	 * 
	 * if (line.contains("LevelName")) { if (parsedFirstLevel) { Level parsedLevel =
	 * new Level(levelName, ++levelIndex, rawLevel); levels.add(parsedLevel);
	 * rawLevel.clear(); } else { parsedFirstLevel = true; }
	 * 
	 * levelName = line.replace("LevelName: ", ""); continue; }
	 * 
	 * line = line.trim(); line = line.toUpperCase(); // If the line contains at
	 * least 2 WALLS, add it to the list if (line.matches(".*W.*W.*")) {
	 * rawLevel.add(line); } }
	 * 
	 * } catch (IOException e) { logger.severe("Error trying to load the game file:
	 * " + e); } catch (NullPointerException e) { logger.severe("Cannot open the
	 * requested file: " + e); }
	 * 
	 * return levels; }
	 * 
	 * public boolean isGameComplete() { return gameComplete; }
	 * 
	 * public void createPlayer() throws LineUnavailableException { // File filePath
	 * = new
	 * File(getClass().getClassLoader().getResource("music/puzzle_theme.wav").toString());
	 * // Media music = new Media(filePath.toURI().toString()); // player = new
	 * MediaPlayer(music); // player.setOnEndOfMedia(() ->
	 * player.seek(Duration.ZERO)); }
	 * 
	 * public void playMusic() { // player.play(); }
	 * 
	 * public void stopMusic() { // player.stop(); }
	 * 
	 * public boolean isPlayingMusic() { // return player.getStatus() ==
	 * MediaPlayer.Status.PLAYING; return false; }
	 * 
	 * public Level getNextLevel() { if (currentLevel == null) { return
	 * levels.get(0); }
	 * 
	 * int currentLevelIndex = currentLevel.getIndex(); if (currentLevelIndex <
	 * levels.size()) { return levels.get(currentLevelIndex + 1); }
	 * 
	 * gameComplete = true; return null; }
	 * 
	 * public Level getCurrentLevel() { return currentLevel; }
	 * 
	 * public void toggleDebug() { debug = !debug; }
	 * 
	 * }
	 */

}