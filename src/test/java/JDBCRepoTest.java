import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repo.ContractRepo;
import repo.builders.CsvContractRepoBuilder;
import repo.db.JDBCWorker;
import repo.injection.Injector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class checks the operation of methods of the JDBCRepo class
 *
 * @author andruha.tm
 * @version 1.0
 */
public class JDBCRepoTest {

	/**
	 * Object of Injector class
	 */
	private Injector injector = new Injector();

	/**
	 * Object of Repository class
	 */
	private ContractRepo repo;

	/**
	 * Object of ReaderCSV class
	 */
	private CsvContractRepoBuilder readerCSV;

	/**
	 * initial tests set up
	 */
	@Before
	public void setUp() {
		Flyway flyway = Flyway.configure()
						.dataSource("jdbc:postgresql://localhost:5432/contracts", "postgres", "123")
						.locations("db.migration")
						.load();
		flyway.migrate();
	}

	/**
	 * checks if contracts have been saved to repo
	 */
	@Test
	public void saveContracts() throws SQLException, IllegalAccessException {
		JDBCWorker jdbcRepo = new JDBCWorker();
		jdbcRepo.dbInit();
		repo = Injector.inject(new ContractRepo());
		CsvContractRepoBuilder builder = Injector.inject(new CsvContractRepoBuilder());
		repo = builder.build("src/test/java/testCSV.csv");
		jdbcRepo.saveContracts(repo);
	}

	/**
	 * checks if contracts from db is equal local to local dump
	 */
	@Test
	public void dump() throws SQLException, IllegalAccessException {

		List<Integer> expected = new ArrayList<>();
		List<Integer> actual = new ArrayList<>();

		JDBCWorker jdbcRepo = new JDBCWorker();

		repo = Injector.inject(new ContractRepo());
		CsvContractRepoBuilder builder = Injector.inject(new CsvContractRepoBuilder());
		repo = builder.build("src/test/java/testCSV.csv");

		ContractRepo repository = Injector.inject(new ContractRepo());
		jdbcRepo.dump(repository);

		for (int i = 0; i < repo.getPointer(); i++) {
			actual.add(repo.getById(i).getId());
		}

		for (int i = 0; i < repository.getPointer(); i++) {
			expected.add(repository.getById(i).getId());
		}

		Assert.assertEquals(expected, actual);
	}
}
