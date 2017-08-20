package evdc.vianet.ticket.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class TicketChangeRecord {
	static final public String TABLE_NAME = "ticket_change_record";
	private long id;
	private long ticketId;
	private long userId;
	private String filed;
	private String oldValue;
	private String newValue;
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

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TicketChangeRecord [id=" + id + ", ticketId=" + ticketId + ", userId=" + userId + ", filed=" + filed
				+ ", oldValue=" + oldValue + ", newValue=" + newValue + ", timestamp=" + timestamp + "]";
	}

}
