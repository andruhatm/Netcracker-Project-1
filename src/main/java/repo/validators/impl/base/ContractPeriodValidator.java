package repo.validators.impl.base;

import Entities.Contract;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class ContractPeriodValidator implements Validator<Contract> {
	@Override
	public Message validate(Contract o) {
		if (o.getStartingDate().isBefore(o.getFinishingDate())){
			return new Message(null, ValidateStatus.OK);
		}
		else {
			return new Message("Illegal contract time",ValidateStatus.WARNING);
		}
	}

	@Override
	public Class<?> getAppliableClass() {
		return Contract.class;
	}
}
