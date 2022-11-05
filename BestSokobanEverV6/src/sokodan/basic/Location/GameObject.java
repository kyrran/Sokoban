package sokodan.basic.Location;

/**
 * This enum is to define what gameobject is
 * 
 * @author Kangle Yuan-modified
 *
 */
public enum GameObject {
	WALL('W'), 
	FLOOR(' '), 
	CRATE('C'), 
	DIAMOND('D'), 
	KEEPER('S'), 
	CRATE_ON_DIAMOND('O'), 
	DEBUG_OBJECT('='),
	/** new object */
	KEEPER_ON_DIAMOND('P');

	private final char SYMBOL;

	GameObject(final char SYMBOL) {
		this.SYMBOL = SYMBOL;
	}

	public static GameObject fromChar(char c) {
		for (GameObject t : GameObject.values()) {
			if (Character.toUpperCase(c) == t.SYMBOL) {
				return t;
			}
		}

		return WALL;
	}

	public String getStringSymbol() {
		return String.valueOf(SYMBOL);
	}

	public char getCharSymbol() {
		return SYMBOL;
	}

	/**
	 * package sample;
	 * 
	 * public enum GameObject { WALL('W'), FLOOR(' '), CRATE('C'), DIAMOND('D'),
	 * KEEPER('S'), CRATE_ON_DIAMOND('O'), DEBUG_OBJECT('=');
	 * 
	 * private final char symbol;
	 * 
	 * GameObject(final char symbol) { this.symbol = symbol; }
	 * 
	 * public static GameObject fromChar(char c) { for (GameObject t :
	 * GameObject.values()) { if (Character.toUpperCase(c) == t.symbol) { return t;
	 * } }
	 * 
	 * return WALL; }
	 * 
	 * public String getStringSymbol() { return String.valueOf(symbol); }
	 * 
	 * public char getCharSymbol() { return symbol; } }
	 */
}