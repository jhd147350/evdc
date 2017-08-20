package evdc.vianet.ticket.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class TicketMessage {
	public static final String TABLE_NAME = "ticket_message";

	/**
	 * 消息的显示范围，确保能和客户交流的只有一个团队<br>
	 * Client只有客户和工单受派组能看到<br>
	 * Internal的只有发送该条消息的组能看到<br>
	 * Shared除了客户都能看到，团队之间共享的消息
	 * 
	 * @author jhd147350
	 *
	 */
	public enum Scope {
		Client, Internal, Shared
	}

	private long id;
	private long ticketId;
	private long userId;
	private long teamId;
	private String message;
	private String attachments;// TODO 先不急于实现
	private Timestamp timestamp;
	private String scope;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "TicketMessage [id=" + id + ", ticketId=" + ticketId + ", userId=" + userId + ", teamId=" + teamId
				+ ", message=" + message + ", attachments=" + attachments + ", timestamp=" + timestamp + ", scope="
				+ scope + "]";
	}
}
