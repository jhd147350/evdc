package evdc.vianet.auth.controller;

import java.util.Iterator;
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

import evdc.vianet.auth.entity.Status;
import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.TeamRole;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.entity.UserRole;
import evdc.vianet.auth.service.UserRoleService;
import evdc.vianet.auth.service.UserService;

/**
 * @author jaden
 *
 * @date	2017年9月10日下午5:25:54
 */

@Controller
@RequestMapping("/teamUserManagement")
public class TeamUserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	private User u;
	
	@RequestMapping("/teamUserManagementPage")
	public String teamManagementPage(Model m, HttpServletRequest request, HttpSession httpSession) {
		u = (User) httpSession.getAttribute("user");
		List<User> teamUsers = userService.findAllUsersByTeamId(u.getTeamId());
		m.addAttribute("teamUsers", teamUsers);
		System.out.println("请求路径"+request.getServletPath());
		return "auth/TeamManagement/teamUserManagement";
	}
	@RequestMapping("/teamUserAddPage")
	public String teamUserAddPage(Model m, HttpSession httpSession) {
		u = (User) httpSession.getAttribute("user");
		List<UserRole> userRoles = userRoleService.findAllUserRolesByTeamId(u.getTeamId());
		m.addAttribute("userRoles", userRoles);
		return "auth/TeamManagement/teamUserAdd";
	}
	@RequestMapping("/addTeamUser")
	public @ResponseBody Status addTeamUser(HttpSession httpSession, HttpServletResponse resp, String name, String role, String phone, String email, String password, String loginId) {
		u = (User) httpSession.getAttribute("user");
		User user = new User();
		user.setName(name);
		user.setRole(Long.parseLong(role));
		user.setTeamId(u.getTeamId());
		user.setPhone(phone);
		user.setEmail(email);
		user.setPassword(password);
		user.setLoginId(loginId);
		Status status = new Status();
		status.setStatus(userService.insertUser(user));
		System.out.println(status.getStatus());
		return status;
	}
	
	@RequestMapping("/deleteTeamUser")
	public @ResponseBody Status deleteTeamUser(HttpSession httpSession, HttpServletResponse resp, String id) {
	
		Status status = new Status();
		status.setStatus(userService.deleteUserById(Long.parseLong(id)));
		System.out.println(status.getStatus());
		return status;
	}
	
	@RequestMapping("/teamUserEditPage")
	public String teamUserEditPage(String id, Model m, HttpSession httpSession) {
		
		u = (User) httpSession.getAttribute("user");
		System.out.println(u.getTeamId());
		
		User teamUser = userService.findUserById(Long.parseLong(id));
		
		m.addAttribute("teamUser", teamUser);
		
		List<UserRole> userRoles = userRoleService.findAllUserRolesByTeamId(u.getTeamId());
		Iterator<UserRole> iter = userRoles.iterator();
		while(iter.hasNext()){
			UserRole userRole = iter.next();
			if(userRole.getId()==teamUser.getRole()){
				m.addAttribute("userRole", userRole);
				iter.remove();
			}
		}
		m.addAttribute("userRoles", userRoles);
		
		return "auth/TeamManagement/teamUserEdit";
	}
	
	@RequestMapping("/updateTeamUser")
	public @ResponseBody Status updateTeamUser(HttpSession httpSession, HttpServletResponse resp, String id, String name, String role, String phone, String email, String password) {
		u = (User) httpSession.getAttribute("user");
		User teamUser = new User();
		teamUser.setId(Long.parseLong(id));
		teamUser.setName(name);
		teamUser.setRole(Long.parseLong(role));
		teamUser.setTeamId(u.getTeamId());
		teamUser.setEmail(email);
		teamUser.setPassword(password);
		
		Status status = new Status();
		userService.updateUserById(teamUser);
		status.setStatus(1);
		System.out.println(status.getStatus());
		return status;
	}
}
