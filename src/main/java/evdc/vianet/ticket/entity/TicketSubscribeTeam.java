package evdc.vianet.ticket.entity;

import java.sql.Timestamp;


/**

 * @ClassName: TicketSubscribeTeam

 * @Description: TODO

 * @author: jaden

 * @date: 2017年10月18日 上午9:35:24


 */
public class TicketSubscribeTeam {
	public static final String TABLE_NAME="ticket_subscribe_team";
	private long id;
	private long ticketId;
	private long subscribeTeamId;
	private Timestamp timestamp;
	public TicketSubscribeTeam() {
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
	public long getSubscribeTeamId() {
		return subscribeTeamId;
	}
	public void setSubscribeTeamId(long subscribeTeamId) {
		this.subscribeTeamId = subscribeTeamId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
