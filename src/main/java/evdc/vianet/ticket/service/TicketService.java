package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.ticket.entity.*;


public interface TicketService {
	public long createTicket(String source, String title, String description, String serviceType, String severity, long submitUserId, long submitTeamId);
	public List<Ticket> findAllTicketsBySubmitTeamAndKeyword(long submitTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsByAssignTeamAndKeyword(long assignTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsBySubscribeTeamAndKeyword(long subscribeTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsByKeyword(String service, String status, String severity, String keyword);
}
