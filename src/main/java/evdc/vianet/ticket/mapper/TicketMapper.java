package evdc.vianet.ticket.mapper;

import org.apache.ibatis.annotations.Insert;

import evdc.vianet.ticket.entity.Ticket;

public interface TicketMapper {

	@Insert("insert into " + Ticket.TABLE_NAME
			+ " (source,title,description,severity,status,service,submitUserId,AssignUserId,AssignTeamId,satisfation)"
			+ " values(#{source},#{title},#{description},#{severity},#{status},#{service},#{submitUserId},#{AssignUserId},#{AssignTeamId},#{satisfation})")
	int insertTicket(Ticket t);
}
