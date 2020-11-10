package repo.sorters.comparators;

import Entities.Contract;

import java.util.Comparator;

public class StartingDateComparator implements Comparator<Contract> {
	@Override
	public int compare(Contract o1, Contract o2) {
		if(o1.getStartingDate().isBefore(o2.getStartingDate())){
			return 1;
		}
		else if(o1.getStartingDate().isAfter(o2.getStartingDate())){
			return -1;
		}
		else {
			return 0;
		}
	}
}
