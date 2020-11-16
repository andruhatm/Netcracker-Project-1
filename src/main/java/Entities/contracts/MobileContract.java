package Entities.contracts;

import Entities.Client;
import Entities.Contract;

import java.sql.Date;
import java.time.LocalDate;

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
	private int SMSCount;
	/**
	 * Count of Ethernet Gb's allowed by Contract
	 */
	private float GbCount;

	/**
	 * Constructor - creates new instance of Mobil Ethernet Contract
	 * @param id unique identifier
	 * @param startingDate starting date of contract
	 * @param finishingDate finishing date of contract
	 * @param client client's entity associated with Contract
	 * @param minutesCount minutes count allowed by Contract
	 * @param SMSCount SMS's count allowed by Contract
	 * @param gbCount Gb's count allowed by Contract
	 */
	public MobileContract(int id, LocalDate startingDate, LocalDate finishingDate, Client client, int minutesCount, int SMSCount, float gbCount) {
		super(id, startingDate, finishingDate, client);
		this.minutesCount = minutesCount;
		this.SMSCount = SMSCount;
		GbCount = gbCount;
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
	public void setMinutesCount(int minutesCount) {
		this.minutesCount = minutesCount;
	}

	/**
	 * mthd for getting value of {@link MobileContract#SMSCount}
	 * @return Contract Sms's count
	 */
	public int getSMSCount() {
		return SMSCount;
	}

	/**
	 * mthd for setting value of {@link MobileContract#SMSCount}
	 * @param SMSCount
	 */
	public void setSMSCount(int SMSCount) {
		this.SMSCount = SMSCount;
	}

	/**
	 * mthd for getting value of {@link MobileContract#GbCount}
	 * @return Contract Gb's count
	 */
	public float getGbCount() {
		return GbCount;
	}

	/**
	 * mthd for setting value of {@link MobileContract#GbCount}
	 * @param gbCount
	 */
	public void setGbCount(float gbCount) {
		GbCount = gbCount;
	}

	@Override
	public String toString() {
		return "MobileContract{" +
						"minutesCount=" + minutesCount +
						", SMSCount=" + SMSCount +
						", GbCount=" + GbCount +
						'}';
	}
}
