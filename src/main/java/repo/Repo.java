package repo;

import Entities.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.function.Predicate;

public interface Repo<T> {

	public void add(@NotNull T contract);

	public T get(int contractId);

	public boolean delete(int contractId);

	public void sortBy(Comparator<T> comparator);

	public Repo<T> searchBy(Predicate<Contract> predicate);

	public void outputRepo();

	public Contract[] getRepo();

}
