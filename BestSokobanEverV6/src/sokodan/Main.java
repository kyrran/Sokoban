package sokodan;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sokodan.LevelStatusNow.CurrentStatus;
import sokodan.MVCMainGameScreen.MainController;
import sokodan.MVCStartScreen.StartController;

/**
 * The BestSokobanEverV6 program implements an application that push the orange
 * dots to blue dots then you can pass the current level and unlock new level
 * 
 * @author Kangle Yuan-modified
 */
public class Main extends Application {
	/** to avoid magic number */
	public final static int WIDTH = 600;
	public final static int HEIGHT = 630;
	/**
	 * This is the main method which launch the javaFX program and print out done
	 * when finished.
	 */
	public static void main(String[] args) {

		launch(args);
		System.out.println("Done!");
	}

	/**
	 * This method is used to generate start screen
	 * 
	 * @param m_primaryStage This is the only parameter to start method
	 * @return Nothing
	 * @exception Exception
	 */
	Parent root;
	Stage m_primaryStage;

	@Override
	public void start(Stage m_primaryStage) throws Exception {

		this.m_primaryStage = m_primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MVCStartScreen/StartScreen.fxml"));
		root = loader.load();

		m_primaryStage.setTitle(CurrentStatus.GAME_NAME);
		m_primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		m_primaryStage.setResizable(false);
		m_primaryStage.show();
		StartController myController = loader.getController();
		myController.setMain(this);
	}

	/**
	 * This method is to go back from start controller to load main playing screen.
	 * 
	 * @param nothing
	 * @return loader
	 * @throws IOException
	 */

	public void startMainGame() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MVCMainGameScreen/MainScreen.fxml"));
		root = loader.load();

		m_primaryStage.setTitle(CurrentStatus.GAME_NAME);
		m_primaryStage.setScene(new Scene(root, Main.WIDTH, Main.HEIGHT));
		m_primaryStage.setResizable(false);
		m_primaryStage.show();

