package repo.validators.impl.mobile;

import Entities.Contract;
import Entities.contracts.MobileContract;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class TrafficDataValidator implements Validator<Contract> {
	@Override
	public Message validate(Contract o) {
		MobileContract contract = (MobileContract) o;
		if (contract.getGbCount()<0 ||
						contract.getMinutesCount()<0 ||
						contract.getSMSCount()<0
		){
			return new Message("Illegal data count", ValidateStatus.WARNING);
		}
		return new Message(ValidateStatus.OK);
	}

	@Override
	public Class<?> getAppliableClass() {
		return MobileContract.class;
	}
}
