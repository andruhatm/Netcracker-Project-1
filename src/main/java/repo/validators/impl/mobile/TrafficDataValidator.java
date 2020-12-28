package repo.validators.impl.mobile;

import entities.Contract;
import entities.contracts.MobileContract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class TrafficDataValidator implements Validator<Contract> {
	static final Logger logger = Logger.getLogger(TrafficDataValidator.class);

	/**
	 * validates received Contract by Contract's data count
	 * @param o obj to validate
	 * @return bool
	 */
	@Override
	public Message validate(final Contract o) {
		MobileContract contract = (MobileContract) o;
		if (
			contract.getGbCount() < 0
							|| contract.getMinutesCount() < 0
							|| contract.getSmsCount() < 0
		) {
			logger.warn("Illegal data count");
			return new Message("Illegal data count", ValidateStatus.WARNING);
		}
		return new Message(ValidateStatus.OK);
	}

	/**
	 * returns applicable class for validator
	 * @return class
	 */
	@Override
	public Class<?> getAppliableClass() {
		return MobileContract.class;
	}
}
