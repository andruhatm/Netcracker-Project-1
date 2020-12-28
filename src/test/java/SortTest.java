import entities.Client;
import entities.Contract;
import entities.contracts.InternetContract;
import entities.contracts.MobileContract;
import entities.contracts.TVContract;
import entities.enums.Package;
import entities.enums.Sex;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repo.ContractRepo;
import repo.sorters.BubbleSorter;
import repo.sorters.SelectionSorter;
import repo.sorters.Sorter;
import repo.sorters.comparators.FinishingDateComparator;

/**
 * Class for Sort functions Tests
 * @author andruha.tm
 * @version 1.0
 */
public class SortTest {
	/**
	 * Sorter field for Bubble tests
	 */
	public Sorter<Integer> sorter = new BubbleSorter<>();

	/**
	 * Sorter field for Selection tests
	 */
	public Sorter<Integer> sorter1 = new SelectionSorter<>();

	/**
	 * Contract repo field for tests
	 */
	public ContractRepo repo = new ContractRepo();

	/**
	 * sets up test environment
	 */
	@Before
	public void setUp() {
		Client client1 = new Client(
			1,
			"client1",
			"client11",
			"client111",
			LocalDate.of(2000, 1, 10),
			Sex.male,
			1234,
			123456
		);
		InternetContract internetContract = new InternetContract(
			1,
			LocalDate.of(2020, 1, 10),
			LocalDate.of(2020, 2, 3),
			client1,
			400.0
		);
		MobileContract mobileContract = new MobileContract(
			2,
			LocalDate.of(2020, 1, 11),
			LocalDate.of(2020, 2, 1),
			client1,
			200,
			50,
			7
		);
		TVContract tvContract = new TVContract(
			3,
			LocalDate.of(2020, 1, 12),
			LocalDate.of(2020, 2, 7),
			client1,
			Package.fifth
		);

		repo.add(mobileContract); //2
		repo.add(tvContract); //3
		repo.add(internetContract); //1
	}

	/**
	 * checks if bubble algorithm works well
	 */
	@Test
	public void bubbleTest() {
		Integer[] before = { 6, 5, 4, 3, 2, 1 };
		Integer[] after = { 1, 2, 3, 4, 5, 6 };

		sorter.sort(before, Integer::compareTo);

		Assert.assertArrayEquals(before, after);
	}

	/**
	 * checks if selection algorithm works properly
	 */
	@Test
	public void selectionTest() {
		Integer[] before = { 6, 5, 4, 3, 2, 1 };
		Integer[] after = { 1, 2, 3, 4, 5, 6 };

		sorter1.sort(before, Integer::compareTo);

		Assert.assertArrayEquals(before, after);
	}

	/**
	 * checks if sortBy mthd works well
	 */
	@Test
	public void sortByTest() {
		FinishingDateComparator finishingDateComparator = new FinishingDateComparator();

		ArrayList<LocalDate> before = new ArrayList<>();
		before.add(LocalDate.of(2020, 02, 01));
		before.add(LocalDate.of(2020, 02, 03));
		before.add(LocalDate.of(2020, 02, 07));

		repo.outputRepo();
		repo.sortBy(finishingDateComparator);
		repo.outputRepo();

		ArrayList<LocalDate> after = new ArrayList<>();
		for (Contract contract : repo.getRepo()) {
			after.add(contract.getFinishingDate());
		}

		Assert.assertArrayEquals(before.toArray(), after.toArray());
	}

	/**
	 * checks if searchBy mthd works properly
	 */
	@Test
	public void searchByTest() {
		Predicate<Contract> startingDateGT = x ->
			x.getStartingDate().isAfter(LocalDate.of(2020, 1, 10));

		Predicate<Contract> idPredicate = x -> x.getId() < 2;

		for (int i = 0; i < repo.getPointer(); i++) {
			System.out.println(repo.getRepo()[i]);
		}

		ContractRepo repo1 = repo.searchBy(startingDateGT);

		Assert.assertEquals(2, repo1.getPointer());
	}
}
