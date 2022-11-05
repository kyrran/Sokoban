package sokodan.basic.Info;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * This class is for generating new window
 * @author psyky2
 *
 */
public class Dialog {
	
	/**
	 * This constructor is to pass some vars and pass into this class.
	 * @param dialogTitle the title of this dialog
	 * @param dialogMessage the message you are going t print
	 * @param dialogMessageEffect the effect of the message
	 * @param m_primaryStage which stage it belongs to*/
	public Dialog(String dialogTitle, String dialogMessage, Effect dialogMessageEffect, Stage m_primaryStage) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(m_primaryStage);
		dialog.setResizable(false);
		dialog.setTitle(dialogTitle);

		Text text1 = new Text(dialogMessage);
		text1.setTextAlignment(TextAlignment.CENTER);
		text1.setFont(javafx.scene.text.Font.font(14));

		if (dialogMessageEffect != null) {
			text1.setEffect(dialogMessageEffect);
		}

		VBox dialogVbox = new VBox(20);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.setBackground(Background.EMPTY);
		dialogVbox.getChildren().add(text1);

		Scene dialogScene = new Scene(dialogVbox, 350, 150);
		dialog.setScene(dialogScene);
		dialog.show();
	}
	
}
