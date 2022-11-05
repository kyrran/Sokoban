package sokodan.basic.Adapter;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
/**This class is for playing mp4 file
 * 
 * @author Kangle Yuan
 *
 */
public class Mp4Player implements AdvancedMediaPlayerInterface {
	/**getter for height of window.
	 * @return HEIGHT height of window
	 * @param nothing
	 */
	public int getHEIGHT() {
		return HEIGHT;
	}
	/**getter for width of window.
	 * @return WIDTH width of window
	 * @param nothing
	 */
	public int getWIDTH() {
		return WIDTH;
	}
	
	private final int HEIGHT =540;
	private final int WIDTH = 960;
	/**do nothing for wav under mp4
	 * @param fileName name of file
	 * @return nothing
	 */
	@Override
	public void playWav(String fileName) {
		//skip
	}
	/**set media to access for mp4.
	 * generate a new windows
	 * @param fileName name of file
	 * @return nothing
	 */
	@Override
	public void playMp4(String fileName) {
		Media music = null;
		try {
			music = new Media(new File(fileName).toURI().toURL().toExternalForm().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		MediaPlayer mediaPlayer = new MediaPlayer(music);

		MediaView mediaView = new MediaView(mediaPlayer);
		mediaPlayer.setAutoPlay(true);
		Group root = new Group();
		root.getChildren().add(mediaView);
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Playing video");
		primaryStage.show();
	}
}
