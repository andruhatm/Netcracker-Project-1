package repo;

import Entities.Contract;

/**
 * Repository class for various Contracts
 * @author andruha.tm
 * @version 1.0
 */
public final class ContractRepo {

	/**
	 * Current Contract Array pointer
	 */
	private int pointer = 0;

	/**
	 * Contract Array field
	 */
	private static Contract[] repo;

	/**
	 * Constructor - creating new instance of Repository
	 */
	public ContractRepo() {
		repo = new Contract[20];
	}

	/**
	 * mthd for expanding array size by creating new with bigger length
	 */
	private void expand(){
		Contract[] nRepo = new Contract[repo.length+10];
		for(int i=0;i<repo.length;i++){
			nRepo[i]=repo[i];
		}
		repo = nRepo;
	}

	/**
	 * mthd for adding new Contract inst to Array of Contracts
	 * @param contract
	 * @return bool success result
	 */
	public void add(Contract contract){
		boolean check = true;

		for(int i=0; i<pointer;i++){
			if (repo[i].getId() == contract.getId()) {
				check = false;
			}
		}
		if(pointer==repo.length-1){
			expand();
		}
		if(check){
			repo[pointer]=contract;
			System.out.println(repo[pointer].getId());
			pointer++;
		}
	}

	/**
	 * mthd for getting Contract instance by recieved Contract id
	 * @param contractId contract id
	 * @return Contract instance
	 */
	public Contract get(int contractId){
		for (int i=0;i<pointer;i++){
			if(repo[i].getId()==contractId)
				return repo[i];
		}
		return null;
	}

	/**
	 * mthd deletes Contract instance from Contract Array by given Contract id
	 * @param contractId contract id
	 * @return bool success result
	 */
	public boolean delete(int contractId){
		if(contractId>repo.length-1){
			return false;
		} else {
			for(int i=0;i<pointer;i++){
				if(repo[i].getId() == contractId){
					repo[i]=null;
					pointer--;
				}
			}
		}
		return true;
	}

	/**
	 * mthd for getting Array of Contracts length
	 * @return repo length
	 */
	public int getLength(){
		return repo.length;
	}
}
