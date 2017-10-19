package evdc.vianet.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.Status;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.entity.UserRole;

import evdc.vianet.auth.service.AuthorityService;
import evdc.vianet.auth.service.TeamRoleService;
import evdc.vianet.auth.service.TeamService;
import evdc.vianet.auth.service.UserRoleService;

/**
 * @author jaden
 *
 * @date	2017年9月10日下午12:21:25
 */

@Controller
@RequestMapping("/userRoleManagement")
public class UserRoleController {
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	@Autowired
	@Qualifier("teamRoleService")
	private TeamRoleService teamRoleService;
	@Autowired
	@Qualifier("authorityService")
	private AuthorityService authorityService;
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	private User u;
	
	@RequestMapping("/userRoleManagementPage")
	public String userRoleManagementPage(Model m, HttpServletRequest request, HttpSession httpSession) {
		u = (User)httpSession.getAttribute("user");
		List<UserRole> userRoles = userRoleService.findAllUserRolesByTeamId(u.getTeamId());
		m.addAttribute("userRoles", userRoles);
		System.out.println("请求路径"+request.getServletPath());
		return "auth/TeamManagement/userRoleManagement";
	}
	@RequestMapping("/userRoleAddPage")
	public String userRoleAddPage(Model m, HttpServletRequest request, HttpSession httpSession) {
		u = (User)httpSession.getAttribute("user");
		List<Authority> teamAuths = teamService.getTeamAuthsById(u.getTeamId());
		m.addAttribute("authoritys", teamAuths);
		return "auth/TeamManagement/userRoleAdd";
	}
	
	@RequestMapping("/addUserRole")
	public @ResponseBody Status adduserRole(HttpSession httpSession, HttpServletRequest request, HttpServletResponse resp, String roleName, String authValue, String describe) {
		u = (User)httpSession.getAttribute("user");
		UserRole userRole = new UserRole();
		userRole.setRoleName(roleName);
		userRole.setRoleTeamId(u.getTeamId());
		userRole.setAuthValue(Long.parseLong(authValue));
		userRole.setDelete(0);
		userRole.setDescribe(describe);
		Status status = new Status();
		status.setStatus(userRoleService.insertUserRole(userRole));
		System.out.println(status.getStatus());
		return status;
	}
	@RequestMapping("/deleteUserRole")
	public @ResponseBody Status deleteuserRole(HttpSession httpSession, HttpServletRequest req, HttpServletResponse resp, String id) {
	
		Status status = new Status();
		status.setStatus(userRoleService.deleteUserRole(Long.parseLong(id)));
		System.out.println(status.getStatus());
		return status;
	}
	@RequestMapping("/userRoleEditPage")
	public String userRoleEditPage(HttpSession httpSession, HttpServletRequest request, String id, Model m) {
		u = (User)httpSession.getAttribute("user");
		List<Authority> teamAuths = teamService.getTeamAuthsById(u.getTeamId());
		request.setAttribute("authoritys", teamAuths);
		request.setAttribute("userRole", userRoleService.findUserRoleById(Long.parseLong(id)));
		m.addAttribute("authoritys", teamAuths);
		m.addAttribute("userRole", userRoleService.findUserRoleById(Long.parseLong(id)));
		return "auth/TeamManagement/userRoleEdit";
	}
	
	@RequestMapping("/updateUserRole")
	public @ResponseBody Status updateUserRole(HttpSession httpSession, HttpServletRequest req, HttpServletResponse resp, String id, String roleName, String authValue, String describe) {
		UserRole userRole = new UserRole();
		userRole.setId(Long.parseLong(id));
		userRole.setRoleName(roleName);
		userRole.setAuthValue(Long.parseLong(authValue));
		userRole.setDelete(0);
		userRole.setDescribe(describe);
		Status status = new Status();
		userRoleService.updateUserRole(userRole);
		status.setStatus(1);
		System.out.println(status.getStatus());
		return status;
	}
}
