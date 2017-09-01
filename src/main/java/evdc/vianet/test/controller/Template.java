package evdc.vianet.test.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author jaden
 *
 * 2017年9月1日下午2:17:35
 */
@Controller
@RequestMapping("/getTemplate")
public class Template {
	
	
	@RequestMapping("/t1")
	public String login() {
		return "template";
	}
	
}