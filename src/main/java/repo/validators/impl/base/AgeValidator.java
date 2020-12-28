package repo.validators.impl.base;

import entities.Contract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class AgeValidator implements Validator<Contract> {
	static final Logger logger = Logger.getLogger(AgeValidator.class);

	/**
	 * validates received Contract by age
	 * @param o obj to validate
	 * @return bool
	 */
	@Override
	public Message validate(final Contract o) {
		if (o.getClient().getAge() < 18) {
			logger.warn("Illegal Cilent age");
			return new Message("Illegal Client Age", ValidateStatus.WARNING);
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
		return Contract.class;
	}
}
