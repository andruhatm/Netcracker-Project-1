package repo.validators.impl.base;

import Entities.Contract;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements Validator<Contract> {
	@Override
	public Message validate(Contract o) {
		if(o.getClient().getAge() < 18){

			return new Message("Illegal Client Age",ValidateStatus.WARNING);
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
