package evdc.vianet.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.TeamRole;
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
	public int deleteTeamById(long id) {
		Team team = teamMapper.findTeamById(id);
		if(team.getDelete()==1) {
			return 2;
		}else {
			return teamMapper.deleteTeamById(id);
		}
	}

	@Override
	public List<Team> findAllTeams() {
		return teamMapper.findAllTeams();
	}

	@Override
	public Team findTeamById(long id) {
		// TODO 
		return null;
	}

	@Override
	public int updateTeamById(Team t) {
		return updateTeamById(t);
	}

}
