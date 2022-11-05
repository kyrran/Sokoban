package sokodan.basic.Singleton;

import javafx.scene.media.*;
import javafx.util.Duration;

/**
 * This class is to play background music.
 * 
 * @author Kangle Yuan
 */
public class MusicClass {
	/**
	 * getter to get object of mediaplayer
	 * 
	 * @param nothing
	 * @return m_player object of player
	 */
	public MediaPlayer getM_player() {
		return m_player;
	}

	/**
	 * setter to get object od mediaplayer
	 * 
	 * @return nothing
	 * @param m_player object of player
	 */
	public void setM_player(MediaPlayer m_player) {
		this.m_player = m_player;
	}

	/**
	 * setter to set instance
	 * 
	 * @return nothing
	 * @param m_instance instance of music class
	 */
	public static void setM_instance(MusicClass m_instance) {
		MusicClass.m_instance = m_instance;
	}

	/**
	 * This method is to get only one instance of this class.
	 * 
	 * @param nothing
	 * @return nothing
	 */
	public static MusicClass getInstance() {
		if (m_instance == null) {
			m_instance = new MusicClass();
		}
		return m_instance;
	}

	private MediaPlayer m_player;
	private static MusicClass m_instance;

	/**
	 * This constructor is to initialise media and mediaplayer
	 * @param nothing
	 * @return nothing
	 */
	private MusicClass() {
		Media music = new Media(getClass().getClassLoader().getResource("puzzle_theme.wav").toExternalForm());
		m_player = new MediaPlayer(music);
		m_player.setOnEndOfMedia(() -> m_player.seek(Duration.ZERO));
	}

	/**
	 * This method is to play music.
	 * 
	 * @param nothing
	 * @return nothing
	 */
	public void playMusic() {
		m_player.play();
	}

	/**
	 * This method is to stop music.
	 * 
	 * @param nothing
	 * @return nothing
	 */
	public void stopMusic() {
		m_player.stop();
	}

	/**
	 * This method is to check music's status.
	 * 
	 * @param nothing
	 * @return boolean if the current status is running or not
	 */
	public boolean isPlayingMusic() {
		return (m_player.getStatus() == MediaPlayer.Status.PLAYING);
	}
}
