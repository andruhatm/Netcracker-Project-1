package repo.validators.impl.tv;

import entities.Contract;
import entities.contracts.MobileContract;
import entities.contracts.TVContract;
import org.apache.log4j.Logger;
import repo.validators.Message;
import repo.validators.ValidateStatus;
import repo.validators.Validator;

public class TvPackageValidator implements Validator<Contract> {
	static final Logger logger = Logger.getLogger(TvPackageValidator.class);

	/**
	 * validates received Contract by Contract's data count
	 * @param o obj to validate
	 * @return bool
	 */
	@Override
	public Message validate(final Contract o) {
		TVContract contract = (TVContract) o;
		if (
			contract.getaPackage().getId() < 0
		) {
			logger.warn("Illegal package state");
			return new Message("Illegal package state", ValidateStatus.WARNING);
		}
		return new Message(ValidateStatus.OK);
	}

	/**
	 * returns applicable class for validator
	 * @return class
	 */
	@Override
	public Class<?> getAppliableClass() {
		return TVContract.class;
	}
}
