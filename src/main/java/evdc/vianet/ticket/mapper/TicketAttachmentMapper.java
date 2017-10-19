package evdc.vianet.ticket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.ticket.entity.TicketAttachment;

/**
 * @author jaden
 *
 * @date	2017年9月19日下午5:59:32
 */
public interface TicketAttachmentMapper {
	@Insert("insert into " + TicketAttachment.TABLE_NAME
			+ " (`ticketId`, `messageId`, `type`, `src`, `name`)"
			+ " values(#{ticketAttachment.ticketId}, #{ticketAttachment.messageId}, #{ticketAttachment.type}, #{ticketAttachment.src}, #{ticketAttachment.name})")
	int insertTicketAttachment(@Param("ticketAttachment")TicketAttachment ticketAttachment);
	
	@Select("select * from " + TicketAttachment.TABLE_NAME + " where `ticketId`=#{ticketId} and `messageId`=#{messageId}")
	List<TicketAttachment> findTicketAttachmentsByTicketIdAndMessageId(@Param("ticketId")long ticketId, @Param("messageId") long messageId);
	
	@Select("select * from " + TicketAttachment.TABLE_NAME + " where `ticketId`=#{ticketId} and `messageId`!=0")
	List<TicketAttachment> findTicketMessagesByTicketId(@Param("ticketId")long ticketId);
}
