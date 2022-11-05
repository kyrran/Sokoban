package sokodan.basic.FactoryPattern;

import sokodan.LevelStatusNow.CurrentStatus;
/**This class is for crate is red
 * 
 * @author Kangle Yuan
 *
 */
public class Red implements Color {

	/**
	 * This method is to set image and generate message
	 * 
	 * @param nothing
	 * @return nothing
	 */
	@Override
	public void paint() {
		if (CurrentStatus.isDebugActive()) {
			System.out.println("Changing to red.");
		}	}

}
