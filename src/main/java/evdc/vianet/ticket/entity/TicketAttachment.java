package evdc.vianet.ticket.entity;

/**
 * @author jaden
 *
 * @date	2017年9月19日下午5:54:48
 */
public class TicketAttachment {
	public static final String TABLE_NAME = "ticket_attachment";
	private long id;
	private long ticketId;
	private long messageId;
	private String name;
	private String type;
	private String src;
	public TicketAttachment() {
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
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
}
