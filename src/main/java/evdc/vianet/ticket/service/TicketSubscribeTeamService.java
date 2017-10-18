package evdc.vianet.ticket.service;

import java.util.List;

import evdc.vianet.auth.entity.Team;

public interface TicketSubscribeTeamService {
	public List<Team> getSubscribeTeamsByTicketId(long ticketId);
	public List<Team> getNonSubscribeTeamsByTicketId(long ticketId);
	public int addSubscribeTeamByTicketIdAndTeamId(long ticketId, long teamId);
	public int deleteSubscribeTeamByTicketIdAndTeamId(long ticketId, long teamId);
}
