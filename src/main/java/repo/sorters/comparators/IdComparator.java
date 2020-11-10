package repo.sorters.comparators;

import Entities.Contract;

import java.util.Comparator;

public class IdComparator implements Comparator<Contract> {

	@Override
	public int compare(Contract o1, Contract o2) {
		if(o1.getId()>o2.getId()){
			return 1;
		}
		else if(o1.getId()<o2.getId()){
			return -1;
		}
		else {
			return 0;
		}
	}
}
