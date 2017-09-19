package evdc.vianet.ticket.service;

public interface TicketAttachmentService {
	public int addTicketAttachmentService(long ticketId, long messageId, String type, String src, String name);
}
