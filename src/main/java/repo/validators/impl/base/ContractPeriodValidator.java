package repo.validators.impl.base;

import entities.Contract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class ContractPeriodValidator implements Validator<Contract> {
	static final Logger logger = Logger.getLogger(ContractPeriodValidator.class);

	/**
	 * validates received Contract by time period
	 * @param o obj to validate
	 * @return bool
	 */
	@Override
	public Message validate(final Contract o) {
		if (o.getStartingDate().isBefore(o.getFinishingDate())) {
			return new Message(null, ValidateStatus.OK);
		} else {
			logger.warn("Illegal contract time");
			return new Message("Illegal contract time", ValidateStatus.WARNING);
		}
	}

	/**
	 * returns applicable class for validator
	 * @return class
	 */
	@Override
	public Class<?> getAppliableClass() {
		return Contract.class;
	}
}
