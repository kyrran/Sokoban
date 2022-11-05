package sokodan.basic.FactoryPattern;

import javafx.scene.image.Image;

/**This class is for factory pattern in color for box.
 * 
 * @author Kangle Yuan
 *
 */
public class ColorFactory {
	/**getter for color you choose
	 * @param nothing
	 * @return m_currentColor the color you choose
	 */
	public static String getM_currentColor() {
		return m_currentColor;
	}
	/**setter for color you choose
	 * @return nothing
	 * @param m_currentColor the color you choose
	 */
	public static void setM_currentColor(String m_currentColor) {
		ColorFactory.m_currentColor = m_currentColor;
	}
	
	/**
	 * getter for image object
	 * 
	 * @param nothing
	 * @return m_image the object
	 */
	public static Image getM_image() {
		return m_image;
	}

	/**
	 * setter for image object
	 * 
	 * @return nothing
	 * @param m_image the object
	 */
	public static void setM_image(Image m_image) {
		ColorFactory.m_image = m_image;
	}
	private static Image m_image =  new Image("crate.png", 30, 30, false, true);;
	private static String m_currentColor = "default";
	/**This method is to call different class
	 * 
	 * @param color the color you choose
	 * @return the object you want to return
	 */
	public Color getColor(String color) {
		
		ColorFactory.m_currentColor = color; 
		
		if(color.equalsIgnoreCase("red")) {
			setImage();
			return new Red();
		}else if(color.equalsIgnoreCase("beige")) {
			setImage();
			return new Beige();
		}else if(color.equalsIgnoreCase("blue")) {
			setImage();
			return new Blue();
		}
		setImage();
		return new DefaultColor();
	}
	/**This method is to set image for differnet color.
	 * @param nothing
	 * @return nothing
	 */
	public void setImage() {
		
		switch(m_currentColor) {
		case "beige":
			m_image = new Image("/beige_box.png", 30, 30, false, true);
			break;
		case "blue":
			m_image = new Image("/blue_box.png", 30, 30, false, true);
			break;
		case "red":
			m_image = new Image("/red_box.png", 30, 30, false, true);
			break;
		default:
			m_image = new Image("crate.png", 30, 30, false, true);
			break;
		}
	}
}
