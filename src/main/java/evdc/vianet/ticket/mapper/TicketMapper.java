package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.entity.TicketSubscribeTeam;

/**
 * @author jaden
 *
 * @date	2017年9月12日上午9:25:02
 */
public interface TicketMapper {

	@Insert("insert into " + Ticket.TABLE_NAME
			+ " (`source`,`title`,`description`,`severity`,`status`,`serviceId`,`submitUserId`,`submitTeamId`,`assignUserId`,`assignTeamId`,`satisfation`,`updateUserId`,`updateDate`)"
			+ " values(#{t.source},#{t.title},#{t.description},#{t.severity},#{t.status},#{t.serviceId},#{t.submitUserId},#{t.submitTeamId},#{t.assignUserId},#{t.assignTeamId},#{t.satisfation},#{t.submitUserId},#{t.submitDate})")
	@Options(useGeneratedKeys = true, keyProperty = "t.id")
	void insertTicket(@Param("t") Ticket t);
	
	//根据提交组及关键字查询工单
	@Select("select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where submitTeamId=#{submitTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	List<Ticket> findAllTicketsBySubmitTeamAndKeyword(@Param("submitTeamId") long submitTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	
	

	//根据指派组及关键字查询工单
	@Select("select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where assignTeamId=#{assignTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	List<Ticket> findAllTicketsByAssignTeamAndKeyword(@Param("assignTeamId") long assignTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	
	//根据订阅组及关键字查询工单
	@Select("select * from "
			+ "(select a.* from "+Ticket.TABLE_NAME+" as a left join "+TicketSubscribeTeam.TABLE_NAME+" as b on a.id = b.ticketId where b.subscribeTeamId = #{subscribeTeamId} and a.status regexp #{status} and a.severity regexp #{severity} and a.serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	List<Ticket> findAllTicketsBySubscribeTeamAndKeyword(@Param("subscribeTeamId") long subscribeTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	//根据关键字查询工单
	@Select("select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
					+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	List<Ticket> findAllTicketsByKeyword(@Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
}
