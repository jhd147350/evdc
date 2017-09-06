package evdc.vianet.shift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 值班管理控制器
 * 
 * @author jhd147350
 *
 */
@Controller
@RequestMapping("/admin/shift")
public class ShiftController {

	@RequestMapping("/edit")
	String shiftManagePage(Model m) {
		m.addAttribute("action", "addshift");
		return "admin/admin";
	}
	@RequestMapping("/test")
	String shiftManagePage1(Model m) {
		m.addAttribute("action", "add-shift");
		m.addAttribute("name", "jhd");
		return "shift/add-shift";
	}

}
