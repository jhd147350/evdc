package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.ticket.entity.TicketChangeRecord;
import evdc.vianet.ticket.entity.view.TicketChangeRecordView;

public interface TicketChangeRecordService {
	public void addNewRecord(TicketChangeRecord t);
	public List<TicketChangeRecordView> getAllViewRecordsByTicketId(long ticketId);
}
