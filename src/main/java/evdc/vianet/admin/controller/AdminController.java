package evdc.vianet.admin.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping
	public String admin(Model m) {
		m.addAttribute("title", "EvDC工单系统后台管理");
		return "admin/admin";
	}

	@RequestMapping("/login")
	public String login() {
		return "sign/login";
	}

	@RequestMapping("/team/add")
	public String addTeam(Model m) {
		m.addAttribute("action", "addteam");
		return "admin/admin";

	}
	@RequestMapping("/team/search")
	public String searchTeam(Model m) {
		m.addAttribute("action", "searchteam");
		return "admin/admin";

	}

	@ResponseBody
	@RequestMapping(value = "/test", produces = "application/json; charset=utf-8")
	public String test(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		return "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"id\":10000,\"username\":\"user-0\",\"sex\":\"Ů\",\"city\":\"����-0\",\"sign\":\"ǩ��-0\",\"experience\":255,\"logins\":24,\"wealth\":82830700,\"classify\":\"����\",\"score\":57},{\"id\":10001,\"username\":\"user-1\",\"sex\":\"��\",\"city\":\"����-1\",\"sign\":\"ǩ��-1\",\"experience\":884,\"logins\":58,\"wealth\":64928690,\"classify\":\"����\",\"score\":27}]}";
	}
}
