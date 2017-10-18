package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.ticket.entity.TicketSubscribeTeam;

/**

 * @ClassName: TicketSubscribeTeamMapper

 * @Description: TODO

 * @author: jaden

 * @date: 2017年10月18日 上午9:36:57


 */
public interface TicketSubscribeTeamMapper {
	@Select("SELECT b.* FROM evdc_ss.ticket_subscribe_team a LEFT JOIN evdc_ss.tier_team b ON a.subscribeTeamId=b.id where a.ticketId=#{ticketId}")
	List<Team> findSubscribeTeamsByTicketId(@Param("ticketId")Long ticketId);
	@Select("SELECT b.* FROM evdc_ss.ticket_subscribe_team a RIGHT JOIN evdc_ss.tier_team b ON a.subscribeTeamId=b.id and a.ticketId=#{ticketId} where a.subscribeTeamId is null")
	List<Team> findNonSubscribeTeamsByTicketId(@Param("ticketId")Long ticketId);
	@Insert("insert into " + TicketSubscribeTeam.TABLE_NAME
			+ " (`ticketId`,`subscribeTeamId`) values(#{ticketId},#{teamId})")
	int addSubscribeTeamByTicketIdAndTeamId(@Param("ticketId") long ticketId, @Param("teamId") long teamId);
	@Delete("delete from " + TicketSubscribeTeam.TABLE_NAME + " where `ticketId`=#{ticketId} and `subscribeTeamId`=#{teamId}")
	int deleteSubscribeTeamByTicketIdAndTeamId(@Param("ticketId") long ticketId, @Param("teamId") long teamId);
}
