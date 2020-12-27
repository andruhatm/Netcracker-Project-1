package repo.validators;

/**
 * Entity class to return validation info
 * @author andruha.tm
 * @version 1.0
 */
public class Message {
	/**
	 * validation result msg field
	 */
	private String message;
	/**
	 * validation status result field
	 */
	private ValidateStatus status;

	/**
	 * Constructor for creating new instance of Message
	 * @param status validation status
	 */
	public Message(ValidateStatus status) {
		this.status = status;
	}

	/**
	 * Constructor for creating new instance of Message
	 * @param message validation error msg
	 * @param status validation status
	 */
	public Message(String message, ValidateStatus status) {
		super();
		this.message = message;
		this.status = status;
	}

	/**
	 * to get message field value
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * to set message field
	 * @param message msg to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * to get validation status
	 * @return validation status
	 */
	public ValidateStatus getStatus() {
		return status;
	}

	/**
	 * to set validation status
	 * @param status status to set
	 */
	public void setStatus(ValidateStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message{" +
						"message='" + message + '\'' +
						", status=" + status +
						'}';
	}
}
