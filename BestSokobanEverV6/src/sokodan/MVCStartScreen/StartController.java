package sokodan.MVCStartScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sokodan.Main;


/**
 * This class is start screen controller. 
 * set username, wall color and to enter the main game 
 * to call main controller
 * 
 * @author Kangle Yuan
 */
public class StartController implements Initializable {
	/**getter of model
	 * @param nothing
	 * @return m_modelStart model object
	 */
	public ModelStartScreen getM_modelStart() {
		return m_modelStart;
	}
	/**setter of model
	 * @return nothing
	 * @param m_modelStart model object
	 */
	public void setM_modelStart(ModelStartScreen m_modelStart) {
		this.m_modelStart = m_modelStart;
	}
	/**getter of view
	 * @param nothing
	 * @return m_main view object
	 */
	public Main getMain() {
		return m_main;
	}
	/**setter of view
	 *@return nothing
	 * @param m_main view object
	 */
	public void setMain(Main main) {
		this.m_main = main;
	}

	/*-----declare all id in here------*/
	@FXML
	private ImageView imageBackgroud;
	@FXML
	private Button playButton;
	@FXML
	public ComboBox<String> combobox;
	@FXML
	private Label myLabel;
	@FXML
	private Label myuserName;
	@FXML
	private TextField userText;

	private ModelStartScreen m_modelStart;
	private Main m_main;
	/**
	 * This method is for switching from start screen to main screen
	 * 
	 * @param event once press enter the game button, load main screen and close the
	 *              current page
	 * @throws IOException 
	 */
	@FXML
	public void play(ActionEvent event) throws IOException {
		m_main.startMainGame();
	}

	/**
	 * This method is to initialize combobox.
	 * 
	 * @param location  This is the first parameter.
	 * @param resources This is the second parameter
	 * @return nothing
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		m_modelStart = new ModelStartScreen("BLACK","Default User",FXCollections.observableArrayList("GRAY", "YELLOW",
			 "BROWN", "BLACK"));
		Image image = new Image("startscreen.png");
		imageBackgroud.setImage(image);
		combobox.setItems(m_modelStart.getM_list());
	}

	/**
	 * This method is to show the value you selected in combobox and get the
	 * selected value.
	 * 
	 * @param event
	 * @return nothing
	 */
	@FXML
	public void getColor(ActionEvent event) {
		myLabel.setText(combobox.getValue());
		m_modelStart.setM_wallcolor(myLabel.getText());
	}

	/**
	 * This method is to show the username you entered in textfield
	 * 
	 * @param event
	 * @return nothing
	 */
	@FXML
	public void getUserName(ActionEvent event) {
		String temp = userText.getText();
		if (temp.isBlank()) {
			myuserName.setText("UserIsNull");
			m_modelStart.setM_username("UserIsNull");
		} else {
			myuserName.setText(temp);
			m_modelStart.setM_username(temp);
		}
	}

}
