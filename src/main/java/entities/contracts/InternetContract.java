package entities.contracts;

import entities.Client;
import entities.Contract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Entity-class of Ethernet Contract
 *
 * @author andruha.tm
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class InternetContract extends Contract {

	/**
	 * Max ethernet speed allowed by Contract
	 */
	private double maxSpeed;

	public InternetContract() {
	}

	/**
	 * Constructor - creates instance of Ethernet Contract
	 *
	 * @param id            unique identifier
	 * @param startingDate  Contract starting date
	 * @param finishingDate Contract finishing date
	 * @param client        Client entity associated with Contract
	 * @param maxSpeed      max speed allowed by Contract
	 */
	public InternetContract(
					final int id,
					final LocalDate startingDate,
					final LocalDate finishingDate,
					final Client client,
					final double maxSpeed
	) {
		super(id, startingDate, finishingDate, client);
		this.maxSpeed = maxSpeed;
	}

	public InternetContract(int id, String startingDate, String finishingDate, Client client, int maxSpeed) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(startingDate, formatter);
		LocalDate end = LocalDate.parse(finishingDate, formatter);

		this.setId(id);
		this.setStartingDate(start);
		this.setFinishingDate(end);
		this.setClient(client);
		this.setMaxSpeed(maxSpeed);
	}

	/**
	 * mthd for getting value of {@link InternetContract#maxSpeed}
	 *
	 * @return max speed allowed by Contract
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * mthd for setting value of {@link InternetContract#maxSpeed}
	 *
	 * @param maxSpeed
	 */
	public void setMaxSpeed(final double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * toString mthd
	 *
	 * @return InternetContract obj values
	 */
	@Override
	public String toString() {
		return "InternetContract{" + "maxSpeed=" + maxSpeed + '}';
	}
}
