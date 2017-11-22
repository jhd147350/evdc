package evdc.vianet.report.service;

import java.util.List;

import evdc.vianet.ticket.entity.view.TicketView;

public interface ReportTicketService {
	public List<TicketView> getTicketViewsBySql(int limit1, int limitint, String sql);
	public int getCountBySql(String sql); 
}
