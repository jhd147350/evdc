package evdc.vianet.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.ticket.entity.view.CustomerTicketView;
import evdc.vianet.ticket.mapper.CustomerTicketViewMapper;
import evdc.vianet.ticket.mapper.TicketMapper;
@Service("customerTicketService")
public class CustomerTicketServiceImp implements CustomerTicketService {
	@Autowired
	private CustomerTicketViewMapper customerTicketViewMapper;
	@Override
	public int findAllCustomerTicketCountByCustomerTeamAndKeyword(long customerTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return customerTicketViewMapper.findAllCustomerTicketCountByCustomerTeamAndKeyword(customerTeamId, service, status, severity, keyword);
	}

	

	@Override
	public CustomerTicketView findCustomerTicketByTicketId(long id) {
		// TODO Auto-generated method stub
		return customerTicketViewMapper.findCustomerTicketByTicketId(id);
	}



	@Override
	public List<CustomerTicketView> findAllTicketViewsByCustomerTeamAndKeywordANDPageANDLimit(int limit1, int limit2,
			long customerTeamId, String service, String status, String severity, String keyword) {
		// TODO Auto-generated method stub
		return customerTicketViewMapper.findAllTicketViewsByCustomerTeamAndKeywordANDPageANDLimit(limit1, limit2, customerTeamId, service, status, severity, keyword);
	}

}
