package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.Ticket;

public interface TicketMapper {

	@Insert("insert into " + Ticket.TABLE_NAME
			+ " (source,title,description,severity,status,service,submitUserId,AssignUserId,AssignTeamId,satisfation)"
			+ " values(#{source},#{title},#{description},#{severity},#{status},#{service},#{submitUserId},#{AssignUserId},#{AssignTeamId},#{satisfation})")
	int insertTicket(Ticket t);
	
	@Select("select from "+Ticket.TABLE_NAME+" where assignTeamId=#{teamId} and status！='Closed'")
	List<Ticket> findAllOpenTicektsByAssignTeam(int teamId);
	
}
