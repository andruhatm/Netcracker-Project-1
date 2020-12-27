package repo.validators.impl.internet;

import Entities.Contract;
import Entities.contracts.InternetContract;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class DataCountValidator implements Validator<Contract> {
	@Override
	public Message validate(Contract o) {
		InternetContract contract = (InternetContract) o;
		if(contract.getMaxSpeed()<0){
			return new Message("Illegal speed count", ValidateStatus.WARNING);
		}
		else {
			return new Message(ValidateStatus.OK);
		}
	}

	@Override
	public Class<?> getAppliableClass() {
		return InternetContract.class;
	}
}
