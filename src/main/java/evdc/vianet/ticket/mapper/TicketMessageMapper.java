package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.entity.TicketMessage;
import evdc.vianet.ticket.entity.TicketMessageView;

/**

 * @ClassName: TicketMessageMapper

 * @Description: TODO

 * @author: jaden

 * @date: 2017年10月19日 上午9:53:19


 */
public interface TicketMessageMapper {
	/**
	
	 * @Title: insertTicket
	
	 * @Description: TODO 插入新comment并返回对应ID
	
	 * @param t
	 * @return
	
	 * @return: int
	
	 */
	@Insert("insert into " + TicketMessage.TABLE_NAME
			+ " (`ticketId`,`userId`,`teamId`,`message`,`scope`)"
			+ " values(#{t.ticketId},#{t.userId},#{t.teamId},#{t.message},#{t.scope})")
	@Options(useGeneratedKeys = true, keyProperty = "t.id")
	int insertTicket(@Param("t") TicketMessage t);
	
	@Select("select * from " + TicketMessageView.TABLE_NAME + " where `ticketId`=#{ticketId} and `scope`=#{scope}")
	List<TicketMessageView> findCommentsByTicketIdAndScope(@Param("ticketId") long ticketId, @Param("scope") String scope);
	
	@Select("select * from " + TicketMessageView.TABLE_NAME + " where `ticketId`=#{ticketId} and `scope`=#{scope}")
	List<TicketMessageView> findCommentsByTicketIdAndteamIdAndScope(@Param("ticketId") long ticketId, @Param("teamId") long teamId, @Param("scope") String scope);
}
