package sokodan.MVCHighScore;

import java.io.File;
import java.util.ArrayList;

import javafx.collections.ObservableList;
/**This class is used as model for high score
 * 
 * @author Kangle Yuan
 *
 */
public class ModelHighScore {
	/**getter for ordered final steps in level6
	 * @param nothing
	 * @return  listview6 ordered final steps
	 */
	public static ObservableList<String> getListview6() {
		return listview6;
	}
	/**setter for ordered final steps in level6
	 * @return nothing
	 * @param  listview6 ordered final steps
	 */
	public static void setListview6(ObservableList<String> listview6) {
		ModelHighScore.listview6 = listview6;
	}
	/**getter for score in level6
	 * @param nothing
	 * @return m_lineArr6 array of scores in level6
	 */
	public ArrayList<String> getM_lineArr6() {
		return m_lineArr6;
	}
	/**setter for score in level6
	 * @return nothing
	 * @param m_lineArr6 array of scores in level6
	 */
	public void setM_lineArr6(ArrayList<String> m_lineArr6) {
		this.m_lineArr6 = m_lineArr6;
	}
	/**getter for score in level1
	 * @param nothing
	 * @return m_lineArr1 array of scores in level1
	 */
	public ArrayList<String> getM_lineArr1() {
		return m_lineArr1;
	}
	/**setter for score in level1
	 * @param m_lineArr1 array of scores in level1
	 * @return nothing
	 */
	public void setM_lineArr1(ArrayList<String> m_lineArr1) {
		this.m_lineArr1 = m_lineArr1;
	}
	/**getter for score in level2
	 * @param nothing
	 * @return m_lineArr2 array of scores in level2
	 */
	public ArrayList<String> getM_lineArr2() {
		return m_lineArr2;
	}
	/**setter for score in level2
	 * @param m_lineArr2 array of scores in level2
	 * @return nothing
	 */
	public void setM_lineArr2(ArrayList<String> m_lineArr2) {
		this.m_lineArr2 = m_lineArr2;
	}
	/**getter for score in level3
	 * @param nothing
	 * @return m_lineArr3 array of scores in level3
	 */
	public ArrayList<String> getM_lineArr3() {
		return m_lineArr3;
	}
	/**setter for score in level3
	 * @param m_lineArr3 array of scores in level3
	 * @return nothing
	 */
	public void setM_lineArr3(ArrayList<String> m_lineArr3) {
		this.m_lineArr3 = m_lineArr3;
	}
	/**getter for score in level4
	 * @param nothing
	 * @return m_lineArr4 array of scores in level4
	 */
	public ArrayList<String> getM_lineArr4() {
		return m_lineArr4;
	}
	/**setter for score in level4
	 * @param m_lineArr4 array of scores in level4
	 * @return nothing
	 */
	public void setM_lineArr4(ArrayList<String> m_lineArr4) {
		this.m_lineArr4 = m_lineArr4;
	}
	/**getter for score in level5
	 * @param nothing
	 * @return m_lineArr5 array of scores in level5
	 */
	public ArrayList<String> getM_lineArr5() {
		return m_lineArr5;
	}
	/**setter for score in level5
	 * @param m_lineArr5 array of scores in level5
	 * @return nothing
	 */
	public void setM_lineArr5(ArrayList<String> m_lineArr5) {
		this.m_lineArr5 = m_lineArr5;
	}
	/**getter for score file
	 * @param nothing
	 * @return m_file score file
	 */
	public File getM_file() {
		return m_file;
	}
	/**setter for  score file
	 * @param m_file score file
	 * @return nothing
	 */
	public void setM_file(File m_file) {
		this.m_file = m_file;
	}
	/**getter for window height
	 * @param nothing
	 * @return  HEIGHT windows height
	 */
	public int getHEIGHT() {
		return HEIGHT;
	}
	/**getter for window width
	 * @param nothing
	 * @return  width windows height
	 */
	public int getWIDTH() {
		return WIDTH;
	}
	/**getter for ordered final steps in level1 
	 * @param nothing
	 * @return  listview1 ordered final steps
	 */
	public static ObservableList<String> getListview1() {
		return listview1;
	}
	/**setter for ordered final steps in level1 
	 * @return nothing
	 * @param listview1 ordered final steps
	 */
	public static void setListview1(ObservableList<String> listview1) {
		ModelHighScore.listview1 = listview1;
	}
	/**getter for ordered final steps in level12
	 * @param nothing
	 * @return  listview2 ordered final steps
	 */
	public static ObservableList<String> getListview2() {
		return listview2;
	}
	/**setter for ordered final steps in level2 
	 * @return nothing
	 * @param listview2 ordered final steps
	 */
	public static void setListview2(ObservableList<String> listview2) {
		ModelHighScore.listview2 = listview2;
	}
	/**getter for ordered final steps in level3 
	 * @param nothing
	 * @return  listview3 ordered final steps
	 */
	public static ObservableList<String> getListview3() {
		return listview3;
	}
	/**setter for ordered final steps in level3 
	 * @return nothing
	 * @param listview3 ordered final steps
	 */
	public static void setListview3(ObservableList<String> listview3) {
		ModelHighScore.listview3 = listview3;
	}
	/**getter for ordered final steps in level4 
	 * @param nothing
	 * @return  listview4 ordered final steps
	 */
	public static ObservableList<String> getListview4() {
		return listview4;
	}
	/**setter for ordered final steps in level4 
	 * @return nothing
	 * @param listview4 ordered final steps
	 */
	public static void setListview4(ObservableList<String> listview4) {
		ModelHighScore.listview4 = listview4;
	}
	/**getter for ordered final steps in level5 
	 * @param nothing
	 * @return  listview5 ordered final steps
	 */
	public static ObservableList<String> getListview5() {
		return listview5;
	}
	/**setter for ordered final steps in level5 
	 * @return nothing
	 * @param listview5 ordered final steps
	 */
	public static void setListview5(ObservableList<String> listview5) {
		ModelHighScore.listview5 = listview5;
	}
	private static ObservableList<String> listview1;
	private static ObservableList<String> listview2;
	private static ObservableList<String> listview3;
	private static ObservableList<String> listview4;
	private static ObservableList<String> listview5;
	private static ObservableList<String> listview6;
	private File m_file;
	private  ArrayList<String> m_lineArr1 = new ArrayList<String>();
	private  ArrayList<String> m_lineArr2 = new ArrayList<String>();
	private  ArrayList<String> m_lineArr3 = new ArrayList<String>();
	private  ArrayList<String> m_lineArr4 = new ArrayList<String>();
	private  ArrayList<String> m_lineArr5 = new ArrayList<String>();
	private  ArrayList<String> m_lineArr6 = new ArrayList<String>();
	private final int HEIGHT = 680;
	private final int WIDTH = 600;
	
}
