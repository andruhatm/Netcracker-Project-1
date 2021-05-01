package repo;

import entities.Contract;
import entities.contracts.InternetContract;
import entities.contracts.MobileContract;
import entities.contracts.TVContract;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import lombok.Getter;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import repo.injection.annotations.MyInject;
import repo.sorters.Sorter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Repository class for various Contracts
 * @author andruha.tm
 * @version 1.0
 */
@XmlRootElement(name = "repository")
@XmlType(propOrder = {"repo"})
public final class ContractRepo implements Repo<Contract> {

	static final Logger logger = Logger.getLogger(ContractRepo.class);

	/**
	 * Current Contract Array pointer
	 */
	private int pointer = 0;

	/**
	 * Contract Array field
	 */
	@Getter
	@XmlElement(name = "contract")
	private Contract[] repo;

	/**
	 * Sorter field
	 */
	@MyInject
	private Sorter<Contract> sorter;

	/**
	 * Constructor - creating new instance of Repository
	 */
	public ContractRepo() {
		logger.debug("Initializing Contract array");
		repo = new Contract[20];
		//		sorter = new BubbleSorter<>();
	}

	/**
	 * mthd for expanding array size by creating new with bigger length
	 */
	private void expand() {
		logger.trace("expanding");
		logger.debug("Expanding Array");
		Contract[] nRepo = new Contract[repo.length + 10];
		for (int i = 0; i < repo.length; i++) {
			nRepo[i] = repo[i];
		}
		repo = nRepo;
	}

	/**
	 * mthd for adding new Contract inst to Array of Contracts
	 * @param contract Contract to add
	 */
	@Override
	public void add(@NotNull final Contract contract) {
		//		boolean check = true;
		//
		//		for(int i=0; i<pointer;i++){
		//			if (repo[i].getId() == contract.getId()) {
		//				check = false;
		//			}
		//		}

		if (pointer == repo.length - 1) {
			expand();
		}
		if (checkContractId(contract.getId())) {
			logger.debug("Moving pointer");
			repo[pointer] = contract;
			pointer++;
		}
	}

	/**
	 * mthd for getting Contract instance by recieved Contract id
	 * @param contractId contract id
	 * @return Contract instance
	 */
	@Override
	public Contract get(final int contractId) {
		logger.debug("Choosing Contract instance to return");
		for (int i = 0; i < pointer; i++) {
			if (repo[i].getId() == contractId) {
				return repo[i];
			}
		}
		return null;
	}

	/**
	 * method for getting Contract instance by id
	 * @param id unique id
	 * @return Contract obj
	 */
	public Contract getById(final int id){
		return repo[id];
	}

	/**
	 * mthd deletes Contract instance from Contract Array by given Contract id
	 * @param contractId contract id
	 * @return bool success result
	 */
	@Override
	public boolean delete(final int contractId) {
		if (contractId > repo.length - 1) {
			logger.debug("Received contract id is out of Repo length");
			return false;
		} else {
			for (int i = 0; i < pointer; i++) {
				if (repo[i].getId() == contractId) {
					repo[i] = null;
					pointer--;
					for (int j = i; j < repo.length - 1; j++) {
						repo[j] = repo[j + 1];
					}
					//outputRepo();
				}
			}
		}
		return true;
	}

	/**
	 * mthd to sort repo obj's by custom comparator
	 * @param comparator custom filtering
	 */
	@Override
	public void sortBy(final Comparator<Contract> comparator) {
		repo = sorter.sort(getRepo(), comparator);
		//outputRepo();
	}

	/**
	 * mthd to filter repo with unique condition
	 * @param predicate bool lambda
	 * @return new Repo with filtered obj's
	 */
	@Override
	public ContractRepo searchBy(final Predicate<Contract> predicate) {
		ContractRepo result = new ContractRepo();

		for (int i = 0; i < pointer; i++) {
			if (repo[i] != null) {
				if (predicate.test(repo[i])) {
					result.add(repo[i]);
				}
			}
		}
		return result;
	}

	/**
	 * mthd for getting Array of Contracts length
	 * @return repo length
	 */
	public int getLength() {
		return repo.length;
	}

	/**
	 * mthd for getting Contract Array field
	 * @return Contract[]
	 */
	@Override
	public Contract[] getRepo() {
		Contract[] repository = new Contract[pointer];
		if (pointer >= 0) {
			System.arraycopy(repo, 0, repository, 0, pointer);
		}
		System.out.println("returned repo here");
		System.out.println(Arrays.toString(repository));
		return repository;
	}

	/**
	 * mthd for current Repo state output
	 */
	@Override
	public void outputRepo() {
		for (int i = 0; i < pointer; i++) {
			System.out.println(repo[i].toString());
		}
		System.out.println("--------");
	}

	/**
	 * mthd for logging Contract Type's unique specs
	 */
	public void outputContractRepo() {
		for (int i = 0; i < pointer; i++) {
			if (repo[i].getClass().equals(InternetContract.class)) {
				System.out.println(repo[i].toString());
			} else if (repo[i].getClass().equals(MobileContract.class)) {
				System.out.println(repo[i].toString());
			} else if (repo[i].getClass().equals(TVContract.class)) {
				System.out.println(repo[i].toString());
			}
		}
		System.out.println("--------");
	}

	/**
	 * mthd for checking Contract id uniqueness
	 * @param id Contract id to check
	 * @return true if no contracts in repo with such id, otherwise false
	 */
	public boolean checkContractId(final int id) {
		boolean check = true;

		for (int i = 0; i < pointer; i++) {
			if (repo[i].getId() == id) {
				logger.debug("Contract already exists");
				check = false;
				break;
			}
		}
		return check;
	}

	/**
	 * mthd to retrun pointer of Contract Array
	 * @return pointer
	 */
	public int getPointer() {
		return this.pointer;
	}

}
