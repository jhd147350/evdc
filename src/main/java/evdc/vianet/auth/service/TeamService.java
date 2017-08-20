package evdc.vianet.auth.service;

import java.util.List;

import evdc.vianet.auth.entity.Team;

public interface TeamService {

	int insertTeam(Team t);

	int deleteTeamById(int id);

	List<Team> findAll();

	Team findTeamById(int id);

	int updateTeamById(Team t);

}
