package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.Ticket;

/**
 * @author jaden
 *
 * @date	2017年9月12日上午9:25:02
 */
public interface TicketMapper {

	@Insert("insert into " + Ticket.TABLE_NAME
			+ " (`source`,`title`,`description`,`severity`,`status`,`service`,`submitUserId`,`submitTeamId`,`assignUserId`,`assignTeamId`,`satisfation`,`updateUserId`,`updateDate`)"
			+ " values(#{t.source},#{t.title},#{t.description},#{t.severity},#{t.status},#{t.service},#{t.submitUserId},#{t.submitTeamId},#{t.assignUserId},#{t.assignTeamId},#{t.satisfation},#{t.updateUserId},#{t.updateDate})")
	int insertTicket(@Param("t") Ticket t);
	
	@Select("select from "+Ticket.TABLE_NAME+" where `assignTeamId` = #{t.assignTeamId} and status regexp t.status and severity regexp t.severity and serviceId regexp  ")
	List<Ticket> findAllOpenTicektsBySubmitTeam(@Param("t") Ticket t);
	
	


	//select * from ( select * from evdc_ss.ticket where `assignTeamId` = 1 and status regexp '.*' and severity regexp '.*' ) ticket_a where ticket_a.id like '%1%' or ticket_a.title like '%1%' or ticket_a.description like '%1%';
	@Select("select from "+Ticket.TABLE_NAME+" where assignTeamId=#{teamId} and status！='Closed'")
	List<Ticket> findAllOpenTicektsByAssignTeam(int teamId);
}
