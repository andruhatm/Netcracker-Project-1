package repo.sorters;

import java.util.Comparator;

public class SelectionSorter<T> implements Sorter<T>{
	@Override
	public T[] sort(T[] array, Comparator<T> comparator) {
		for(int i=0;i<array.length;i++){
			int pos = i;
			T min = array[i];
			for(int j=i+1;j<array.length;j++){
				if(comparator.compare(array[j],min)<0){
					pos=j;
					min = array[j];
				}
			}
			array[pos]=array[i];
			array[i]=min;
		}
		return array;
	}
}
