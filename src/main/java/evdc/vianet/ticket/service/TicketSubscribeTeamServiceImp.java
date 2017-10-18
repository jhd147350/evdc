package evdc.vianet.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.ticket.mapper.TicketSerMapper;
import evdc.vianet.ticket.mapper.TicketSubscribeTeamMapper;

/**

 * @ClassName: TicketSubscribeTeamServiceImp

 * @Description: TODO

 * @author: jaden

 * @date: 2017年10月18日 上午10:33:18


 */
@Service("ticketSubscribeTeamService")
public class TicketSubscribeTeamServiceImp implements TicketSubscribeTeamService {
	@Autowired
	private TicketSubscribeTeamMapper ticketSubscribeTeamMapper;
	@Override
	public List<Team> getSubscribeTeamsByTicketId(long ticketId) {
		// TODO Auto-generated method stub
		return ticketSubscribeTeamMapper.findSubscribeTeamsByTicketId(ticketId);
	}

	@Override
	public List<Team> getNonSubscribeTeamsByTicketId(long ticketId) {
		// TODO Auto-generated method stub
		return ticketSubscribeTeamMapper.findNonSubscribeTeamsByTicketId(ticketId);
	}

	@Override
	public int addSubscribeTeamByTicketIdAndTeamId(long ticketId, long teamId) {
		// TODO Auto-generated method stub
		return ticketSubscribeTeamMapper.addSubscribeTeamByTicketIdAndTeamId(ticketId, teamId);
	}

	@Override
	public int deleteSubscribeTeamByTicketIdAndTeamId(long ticketId, long teamId) {
		// TODO Auto-generated method stub
		return ticketSubscribeTeamMapper.deleteSubscribeTeamByTicketIdAndTeamId(ticketId, teamId);
	}

}
