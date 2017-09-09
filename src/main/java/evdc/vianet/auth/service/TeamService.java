package evdc.vianet.auth.service;

import java.util.List;

import evdc.vianet.auth.entity.Team;

public interface TeamService {

	int insertTeam(Team t);

	int deleteTeamById(long id);

	List<Team> findAllTeams();

	Team findTeamById(long id);

	int updateTeamById(Team t);

}
