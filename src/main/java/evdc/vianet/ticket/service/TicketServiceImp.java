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

/**

 * @ClassName: TicketServiceImp

 * @Description: TODO

 * @author: jaden

 * @date: 2017年10月18日 上午9:22:21


 */
@Service("ticketService")
public class TicketServiceImp implements TicketService {

	@Autowired
	private TicketMapper ticketMapper;
	@Autowired
	@Qualifier("shiftService")
	private ShiftService shiftService;
	
	/* (non Javadoc)
	
	 * @Title: createTicket
	
	 * @Description: TODO
		暂时设置team 18 为指派组
	 * @param source
	 * @param title
	 * @param description
	 * @param serviceType
	 * @param severity
	 * @param submitUserId
	 * @param submitTeamId
	 * @return
	
	 * @see evdc.vianet.ticket.service.TicketService#createTicket(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long, long)
	
	 */
	@Override
	public long createTicket(String source, String title, String description, String serviceType, String severity,
			long submitUserId, long submitTeamId) {
			List<ViewOnDutyUser> onDutyUsers = null;
			try {
				onDutyUsers = shiftService.getOnDutyUsersByTeamId(18, Calendar.getInstance(TimeZone.getTimeZone("GMT+8"), Locale.ENGLISH));
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
	public void changeTicketStatus(String status, long ticketId) {
		// TODO Auto-generated method stub
		ticketMapper.updateTicketStatus(status, ticketId);
	}



	@Override
	public List<Ticket> findAllTicketsBySubmitTeamAndKeywordANDPageANDLimit(int limit1, int limit2, long submitTeamId,
			String service, String status, String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsBySubmitTeamAndKeywordANDPageANDLimit(limit1, limit2, submitTeamId, service, status, severity, keyword);
	}



	@Override
	public List<Ticket> findAllTicketsByAssignTeamAndKeywordANDPageANDLimit(int limit1, int limit2, long assignTeamId,
			String service, String status, String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsByAssignTeamAndKeywordANDPageANDLimit(limit1, limit2, assignTeamId, service, status, severity, keyword);
	}



	@Override
	public List<Ticket> findAllTicketsBySubscribeTeamAndKeywordANDPageANDLimit(int limit1, int limit2,
			long subscribeTeamId, String service, String status, String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsBySubscribeTeamAndKeywordANDPageANDLimit(limit1, limit2, subscribeTeamId, service, status, severity, keyword);
	}



	@Override
	public List<Ticket> findAllTicketsByKeywordANDPageANDLimit(int limit1, int limit2, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketsByKeywordANDPageANDLimit(limit1, limit2, service, status, severity, keyword);
	}



	@Override
	public int findAllTicketCountBySubmitTeamAndKeyword(long submitTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketCountBySubmitTeamAndKeyword(submitTeamId, service, status, severity, keyword);
	}



	@Override
	public int findAllTicketCountByAssignTeamAndKeyword(long assignTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketCountByAssignTeamAndKeyword(assignTeamId, service, status, severity, keyword);
	}



	@Override
	public int findAllTicketCountBySubscribeTeamAndKeyword(long subscribeTeamId, String service, String status,
			String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketCountBySubscribeTeamAndKeyword(subscribeTeamId, service, status, severity, keyword);
	}



	@Override
	public int findAllTicketCountByKeyword(String service, String status, String severity, String keyword) {
		// TODO Auto-generated method stub
		return ticketMapper.findAllTicketCountByKeyword(service, status, severity, keyword);
	}

	

}
