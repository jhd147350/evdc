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
	private String serviceName;
	private String submitUserName;
	private String submitTeamName;
	private String assignUserName;
	private String assignTeamName;
	private String updateUserName;
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
	
}
