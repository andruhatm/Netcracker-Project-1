package repo.sorters;

import java.util.Comparator;

public interface Sorter<T> {
	T[] sort(T[] array, Comparator<T> comparator);
}
