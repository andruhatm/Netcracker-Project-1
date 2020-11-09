import Entities.Client;
import Entities.Contract;
import Entities.contracts.InternetContract;
import Entities.contracts.MobileContract;
import Entities.contracts.TVContract;
import Entities.enums.Package;
import Entities.enums.Sex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repo.ContractRepo;

import java.time.LocalDate;

/**
 * Class for Tests
 * @author andruha.tm
 * @version 1.0
 */
public class Tests {

	/**
	 * Contract repo field for tests
	 */
	private final ContractRepo repo = new ContractRepo();

	/**
	 * sets up test environment
	 */
	@Before
	public void setUp(){
		Client client1 = new Client(
						1,
						"client1",
						"client11",
						"client111",
						LocalDate.of(2000,1,10),
						Sex.male,
						1234,
						123456
		);
		Client client2 = new Client(
						2,
						"client2",
						"client22",
						"client222",
						LocalDate.of(2000,1,10),
						Sex.male,
						1234,
						123456
		);
		Client client3 = new Client(
						3,
						"client3",
						"client33",
						"client333",
						LocalDate.of(2000,1,10),
						Sex.male,
						1234,
						123456
		);
		InternetContract internetContract = new InternetContract(
						1,
						LocalDate.of(2020,1,10),
						LocalDate.of(2020,2,10),
						client1,
						400.0
		);
		MobileContract mobileContract = new MobileContract(
						2,
						LocalDate.of(2020,1,10),
						LocalDate.of(2020,2,10),
						client2,
						200,
						50,
						7
		);
		TVContract tvContract = new TVContract(
						3,
						LocalDate.of(2020,1,10),
						LocalDate.of(2020,2,10),
						client3,
						Package.fifth
		);

		repo.add(mobileContract); //2
		repo.add(tvContract);     //3
		repo.add(internetContract); //1
	}

	/**
	 * checks if age of Client is correct
	 */
	@Test
	public void getAgeTest(){
		Client client = new Client(
						1,
						"tester",
						"surname",
						"tester",
						LocalDate.of(2001, 3,31),
						Sex.male,
						123456,
						3245
		);
		Assert.assertEquals(19,client.getAge());
	}

	/**
	 * checks if Contract successfully added to repo
	 */
	@Test
	public void addContractRepoTest(){
		Assert.assertNull(repo.get(10));

		Client client = new Client(
						5,
						"tester",
						"surname",
						"tester",
						LocalDate.of(2001, 3,31),
						Sex.male,
						123456,
						3245
		);

		TVContract tvContract = new TVContract(
						10,
						LocalDate.of(2020,1,10),
						LocalDate.of(2020,2,10),
						client,
						Package.fifth
		);

		repo.add(tvContract);
		Assert.assertNotNull(repo.get(10));
	}

	@Test
	public void expandRepoTest(){
		Assert.assertEquals(20,repo.getLength());
		Client client = new Client(
						7,
						"tester",
						"surname",
						"tester",
						LocalDate.of(2001, 3,31),
						Sex.male,
						123456,
						3245
		);

		for(int i=0;i<25;i++){
			repo.add(new TVContract(
							i,
							LocalDate.of(2020,1,10),
							LocalDate.of(2020,2,10),
							client,
							Package.fifth
			));
		}
		Assert.assertEquals(30,repo.getLength());

	}
	/**
	 * checks if received Contracts return correct id
	 */
	@Test
	public void getContractRepoTest(){
		Assert.assertEquals(1,repo.get(1).getId());
		Assert.assertEquals(2,repo.get(2).getId());
		Assert.assertEquals(3,repo.get(3).getId());
	}

	/**
	 * checks if Contracts delete correctly
	 */
	@Test
	public void deleteFromRepoTest(){
		Client client = new Client(
						1,
						"tester",
						"surname",
						"tester",
						LocalDate.of(2001, 3,31),
						Sex.male,
						123456,
						3245
		);
		TVContract tvContract = new TVContract(
						8,
						LocalDate.of(2020,1,10),
						LocalDate.of(2020,2,10),
						client,
						Package.fifth
		);
		repo.add(tvContract);
		Assert.assertEquals(8,repo.get(8).getId());

		repo.getRepo();

		repo.delete(3);

		Assert.assertNull(repo.get(3));
	}
}
