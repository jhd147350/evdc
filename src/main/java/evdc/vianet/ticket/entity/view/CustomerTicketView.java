package evdc.vianet.ticket.entity.view;

public class CustomerTicketView {
	public static final String TABLE_NAME = "view_customerTicket";
	private long id;
	private String title;
	private String severity;
	private String status;
	private String submitDate;
	private String updateDate;
	private long serviceId;
	private String serviceName;
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
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
	public static String getTableName() {
		return TABLE_NAME;
	}
	
	
}
