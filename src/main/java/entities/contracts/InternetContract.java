package entities.contracts;

import entities.Client;
import entities.Contract;
import java.time.LocalDate;

/**
 * Entity-class of Ethernet Contract
 * @author andruha.tm
 * @version 1.0
 */
public class InternetContract extends Contract {
	/**
	 * Max ethernet speed allowed by Contract
	 */
	private double maxSpeed;

	/**
	 * Constructor - creates instance of Ethernet Contract
	 * @param id unique identifier
	 * @param startingDate Contract starting date
	 * @param finishingDate Contract finishing date
	 * @param client Client entity associated with Contract
	 * @param maxSpeed max speed allowed by Contract
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

	/**
	 * mthd for getting value of {@link InternetContract#maxSpeed}
	 * @return max speed allowed by Contract
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * mthd for setting value of {@link InternetContract#maxSpeed}
	 * @param maxSpeed
	 */
	public void setMaxSpeed(final double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * toString mthd
	 * @return InternetContract obj values
	 */
	@Override
	public String toString() {
		return "InternetContract{" + "maxSpeed=" + maxSpeed + '}';
	}
}
