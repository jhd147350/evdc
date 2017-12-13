package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.entity.TicketSubscribeTeam;
import evdc.vianet.ticket.entity.view.TicketView;

/**
 * @author jaden
 *
 * @date	2017年9月12日上午9:25:02
 */
public interface TicketMapper {

	@Insert("insert into " + Ticket.TABLE_NAME
			+ " (`source`,`title`,`description`,`severity`,`status`,`serviceId`,`submitUserId`,`submitTeamId`,`assignUserId`,`assignTeamId`,`satisfation`,`updateUserId`,`updateDate`,`customerId`,`customerTeamId`)"
			+ " values(#{t.source},#{t.title},#{t.description},#{t.severity},#{t.status},#{t.serviceId},#{t.submitUserId},#{t.submitTeamId},#{t.assignUserId},#{t.assignTeamId},#{t.satisfation},#{t.submitUserId},#{t.submitDate},#{t.customerId},#{t.customerTeamId})")
	@Options(useGeneratedKeys = true, keyProperty = "t.id")
	void insertTicket(@Param("t") Ticket t);
	
	//根据提交组及关键字查询工单
	@Select("select COUNT(*) from "
			+ "( select * from "+Ticket.TABLE_NAME+" where submitTeamId=#{submitTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	int findAllTicketCountBySubmitTeamAndKeyword(@Param("submitTeamId") long submitTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	//根据提交组及关键字分页查询工单
	@Select("select * from " + "( select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where submitTeamId=#{submitTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}') ticket_b limit #{limit1},#{limit2}")
	List<Ticket> findAllTicketsBySubmitTeamAndKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("submitTeamId") long submitTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	
	

	//根据指派组及关键字查询工单
	@Select("select COUNT(*) from "
			+ "( select * from "+Ticket.TABLE_NAME+" where assignTeamId=#{assignTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	int findAllTicketCountByAssignTeamAndKeyword(@Param("assignTeamId") long assignTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	//根据指派组及关键字分页查询工单
	@Select("select * from " + "( select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where assignTeamId=#{assignTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}') ticket_b limit #{limit1},#{limit2}")
	List<Ticket> findAllTicketsByAssignTeamAndKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("assignTeamId") long assignTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);	
	
	//根据订阅组及关键字查询工单
	@Select("select COUNT(*) from "
			+ "(select a.* from "+Ticket.TABLE_NAME+" as a left join "+TicketSubscribeTeam.TABLE_NAME+" as b on a.id = b.ticketId where b.subscribeTeamId = #{subscribeTeamId} and a.status regexp #{status} and a.severity regexp #{severity} and a.serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	int findAllTicketCountBySubscribeTeamAndKeyword(@Param("subscribeTeamId") long subscribeTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	//根据订阅组及关键字分页查询工单
	@Select("select * from " + "( select * from "
			+ "(select a.* from "+Ticket.TABLE_NAME+" as a left join "+TicketSubscribeTeam.TABLE_NAME+" as b on a.id = b.ticketId where b.subscribeTeamId = #{subscribeTeamId} and a.status regexp #{status} and a.severity regexp #{severity} and a.serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}') ticket_b limit #{limit1},#{limit2}")
	List<Ticket> findAllTicketsBySubscribeTeamAndKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("subscribeTeamId") long subscribeTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);	
	
	//根据关键字查询工单数量
	@Select("select COUNT(*) from "
			+ "( select * from "+Ticket.TABLE_NAME+" where status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
					+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}'")
	int findAllTicketCountByKeyword(@Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	//根据关键字分页查询工单
	@Select("select * from " + "( select * from "
			+ "( select * from "+Ticket.TABLE_NAME+" where status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
					+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}') ticket_b limit #{limit1},#{limit2}")
	List<Ticket> findAllTicketsByKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	
	@Update("update " + Ticket.TABLE_NAME
			+ " set `status`=#{status} where `id`=#{ticketId}")
	void updateTicketStatus(@Param("status") String status, @Param("ticketId") long ticketId);
	
	@Update("update " + Ticket.TABLE_NAME
			+ " set `serviceId`=#{serviceId} where `id`=#{ticketId}")
	void updateTicketServiceId(@Param("serviceId") long serviceId, @Param("ticketId") long ticketId);
	
	@Update("update " + Ticket.TABLE_NAME
			+ " set `severity`=#{severity} where `id`=#{ticketId}")
	void updateTicketSeverity(@Param("severity") String severity, @Param("ticketId") long ticketId);
	
	
	List<TicketView> findTicketViewsBySQL(String sql);
	
	int findTicketViewsCountBySQL(String sql);
	
	
	//根据提交组及关键字分页查询工单视图
	@Select("select * from " + "( select * from "
			+ "( select * from "+TicketView.TABLE_NAME+" where submitTeamId=#{submitTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}' order by ticket_a.id desc) ticket_b limit #{limit1},#{limit2}")
	List<TicketView> findAllTicketViewsBySubmitTeamAndKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("submitTeamId") long submitTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
		
	//根据指派组及关键字分页查询工单视图
	@Select("select * from " + "( select * from "
			+ "( select * from "+TicketView.TABLE_NAME+" where assignTeamId=#{assignTeamId} and status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}' order by ticket_a.id desc) ticket_b limit #{limit1},#{limit2}")
	List<TicketView> findAllTicketViewsByAssignTeamAndKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("assignTeamId") long assignTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);	
	
	//根据订阅组及关键字分页查询工单视图
	@Select("select * from " + "( select * from "
			+ "(select a.* from "+TicketView.TABLE_NAME+" as a left join "+TicketSubscribeTeam.TABLE_NAME+" as b on a.id = b.ticketId where b.subscribeTeamId = #{subscribeTeamId} and a.status regexp #{status} and a.severity regexp #{severity} and a.serviceId regexp #{service}) ticket_a "
				+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}' order by ticket_a.id desc) ticket_b limit #{limit1},#{limit2}")
	List<TicketView> findAllTicketViewsBySubscribeTeamAndKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("subscribeTeamId") long subscribeTeamId, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);	
	
	//根据关键字分页查询工单视图
	@Select("select * from " + "( select * from "
			+ "( select * from "+TicketView.TABLE_NAME+" where status regexp #{status} and severity regexp #{severity} and serviceId regexp #{service}) ticket_a "
					+ "where ticket_a.id like '${keyword}' or ticket_a.title like '${keyword}' or ticket_a.description like '${keyword}' order by ticket_a.id desc) ticket_b limit #{limit1},#{limit2}")
	List<TicketView> findAllTicketViewsByKeywordANDPageANDLimit(@Param("limit1") int limit1, @Param("limit2") int limit2, @Param("service") String service, @Param("status") String status, @Param("severity") String severity, @Param("keyword") String keyword);
	
	
	
}
