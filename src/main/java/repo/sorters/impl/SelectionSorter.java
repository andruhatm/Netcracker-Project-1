package repo.sorters.impl;

import java.util.Comparator;
import org.apache.log4j.Logger;
import repo.sorters.Sorter;

public class SelectionSorter<T> implements Sorter<T> {
	static final Logger logger = Logger.getLogger(SelectionSorter.class);

	/**
	 * sorts received elements array with comparator
	 * @param array array of elements
	 * @param comparator comparator to sort by
	 * @return sorted array
	 */
	@Override
	public T[] sort(final T[] array, final Comparator<T> comparator) {
		for (int i = 0; i < array.length; i++) {
			int pos = i;
			T min = array[i];
			for (int j = i + 1; j < array.length; j++) {
				if (comparator.compare(array[j], min) < 0) {
					pos = j;
					min = array[j];
				}
			}
			array[pos] = array[i];
			array[i] = min;
		}
		return array;
	}
}
