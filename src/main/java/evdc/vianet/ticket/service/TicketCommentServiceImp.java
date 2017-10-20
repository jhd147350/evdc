package evdc.vianet.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.ticket.entity.TicketMessage;
import evdc.vianet.ticket.entity.TicketMessageView;
import evdc.vianet.ticket.mapper.TicketMessageMapper;

/**

 * @ClassName: TicketCommentServiceImp

 * @Description: TODO

 * @author: jaden

 * @date: 2017年10月19日 上午10:51:16


 */
@Service("ticketCommentService")
public class TicketCommentServiceImp implements TicketCommentService {
	@Autowired
	private TicketMessageMapper ticketMessageMapper;
	
	@Override
	public long addTicketComment(long ticketId, long userId, long teamId, String message, String scope) {
		// TODO Auto-generated method stub
		TicketMessage t = new TicketMessage();
		t.setTicketId(ticketId);
		t.setUserId(userId);
		t.setTeamId(teamId);
		t.setMessage(message);
		t.setScope(scope);
		ticketMessageMapper.insertTicketMessage(t);
		return t.getId();
	}

	@Override
	public List<TicketMessageView> getCommentsByTicketIdAndScope(long ticketId, String scope) {
		// TODO Auto-generated method stub
		return ticketMessageMapper.findCommentsByTicketIdAndScope(ticketId, scope);
	}

	@Override
	public List<TicketMessageView> getCommentsByTicketIdAndteamIdAndScope(long ticketId, long teamId, String scope) {
		// TODO Auto-generated method stub
		return ticketMessageMapper.findCommentsByTicketIdAndteamIdAndScope(ticketId, teamId, scope);
	}

}
