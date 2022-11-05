package sokodan.MVCHighScore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sokodan.LevelStatusNow.CurrentStatus;
import sokodan.MVCStartScreen.ModelStartScreen;

/**
 * This class is to write highscore to a file and read sentence from this file
 * to sort finally display them on the pop-up, call highscore controller
 * 
 * @author Kangle Yuan
 */
public class MVCpopup {
	/**getter of model
	 * @param nothing
	 * @return m_modelpop model object
	 */
	public ModelHighScore getM_modelPop() {
		return m_modelPop;
	}
	/**setter of model
	 * @return nothing
	 * @param m_modelpop model object
	 */
	public void setM_modelPop(ModelHighScore m_modelPop) {
		this.m_modelPop = m_modelPop;
	}

	private ModelHighScore m_modelPop = retriveData();

	/** This constructor is to initialise object */
	public ModelHighScore retriveData() {
		m_modelPop = new ModelHighScore();
		m_modelPop.setM_file(new File("scores.txt"));
		return m_modelPop;
	}

	/**
	 * This method is to call FXML page to show pop-up
	 * 
	 * @param levelindex the current level you finished
	 * @param movesCount the number of steps you used to pass this level
	 * @return nothing
	 */
	public void popup() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HighScore.fxml"));
		Parent root = loader.load();
		Stage m_primaryStage = new Stage();
		m_primaryStage.setTitle(CurrentStatus.GAME_NAME);
		m_primaryStage.setScene(new Scene(root, m_modelPop.getWIDTH(), m_modelPop.getHEIGHT()));
		m_primaryStage.setResizable(false);
		m_primaryStage.show();
	}

	/**
	 * This method is to write scores lines into file. it just track every single
	 * round and store them not in order BUT SCORES ARE IN ORDER ON POPUP
	 * 
	 * @param nothing
	 * @return nothing
	 * @throws IOException
	 */
	public void writetoFile(int levelindex, int movesCount, String date) throws IOException {

		String line = "Level " + levelindex + " : " + movesCount + " moves!" + "  ||Time: " + date + "  ||Username: "
				+ ModelStartScreen.getM_username() + "\n";
		FileWriter fw = new FileWriter(m_modelPop.getM_file(), true);
		BufferedWriter output = new BufferedWriter(fw);
		output.append(line);
		output.close();
		readScore();
	}

	/**
	 * This method is to read every single line in text file and put them into
	 * proper array and listview
	 * 
	 * @param levelindex the current level you finished
	 * @param movesCount the number of steps you used to pass this level
	 * @return nothing
	 * @exception IOException
	 * @see IOException
	 */
	public void readScore() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(m_modelPop.getM_file()));
		String s = reader.readLine();
		/**
		 * Put different levels into different arrays
		 */
		while (s != null) {
			if (s.indexOf("Level 1") != -1) {
				m_modelPop.getM_lineArr1().add(s);
			} else if (s.indexOf("Level 2") != -1) {
				m_modelPop.getM_lineArr2().add(s);
			} else if (s.indexOf("Level 3") != -1) {
				m_modelPop.getM_lineArr3().add(s);
			} else if (s.indexOf("Level 4") != -1) {
				m_modelPop.getM_lineArr4().add(s);
			} else if (s.indexOf("Level 5") != -1) {
				m_modelPop.getM_lineArr5().add(s);
			} else if (s.indexOf("Level 6") != -1) {
			m_modelPop.getM_lineArr6().add(s);
			}
			s = reader.readLine();
		}
		reader.close();
		sortAndPrint();

	}

	/**
	 * This method is to call sortScoreDisplayed function and pass array into
	 * listview
	 * 
	 * @param void
	 * @return nothing
	 * @throws IOException
	 */
	public void sortAndPrint() throws IOException {
		/** sort the scores you read */
		sortScoreDisplayed(m_modelPop.getM_lineArr1());
		sortScoreDisplayed(m_modelPop.getM_lineArr2());
		sortScoreDisplayed(m_modelPop.getM_lineArr3());
		sortScoreDisplayed(m_modelPop.getM_lineArr4());
		sortScoreDisplayed(m_modelPop.getM_lineArr5());
		sortScoreDisplayed(m_modelPop.getM_lineArr6());
		/** pass different arrays into listview (fxml) */
		ModelHighScore.setListview1(FXCollections.observableArrayList(m_modelPop.getM_lineArr1()));
		ModelHighScore.setListview2(FXCollections.observableArrayList(m_modelPop.getM_lineArr2()));
		ModelHighScore.setListview3(FXCollections.observableArrayList(m_modelPop.getM_lineArr3()));
		ModelHighScore.setListview4(FXCollections.observableArrayList(m_modelPop.getM_lineArr4()));
		ModelHighScore.setListview5(FXCollections.observableArrayList(m_modelPop.getM_lineArr5()));
		ModelHighScore.setListview6(FXCollections.observableArrayList(m_modelPop.getM_lineArr6()));
		/**
		 * to clear every array so that when user enters next level the results that
		 * have existed won't be printed again on popup
		 */
		m_modelPop.getM_lineArr1().clear();
		m_modelPop.getM_lineArr2().clear();
		m_modelPop.getM_lineArr3().clear();
		m_modelPop.getM_lineArr4().clear();
		m_modelPop.getM_lineArr5().clear();
		m_modelPop.getM_lineArr6().clear();
		popup();

	}

	/**
	 * This method it to sort the scores displayed on the screen just sorting steps
	 * number and ignore words after time
	 * 
	 * @param arr ArrayList to be sorted out
	 * @return nothing
	 */
	public void sortScoreDisplayed(ArrayList<String> arr) {

		Collections.sort(arr, new Comparator<String>() {
			public int compare(String a, String b) {
				return filterInt(a) - filterInt(b);
			}

			int filterInt(String s) {
				String number1 = s.split("Time:")[0];
				String number = number1.replaceAll("\\D", "");
				return number.isEmpty() ? 0 : Integer.parseInt(number);
			}
		});
	}
}
