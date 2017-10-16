package evdc.vianet.emailticket.task;

import java.sql.Timestamp;

public class EmailTicket {
	public static final String TABLE_NAME = "email_ticket";
	private long id;
	private String title;
	private String client;
	private String status;
	private Timestamp timestamp;
	private String service;

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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "EmailTicket [id=" + id + ", title=" + title + ", client=" + client + ", status=" + status
				+ ", timestamp=" + timestamp + ", service=" + service + "]";
	}

}
