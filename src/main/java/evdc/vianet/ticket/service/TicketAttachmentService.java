package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.ticket.entity.TicketAttachment;

public interface TicketAttachmentService {
	public int addTicketAttachmentService(long ticketId, long messageId, String type, String src, String name);
	
	public List<TicketAttachment> getTicketAttachmentsByTicketIdAndMessageId(long ticketId, long messageId);
	
	public List<TicketAttachment> getTicketMessagesByTicketId(long ticketId);
}
