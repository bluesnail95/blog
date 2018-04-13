package gdut.ff.exception;

public class LoginException extends Exception{
	
	private static final Long serialVersionUID  = 1L;
	
	private String message;

	public LoginException(String message) {
		this.message = message;
	}
	
	
}
