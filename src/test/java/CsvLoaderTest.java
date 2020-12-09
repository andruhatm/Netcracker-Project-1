import Entities.Contract;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repo.ContractRepo;
import repo.builders.CsvContractRepoBuilder;
import repo.validators.Message;
import repo.validators.Validator;
import repo.validators.impl.base.AgeValidator;
import repo.validators.impl.base.ContractPeriodValidator;
import repo.validators.impl.base.FioValidator;
import repo.validators.impl.base.PassportValidator;
import repo.validators.impl.internet.DataCountValidator;
import repo.validators.impl.mobile.TrafficDataValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvLoaderTest {

	/**
	 * Contract repo field for tests
	 */
	public ContractRepo repo = new ContractRepo();

	/**
	 * validators field
	 */
	private static final List<Validator<Contract>> validators = new ArrayList<>();

	@Before
	public void setUp() {
		repo = new CsvContractRepoBuilder().build("src/test/java/testCSV.csv");
		repo.outputRepo();
	}

	/**
	 * tests identifying client for many contracts
	 */
	@Test
	public void IdentifyingClient(){

		Assert.assertEquals(repo.getRepo().length,4);

		Assert.assertEquals(repo.getRepo()[0].getClient().hashCode(),repo.getRepo()[1].getClient().hashCode());
	}

	/**
	 * test of contract validation
	 */
	@Test
	public void ValidationTest(){
		Contract contract = repo.getRepo()[2];
		contract.getClient().setDateOfBirth(LocalDate.of(1998,2,10));

		CsvContractRepoBuilder builder = new CsvContractRepoBuilder();
		Assert.assertTrue(builder.doValidate(contract));
	}
}
