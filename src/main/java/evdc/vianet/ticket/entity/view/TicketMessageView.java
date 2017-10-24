package evdc.vianet.ticket.entity.view;

import java.sql.Timestamp;

public class TicketMessageView {
	public static final String TABLE_NAME = "view_ticketMessage";

	/**
	 * 消息的显示范围，确保能和客户交流的只有一个团队<br>
	 * Client只有客户和工单受派组能看到<br>
	 * Internal的只有发送该条消息的组能看到<br>
	 * Shared除了客户都能看到，团队之间共享的消息
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
	private String userName;
	private String teamName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

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
}
