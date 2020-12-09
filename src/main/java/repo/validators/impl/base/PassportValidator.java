package repo.validators.impl.base;

import Entities.Contract;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class PassportValidator implements Validator<Contract> {
	@Override
	public Message validate(Contract o) {

		if (String.valueOf(o.getClient().getPassportId()).length() != 6 ||
						String.valueOf(o.getClient().getPassportSeries()).length() != 4
		) {
			return new Message("Illegal Passport id or Series", ValidateStatus.WARNING);
		}
		else {
			return new Message(ValidateStatus.OK);
		}
	}

	@Override
	public Class<?> getAppliableClass() {
		return Contract.class;
	}
}
