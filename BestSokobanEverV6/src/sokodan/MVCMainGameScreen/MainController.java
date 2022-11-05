package sokodan.MVCMainGameScreen;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sokodan.LevelStatusNow.CurrentStatus;
import sokodan.basic.GraphicObject;
import sokodan.basic.Adapter.ConcreteMediaPlayer;
import sokodan.basic.FactoryPattern.Color;
import sokodan.basic.Info.Dialog;
import sokodan.basic.Location.GameObject;
import sokodan.basic.Location.IteratorPattern.Level;

/**
 * This class is used as controller for main playing screen, also the controller
 * part of mvc pattern adhereed in main screen.
 * 
 * @author Kangle Yuan
 *
 */
public class MainController implements Initializable {

	/**
	 * getter for main screen model object
	 * 
	 * @param nothing
	 * @return m_modelMain object of model
	 */
	public ModelMainScreen getM_modelMain() {
		return m_modelMain;
	}

	/**
	 * setter for main screen model object.
	 * 
	 * @return nothing
	 * @param m_modelMain object of model
	 */
	public void setM_modelMain(ModelMainScreen m_modelMain) {
		this.m_modelMain = m_modelMain;
	}

	@FXML
	private GridPane GameArea;

	private ModelMainScreen m_modelMain;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		 
		  String saveDataPath = null;
		try {
			saveDataPath = MainController.class.getProtectionDomain().getCodeSource().
			  getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		m_modelMain = new ModelMainScreen();

		m_modelMain.getM_fileChooser()
				.setInitialDirectory(new File(saveDataPath));
	}