		MainController myController = loader.getController();
		myController.loadDefaultSaveFile(m_primaryStage);
	}


	/**
	 * the original code in main class is
	 * 
	 * package sample;
	 * 
	 * import javafx.application.Application; import javafx.geometry.Pos; import
	 * javafx.scene.Scene; import javafx.scene.control.Menu; import
	 * javafx.scene.control.MenuBar; import javafx.scene.control.MenuItem; import
	 * javafx.scene.control.*; import javafx.scene.effect.Effect; import
	 * javafx.scene.effect.MotionBlur; import javafx.scene.input.KeyEvent; import
	 * javafx.scene.layout.Background; import javafx.scene.layout.GridPane; import
	 * javafx.scene.layout.VBox; import javafx.scene.text.Text; import
	 * javafx.scene.text.TextAlignment; import javafx.stage.FileChooser; import
	 * javafx.stage.Modality; import javafx.stage.Stage;
	 * 
	 * import java.awt.*; import java.io.File; import java.io.FileInputStream;
	 * import java.io.FileNotFoundException; import java.io.InputStream;
	 * 
	 * 
	 * 
	 * public class Main extends Application {
	 * 
	 * private Stage primaryStage; private StartMeUp gameEngine; private GridPane
	 * gameGrid; private File saveFile; private MenuBar MENU;
	 * 
	 * 
	 * public static void main(String[] args) { launch(args);
	 * System.out.println("Done!"); }
	 * 
	 * @Override public void start(Stage primaryStage) throws Exception {
	 * 
	 *           this.primaryStage = primaryStage;
	 * 
	 *           MENU = new MenuBar();
	 * 
	 *           MenuItem menuItemSaveGame = new MenuItem("Save Game");
	 *           menuItemSaveGame.setDisable(true);
	 *           menuItemSaveGame.setOnAction(actionEvent -> saveGame()); MenuItem
	 *           menuItemLoadGame = new MenuItem("Load Game");
	 *           menuItemLoadGame.setOnAction(actionEvent -> loadGame()); MenuItem
	 *           menuItemExit = new MenuItem("Exit");
	 *           menuItemExit.setOnAction(actionEvent -> closeGame()); Menu menuFile
	 *           = new Menu("File"); menuFile.getItems().addAll(menuItemSaveGame,
	 *           menuItemLoadGame, new SeparatorMenuItem(), menuItemExit);
	 * 
	 *           MenuItem menuItemUndo = new MenuItem("Undo");
	 *           menuItemUndo.setDisable(true); menuItemUndo.setOnAction(actionEvent
	 *           -> undo()); RadioMenuItem radioMenuItemMusic = new
	 *           RadioMenuItem("Toggle Music");
	 *           radioMenuItemMusic.setOnAction(actionEvent -> toggleMusic());
	 *           RadioMenuItem radioMenuItemDebug = new RadioMenuItem("Toggle
	 *           Debug"); radioMenuItemDebug.setOnAction(actionEvent ->
	 *           toggleDebug()); MenuItem menuItemResetLevel = new MenuItem("Reset
	 *           Level"); Menu menuLevel = new Menu("Level");
	 *           menuLevel.setOnAction(actionEvent -> resetLevel());
	 *           menuLevel.getItems().addAll(menuItemUndo, radioMenuItemMusic,
	 *           radioMenuItemDebug, new SeparatorMenuItem(), menuItemResetLevel);
	 * 
	 *           MenuItem menuItemGame = new MenuItem("About This Game"); Menu
	 *           menuAbout = new Menu("About"); menuAbout.setOnAction(actionEvent ->
	 *           showAbout()); menuAbout.getItems().addAll(menuItemGame);
	 *           MENU.getMenus().addAll(menuFile, menuLevel, menuAbout); gameGrid =
	 *           new GridPane(); GridPane root = new GridPane(); root.add(MENU,
	 *           0,0); root.add(gameGrid, 0, 1);
	 *           primaryStage.setTitle(StartMeUp.GAME_NAME);
	 *           primaryStage.setScene(new Scene(root)); primaryStage.show();
	 *           loadDefaultSaveFile(primaryStage); }
	 * 
	 *           void loadDefaultSaveFile(Stage primaryStage) { this.primaryStage =
	 *           primaryStage; System.out.println("Hi"); InputStream in =
	 *           getClass().getClassLoader().getResourceAsStream("sample/SampleGame.skb");
	 *           System.out.println(in); initializeGame(in);
	 *           System.out.println("Hi"); setEventFilter();
	 *           System.out.println("Hi"); }
	 * 
	 *           public void initializeGame(InputStream input) { gameEngine = new
	 *           StartMeUp(input, true); reloadGrid(); }
	 * 
	 *           public void setEventFilter() {
	 *           primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
	 *           gameEngine.handleKey(event.getCode()); reloadGrid(); });}
	 * 
	 *           public void loadGameFile() throws FileNotFoundException {
	 *           FileChooser fileChooser = new FileChooser();
	 *           fileChooser.setTitle("Open Save File");
	 *           fileChooser.getExtensionFilters().add(new
	 *           FileChooser.ExtensionFilter("Sokoban save file", "*.skb"));
	 *           saveFile = fileChooser.showOpenDialog(primaryStage);
	 * 
	 *           if (saveFile != null) { if (StartMeUp.isDebugActive()) {
	 *           StartMeUp.logger.info("Loading save file: " + saveFile.getName());
	 *           } initializeGame(new FileInputStream(saveFile)); }}private void
	 *           reloadGrid() { if (gameEngine.isGameComplete()) {
	 *           showVictoryMessage(); return; }
	 * 
	 *           Level currentLevel = gameEngine.getCurrentLevel();
	 *           Level.LevelIterator levelGridIterator = (Level.LevelIterator)
	 *           currentLevel.iterator(); gameGrid.getChildren().clear();
	 * 
	 *           while(levelGridIterator.hasNext()) {
	 *           addObjectToGrid(levelGridIterator.next(),levelGridIterator.getCurrentPosition());
	 *           }
	 * 
	 *           gameGrid.autosize(); primaryStage.sizeToScene(); }
	 * 
	 *           public void showVictoryMessage() { String dialogTitle = "Game
	 *           Over!"; String dialogMessage = "You completed " +
	 *           gameEngine.getMapSetName() + " in " + gameEngine.getMovesCount() +
	 *           " moves!"; MotionBlur mb = new MotionBlur(2, 3);
	 * 
	 *           newDialog(dialogTitle, dialogMessage, mb); }
	 * 
	 *           public void newDialog(String dialogTitle, String
	 *           dialogMessage,Effect dialogMessageEffect) { final Stage dialog =
	 *           new Stage(); dialog.initModality(Modality.APPLICATION_MODAL);
	 *           dialog.initOwner(primaryStage); dialog.setResizable(false);
	 *           dialog.setTitle(dialogTitle);
	 * 
	 *           Text text1 = new Text(dialogMessage);
	 *           text1.setTextAlignment(TextAlignment.CENTER);
	 *           text1.setFont(javafx.scene.text.Font.font(14));
	 * 
	 *           if (dialogMessageEffect != null) {
	 *           text1.setEffect(dialogMessageEffect); }
	 * 
	 *           VBox dialogVbox = new VBox(20);
	 *           dialogVbox.setAlignment(Pos.CENTER);
	 *           dialogVbox.setBackground(Background.EMPTY);
	 *           dialogVbox.getChildren().add(text1);
	 * 
	 *           Scene dialogScene = new Scene(dialogVbox, 350, 150);
	 *           dialog.setScene(dialogScene); dialog.show(); }
	 * 
	 *           public void addObjectToGrid(GameObject gameObject, Point location){
	 *           GraphicObject graphicObject = new GraphicObject(gameObject);
	 *           gameGrid.add(graphicObject, location.y, location.x); }
	 * 
	 *           public void closeGame() { System.exit(0); }
	 * 
	 *           public void saveGame(){ }
	 * 
	 *           public void loadGame() { try { loadGameFile(); } catch
	 *           (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 *           public void undo() { closeGame(); }
	 * 
	 *           public void resetLevel() {}
	 * 
	 *           public void showAbout() { String title = "About This Game"; String
	 *           message = "Enjoy the Game!\n";
	 * 
	 *           newDialog(title, message, null); }
	 * 
	 *           public void toggleMusic() { //TODO }
	 * 
	 *           public void toggleDebug() { gameEngine.toggleDebug(); reloadGrid();
	 *           } }
	 */

}
