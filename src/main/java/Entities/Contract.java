package Entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Entity-class of Contract
 * @author andruha.tm
 * @version 1.0
 */
public class Contract {
	/**
	 * Contract id
	 */
	private int id;
	/**
	 * Contract start date
 	 */
	LocalDate startingDate;
	/**
	 * Contract end date
	 */
	LocalDate finishingDate;
	/**
	 * Client entity associated with Contract
	 */
	Client client;

	/**
	 * Constuctor - creating new instance of Contract
	 * @param id id
	 * @param startingDate Contract starting date
	 * @param finishingDate Contract finishing date
	 * @param client Associated client entity
	 */
	public Contract(int id, LocalDate startingDate, LocalDate finishingDate, Client client) {
		this.id = id;
		this.startingDate = startingDate;
		this.finishingDate = finishingDate;
		this.client = client;
	}

	/**
	 * mthd getting value of {@link Contract#id}
	 * @return Contract id
	 */
	public int getId() {
		return id;
	}

	/**
	 * mthd for setting value of {@link Contract#id}
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * mthd for getting value of {@link Contract#startingDate}
	 * @return contract start date
	 */
	public LocalDate getStartingDate() {
		return startingDate;
	}

	/**
	 * mthd for setting value of {@link Contract#startingDate}
	 * @param startingDate
	 */
	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	/**
	 * mthd for getting value of {@link Contract#finishingDate}
	 * @return contract finish date
	 */
	public LocalDate getFinishingDate() {
		return finishingDate;
	}

	/**
	 * mthd for setting value of {@link Contract#finishingDate}
	 * @param finishingDate
	 */
	public void setFinishingDate(LocalDate finishingDate) {
		this.finishingDate = finishingDate;
	}

	/**
	 * mthd for getting value of {@link Contract#client}
	 * @return client entity
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * mthd for setting value of {@link Contract#client}
	 * @param client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Contract{" +
						"id=" + id +
						", startingDate=" + startingDate +
						", finishingDate=" + finishingDate +
						", client=" + client.toString() +
						'}';
	}
}
