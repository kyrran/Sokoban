package sokodan.MVCStartScreen;

import javafx.collections.ObservableList;
/**This class is used as model of start screen
 * 
 * @author Kangle Yuan
 *
 */
public class ModelStartScreen {
	/**getter of wall color
	 * @param nothing
	 * @return m_wallcolor wall color
	 */
	public static String getM_wallcolor() {
		return m_wallcolor;
	}

	/**setter of wall color
	 * @return nothing
	 * @param m_wallcolor wall color
	 */
	public void setM_wallcolor(String m_wallcolor) {
		ModelStartScreen.m_wallcolor = m_wallcolor;
	}

	/**getter of username
	 * @param nothing
	 * @return m_username username
	 */
	public static String getM_username() {
		return m_username;
	}

	/**setter of username
	 * @return nothing
	 * @param m_username username
	 */
	public void setM_username(String m_username) {
		ModelStartScreen.m_username = m_username;
	}

	/**getter of list of wall color strings
	 * @param nothing
	 * @return  m_list list of wall color
	 */
	public ObservableList<String> getM_list() {
		return m_list;
	}

	/**setter of list of wall color strings
	 * @return nothing
	 * @param  m_list list of wall color
	 */
	public void setM_list(ObservableList<String> m_list) {
		this.m_list = m_list;
	}


	/** set default color as black */
	private static String m_wallcolor = "BLACK";
	/** set default color as black */
	private static String m_username = "Default User";

	private ObservableList<String> m_list;
	
	/**This constructor is to initialise variables.
	 * @return nothing
	 * @param m_wallcolor wall color
	 * @param m_username username you input
	 * @param list the wall color list
	 */
	public ModelStartScreen(String m_wallcolor, String m_username, ObservableList<String> list) {
		ModelStartScreen.m_wallcolor = m_wallcolor;
		ModelStartScreen.m_username = m_username;
		this.m_list = list;
	}

	
}
