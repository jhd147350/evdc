package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.entity.TicketSubscibeTeam;;

/**
 * @author jaden
 *
 * @date	2017年9月12日上午9:25:02
 */
public interface TicketMapper {

	@Insert("insert into " + Ticket.TABLE_NAME
			+ " (`source`,`title`,`description`,`severity`,`status`,`serviceId`,`submitUserId`,`submitTeamId`,`assignUserId`,`assignTeamId`,`satisfation`,`updateUserId`,`updateDate`)"
			+ " values(#{t.source},#{t.title},#{t.description},#{t.severity},#{t.status},#{t.serviceId},#{t.submitUserId},#{t.submitTeamId},#{t.assignUserId},#{t.assignTeamId},#{t.satisfation},#{t.updateUserId},#{t.updateDate})")
	@Options(useGeneratedKeys = true, keyProperty = "t.id")
	int insertTicket(@Param("t") Ticket t);
	
	//根据提交组及关键字查询工单
	@Select("select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where submitTeamId=#{submitTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	List<Ticket> findAllTicketsBySubmitTeamAndKeyword(@Param("submitTeamId") long submitTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	
	

	//根据指派组及关键字查询工单
	//select * from ( select * from evdc_ss.ticket where `assignTeamId` = 1 and status regexp '.*' and severity regexp '.*' and service regexp '' ) ticket_a where ticket_a.id like '%1%' or ticket_a.title like '%1%' or ticket_a.description like '%1%';
	@Select("select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where assignTeamId=#{assignTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	List<Ticket> findAllTicketsByAssignTeamAndKeyword(@Param("assignTeamId") long assignTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	
	/*@Select("select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where assignTeamId=#{id} and status regexp #{status} and severity regexp #{severity} and service regexp #{service}) ticket_a "
					+ "where ticket_a.id like '%#{keyword}%' or ticket_a.title like '%#{keyword}%' or ticket_a.description like '%#{keyword}%'")
	List<Ticket> findAllTicektsByTicketIdAndKeyword(long ticketId, String service, String status, String severity, String keyword);*/
	//根据订阅组及关键字查询工单
	@Select("select * from "
			+ "(select a.* from "+Ticket.TABLE_NAME+" as a left join "+TicketSubscibeTeam.TABLE_NAME+" as b on a.id = b.ticketId where b.subscibeTeamId = #{subscibeTeamId} and a.status regexp #{status} and a.severity regexp #{severity} and a.serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	List<Ticket> findAllTicketsBySubscibeTeamAndKeyword(@Param("subscibeTeamId") long subscibeTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	//根据关键字查询工单
	@Select("select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
					+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	List<Ticket> findAllTicketsByKeyword(@Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
}
