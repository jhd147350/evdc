package evdc.vianet.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import evdc.vianet.auth.entity.Authority;
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
		System.out.println("请求路径为："+request.getServletPath());
		return "auth/SystemManagement/teamRoleManagement";
	}
	@RequestMapping("/teamRoleAddPage")
	public String teamRoleAddPage(Model m) {
		List<Authority> authoritys = authorityService.findAllAuthoritys();
		m.addAttribute("authoritys", authoritys);
		return "auth/SystemManagement/teamRoleAdd";
	}
}
