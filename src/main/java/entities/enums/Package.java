package entities.enums;

/**
 * Enum represents TV packages available
 * @author andruha.tm
 * @version 1.0
 */
public enum Package {
	first(1),
	second(2),
	third(3),
	fourth(4),
	fifth(5),
	sixth(6),
	seventh(7);

	private int id;

	Package(final int i) {
		this.id = i;
	}

	public int getId() {
		return id;
	}
}
