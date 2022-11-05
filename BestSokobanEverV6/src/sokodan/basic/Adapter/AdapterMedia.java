package sokodan.basic.Adapter;

/**
 * This class is to use as adapter in adapter pattern
 * 
 * @author Kangle Yuan
 *
 */
public class AdapterMedia implements InterfaceMediaPlayer {
	/**
	 * getter of object
	 * 
	 * @param nothing
	 * @return m_advancedMusicPlayer object of this class
	 */
	public AdvancedMediaPlayerInterface getAdvancedMusicPlayer() {
		return m_advancedMusicPlayer;
	}

	/**
	 * setter of object
	 * 
	 * @return nothing
	 * @param m_advancedMusicPlayer object of this class
	 */
	public void setAdvancedMusicPlayer(AdvancedMediaPlayerInterface advancedMusicPlayer) {
		this.m_advancedMusicPlayer = advancedMusicPlayer;
	}

	private AdvancedMediaPlayerInterface m_advancedMusicPlayer;

	/**
	 * This constructor is to generate different object for different type of file
	 * 
	 * @return nothing
	 * @param audioType the type of file
	 */
	public AdapterMedia(String audioType) {

		if (audioType.equalsIgnoreCase("wav")) {
			m_advancedMusicPlayer = new WavPlayer();

		} else if (audioType.equalsIgnoreCase("mp4")) {
			m_advancedMusicPlayer = new Mp4Player();
		}
	}

	/**
	 * This method is to call different method for different type.
	 * 
	 * @param audioType type of file
	 * @param fileName  the name of file
	 */
	@Override
	public void play(String audioType, String fileName) {

		if (audioType.equalsIgnoreCase("wav")) {
			m_advancedMusicPlayer.playWav(fileName);
		} else if (audioType.equalsIgnoreCase("mp4")) {
			m_advancedMusicPlayer.playMp4(fileName);
		}
	}
}
