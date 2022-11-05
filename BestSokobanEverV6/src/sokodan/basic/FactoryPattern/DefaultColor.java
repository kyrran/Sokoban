package sokodan.basic.FactoryPattern;

import sokodan.LevelStatusNow.CurrentStatus;
/**This class is for crate is yellow, the default one
 * 
 * @author Kangle Yuan
 *
 */
public class DefaultColor implements Color {
	
	/**This method is to set image and generate message
	 * @param nothing
	 * @return nothing
	 */
	@Override
	public void paint() {
		
		if (CurrentStatus.isDebugActive()) {
			System.out.println("Changing to yellow.");
		}
	}
}
