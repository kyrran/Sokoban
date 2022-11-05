package sokodan.basic;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import sokodan.LevelStatusNow.CurrentStatus;
import sokodan.MVCMainGameScreen.ModelMainScreen;
import sokodan.MVCStartScreen.ModelStartScreen;
import sokodan.basic.FactoryPattern.ColorFactory;
import sokodan.basic.Location.GameObject;

/**
 * This class is to set game object.
 * 
 * @author Kangle Yuan -modified
 *
 */

public class GraphicObject extends ImageView {
	Image cra = null;

	/**
	 * This method is to check what every object is, and set it as different images.
	 * 
	 * @param obj gameobject
	 * @return nothing
	 */
	public GraphicObject(GameObject obj) {

		switch (obj) {
		case WALL:

			switch (ModelStartScreen.getM_wallcolor()) {
			case "GRAY":
				cra = new Image("/wall_grey.png", 30, 30, false, true);
				break;
			case "YELLOW":
				cra = new Image("/wall_yellow.png", 30, 30, false, true);
				break;
			case "BROWN":
				cra = new Image("/wall_brown.png", 30, 30, false, true);
				break;
			case "BLACK":
				cra = new Image("/wall_black.png", 30, 30, false, true);
				break;
			default:
			}
			break;

		case CRATE:
			String var = ColorFactory.getM_currentColor();
			
			switch(var) {
			case "red":
				cra = ColorFactory.getM_image();
				break;
			case "blue":
				cra = ColorFactory.getM_image();
				break;
			case "beige":
				cra = ColorFactory.getM_image();
				break;
			case "default":
				cra = ColorFactory.getM_image();
				break;
			default:
				cra = ColorFactory.getM_image();
				
			}
			
			break;

		case DIAMOND:
			cra = new Image("/diamond.png", 30, 30, false, true);
			// TODO: fix memory leak.
			if (CurrentStatus.isDebugActive()) {
				FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
				ft.setFromValue(1.0);
				ft.setToValue(0.2);
				ft.setCycleCount(Timeline.INDEFINITE);
				ft.setAutoReverse(true);
				ft.play();
			}

			break;

		case KEEPER:
			KeyCode key = ModelMainScreen.getM_var();

			if (key == null) {
				cra = new Image("/keeper_down.png", 30, 30, false, true);
				break;
			}

			switch (key) {
			case UP:
				cra = new Image("/keeper_up.png", 30, 30, false, true);
				break;
			case DOWN:
				cra = new Image("/keeper_down.png", 30, 30, false, true);
				break;
			case LEFT:
				cra = new Image("/keeper_left.png", 30, 30, false, true);
				break;
			case RIGHT:
				cra = new Image("/keeper_right.png", 30, 30, false, true);
				break;
			default:
				cra = new Image("/keeper_down.png", 30, 30, false, true);
			}
			break;

		case FLOOR:
			cra = new Image("/floor.png", 30, 30, false, true);
			break;

		case CRATE_ON_DIAMOND:
			cra = new Image("/crate_on_diamond.png", 30, 30, false, true);
			break;
		case KEEPER_ON_DIAMOND:
			cra = new Image("/keeper_on_diamond.png", 30, 30, false, true);
			break;

		default:
			String message = "Error in Level constructor. Object not recognized.";
			CurrentStatus.m_logger.severe(message);
			throw new AssertionError(message);
		}
		this.setImage(cra);
	}

	/**
	 * source code is: package sample;
	 * 
	 * import javafx.animation.FadeTransition; import javafx.animation.Timeline;
	 * import javafx.scene.paint.Color; import javafx.scene.paint.Paint; import
	 * javafx.scene.shape.Rectangle; import javafx.util.Duration;
	 * 
	 * class GraphicObject extends Rectangle {
	 * 
	 * GraphicObject(GameObject obj) { Paint color; switch (obj) { case WALL: color
	 * = Color.BLACK; break;
	 * 
	 * case CRATE: color = Color.ORANGE; break;
	 * 
	 * case DIAMOND: color = Color.DEEPSKYBLUE;
	 * 
	 * // TODO: fix memory leak. if (StartMeUp.isDebugActive()) { FadeTransition ft
	 * = new FadeTransition(Duration.millis(1000), this); ft.setFromValue(1.0);
	 * ft.setToValue(0.2); ft.setCycleCount(Timeline.INDEFINITE);
	 * ft.setAutoReverse(true); ft.play(); }
	 * 
	 * break;
	 * 
	 * case KEEPER: color = Color.RED; break;
	 * 
	 * case FLOOR: color = Color.WHITE; break;
	 * 
	 * case CRATE_ON_DIAMOND: color = Color.DARKCYAN; break;
	 * 
	 * default: String message = "Error in Level constructor. Object not
	 * recognized."; StartMeUp.logger.severe(message); throw new
	 * AssertionError(message); }
	 * 
	 * this.setFill(color); this.setHeight(30); this.setWidth(30);
	 * 
	 * if (obj != GameObject.WALL) { this.setArcHeight(50); this.setArcWidth(50); }
	 * 
	 * if (StartMeUp.isDebugActive()) { this.setStroke(Color.RED);
	 * this.setStrokeWidth(0.25); } }
	 * 
	 * }
	 * 
	 */
}
