package repo.injection;

import java.util.Comparator;
import org.apache.log4j.Logger;
import repo.sorters.Sorter;

public class BubbleSorter<T> implements Sorter<T> {
	static final Logger logger = Logger.getLogger(BubbleSorter.class);

	/**
	 * sorts received elements array with comparator
	 * @param elements array of elements
	 * @param comparator comparator to sort by
	 * @return sorted array
	 */
	@Override
	public T[] sort(final T[] elements, final Comparator<T> comparator) {
		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements.length; j++) {
				if (comparator.compare(elements[i], elements[j]) < 0) {
					T c = elements[i];
					elements[i] = elements[j];
					elements[j] = c;
				}
			}
		}
		return elements;
	}
}
