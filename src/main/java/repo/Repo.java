package repo;

import entities.Contract;
import java.util.Comparator;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

public interface Repo<T> {
	void add(@NotNull T contract);

	T get(int contractId);

	boolean delete(int contractId);

	void sortBy(Comparator<T> comparator);

	Repo<T> searchBy(Predicate<Contract> predicate);

	void outputRepo();

	Contract[] getRepo();
}
