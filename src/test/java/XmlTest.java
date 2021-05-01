import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repo.ContractRepo;
import repo.builders.CsvContractRepoBuilder;
import repo.injection.Injector;
import repo.xml.XMLRepo;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * checks xml marshalling and unmarshalling
 */
public class XmlTest {
	/**
	 * Object of the Injector class
	 */
	private Injector injector = new Injector();
	/**
	 * Object of the Repository class
	 */
	private ContractRepo repo;

	/**
	 * Object of the ReaderCSV class
	 */
	private CsvContractRepoBuilder readerCSV;

	/**
	 * The initial conditions for the tests
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
	 * This method checks saving contracts repository in XML
	 */
	@Test
	public void saveContracts() throws JAXBException, IllegalAccessException {
		XMLRepo xmlRepo = new XMLRepo();

		repo = Injector.inject(new ContractRepo());
		CsvContractRepoBuilder builder = Injector.inject(new CsvContractRepoBuilder());
		repo = builder.build("src/test/java/testCSV.csv");

		xmlRepo.saveContracts(repo, "src/main/resources/repository.xml");
	}

	/**
	 * This method checks getting contracts repository from XML
	 */
	@Test
	public void dumpContracts() throws JAXBException, IllegalAccessException, FileNotFoundException {
		List<Integer> expected = new ArrayList<>();
		List<Integer> actual = new ArrayList<>();

		XMLRepo xmlRepo = new XMLRepo();

		repo = Injector.inject(new ContractRepo());
		CsvContractRepoBuilder builder = Injector.inject(new CsvContractRepoBuilder());
		repo = builder.build("src/test/java/testCSV.csv");

		ContractRepo repository = Injector.inject(new ContractRepo());
		xmlRepo.dump(repository, "src/main/resources/repository.xml");
		System.out.println("repo");
		repository.outputRepo();

		for (int i = 0; i < repo.getPointer(); i++) {
			actual.add(repo.getById(i).getId());
		}

		for (int i = 0; i < repository.getPointer(); i++) {
			expected.add(repository.getById(i).getId());
		}

		Assert.assertEquals(expected, actual);
	}
}
