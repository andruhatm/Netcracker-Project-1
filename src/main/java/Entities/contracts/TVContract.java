package Entities.contracts;

import Entities.Client;
import Entities.Contract;
import Entities.enums.Package;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Entity-class for TV Contracts
 * @author andruha.tm
 * @version 1.0
 */
public class TVContract extends Contract {

	/**
	 * TV package field
	 */
	Package aPackage;

	/**
	 * Constructor - creates new instance of TV Contract
	 * @param id unique identifier
	 * @param startingDate starting date of Contract
	 * @param finishingDate finishing date of Contract
	 * @param client Client entity associated with Contract
	 * @param aPackage
	 */
	public TVContract(int id, LocalDate startingDate, LocalDate finishingDate, Client client, Package aPackage) {
		super(id, startingDate, finishingDate, client);
		this.aPackage = aPackage;
	}

	/**
	 * mthd for getting {@link TVContract#aPackage}
	 * @return package of channels of Contract
	 */
	public Package getaPackage() {
		return aPackage;
	}

	/**
	 * mthd for setting package of channels of Contract
	 * @param aPackage
	 */
	public void setaPackage(Package aPackage) {
		this.aPackage = aPackage;
	}
}
