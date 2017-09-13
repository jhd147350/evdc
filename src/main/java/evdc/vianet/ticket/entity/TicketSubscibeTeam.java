package evdc.vianet.ticket.entity;

import java.sql.Timestamp;

/**
 * @author jaden
 *
 * 2017年9月12日下午4:28:24
 */
public class TicketSubscibeTeam {
	public static final String TABLE_NAME="ticket_subscibe_team";
	private long id;
	private long ticketId;
	private long userId;
	private long subscibeTeamId;
	private Timestamp timestamp;
	public TicketSubscibeTeam() {
		super();
		// TODO Auto-generated constructor stub
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
	public long getSubscibeTeamId() {
		return subscibeTeamId;
	}
	public void setSubscibeTeamId(long subscibeTeamId) {
		this.subscibeTeamId = subscibeTeamId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
