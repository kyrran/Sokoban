package sokodan.MVCMainGameScreen;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sokodan.LevelStatusNow.CurrentStatus;
import sokodan.basic.GraphicObject;
import sokodan.basic.FactoryPattern.ColorFactory;
import sokodan.basic.Location.IteratorPattern.GameGrid;
import sokodan.basic.Location.IteratorPattern.Level;
import sokodan.basic.Singleton.MusicClass;

/**
 * This class is to use as model for main screen.
 * 
 * @author Kangle Yuan
 *
 */
public class ModelMainScreen {
	/**getter for color factory object 
	 * @param nothing
	 * @return m_colorfactory the colorfactory object
	 */
	public ColorFactory getM_colorfactory() {
		return m_colorfactory;
	}
	/**setter for color factory object 
	 * @return nothing
	 * @param m_colorfactory the colorfactory object
	 */
	public void setM_colorfactory(ColorFactory m_colorfactory) {
		this.m_colorfactory = m_colorfactory;
	}

	/**
	 * getter for every object
	 * 
	 * @param nothing
	 * @return m_graphobject the game object
	 */
	public GraphicObject getGraphicObject() {
		return m_graphicObject;
	}

	/**
	 * setter for every object
	 * 
	 * @param graphicobjct the object you want to set as
	 * @return nothing
	 */
	public void setGraphicObject(GraphicObject graphicObject) {
		this.m_graphicObject = graphicObject;
	}

	

	/**
	 * getter for title of generated window.
	 * 
	 * @param nothing
	 * @return m_dialogTitle title for window
	 */
	public String getDialogTitle() {
		return m_dialogTitle;
	}

	/**
	 * setter for title of generated window
	 * 
	 * @param diadtitle title you to set
	 * @return nothing
	 */
	public void setDialogTitle(String dialogTitle) {
		this.m_dialogTitle = dialogTitle;
	}

	/**
	 * getter for message in scene
	 * 
	 * @param nothing
	 * @return m_dialogmessage message to show up in scene
	 */
	public String getDialogMessage() {
		return m_dialogMessage;
	}

	/**
	 * setter for message of generated window
	 * 
	 * @param dialogmessage message you to set
	 * @return nothing
	 */
	public void setDialogMessage(String dialogMessage) {
		this.m_dialogMessage = dialogMessage;
	}

	/**
	 * getter for diamond grid under specific level
	 * 
	 * @param nothing
	 * @return m_dia diamond grid
	 */
	public GameGrid getM_dia() {
		return m_dia;
	}

	/**
	 * setter for diamond grid under specific level.
	 * 
	 * @return nothing
	 * @param m_dia diamond grid
	 */
	public void setM_dia(GameGrid m_dia) {
		this.m_dia = m_dia;
	}

	/**
	 * getter for number of steps you had before.
	 * 
	 * @param nothing
	 * @return m_originalSteps total steps
	 */
	public int getM_originalSteps() {
		return m_originalSteps;
	}

	/**
	 * setter for number of number of steps you had before
	 * 
	 * @return nothing
	 * @param m_originalSteps total steps
	 */
	public void setM_originalSteps(int m_originalSteps) {
		this.m_originalSteps = m_originalSteps;
	}

	/**
	 * getter for current keycode.
	 * 
	 * @param nothing
	 * @return m_var keycode you pressed
	 */
	public static KeyCode getM_var() {
		return m_var;
	}

	/**
	 * setter for keycode you want to set
	 * 
	 * @return nothing
	 * @param m_var keycode
	 */
	public static void setM_var(KeyCode m_var) {
		ModelMainScreen.m_var = m_var;
	}

	/**
	 * getter for current stage
	 * 
	 * @param nothing
	 * @return m_primaryStage stage you are at
	 */
	public Stage getM_primaryStage() {
		return m_primaryStage;
	}

	/**
	 * setter for current stage
	 * 
	 * @return nothing
	 * @param m_primaryStage stage you are at
	 */
	public void setM_primaryStage(Stage m_primaryStage) {
		this.m_primaryStage = m_primaryStage;
	}

	/**
	 * getter for current status
	 * 
	 * @param nothing
	 * @return m_gameEngine current status you are at
	 */
	public CurrentStatus getM_gameEngine() {
		return m_gameEngine;
	}

