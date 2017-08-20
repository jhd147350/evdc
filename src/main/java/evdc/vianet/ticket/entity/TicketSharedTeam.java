package evdc.vianet.ticket.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 支持人员可以将自己不能处理的工单，共享给其他组
 * 
 * @author jhd147350
 *
 */
public class TicketSharedTeam {
	public static final String TABLE_NAME = "ticket_shared_team";
	private long id;
	private long ticketId;
	private long userId;
	private long sharedTeamId;
	private Timestamp timestamp;

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

	public long getSharedTeamId() {
		return sharedTeamId;
	}

	public void setSharedTeamId(long sharedTeamId) {
		this.sharedTeamId = sharedTeamId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TicketSharedTeam [id=" + id + ", ticketId=" + ticketId + ", userId=" + userId + ", sharedTeamId="
				+ sharedTeamId + ", timestamp=" + timestamp + "]";
	}

}
