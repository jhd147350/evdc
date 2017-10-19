package evdc.vianet.auth.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.auth.entity.ClientConfig;
import evdc.vianet.auth.entity.ConsoleList;
import evdc.vianet.auth.entity.Status;
import evdc.vianet.auth.entity.TeamRole;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.entity.UserRole;
import evdc.vianet.auth.service.AuthorityService;
import evdc.vianet.auth.service.ClientConfigService;
import evdc.vianet.auth.service.ConsoleListService;
import evdc.vianet.auth.service.TeamRoleService;
import evdc.vianet.auth.service.TeamService;
import evdc.vianet.auth.service.UserRoleService;
import evdc.vianet.auth.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;// =new UserServiceImp();
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	@Autowired
	@Qualifier("teamRoleService")
	private TeamRoleService teamRoleService;
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	@Autowired
	@Qualifier("authorityService")
	private AuthorityService authorityService;
	@Autowired
	@Qualifier("consoleListService")
	private ConsoleListService consoleListService;
	@Autowired
	@Qualifier("clientConfigService")
	ClientConfigService clientConfigService;
	
	@RequestMapping("/login")
	public String login(Model m, String loginId, String password) {
		
		return "auth/login";
		
	}
	@RequestMapping("/permissionDenied")
	public String permissionDenied() {
		
		return "auth/PermissionDenied";
		
	}
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String doLogin(String loginId, String password, Model m, HttpSession httpSession) {
		User u = userService.login(loginId, password);
		if(u!=null){
			httpSession.setMaxInactiveInterval(30*60);
			httpSession.setAttribute("user", u);
			return "redirect:/user/indexPage";
		}
		return "redirect:/user/login";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute("user");
		return "redirect:/user/login";
		
	}
	@RequestMapping("/indexPage")
	public String indexPage(HttpSession httpSession, Model m) {
		User u = (User) httpSession.getAttribute("user");
		m.addAttribute("user",u);
		long userAuth = userRoleService.findUserRoleById(u.getRole()).getAuthValue();
		List<ConsoleList> mainMeans = consoleListService.getMeansByFather(0);
		List<MeanModule> mainModules = new ArrayList<>(); 
		for (ConsoleList consoleList : mainMeans) {
			if((userAuth&consoleList.getValue()) > 0){
				MeanModule mainModule = new MeanModule(consoleList);
				List<ConsoleList> subCl = consoleListService.getMeansByFather(consoleList.getId());
				mainModule.setSubmodulesByConsoleList(userAuth , subCl);
				mainModules.add(mainModule);
			}
		}
		ClientConfig clientConfig = clientConfigService.getMeansByTeamRoleId((teamService.findTeamById(u.getTeamId())).getRole());
		m.addAttribute("mainModules", mainModules);
		m.addAttribute("defPage", consoleListService.getMeanById(clientConfig.getConsoleMeanId()).getPath());
		return "template";
	}
	@RequestMapping("/userAddPage")
	public String userAddPage(String teamId, Model m, HttpSession httpSession) {
		
		List<UserRole> userRoles = userRoleService.findAllUserRolesByTeamId(Long.parseLong(teamId));
		if(userRoles.size()>=1){
			
			
		}else{
			UserRole userRole = new UserRole();
			TeamRole teamRole = teamRoleService.findTeamRoleById(teamService.findTeamById(Long.parseLong(teamId)).getRole());	
			userRole.setRoleName("admin");
			userRole.setRoleTeamId(Long.parseLong(teamId));
			userRole.setAuthValue(teamRole.getAuthValue());
			userRole.setDelete(1);
			userRole.setDescribe("");
			userRoleService.insertUserRole(userRole);
			userRoles = userRoleService.findAllUserRolesByTeamId(Long.parseLong(teamId));
		}
		m.addAttribute("userRoles", userRoles);
		m.addAttribute("userTeamId", teamId);
		return "auth/UserManagement/userAdd";
	}
	@RequestMapping("/addUser")
	public @ResponseBody Status addUser(HttpSession httpSession, HttpServletResponse resp, String name, String role,String teamId, String phone, String email, String password, String loginId) {

		User user = new User();
		user.setName(name);
		user.setRole(Long.parseLong(role));
		user.setTeamId(Long.parseLong(teamId));
		user.setPhone(phone);
		user.setEmail(email);
		user.setPassword(password);
		user.setLoginId(loginId);
		Status status = new Status();
		status.setStatus(userService.insertUser(user));
		System.out.println(status.getStatus());
		return status;
	}
	
}
