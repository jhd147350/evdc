package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.ticket.entity.TicketSer;

/**
 * @author jaden
 *
 * 2017年9月12日下午5:33:40
 */
public interface TicketSerService {
	public List<TicketSer> findAllTicketService();
	public TicketSer findTicketServiceById(long id);
}
