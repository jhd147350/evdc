package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.ticket.entity.view.CustomerTicketView;

public interface CustomerTicketService {
public int findAllCustomerTicketCountByCustomerTeamAndKeyword(long customerTeamId, String service, String status, String severity, String keyword);
public List<CustomerTicketView> findAllTicketViewsByCustomerTeamAndKeywordANDPageANDLimit(int limit1, int limit2, long customerTeamId, String service, String status, String severity, String keyword);
public CustomerTicketView findCustomerTicketByTicketId(long id);
}
