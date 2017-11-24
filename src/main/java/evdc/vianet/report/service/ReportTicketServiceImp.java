package evdc.vianet.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.ticket.entity.view.TicketView;
import evdc.vianet.ticket.mapper.TicketMapper;

@Service("reportTicketService")
public class ReportTicketServiceImp implements ReportTicketService {
	@Autowired
	private TicketMapper ticketMapper;
	@Override
	public List<TicketView> getTicketViewsBySql(int limit1, int limitint, String sql) {
		// TODO Auto-generated method stub
		String pageSQL = "select * from ( select * from " + TicketView.TABLE_NAME 
				+ " where " + sql + ") table_a limit " + limit1 + "," + limitint;
		if(limitint == 0){
			pageSQL =  "select * from " + TicketView.TABLE_NAME 
					+ " where " + sql;
		}
		return ticketMapper.findTicketViewsBySQL(pageSQL);
	}

	@Override
	public int getCountBySql(String sql) {
		// TODO Auto-generated method stub
		String countSQL = "select count(*) from "+ TicketView.TABLE_NAME 
				+ " where " + sql;
		return ticketMapper.findTicketViewsCountBySQL(countSQL);
	}

}
