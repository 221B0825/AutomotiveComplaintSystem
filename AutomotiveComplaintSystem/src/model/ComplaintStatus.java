package model;

public enum ComplaintStatus {
    PENDING_REVIEW("심사대기"),    
    AWAITING_PAYMENT("비용납부대기"),  
    PAYMENT_COMPLETED("비용납부완료"), 
    REVIEW_REJECTED("심사반려"),   
    COMPLETED("처리완료");   
    
	private final String status;

	private ComplaintStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return status;
	}
}
