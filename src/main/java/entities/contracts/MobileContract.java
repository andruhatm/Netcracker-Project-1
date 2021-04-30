package entities.contracts;

import entities.Client;
import entities.Contract;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Entity-class of Mobile Ethernet Contract
 * @author andruha.tm
 * @version 1.0
 */
public class MobileContract extends Contract {
	/**
	 * Count of minutes allowed by Contract
	 */
	private int minutesCount;
	/**
	 * Count of SMS's allowed by Contract
	 */
	private int smsCount;
	/**
	 * Count of Ethernet Gb's allowed by Contract
	 */
	private float gbCount;

	/**
	 * Constructor - creates new instance of Mobil Ethernet Contract
	 * @param id unique identifier
	 * @param startingDate starting date of contract
	 * @param finishingDate finishing date of contract
	 * @param client client's entity associated with Contract
	 * @param minutesCount minutes count allowed by Contract
	 * @param smsCount SMS's count allowed by Contract
	 * @param gbCount Gb's count allowed by Contract
	 */
	public MobileContract(
		final int id,
		final LocalDate startingDate,
		final LocalDate finishingDate,
		final Client client,
		final int minutesCount,
		final int smsCount,
		final float gbCount
	) {
		super(id, startingDate, finishingDate, client);
		this.minutesCount = minutesCount;
		this.smsCount = smsCount;
		this.gbCount = gbCount;
	}

	public MobileContract(int id, String startingDate, String finishingDate, Client client, int minutesCount, int smsCount, int gbCount) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(startingDate, formatter);
		LocalDate end = LocalDate.parse(finishingDate, formatter);

		this.setGbCount(gbCount);
		this.setMinutesCount(minutesCount);
		this.setClient(client);
		this.setStartingDate(start);
		this.setFinishingDate(end);
		this.setId(id);
		this.setSmsCount(smsCount);
	}

	/**
	 * mthd for getting value of {@link MobileContract#minutesCount}
	 * @return Contract minutes count
	 */
	public int getMinutesCount() {
		return minutesCount;
	}

	/**
	 * mthd for setting value of {@link MobileContract#minutesCount}
	 * @param minutesCount
	 */
	public void setMinutesCount(final int minutesCount) {
		this.minutesCount = minutesCount;
	}

	/**
	 * mthd for getting value of {@link MobileContract#smsCount}
	 * @return Contract Sms's count
	 */
	public int getSmsCount() {
		return smsCount;
	}

	/**
	 * mthd for setting value of {@link MobileContract#smsCount}
	 * @param smsCount
	 */
	public void setSmsCount(final int smsCount) {
		this.smsCount = smsCount;
	}

	/**
	 * mthd for getting value of {@link MobileContract#gbCount}
	 * @return Contract Gb's count
	 */
	public float getGbCount() {
		return gbCount;
	}

	/**
	 * mthd for setting value of {@link MobileContract#gbCount}
	 * @param gbCount
	 */
	public void setGbCount(final float gbCount) {
		this.gbCount = gbCount;
	}

	/**
	 * toString mthd
	 * @return MobileContract obj values
	 */
	@Override
	public String toString() {
		return (
			"MobileContract{" +
			"minutesCount=" +
			minutesCount +
			", SMSCount=" +
			smsCount +
			", GbCount=" +
			gbCount +
			'}'
		);
	}
}
