package evdc.vianet.ticket.entity;

import java.sql.Date;

public class Ticket {
	public static final String TABLE_NAME="ticket";
	/**
	 * 工单来源
	 * 
	 * @author jhd147350
	 *
	 */
	public enum Source {
		Client, Support, Email
	}

	/**
	 * 严重等级
	 * 
	 * @author jhd147350
	 *
	 */
	public enum Severity {
		Sev1, Sev2, Sev3, Sev4
	}

	/**
	 * 工单状态
	 * 
	 * @author jhd147350
	 *
	 */
	public enum Status {
		Assigned, In_Process, Resolved, Close;
	}

	private long id;
	private String source;
	private String title;
	private String description;
	private String severity;
	private String status;
	private String service;
	private Date submitDate;
	private long submitUserId;
	private long assignUserId;
	private long AssignTeamId;
	private int satisfation;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public long getSubmitUserId() {
		return submitUserId;
	}
	public void setSubmitUserId(long submitUserId) {
		this.submitUserId = submitUserId;
	}
	public long getAssignUserId() {
		return assignUserId;
	}
	public void setAssignUserId(long assignUserId) {
		this.assignUserId = assignUserId;
	}
	public long getAssignTeamId() {
		return AssignTeamId;
	}
	public void setAssignTeamId(long assignTeamId) {
		AssignTeamId = assignTeamId;
	}
	public int getSatisfation() {
		return satisfation;
	}
	public void setSatisfation(int satisfation) {
		this.satisfation = satisfation;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", source=" + source + ", title=" + title + ", description=" + description
				+ ", severity=" + severity + ", status=" + status + ", service=" + service + ", submitDate="
				+ submitDate + ", submitUserId=" + submitUserId + ", assignUserId=" + assignUserId + ", AssignTeamId="
				+ AssignTeamId + ", satisfation=" + satisfation + "]";
	}
	

}
