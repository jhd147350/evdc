package evdc.vianet.auth.service;
import java.util.List;

import evdc.vianet.auth.entity.TeamRole;

/**
 * @author jaden
 *
 * 2017年9月8日上午11:32:43
 */
public interface TeamRoleService {
	public List<TeamRole> findAllTeamRoles();
	public TeamRole findTeamRoleById(long id);
	public int insertTeamRole(TeamRole teamRole);
	public void updateTeamRole(TeamRole teamRole);
	public int deleteTeamRole(long id);
}
