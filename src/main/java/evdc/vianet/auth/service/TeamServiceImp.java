package evdc.vianet.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.TeamRole;
import evdc.vianet.auth.mapper.TeamMapper;
import evdc.vianet.auth.mapper.TeamRoleMapper;

@Service("teamService")
public class TeamServiceImp implements TeamService {

	@Autowired
	private TeamMapper teamMapper;
	@Autowired
	@Qualifier("teamRoleService")
	private TeamRoleService teamRoleService;
	@Autowired
	@Qualifier("authorityService")
	private AuthorityService authorityService;

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
		return teamMapper.findTeamById(id);
	}

	@Override
	public void updateTeamById(Team t) {
		teamMapper.updateTeam(t);
	}

	@Override
	public List<Authority> getTeamAuthsById(long teamId) {
		// TODO Auto-generated method stub
		List<Authority> teamAuths = new ArrayList<Authority>();
		long teamAuthValue = teamRoleService.findTeamRoleById(teamMapper.findTeamById(teamId).getRole()).getAuthValue();
		List<Authority> allAuth = authorityService.findAllAuthoritys();
		
		for (Authority authority : allAuth) {
			if(teamAuthValue%authority.getAuthValue()==0){
				teamAuths.add(authority);
			}
		}
		return teamAuths;
	}

}
