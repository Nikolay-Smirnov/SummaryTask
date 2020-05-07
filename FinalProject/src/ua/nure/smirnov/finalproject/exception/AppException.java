package ua.nure.smirnov.finalproject.exception;

public class AppException extends Throwable {

	private static final long serialVersionUID = -1794511791244820197L;
	
	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}
