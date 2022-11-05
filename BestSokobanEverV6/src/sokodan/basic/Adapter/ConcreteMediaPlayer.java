package sokodan.basic.Adapter;

/**
 * This class is for a concrete class of mediaplayer.
 * 
 * @author Kangle Yuan
 *
 */
public class ConcreteMediaPlayer implements InterfaceMediaPlayer {
	/**
	 * getter for media adapter.
	 * 
	 * @param nothing
	 * @return m_mediaAdapter the object of adapter
	 */
	public AdapterMedia getM_mediaAdapter() {
		return m_mediaAdapter;
	}

	/**
	 * setter for media adapter.
	 * 
	 * @return nothing
	 * @param m_mediaAdapter the object of adapter
	 */
	public void setM_mediaAdapter(AdapterMedia m_mediaAdapter) {
		this.m_mediaAdapter = m_mediaAdapter;
	}

	private AdapterMedia m_mediaAdapter;

	/**
	 * This method is to create adapter object and then call different functions.
	 * 
	 * @param audioType type of file
	 * @param fileName  name of file
	 */
	@Override
	public void play(String audioType, String fileName) {

		// mediaAdapter is providing support to play other file formats
		if (audioType.equalsIgnoreCase("wav") || audioType.equalsIgnoreCase("mp4")) {
			m_mediaAdapter = new AdapterMedia(audioType);
			m_mediaAdapter.play(audioType, fileName);
		}

		else {
			System.out.println("Invalid media. " + audioType + " format not supported");
		}
	}
}