	/**
	 * This method is to close game when user clicks 'exit'
	 * 
	 * @param event This is a ActionEvent to listen to action
	 * @return Nothing
	 */
	@FXML
	private void closeGame(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * This method is to change image of crate to blue
	 * 
	 * @return nothing
	 * @param event once mouse clicked
	 */
	@FXML
	private void changetoBlue(ActionEvent event) {
		Color color1 = m_modelMain.getM_colorfactory().getColor("blue");
		color1.paint();
	}

	/**
	 * This method is to change image of crate to red
	 * 
	 * @return nothing
	 * @param event once mouse clicked
	 */
	@FXML
	private void changetoRed(ActionEvent event) {
		Color color2 = m_modelMain.getM_colorfactory().getColor("red");
		color2.paint();
	}

	/**
	 * This method is to change image of crate to beige
	 * 
	 * @return nothing
	 * @param event once mouse clicked
	 */
	@FXML
	private void changetoBeige(ActionEvent event) {
		Color color2 = m_modelMain.getM_colorfactory().getColor("beige");
		color2.paint();
	}

	/**
	 * This method is to change image of crate to default color yellow
	 * 
	 * @return nothing
	 * @param event once mouse clicked
	 */
	@FXML
	private void resettoDefaultColor(ActionEvent event) {
		Color color2 = m_modelMain.getM_colorfactory().getColor("Default");
		color2.paint();
	}

	/**
	 * This method is to save game as a file.
	 * 
	 * @param event
	 * @return nothing
	 * @throws FileNotFoundException
	 */

	@FXML
	public void saveGame(ActionEvent event) throws FileNotFoundException {
		m_modelMain.getM_fileChooser().setTitle("Save Dialog");
		m_modelMain.getM_fileChooser().setInitialFileName("game");
		m_modelMain.getM_fileChooser().getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
		m_modelMain.setM_saveFile(m_modelMain.getM_fileChooser().showSaveDialog(m_modelMain.getM_primaryStage()));

		if (m_modelMain.getM_saveFile() != null) {
			// edit the string for current level
			int currentindex = m_modelMain.getM_gameEngine().getM_currentLevel().getINDEX();
			m_modelMain.setM_level1(m_modelMain.getM_gameEngine().getM_levels().get(currentindex - 1));
			m_modelMain.setM_dia(m_modelMain.getM_level1().getDIAMONDSGRID());
			m_modelMain.setM_originalSteps(m_modelMain.getM_gameEngine().getM_movesCount());

			for (int i = 0; i < m_modelMain.getM_dia().COLUMNS; i++) {
				for (int j = 0; j < m_modelMain.getM_dia().ROWS; j++) {
					if (m_modelMain.getM_dia().getGameObjectAt(i, j) == GameObject.DIAMOND) {
						Point p = new Point(i, j);
						if (m_modelMain.getM_level1().getObjectAt(p) == GameObject.CRATE) {
							// put o to objectgrid
							m_modelMain.getM_level1().putObjtoDes(GameObject.CRATE_ON_DIAMOND, p);
						}
						if (m_modelMain.getM_level1().getObjectAt(p) == GameObject.KEEPER_ON_DIAMOND) {
							// put o to objectgrid
							m_modelMain.getM_level1().putObjtoDes(GameObject.KEEPER_ON_DIAMOND, p);
						}
					}
				}
			}
		}

		String s = "MapSetName: " + CurrentStatus.GAME_NAME + "\n";
		
		s += "Previous Level Steps in total: " + m_modelMain.getM_gameEngine().getStepsTotal() + "\n";
		
		editGridString(m_modelMain.getM_saveFile(), m_modelMain.getM_level1().getINDEX(), s,
				m_modelMain.getM_originalSteps());
	}

	/**
	 * This method is to edit all strings, and combine them as a single string to
	 * store into a file.
	 * 
	 * @param file   the file you are gonna to store strings to
	 * @param level1 the current level you are in
	 * @return s string you are gonna to edit and store
	 * @throws FileNotFoundException
	 */
	public void editGridString(File file, int currentindex, String s, int originalSteps) throws FileNotFoundException {
		int predefine = 0;

		for (int index = currentindex; index <= m_modelMain.getM_gameEngine().getM_levels().size(); index++) {

			if (index == currentindex) {
				if (m_modelMain.getM_gameEngine().getObj().getM_arr_indexStored().size() != 0) {
					predefine = m_modelMain.getM_gameEngine().getObj().getM_arr_indexStored().get(currentindex - 1);
					s += "LevelIndex: " + predefine + "\n" + "LevelName: " + m_modelMain.getM_level1().getNAME() + "\n"
							+ "Original Steps: " + originalSteps + "\n" + m_modelMain.getM_level1().toString() + "\n";
				} else
					s += "LevelIndex: " + currentindex + "\n" + "LevelName: " + m_modelMain.getM_level1().getNAME()
							+ "\n" + "Original Steps: " + originalSteps + "\n" + m_modelMain.getM_level1().toString()
							+ "\n";
			} else {
				predefine++;
				Level l = m_modelMain.getM_gameEngine().getSpecificLevel(index - 1);
				m_modelMain.setM_dia(l.getDIAMONDSGRID());

				for (int i = 0; i < m_modelMain.getM_dia().COLUMNS; i++) {
					for (int j = 0; j < m_modelMain.getM_dia().ROWS; j++) {
						if (m_modelMain.getM_dia().getGameObjectAt(i, j) == GameObject.DIAMOND) {
							Point q = new Point(i, j);
							if (l.getObjectAt(q) == GameObject.CRATE) {
								// put o to objectgrid
								l.putObjtoDes(GameObject.CRATE_ON_DIAMOND, q);
							}
							if (l.getObjectAt(q) == GameObject.KEEPER_ON_DIAMOND) {
								// put o to objectgrid
								l.putObjtoDes(GameObject.KEEPER_ON_DIAMOND, q);
							}
						}
					}
				}

				if (m_modelMain.getM_gameEngine().getObj().getM_arr_indexStored().size() != 0) {
					s += "LevelIndex: " + predefine + "\n" + "LevelName: " + l.getNAME() + "\n" + "Original Steps: 0\n"
							+ l.toString() + "\n";
				} else
					s += "LevelIndex: " + index + "\n" + "LevelName: " + l.getNAME() + "\n" + "Original Steps: 0\n"
							+ l.toString() + "\n";
			}

		}
		saveGridToFile(s, file);

	}

	/**
	 * this method is to save the current grid to a file
	 * 
	 * @param content a string contains all objects
	 * @param file    the file you are gonna to save content in
	 * @return nothing
	 * @throws FileNotFoundException
	 */
	public void saveGridToFile(String content, File file) throws FileNotFoundException {

		m_modelMain.setM_writer(new PrintWriter(file));
		m_modelMain.getM_writer().write(content);
		m_modelMain.getM_writer().close();

		if (CurrentStatus.isDebugActive()) {
			CurrentStatus.m_logger.info("Saving file: " + file.getName());
		}
	}

	/**
	 * This method is to load a game from a file.
	 * 
	 * @param event
	 * @return nothing
	 * @exception FileNotFoundException on file error
	 */
	@FXML
	public void loadGame(ActionEvent event) {
		try {
			loadGameFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is how to load the file
	 * 
	 * @param nothing
	 * @return nothing
	 * @throws IOException
	 */
	public void loadGameFile() throws IOException {
		m_modelMain.getM_fileChooser().setTitle("Open Save File");
		m_modelMain.getM_fileChooser().getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
		m_modelMain.setM_saveFile(m_modelMain.getM_fileChooser().showOpenDialog(m_modelMain.getM_primaryStage()));

		if (m_modelMain.getM_saveFile() != null) {
			if (CurrentStatus.isDebugActive()) {
				CurrentStatus.m_logger.info("Loading save file: " + m_modelMain.getM_saveFile().getName());
			}
			m_modelMain.setIn(new FileInputStream(m_modelMain.getM_saveFile()));
			initializeGame(m_modelMain.getIn());
		}
	}

	/**
	 * This method is to let level back to your previous state.
	 * 
	 * @param event
	 * @return nothing
	 */
	@FXML
	public void undo(ActionEvent event) {
		int i = m_modelMain.getM_keyarr().size();
		if (i >= 1) {
			KeyCode currentmove = m_modelMain.getM_keyarr().get(i - 1);
			undoKey(currentmove);
			m_modelMain.getM_keyarr().remove(i - 1);
			reloadGrid();
		}
	}

	/**
	 * This method is reset the current level.
	 * 
	 * @param event
	 * @return nothing
	 */
	@FXML
	public void resetLevel(ActionEvent event) {
		iteratorLoadingGrid(m_modelMain.getM_gameEngine()
				.getOneLevel(m_modelMain.getM_gameEngine().getM_currentLevel().getINDEX() - 1));
	}

	/**
	 * This method is to show a dialogue with the following words.
	 * 
	 * @param event
	 * @return nothing
	 */
	@FXML
	public void showAbout(ActionEvent event) {
		m_modelMain.setDialogTitle("About This Game");
		m_modelMain.setDialogMessage("Enjoy the Game!\n");
		new Dialog(m_modelMain.getDialogTitle(), m_modelMain.getDialogMessage(), null, m_modelMain.getM_primaryStage());
	}

	/**
	 * This method is to turn on and turn off music.
	 * 
	 * @param event
	 * @return nothing
	 */
	@FXML
	public void toggleMusic(ActionEvent event) throws LineUnavailableException {
		if (m_modelMain.getM_music().isPlayingMusic()) {
			m_modelMain.getM_music().stopMusic();
		} else {
			m_modelMain.getM_music().playMusic();
		}
	}

	/**
	 * This method is to toggle debug. if debug is open, your every step will show
	 * at the console, and your layout will change. once you unclicked that,
	 * everything will back to normal.
	 * 
	 * @param event
	 * @return nothing
	 */
	@FXML
	public void toggleDebug(ActionEvent event) {
		m_modelMain.getM_gameEngine().toggleDebug();
		reloadGrid();
	}

	/**
	 * This method is to show the default play screen.
	 * 
	 * @param m_primaryStage
	 * @return nothing
	 */
	public void loadDefaultSaveFile(Stage m_primaryStage) {
		m_modelMain.setM_primaryStage(m_primaryStage);
		System.out.println("Hi");
		InputStream in = getClass().getClassLoader().getResourceAsStream("SampleGame.skb");
		System.out.println(in);
		initializeGame(in);
		System.out.println("Hi");
		setEventFilter();
		System.out.println("Hi");
	}

	/**
	 * This method is to initialize this game.
	 * 
	 * @param input
	 * @return nothing
	 */
	public void initializeGame(InputStream input) {
		m_modelMain.setM_gameEngine(new CurrentStatus(input));
		reloadGrid();
	}

	/**
	 * This method is to reload the grid. erase the whole area and then reload every
	 * object.
	 * 
	 * @param nothing
	 * @return nothing
	 */
	private void reloadGrid() {
		if (m_modelMain.getM_gameEngine().isM_gameComplete()) {
			showVictoryMessage();
			return;
		}

		iteratorLoadingGrid(m_modelMain.getM_gameEngine().getM_currentLevel());
	}

	/**
	 * This method is to use iterator to reload the whole grid.
	 * 
	 * @return nothing
	 * @param currentLevel the currentlevel you are at
	 */
	public void iteratorLoadingGrid(Level currentLevel) {
		Level.LevelIterator levelGridIterator = (Level.LevelIterator) currentLevel.getIterator();
		GameArea.getChildren().clear();
		while (levelGridIterator.hasNext()) {
			addObjectToGrid(levelGridIterator.next(), levelGridIterator.getCurrentPosition());
		}
		GameArea.autosize();
		m_modelMain.getM_primaryStage().sizeToScene();
	}

	/**
	 * this method is to add specific object to the current grid
	 * 
	 * @param gameObject the specfic object you want to put on the grid
	 * @param location   where you want to put the object on
	 * @return nothing
	 */
	public void addObjectToGrid(GameObject gameObject, Point location) {
		m_modelMain.setGraphicObject(new GraphicObject(gameObject));
		GameArea.add(m_modelMain.getGraphicObject(), location.y, location.x);
	}

	/**
	 * this method is to show victory message when the whole game finishes.
	 * 
	 * @param nothing
	 * @return nothing
	 */
	public void showVictoryMessage() {
		m_modelMain.setDialogTitle("Game Over!");

		m_modelMain.getM_gameEngine();
		m_modelMain.setDialogMessage("You completed " + CurrentStatus.getGameName() + " in "
				+ m_modelMain.getM_gameEngine().getStepsTotal() + " moves!");
		MotionBlur mb = new MotionBlur(2, 3);
		new Dialog(m_modelMain.getDialogTitle(), m_modelMain.getDialogMessage(), mb, m_modelMain.getM_primaryStage());
	}

	/**
	 * This method is to track the keycode you input.
	 * 
	 * @param nothing
	 * @return nothing
	 */
	public void setEventFilter() {
		m_modelMain.getM_primaryStage().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			m_modelMain.setM_lastmove(event.getCode());
			m_modelMain.getM_keyarr().add(m_modelMain.getM_lastmove());
			handleKey(event.getCode());
			reloadGrid();
		});
	}

	/**
	 * This method is to track single keycode and move it back to where it was
	 * 
	 * @return nothing
	 * @param code keycode user input
	 */

	public void undoKey(KeyCode code) {

		switch (code) {
		case UP:
			m_modelMain.getM_gameEngine().undomove(new Point(1, 0), new Point(-1, 0));
			break;

		case RIGHT:
			m_modelMain.getM_gameEngine().undomove(new Point(0, -1), new Point(0, 1));
			break;

		case DOWN:
			m_modelMain.getM_gameEngine().undomove(new Point(-1, 0), new Point(1, 0));
			break;

		case LEFT:
			m_modelMain.getM_gameEngine().undomove(new Point(0, 1), new Point(0, -1));
			break;

		default:
		}
		ModelMainScreen.setM_var(code);
		if (CurrentStatus.isDebugActive()) {
			System.out.println(code);
		}
	}

	/**
	 * This method is to track single keycode and move it back to where it goes to.
	 * 
	 * @return nothing
	 * @param code keycode user input
	 */

	public void handleKey(KeyCode code) {
		ConcreteMediaPlayer m_audioPlayer = new ConcreteMediaPlayer();
		switch (code) {
		case UP:
			m_modelMain.getM_gameEngine().move(new Point(-1, 0));
			break;

		case RIGHT:
			m_modelMain.getM_gameEngine().move(new Point(0, 1));
			break;

		case DOWN:
			m_modelMain.getM_gameEngine().move(new Point(1, 0));
			break;

		case LEFT:
			m_modelMain.getM_gameEngine().move(new Point(0, -1));
			break;

		default:
			if (code == KeyCode.SHIFT) {
				m_audioPlayer.play("mp4", "RuleofGame.mp4");
			} else {
				m_audioPlayer.play("wav", "error_sound.wav");
			}
		}
		ModelMainScreen.setM_var(code);
		if (CurrentStatus.isDebugActive()) {
			System.out.println(code);
		}
	}

}
