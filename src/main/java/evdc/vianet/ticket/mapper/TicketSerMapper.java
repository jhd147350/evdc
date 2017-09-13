package evdc.vianet.ticket.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.*;
public interface TicketSerMapper {
	
	@Select("select * from " + TicketSer.TABLE_NAME)
	List<TicketSer> findAllTicketService();
	
	@Select("select * from " + TicketSer.TABLE_NAME + " where id =#{id}")
	TicketSer findTicketServiceById(@Param("id") long id);
}
