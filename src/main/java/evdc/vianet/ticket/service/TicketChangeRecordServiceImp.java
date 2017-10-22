package evdc.vianet.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.ticket.entity.TicketChangeRecord;
import evdc.vianet.ticket.entity.view.TicketChangeRecordView;
import evdc.vianet.ticket.mapper.TicketChangeRecordMapper;

@Service("ticketChangeRecordService")
public class TicketChangeRecordServiceImp implements TicketChangeRecordService {
	@Autowired
	private TicketChangeRecordMapper ticketChangeRecordMapper;
	@Override
	public void addNewRecord(TicketChangeRecord t) {
		// TODO Auto-generated method stub
		ticketChangeRecordMapper.insertTicketChangeRecord(t);
	}

	@Override
	public List<TicketChangeRecordView> getAllViewRecordsByTicketId(long ticketId) {
		// TODO Auto-generated method stub
		return ticketChangeRecordMapper.findAllViewRecordsByTicketId(ticketId);
	}

}
