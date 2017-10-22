package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.ticket.entity.view.TicketMessageView;

public interface TicketCommentService {
	public long addTicketComment(long ticketId, long userId, long teamId, String message, String scope);
	public List<TicketMessageView> getCommentsByTicketIdAndScope(long ticketId, String scope);
	public List<TicketMessageView> getCommentsByTicketIdAndteamIdAndScope(long ticketId, long teamId, String scope);
}