	/**
	 * setter for current status
	 * 
	 * @return nothing
	 * @param m_gameEngine current status you are at
	 */
	public void setM_gameEngine(CurrentStatus m_gameEngine) {
		this.m_gameEngine = m_gameEngine;
	}

	/**
	 * getter for musicplayer
	 * 
	 * @param nothing
	 * @return m_music musicclass object
	 */
	public MusicClass getM_music() {
		return m_music;
	}

	/**
	 * setter for musicclass object
	 * 
	 * @return nothing
	 * @param m_music musicclass object
	 */
	public void setM_music(MusicClass m_music) {
		this.m_music = m_music;
	}

	/**
	 * getter for filechooser
	 * 
	 * @param nothing
	 * @return m_fileChooser the filechooser
	 */
	public FileChooser getM_fileChooser() {
		return m_fileChooser;
	}

	/**
	 * setter for filechooser
	 * 
	 * @return nothing
	 * @param m_fileChooser the filechooser
	 */
	public void setM_fileChooser(FileChooser m_fileChooser) {
		this.m_fileChooser = m_fileChooser;
	}

	/**
	 * getter for keycode of your last move
	 * 
	 * @param nothing
	 * @return m_lastmove the keycode of your last move
	 */
	public KeyCode getM_lastmove() {
		return m_lastmove;
	}

	/**
	 * setter for keycode of your last move
	 * 
	 * @param m_lastmove the keycode of your last move
	 * @return nothing
	 */
	public void setM_lastmove(KeyCode m_lastmove) {
		this.m_lastmove = m_lastmove;
	}

	/**
	 * getter for array of keycode
	 * 
	 * @param nothing
	 * @return m_keyarr the array of keycode
	 */
	public ArrayList<KeyCode> getM_keyarr() {
		return m_keyarr;
	}

	/**
	 * setter for array of keycode
	 * 
	 * @param m_keyarr the array of keycode
	 * @return nothing
	 */
	public void setM_keyarr(ArrayList<KeyCode> m_keyarr) {
		this.m_keyarr = m_keyarr;
	}

	/**
	 * getter for the file you save
	 * 
	 * @param nothing
	 * @return m_saveFile the file you to save
	 */
	public File getM_saveFile() {
		return m_saveFile;
	}

	/**
	 * setter for the file you save
	 * 
	 * @param m_saveFile the file you to save
	 * @return nothing
	 */
	public void setM_saveFile(File m_saveFile) {
		this.m_saveFile = m_saveFile;
	}

	/**
	 * getter for the level you are at
	 * 
	 * @param nothing
	 * @return m_level1 the level you are at
	 */
	public Level getM_level1() {
		return m_level1;
	}

	/**
	 * setter for the level you are at
	 * 
	 * @param m_level1 the level you are at
	 * @return nothing
	 */
	public void setM_level1(Level m_level1) {
		this.m_level1 = m_level1;
	}

	/**
	 * getter for the input you read from file
	 * 
	 * @param nothing
	 * @return m_in the input you read from file
	 */
	public InputStream getIn() {
		return m_in;
	}

	/**
	 * setter for the input you read from file
	 * 
	 * @param m_in the input you read from file
	 * @return nothing
	 */
	public void setIn(InputStream in) {
		this.m_in = in;
	}

	/**
	 * getter for the file writer
	 * 
	 * @param nothing
	 * @return m_writer file writer
	 */
	public PrintWriter getM_writer() {
		return m_writer;
	}

	/**
	 * setter for file writer
	 * 
	 * @param m_in the input you read from file
	 * @return m_writer file writer
	 */
	public void setM_writer(PrintWriter m_writer) {
		this.m_writer = m_writer;
	}

	private static KeyCode m_var;
	private Stage m_primaryStage;
	private CurrentStatus m_gameEngine;
	private MusicClass m_music = MusicClass.getInstance();
	private FileChooser m_fileChooser = new FileChooser();
	private KeyCode m_lastmove;
	private ArrayList<KeyCode> m_keyarr = new ArrayList<KeyCode>();
	private File m_saveFile;
	private Level m_level1;
	private int m_originalSteps = 0;
	private GameGrid m_dia;
	private String m_dialogTitle;
	private String m_dialogMessage;
	
	private GraphicObject m_graphicObject;
	private InputStream m_in;
	private PrintWriter m_writer;
	private ColorFactory m_colorfactory = new ColorFactory();
}
