package repo.sorters.comparators;

import Entities.Contract;

import java.util.Comparator;

public class FinishingDateComparator implements Comparator<Contract> {
	@Override
	public int compare(Contract o1, Contract o2) {
		if(o1.getFinishingDate().isAfter(o2.getFinishingDate())){
			return 1;
		}
		else if(o1.getFinishingDate().isBefore(o2.getFinishingDate())){
			return -1;
		}
		else {
			return 0;
		}
	}
}
