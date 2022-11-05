package sokodan.basic.Adapter;

/**
 * This interface is to define the default method
 * 
 * @author Kangle Yuan
 *
 */
public interface AdvancedMediaPlayerInterface {
	/**play wav file
	 * @return nothing
	 * @param fileName name of file
	 */
	public void playWav(String fileName);
	/**play mp4 file
	 * @return nothing
	 * @param fileName name of file
	 */
	public void playMp4(String fileName);
}
