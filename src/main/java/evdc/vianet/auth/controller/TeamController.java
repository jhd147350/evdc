package evdc.vianet.auth.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.auth.entity.Status;
import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.TeamRole;
import evdc.vianet.auth.service.TeamRoleService;
import evdc.vianet.auth.service.TeamService;

/**
 * @author jaden
 *
 * 2017年9月9日下午6:36:36
 */
@Controller
@RequestMapping("/teamManagement")
public class TeamController {
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	@Autowired
	@Qualifier("teamRoleService")
	private TeamRoleService teamRoleService;
	
	@RequestMapping("/teamManagementPage")
	public String teamManagementPage(Model m, HttpServletRequest request) {
		List<Team> teams = teamService.findAllTeams();
		m.addAttribute("teams", teams);
		System.out.println("请求路径"+request.getServletPath());
		return "auth/SystemManagement/teamManagement";
	}
	@RequestMapping("/teamAddPage")
	public String teamAddPage(Model m) {
		List<TeamRole> teamRoles = teamRoleService.findAllTeamRoles();
		m.addAttribute("teamRoles", teamRoles);
		return "auth/SystemManagement/teamAdd";
	}
	@RequestMapping("/addTeam")
	public @ResponseBody Status addTeam(HttpServletRequest req, HttpServletResponse resp, String teamName, String role, String companyName, String code, String describe) {
		Team team = new Team();
		team.setName(teamName);
		team.setRole(Long.parseLong(role));
		team.setCompanyName(companyName);
		team.setCode(code);
		team.setDelete(0);
		team.setDescription(describe);
		Status status = new Status();
		status.setStatus(teamService.insertTeam(team));
		System.out.println(status.getStatus());
		return status;
	}
	
	@RequestMapping("/deleteTeam")
	public @ResponseBody Status deleteTeam(HttpServletRequest req, HttpServletResponse resp, String id) {
	
		Status status = new Status();
		status.setStatus(teamService.deleteTeamById(Long.parseLong(id)));
		System.out.println(status.getStatus());
		return status;
	}
	
	@RequestMapping("/teamEditPage")
	public String teamEditPage(String id, Model m) {
		
		Team team = teamService.findTeamById(Long.parseLong(id));
		m.addAttribute("team", team);
		List<TeamRole> teamRoles = teamRoleService.findAllTeamRoles();
		Iterator<TeamRole> iter = teamRoles.iterator();
		while(iter.hasNext()){
			TeamRole teamRole = iter.next();
			if(teamRole.getId()==team.getRole()){
				m.addAttribute("teamRole", teamRole);
				iter.remove();
			}
		}
		m.addAttribute("teamRoles", teamRoles);
		
		return "auth/SystemManagement/teamEdit";
	}
	
	@RequestMapping("/updateTeam")
	public @ResponseBody Status updateTeam(HttpServletRequest req, HttpServletResponse resp, String id, String teamName, String role, String companyName, String code, String describe) {
		Team team = new Team();
		team.setId(Long.parseLong(id));
		team.setName(teamName);
		team.setRole(Long.parseLong(role));
		team.setCompanyName(companyName);
		team.setCode(code);
		team.setDelete(0);
		team.setDescription(describe);
		Status status = new Status();
		teamService.updateTeamById(team);
		status.setStatus(1);
		System.out.println(status.getStatus());
		return status;
	}
}
