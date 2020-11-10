package repo.sorters;

import java.util.Comparator;

public interface Sorter<T>{

	public T[] sort(T [] array, Comparator<T> comparator);
}
