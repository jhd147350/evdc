package evdc.vianet.admin.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping("/team/search")
	public String searchTeam(Model m) {
		m.addAttribute("action", "searchteam");
		return "admin/admin";

	}

	@ResponseBody
	@RequestMapping(value = "/test", produces = "application/json; charset=utf-8")
	public String test(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		return "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"id\":10000,\"username\":\"user-0\",\"sex\":\"女\",\"city\":\"城市-0\",\"sign\":\"签名-0\",\"experience\":255,\"logins\":24,\"wealth\":82830700,\"classify\":\"作家\",\"score\":57},{\"id\":10001,\"username\":\"user-1\",\"sex\":\"男\",\"city\":\"城市-1\",\"sign\":\"签名-1\",\"experience\":884,\"logins\":58,\"wealth\":64928690,\"classify\":\"词人\",\"score\":27}]}";
	}
}
