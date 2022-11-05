package sokodan.basic.Location.IteratorPattern;

/**
 * This interface is used as container in iterator pattern.
 * 
 * @author Kangle Yuan
 *
 * @param <GameObject> generic framework
 */
public interface Container<GameObject> {
	/**default method under this interface
	 * @param nothing
	 * @return get the iterator
	 */
	public Iterator<GameObject> getIterator();
}
