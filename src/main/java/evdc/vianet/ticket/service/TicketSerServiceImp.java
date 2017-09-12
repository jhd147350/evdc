package evdc.vianet.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.ticket.entity.TicketSer;

import evdc.vianet.ticket.mapper.TicketSerMapper;

@Service("ticketSerService")
public class TicketSerServiceImp implements TicketSerService{
	@Autowired
	private TicketSerMapper ticketSerMapper;
	@Override
	public List<TicketSer> findAllTicketService() {
		// TODO Auto-generated method stub
		return ticketSerMapper.findAllTicketService();
	}
	@Override
	public TicketSer findTicketServiceById(long id) {
		// TODO Auto-generated method stub
		return ticketSerMapper.findTicketServiceById(id);
	}

}
