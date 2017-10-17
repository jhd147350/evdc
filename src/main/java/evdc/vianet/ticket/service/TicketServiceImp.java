package evdc.vianet.ticket.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.User;
import evdc.vianet.constant.ScheduleException;
import evdc.vianet.shift.entity.view.ViewOnDutyUser;
import evdc.vianet.shift.service.ShiftService;
import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.mapper.TicketMapper;

@Service("ticketService")
public class TicketServiceImp implements TicketService {

	@Autowired
	private TicketMapper ticketMapper;
	@Autowired
	@Qualifier("shiftService")
	private ShiftService shiftService;
	
	@Override
	public long createTicket(String source, String title, String description, String serviceType, String severity,
			long submitUserId, long submitTeamId) {
			List<ViewOnDutyUser> onDutyUsers = null;
			try {
				onDutyUsers = shiftService.getOnDutyUsersByTeamId(submitTeamId, Calendar.getInstance(TimeZone.getTimeZone("GMT+8"), Locale.ENGLISH));
			} catch (ScheduleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			User onDutyUser = null;
			for (ViewOnDutyUser viewOnDutyUser : onDutyUsers) {
				if(viewOnDutyUser.isPrimary()){
					onDutyUser = viewOnDutyUser;
				}
			}
		Ticket ticket = new Ticket();
		ticket.setSource(source);
		ticket.setTitle(title);
		ticket.setDescription(description);
		ticket.setService(Long.parseLong(serviceType));
		ticket.setSeverity(severity);
		ticket.setStatus("New");
		ticket.setSubmitUserId(submitUserId);
		ticket.setSubmitTeamId(submitTeamId);
		ticket.setSubmitDate(new Timestamp(System.currentTimeMillis()));
		ticket.setAssignUserId(onDutyUser.getId());
		ticket.setAssignTeamId(onDutyUser.getTeamId());
		ticketMapper.insertTicket(ticket);
		return ticket.getId();
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ticket> findAllTicketsBySubmitTeamAndKeyword(long submitTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsBySubmitTeamAndKeyword(submitTeamId, service, status, severity, keyword);
	}

	@Override
	public List<Ticket> findAllTicketsByAssignTeamAndKeyword(long assignTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsByAssignTeamAndKeyword(assignTeamId, service, status, severity, keyword);
	}

	@Override
	public List<Ticket> findAllTicketsBySubscibeTeamAndKeyword(long subscibeTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsBySubscibeTeamAndKeyword(subscibeTeamId, service, status, severity, keyword);
	}

	@Override
	public List<Ticket> findAllTicketsByKeyword(String service, String status, String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsByKeyword(service, status, severity, keyword);
	}

	

}
