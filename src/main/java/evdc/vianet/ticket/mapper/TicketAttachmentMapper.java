package evdc.vianet.ticket.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import evdc.vianet.ticket.entity.Ticket;
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
}
