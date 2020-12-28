package repo.validators.impl.mobile;

import Entities.Contract;
import Entities.contracts.MobileContract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;
import repo.validators.impl.base.FioValidator;

public class TrafficDataValidator implements Validator<Contract> {

	final static Logger logger = Logger.getLogger(TrafficDataValidator.class);

	@Override
	public Message validate(Contract o) {
		MobileContract contract = (MobileContract) o;
		if (contract.getGbCount()<0 ||
						contract.getMinutesCount()<0 ||
						contract.getSMSCount()<0
		){
			logger.warn("Illegal data count");
			return new Message("Illegal data count", ValidateStatus.WARNING);
		}
		return new Message(ValidateStatus.OK);
	}

	@Override
	public Class<?> getAppliableClass() {
		return MobileContract.class;
	}
}
