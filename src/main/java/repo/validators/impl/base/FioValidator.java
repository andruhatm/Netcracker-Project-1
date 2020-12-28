package repo.validators.impl.base;

import entities.Contract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class FioValidator implements Validator<Contract> {
	static final Logger logger = Logger.getLogger(FioValidator.class);

	/**
	 * validates received Contract by Client's fio
	 * @param o obj to validate
	 * @return bool
	 */
	@Override
	public Message validate(final Contract o) {
		if (
			o.getClient().getName().length() > 0
							&& o.getClient().getName().length() < 15
							&& o.getClient().getSurname().length() > 0
							&& o.getClient().getSurname().length() < 15
							&& o.getClient().getPatronymic().length() > 0
							&& o.getClient().getPatronymic().length() < 15
		) {
			return new Message(ValidateStatus.OK);
		} else {
			logger.warn("Illegal fio length");
			return new Message("Illegal fio length params", ValidateStatus.WARNING);
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
