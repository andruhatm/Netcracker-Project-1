package repo;

import Entities.Contract;

/**
 * Repository class for various Contracts
 * @author andruha.tm
 * @version 1.0
 */
public final class ContractRepo {

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
	 * @param id
	 * @return bool success result
	 */
	public boolean add(Contract contract, int id){
		if(repo.length<id){
			while (repo.length<id){
				expand();
			}
		}
		repo[id]=contract;
		return true;
	}

	/**
	 * mthd for getting Contract instance by recieved #id
	 * @param id
	 * @return Contract instance
	 */
	public Contract get(int id){
		if(repo.length>id){
			return repo[id];
		}
		return null;
	}

	/**
	 * mthd deletes Contract instance from Contract Array
	 * @param id
	 * @return bool success result
	 */
	public boolean delete(int id){
		if(repo.length<id){
			return false;
		}else {
			repo[id]=null;
			return true;
		}
	}

	/**
	 * mthd for getting Array of Contracts length
	 * @return repo length
	 */
	public int getLength(){
		return repo.length;
	}
}
