package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.ticket.entity.*;


public interface TicketService {
	public void createTicket();
	public List<Ticket> findAllTicketsBySubmitTeamAndKeyword(long submitTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsByAssignTeamAndKeyword(long assignTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsBySubscibeTeamAndKeyword(long subscibeTeamId, String service, String status, String severity, String keyword);
	public List<Ticket> findAllTicketsByKeyword(String service, String status, String severity, String keyword);
}
