package repo.validators.impl.base;

import Entities.Contract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class FioValidator implements Validator<Contract> {

	final static Logger logger = Logger.getLogger(FioValidator.class);

	@Override
	public Message validate(Contract o) {
		if(o.getClient().getName().length()>0
						&& o.getClient().getName().length()<15
						&& o.getClient().getSurname().length()>0
						&& o.getClient().getSurname().length()<15
						&& o.getClient().getPatronymic().length()>0
						&& o.getClient().getPatronymic().length()<15
		){
			return new Message(ValidateStatus.OK);
		}
		else {
			logger.warn("Illegal fio length");
			return new Message("Illegal fio length params",ValidateStatus.WARNING);
		}
	}

	@Override
	public Class<?> getAppliableClass() {
		return Contract.class;
	}
}
