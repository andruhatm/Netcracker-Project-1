package repo.validators.impl.base;

import entities.Contract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class PassportValidator implements Validator<Contract> {
	static final Logger logger = Logger.getLogger(PassportValidator.class);

	/**
	 * validates received Contract by Client's passport
	 * @param o obj to validate
	 * @return bool
	 */
	@Override
	public Message validate(final Contract o) {
		if (
			String.valueOf(o.getClient().getPassportId()).length() != 6 ||
			String.valueOf(o.getClient().getPassportSeries()).length() != 4
		) {
			logger.warn("Illegal Passport id or Series");
			return new Message("Illegal Passport id or Series", ValidateStatus.WARNING);
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
