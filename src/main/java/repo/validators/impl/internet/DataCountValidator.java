package repo.validators.impl.internet;

import entities.Contract;
import entities.contracts.InternetContract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class DataCountValidator implements Validator<Contract> {
	static final Logger logger = Logger.getLogger(DataCountValidator.class);

	/**
	 * validates received Contract by Contract's speed argument
	 * @param o obj to validate
	 * @return bool
	 */
	@Override
	public Message validate(final Contract o) {
		InternetContract contract = (InternetContract) o;
		if (contract.getMaxSpeed() < 0) {
			logger.warn("Illegal speed count");
			return new Message("Illegal speed count", ValidateStatus.WARNING);
		} else {
			return new Message(ValidateStatus.OK);
		}
	}

	/**
	 * returns applicable class for validator
	 * @return class
	 */
	@Override
	public Class<?> getAppliableClass() {
		return InternetContract.class;
	}
}
