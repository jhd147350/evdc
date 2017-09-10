package evdc.vianet.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.TeamRole;
import evdc.vianet.auth.mapper.TeamRoleMapper;

/**
 * @author jaden
 *
 * 2017年9月8日上午11:32:49
 */
@Service("teamRoleService")
public class TeamRoleServiceImp implements TeamRoleService {
	@Autowired
	private TeamRoleMapper teamRoleMapper;
	@Override
	public List<TeamRole> findAllTeamRoles() {
		// TODO Auto-generated method stub
		return teamRoleMapper.findAllTeamRoles();
	}

	@Override
	public TeamRole findTeamRoleById(long teamRole) {
		// TODO Auto-generated method stub
		return teamRoleMapper.findTeamRoleById(teamRole);
	}

	@Override
	public int insertTeamRole(TeamRole teamRole) {
		// TODO Auto-generated method stub
		return teamRoleMapper.insertTeamRole(teamRole);
	}

	@Override
	public void updateTeamRole(TeamRole teamRole) {
		// TODO Auto-generated method stub
		teamRoleMapper.updateTeamRole(teamRole);
	}

	@Override
	public int deleteTeamRole(long id) {
		// TODO Auto-generated method stub
		TeamRole teamRole = teamRoleMapper.findTeamRoleById(id);
		if(teamRole.getDelete()==1) {
			return 2;
		}else {
			return teamRoleMapper.deleteTeamRoleById(id);
		}
		
	}

}
