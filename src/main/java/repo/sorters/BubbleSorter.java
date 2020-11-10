package repo.sorters;

import java.util.Comparator;

public class BubbleSorter<T> implements Sorter<T>{
	@Override
	public T[] sort(T[] elements, Comparator<T> comparator) {
		for(int i=0;i<elements.length;i++) {
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
