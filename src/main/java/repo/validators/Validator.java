package repo.validators;

/**
 * Validator interface
 * @param <T> object type to validate
 */
public interface Validator<T> {
	/**
	 * validates an obj
	 * @param o obj to validate
	 * @return {@link Message} obj contains {@link ValidateStatus} and error msg(optional)
	 */
	Message validate(T o);

	/**
	 * to filter validators by Class
	 * @return appliable class for validator
	 */
	Class<?> getAppliableClass();
}
