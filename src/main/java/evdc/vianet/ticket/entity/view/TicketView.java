package evdc.vianet.ticket.entity.view;

public class TicketView {
	public static final String TABLE_NAME = "view_ticket";
	private long id;
	private String source;
	private String title;
	private String severity;
	private String status;
	private String submitDate;
	private String satisfation;
	private String updateDate;
	private long serviceId;
	private String serviceName;
	private long submitUserId;
	private String submitUserName;
	private long submitTeamId;
	private String submitTeamName;
	private long assignUserId;
	private String assignUserName;
	private long assignTeamId;
	private String assignTeamName;
	private long updateUserId;
	private String updateUserName;
	private String resolveDate;
	private long customerId;
	private String customerUserName;
	private long customerTeamId;
	private String customerTeamName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getSatisfation() {
		return satisfation;
	}
	public void setSatisfation(String satisfation) {
		this.satisfation = satisfation;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getSubmitUserName() {
		return submitUserName;
	}
	public void setSubmitUserName(String submitUserName) {
		this.submitUserName = submitUserName;
	}
	public String getSubmitTeamName() {
		return submitTeamName;
	}
	public void setSubmitTeamName(String submitTeamName) {
		this.submitTeamName = submitTeamName;
	}
	public String getAssignUserName() {
		return assignUserName;
	}
	public void setAssignUserName(String assignUserName) {
		this.assignUserName = assignUserName;
	}
	public String getAssignTeamName() {
		return assignTeamName;
	}
	public void setAssignTeamName(String assignTeamName) {
		this.assignTeamName = assignTeamName;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public static String getTableName() {
		return TABLE_NAME;
	}
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public long getSubmitUserId() {
		return submitUserId;
	}
	public void setSubmitUserId(long submitUserId) {
		this.submitUserId = submitUserId;
	}
	public long getSubmitTeamId() {
		return submitTeamId;
	}
	public void setSubmitTeamId(long submitTeamId) {
		this.submitTeamId = submitTeamId;
	}
	public long getAssignUserId() {
		return assignUserId;
	}
	public void setAssignUserId(long assignUserId) {
		this.assignUserId = assignUserId;
	}
	public long getAssignTeamId() {
		return assignTeamId;
	}
	public void setAssignTeamId(long assignTeamId) {
		this.assignTeamId = assignTeamId;
	}
	public long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(long updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getResolveDate() {
		return resolveDate;
	}
	public void setResolveDate(String resolveDate) {
		this.resolveDate = resolveDate;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerUserName() {
		return customerUserName;
	}
	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}
	public long getCustomerTeamId() {
		return customerTeamId;
	}
	public void setCustomerTeamId(long customerTeamId) {
		this.customerTeamId = customerTeamId;
	}
	public String getCustomerTeamName() {
		return customerTeamName;
	}
	public void setCustomerTeamName(String customerTeamName) {
		this.customerTeamName = customerTeamName;
	}
	
}
