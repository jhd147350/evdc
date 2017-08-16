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
	public String doLogin(String email, String password, Model m, HttpServletRequest request) {
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		m.addAttribute("email", email);
		User login = service.login(u);
		if (login != null) {
			request.getSession().setAttribute("user", login);
			if (login.getRole().equals(User.Role.ADMIN.toString())) {
				return "admin/admin";
			}
			return "console/main";
		}
		return "redirect:/user/login";
	}

}
