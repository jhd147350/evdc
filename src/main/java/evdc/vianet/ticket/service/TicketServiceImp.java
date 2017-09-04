package evdc.vianet.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import evdc.vianet.ticket.mapper.TicketMapper;

@Service("ticketService")
public class TicketServiceImp implements TicketService {

	@Autowired
	private TicketMapper ticketMapper;

	@Override
	public void createTicket() {

	}

}
