package sokodan.basic.Adapter;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**This class is for playing wav file
 * 
 * @author Kangle Yuan
 *
 */
public class WavPlayer implements AdvancedMediaPlayerInterface {
	/**set media to access for wav.
	 * @param fileName name of file
	 * @return nothing
	 */
	 @Override
	   public void playWav(String fileName) {
		 Media music = new Media(getClass().getClassLoader().getResource(fileName).toExternalForm());
		  new MediaPlayer(music).play();
	      System.out.println("Playing wav file. Name: "+ fileName);		
	   }
	 /**do nothing for wav under mp4
		 * @param fileName name of file
		 * @return nothing
		 */
	   @Override
	   public void playMp4(String fileName) {
	      //do nothing
	   }
}
