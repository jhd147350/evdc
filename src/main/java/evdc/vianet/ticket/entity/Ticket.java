package evdc.vianet.ticket.entity;

import java.sql.Timestamp;

public class Ticket {
	public static final String TABLE_NAME="ticket";
	/**
	 * Client 表示来源是客户<br>
	 * Support 是支持人员自己创建的<br>
	 * Email 表示是根据指定邮箱中的指定客户邮件自动生成的<br>
	 * @author jhd147350
	 */
	public enum Source {
		Client, Support, Email
	}

	/**
	 * 4个严重等级<br>
	 * Se1, Se2, Se3, Se4
	 * 
	 * @author jhd147350
	 *
	 */
	public enum Severity {
		Sev1, Sev2, Sev3, Sev4
	}

	/**
	 * 4个工单状态<br>
	 * Assigned, In_Process, Resolved, Close;
	 * @author jhd147350
	 *
	 */
	public enum Status {
		New, In_Process, Resolved, Closed;
	}

	private long id;
	private String source;
	private String title;
	private String description;
	private String severity;
	private String status;
	private long serviceId;
	private Timestamp submitDate;
	private long submitUserId;
	private long submitTeamId;
	private long assignUserId;
	private long assignTeamId;
	private long satisfation;
	private long updateUserId;
	private Timestamp updateDate;
	
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
	public long getServiceId() {
		return serviceId;
	}
	public void setService(long serviceId) {
		this.serviceId = serviceId;
	}
	public Timestamp getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Timestamp submitDate) {
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
		return assignTeamId;
	}
	public void setAssignTeamId(long assignTeamId) {
		assignTeamId = assignTeamId;
	}
	public long getSatisfation() {
		return satisfation;
	}
	public void setSatisfation(long satisfation) {
		this.satisfation = satisfation;
	}
	
	
	public long getSubmitTeamId() {
		return submitTeamId;
	}
	public void setSubmitTeamId(long submitTeamId) {
		this.submitTeamId = submitTeamId;
	}
	public long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(long updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", source=" + source + ", title=" + title + ", description=" + description
				+ ", severity=" + severity + ", status=" + status + ", service=" + serviceId + ", submitDate="
				+ submitDate + ", submitUserId=" + submitUserId + ", assignUserId=" + assignUserId + ", assignTeamId="
				+ assignTeamId + ", satisfation=" + satisfation + "updateUserId=" + updateUserId + "updateDate=" + updateDate+"]";
	}
	

}
