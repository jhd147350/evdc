package evdc.vianet.auth.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.Status;
import evdc.vianet.auth.entity.TeamRole;
import evdc.vianet.auth.service.AuthorityService;
import evdc.vianet.auth.service.TeamRoleService;

@Controller
@RequestMapping("/teamRoleManagement")
public class TeamRoleController {
	@Autowired
	@Qualifier("teamRoleService")
	private TeamRoleService teamRoleService;
	@Autowired
	@Qualifier("authorityService")
	private AuthorityService authorityService;
	
	@RequestMapping("/teamRoleManagementPage")
	public String teamManagementPage(Model m, HttpServletRequest request) {
		List<TeamRole> teamRoles = teamRoleService.findAllTeamRoles();
		m.addAttribute("teamRoles", teamRoles);
		System.out.println("请求路径"+request.getServletPath());
		return "auth/SystemManagement/teamRoleManagement";
	}
	@RequestMapping("/teamRoleAddPage")
	public String teamRoleAddPage(Model m) {
		List<Authority> authoritys = authorityService.findAllAuthoritys();
		m.addAttribute("authoritys", authoritys);
		return "auth/SystemManagement/teamRoleAdd";
	}
	
	@RequestMapping("/addTeamRole")
	public @ResponseBody Status addTeamRole(HttpServletRequest req, HttpServletResponse resp, String roleName, String authValue, String describe) {
		TeamRole teamRole = new TeamRole();
		teamRole.setRoleName(roleName);
		teamRole.setAuthValue(Long.parseLong(authValue));
		teamRole.setDelete(0);
		teamRole.setDescribe(describe);
		Status status = new Status();
		status.setStatus(teamRoleService.insertTeamRole(teamRole));
		System.out.println(status.getStatus());
		return status;
	}
	@RequestMapping("/deleteTeamRole")
	public @ResponseBody Status deleteTeamRole(HttpServletRequest req, HttpServletResponse resp, String id) {
	
		Status status = new Status();
		status.setStatus(teamRoleService.deleteTeamRole(Long.parseLong(id)));
		System.out.println(status.getStatus());
		return status;
	}
	@RequestMapping("/teamRoleEditPage")
	public String teamRoleEditPage(String id, Model m) {
		List<Authority> authoritys = authorityService.findAllAuthoritys();
		m.addAttribute("authoritys", authoritys);
		m.addAttribute("teamRole", teamRoleService.findTeamRoleById(Long.parseLong(id)));
		return "auth/SystemManagement/teamRoleEdit";
	}
	
	@RequestMapping("/updateTeamRole")
	public @ResponseBody Status updateTeamRole(HttpServletRequest req, HttpServletResponse resp, String id, String roleName, String authValue, String describe) {
		TeamRole teamRole = new TeamRole();
		teamRole.setId(Long.parseLong(id));
		teamRole.setRoleName(roleName);
		teamRole.setAuthValue(Long.parseLong(authValue));
		teamRole.setDelete(0);
		teamRole.setDescribe(describe);
		Status status = new Status();
		teamRoleService.updateTeamRole(teamRole);
		status.setStatus(0);
		System.out.println(status.getStatus());
		return status;
	}
}
