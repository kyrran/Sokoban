package sokodan.basic.Location.IteratorPattern;

/**
 * This class is to use as iterator interface
 * 
 * @author Kangle Yuan
 *
 */
public interface Iterator<GameObject> {
	/**
	 * Check if it has next.
	 * 
	 * @param nothing
	 * @return boolean number that if it has next
	 */
	public boolean hasNext();

	/**
	 * access next one.
	 * 
	 * @param nothing
	 * @return object of the next one
	 */
	public GameObject next();
}
