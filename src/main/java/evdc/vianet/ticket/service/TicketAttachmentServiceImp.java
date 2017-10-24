package evdc.vianet.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.ticket.entity.TicketAttachment;
import evdc.vianet.ticket.mapper.TicketAttachmentMapper;

/**
 * @author jaden
 *
 * @date	2017年9月19日下午6:18:56
 */
@Service("ticketAttachmentService")
public class TicketAttachmentServiceImp implements TicketAttachmentService {
	@Autowired
	TicketAttachmentMapper ticketAttachmentMapper;
	@Override
	public int addTicketAttachmentService(long ticketId, long messageId, String type, String src, String name) {
		// TODO Auto-generated method stub
		TicketAttachment ticketAttachment = new TicketAttachment();
		ticketAttachment.setTicketId(ticketId);
		ticketAttachment.setMessageId(messageId);
		ticketAttachment.setType(type);
		ticketAttachment.setSrc(src);
		ticketAttachment.setName(name);
		return ticketAttachmentMapper.insertTicketAttachment(ticketAttachment);
	}
	@Override
	public List<TicketAttachment> getTicketAttachmentsByTicketIdAndMessageId(long ticketId, long messageId) {
		// TODO Auto-generated method stub
		return ticketAttachmentMapper.findTicketAttachmentsByTicketIdAndMessageId(ticketId, messageId);
	}
	@Override
	public List<TicketAttachment> getTicketMessagesByTicketId(long ticketId) {
		// TODO Auto-generated method stub
		return ticketAttachmentMapper.findTicketMessagesByTicketId(ticketId);
	}

}
