package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.ticket.entity.*;


public interface TicketService {
	public long createTicket(String source, String title, String description, String serviceType, String severity, long submitUserId, long submitTeamId);
	
	public List<Ticket> findAllTicketsBySubmitTeamAndKeywordANDPageANDLimit(int limit1, int limit2, long submitTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsByAssignTeamAndKeywordANDPageANDLimit(int limit1, int limit2, long assignTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsBySubscribeTeamAndKeywordANDPageANDLimit(int limit1, int limit2, long subscribeTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsByKeywordANDPageANDLimit(int limit1, int limit2, String service, String status, String severity, String keyword);
	
	
	public int findAllTicketCountBySubmitTeamAndKeyword(long submitTeamId, String service, String status, String severity, String keyword);
	public int findAllTicketCountByAssignTeamAndKeyword(long assignTeamId, String service, String status, String severity, String keyword);
	public int findAllTicketCountBySubscribeTeamAndKeyword(long subscribeTeamId, String service, String status, String severity, String keyword);
	public int findAllTicketCountByKeyword(String service, String status, String severity, String keyword);
	
	public void changeTicketStatus(String status, long ticketId); 
}
