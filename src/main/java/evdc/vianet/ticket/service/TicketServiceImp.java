package evdc.vianet.ticket.service;

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
	public void createTicket() {

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
