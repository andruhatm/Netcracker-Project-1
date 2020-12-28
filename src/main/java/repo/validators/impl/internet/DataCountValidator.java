package repo.validators.impl.internet;

import Entities.Contract;
import Entities.contracts.InternetContract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;
import repo.validators.impl.base.FioValidator;

public class DataCountValidator implements Validator<Contract> {

	final static Logger logger = Logger.getLogger(DataCountValidator.class);

	@Override
	public Message validate(Contract o) {
		InternetContract contract = (InternetContract) o;
		if(contract.getMaxSpeed()<0){
			logger.warn("Illegal speed count");
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
