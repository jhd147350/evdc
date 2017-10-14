package evdc.vianet.emailticket.task;

import java.sql.Timestamp;

public class Email implements Comparable<Email> {
	static public final String TABLE_NAME = "email";
	private long id;
	private String subject;
	private String body;
	private String from;
	private String to;
	private String cc;
	private Timestamp cdate;
	private String note;
	private long ticketId;
	private boolean delete = false;
	private String emailUniqueId;
	private boolean fromInbox;

	public boolean isFromInbox() {
		return fromInbox;
	}

	public void setFromInbox(boolean fromInbox) {
		this.fromInbox = fromInbox;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public Timestamp getCdate() {
		return cdate;
	}

	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public String getEmailUniqueId() {
		return emailUniqueId;
	}

	public void setEmailUniqueId(String emailUniqueId) {
		this.emailUniqueId = emailUniqueId;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", subject=" + subject + ", body=" + body + ", from=" + from + ", to=" + to + ", cc="
				+ cc + ", cdate=" + cdate + ", note=" + note + ", ticketId=" + ticketId + ", delete=" + delete
				+ ", emailUniqueId=" + emailUniqueId + ", fromInbox=" + fromInbox + "]";
	}

	/**
	 * 按时间排序，大的在后面
	 */
	@Override
	public int compareTo(Email o) {
		// TODO this-o 从小到大
		int flag = 0;
		if (this.getCdate().before(o.getCdate())) {
			flag = -1;

		} else if (this.getCdate().equals(o.getCdate())) {
			flag = 0;
		} else {
			flag = 1;
		}
		return flag;
	}

}
