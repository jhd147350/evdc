package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.entity.view.CustomerTicketView;
import evdc.vianet.ticket.entity.view.TicketView;

public interface CustomerTicketViewMapper {
	
	//根据客户组及关键字查询工单数量
	@Select("select COUNT(*) from "
			+ "( select * from "+CustomerTicketView.TABLE_NAME+" where customerTeamId=#{customerTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	int findAllCustomerTicketCountByCustomerTeamAndKeyword(@Param("customerTeamId") long customerTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);

	
	//根据客户组及关键字分页查询工单视图
	@Select("select * from " + "( select * from "
			+ "( select * from "+CustomerTicketView.TABLE_NAME+" where customerTeamId=#{customerTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}' order by ticket_a.id desc) ticket_b limit #{limit1},#{limit2}")
	List<CustomerTicketView> findAllTicketViewsByCustomerTeamAndKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("customerTeamId") long customerTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);	
	
	//根据ticketid查询客户工单试图
	@Select("select * from "+CustomerTicketView.TABLE_NAME+" where id=#{id}")
	CustomerTicketView findCustomerTicketByTicketId(@Param("id") long id);
}
