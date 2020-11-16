import org.junit.Assert;
import org.junit.Test;
import repo.ContractRepo;
import repo.builders.CsvContractRepoBuilder;

public class CsvLoaderTest {

	/**
	 * Contract repo field for tests
	 */
	public ContractRepo repo = new ContractRepo();

	/**
	 * sets up test environment
	 */
	@Test
	public void IdentifyingClient(){

		repo = new CsvContractRepoBuilder().build("src/test/java/testCSV.csv");
		Assert.assertEquals(repo.getRepo().length,5);

		Assert.assertEquals(repo.getRepo()[0].getClient().hashCode(),repo.getRepo()[1].getClient().hashCode());
	}

}
