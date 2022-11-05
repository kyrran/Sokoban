package sokodan.MVCHighScore;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sokodan.MVCStartScreen.ModelStartScreen;

/**
 * This class is highscore fxml controller to display 5 list on the screen and
 * update them
 * 
 * @author Kangle Yuan
 */
public class HighScoreController implements Initializable {

	/**
	 * getter for model object
	 * 
	 * @param nothing
	 * @return m_modelScores model for highscore popup
	 */
	public ModelHighScore getM_modelScores() {
		return m_modelPop;
	}

	/**
	 * setter for model object
	 * 
	 * @return nothing
	 * @param m_modelScores model for highscore popup
	 */
	public void setM_modelScores(ModelHighScore m_modelScores) {
		this.m_modelPop = m_modelScores;
	}

	@FXML
	private Button scorebtn;
	@FXML
	private ListView<String> score1, score2, score3, score4, score5, score6;
	@FXML
	private Label user;
	private ModelHighScore m_modelPop;
	/**
	 * This method is to proceed to next level and close the current pop-up
	 * 
	 * @param event
	 * @return null
	 * @throws IOException 
	 */
	@FXML
	public void levelMessage1(ActionEvent event) throws IOException {
		Stage stage = (Stage) scorebtn.getScene().getWindow();
		stage.close();
	}

	/**
	 * This method is to initialise fxml.
	 * 
	 * @param location
	 * @param resources
	 * @return nothing
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setM_modelScores(new ModelHighScore());
		score1.setItems(ModelHighScore.getListview1());
		score2.setItems(ModelHighScore.getListview2());
		score3.setItems(ModelHighScore.getListview3());
		score4.setItems(ModelHighScore.getListview4());
		score5.setItems(ModelHighScore.getListview5());
		score6.setItems(ModelHighScore.getListview6());

		user.setText(ModelStartScreen.getM_username());
	}
	
	
}

