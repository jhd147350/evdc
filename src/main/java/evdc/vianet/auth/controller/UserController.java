package evdc.vianet.auth.controller;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService service;// =new UserServiceImp();

	@RequestMapping("/login")
	public String login() {
		return "sign/login";
	}

	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String doLogin(String loginId, String password, Model m, HttpServletRequest request) {

		// m.addAttribute("email", email);

		// -----拦截“jhd” “123”
		/*
		 * if (email.equals("jhd") && password.equals("123")) {
		 * request.getSession().setAttribute("user", u); return "admin/admin"; }
		 */
		User login = service.login(loginId, password);
		if (login != null) {
			boolean client = service.isClient(login.getTeamId());
			m.addAttribute("isClient", client);
			m.addAttribute("isTeamAdmin", login.isTeamAdmin());
			request.getSession().setAttribute("user", login);
			if (client) {
				return "console/main";
			} else {
				return "console/main";
			}
		}
		return "redirect:/user/login";
	}
}
