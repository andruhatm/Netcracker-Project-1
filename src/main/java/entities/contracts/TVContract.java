package entities.contracts;

import entities.Client;
import entities.Contract;
import entities.enums.Package;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Entity-class for TV Contracts
 *
 * @author andruha.tm
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TVContract extends Contract {
	/**
	 * TV package field
	 */
	private Package aPackage;

	public TVContract() {
	}

	/**
	 * Constructor - creates new instance of TV Contract
	 *
	 * @param id            unique identifier
	 * @param startingDate  starting date of Contract
	 * @param finishingDate finishing date of Contract
	 * @param client        Client entity associated with Contract
	 * @param aPackage
	 */
	public TVContract(
					final int id,
					final LocalDate startingDate,
					final LocalDate finishingDate,
					final Client client,
					final Package aPackage
	) {
		super(id, startingDate, finishingDate, client);
		this.aPackage = aPackage;
	}

	public TVContract(int id, String startingDate, String finishingDate, Client client, String aPackage) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(startingDate, formatter);
		LocalDate end = LocalDate.parse(finishingDate, formatter);

		this.setId(id);
		this.setStartingDate(start);
		this.setFinishingDate(end);
		this.setClient(client);
		this.setaPackage(Package.valueOf(aPackage));
	}

	/**
	 * mthd for getting {@link TVContract#aPackage}
	 *
	 * @return package of channels of Contract
	 */
	public Package getaPackage() {
		return aPackage;
	}

	/**
	 * mthd for setting package of channels of Contract
	 *
	 * @param aPackage
	 */
	public void setaPackage(final Package aPackage) {
		this.aPackage = aPackage;
	}

	/**
	 * toString mthd
	 *
	 * @return TVContract obj values
	 */
	@Override
	public String toString() {
		return "TVContract{" + "aPackage=" + aPackage + '}';
	}
}
