package evdc.vianet.ticket.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.mapper.TicketMapper;

@Service("ticketService")
public class TicketServiceImp implements TicketService {

	@Autowired
	private TicketMapper ticketMapper;

	@Override
	public long createTicket(String source, String title, String description, String serviceType, String severity,
			long submitUserId, long submitTeamId) {
		Ticket ticket = new Ticket();
		ticket.setSource(source);
		ticket.setTitle(title);
		ticket.setDescription(description);
		ticket.setService(Long.parseLong(serviceType));
		ticket.setSeverity(severity);
		ticket.setStatus("New");
		ticket.setSubmitUserId(submitUserId);
		ticket.setSubmitTeamId(submitTeamId);
		ticket.setSubmitDate(new Timestamp(System.currentTimeMillis()));
		ticketMapper.insertTicket(ticket);
		return ticket.getId();
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ticket> findAllTicketsBySubmitTeamAndKeyword(long submitTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsBySubmitTeamAndKeyword(submitTeamId, service, status, severity, keyword);
	}

	@Override
	public List<Ticket> findAllTicketsByAssignTeamAndKeyword(long assignTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsByAssignTeamAndKeyword(assignTeamId, service, status, severity, keyword);
	}

	@Override
	public List<Ticket> findAllTicketsBySubscibeTeamAndKeyword(long subscibeTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsBySubscibeTeamAndKeyword(subscibeTeamId, service, status, severity, keyword);
	}

	@Override
	public List<Ticket> findAllTicketsByKeyword(String service, String status, String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsByKeyword(service, status, severity, keyword);
	}

	

}
