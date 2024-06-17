package view;

public enum BasicMessage {
	LoginSuccess("LOGIN SUCCESS"), LoginFail("LOGIN FAIL"), JoinSuccess("JOIN SUCCESS"), JoinFail("JOIN FAIL"),
	Logout("LOGOUT"), Exit("EXIT SYSTEM");

	private final String message;

	private BasicMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
