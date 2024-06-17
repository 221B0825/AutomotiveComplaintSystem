package model;

public enum CarStatus {
	REGISTER("REGISTER"), CANCELLATION("CANCELLATION"), DEFAULT("DEFAULT");

	private final String status;

	private CarStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return status;
	}
}
