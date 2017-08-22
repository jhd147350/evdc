package evdc.vianet.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("/login")
	public String login() {
		return "sign/login";
	}
	@RequestMapping("/team/add")
	public String addTeam(Model m) {
		m.addAttribute("action", "addteam");
		return "admin/admin";
		
	}
}
