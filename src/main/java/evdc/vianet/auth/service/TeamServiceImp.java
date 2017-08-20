package evdc.vianet.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.mapper.TeamMapper;

@Service("teamService")
public class TeamServiceImp implements TeamService {

	@Autowired
	private TeamMapper teamMapper;

	@Override
	public int insertTeam(Team t) {
		return teamMapper.insertTeam(t);
	}

	@Override
	public int deleteTeamById(int id) {
		return teamMapper.deleteTeamById(id);
	}

	@Override
	public List<Team> findAll() {
		return teamMapper.findAllTeams();
	}

	@Override
	public Team findTeamById(int id) {
		// TODO 
		return null;
	}

	@Override
	public int updateTeamById(Team t) {
		return updateTeamById(t);
	}

}
