package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.TicketChangeRecord;
import evdc.vianet.ticket.entity.view.TicketChangeRecordView;

public interface TicketChangeRecordMapper {
	@Insert("insert into " + TicketChangeRecord.TABLE_NAME
			+ " (`ticketId`,`userId`,`filed`,`oldValue`,`newValue`)"
			+ " values(#{t.ticketId},#{t.userId},#{t.filed},#{t.oldValue},#{t.newValue})")
	void insertTicketChangeRecord(@Param("t") TicketChangeRecord t);
	
	@Select("select * from " + TicketChangeRecord.TABLE_NAME
			+ " where `ticketId`=#{ticketId}")
	List<TicketChangeRecord> findAllRecordsByTicketId(@Param("ticketId") long ticketId);
	
	@Select("select * from " + TicketChangeRecordView.TABLE_NAME
			+ " where `ticketId`=#{ticketId}")
	List<TicketChangeRecordView> findAllViewRecordsByTicketId(@Param("ticketId") long ticketId);
}
