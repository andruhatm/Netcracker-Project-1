package repo.sorters.comparators;

import entities.Contract;
import java.util.Comparator;

public class StartingDateComparator implements Comparator<Contract> {

	/**
	 * compares to objects
	 * @param o1 first obj
	 * @param o2 second obj
	 * @return 1, 0, -1
	 */
	@Override
	public int compare(final Contract o1, final Contract o2) {
		if (o1.getStartingDate().isBefore(o2.getStartingDate())) {
			return 1;
		} else if (o1.getStartingDate().isAfter(o2.getStartingDate())) {
			return -1;
		} else {
			return 0;
		}
	}
}
