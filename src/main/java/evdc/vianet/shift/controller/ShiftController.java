package evdc.vianet.shift.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.shift.service.ShiftService;

/**
 * 值班管理控制器
 * 
 * @author jhd147350
 *
 */
@Controller
@RequestMapping("/admin/shift")
public class ShiftController {

	@Autowired
	@Qualifier("shiftService")
	private ShiftService shiftService;

	@RequestMapping("/edit")
	String editShift(Model m) {
		m.addAttribute("action", "editshift");
		return "admin/admin";
	}

	@RequestMapping("/test")
	String shiftManagePage1(Model m) {
		m.addAttribute("action", "add-shift");
		m.addAttribute("name", "jhd");
		return "shift/add-shift";
	}

	@RequestMapping("/generate")
	String generateShift(Model m) {
		m.addAttribute("action", "generateshift");
		return "admin/admin";
	}

	@RequestMapping("/query")
	String queryShift(Model m) {
		m.addAttribute("action", "queryshift");
		return "admin/admin";
	}

	@RequestMapping("/createshiftrule")
	String createShiftRule(Model m) {
		// m.addAttribute("action", "queryshift");
		return "shift/create-shift-rule";
	}
	

	@RequestMapping("/viewshiftrule")
	String viewShiftRule(Model m) {
		// m.addAttribute("action", "queryshift");
		return "shift/create-shift-rule";
	}
	
	@RequestMapping("/editshiftrule")
	String editShiftRule(Model m) {
		// m.addAttribute("action", "queryshift");
		return "shift/create-shift-rule";
	}
	
	@RequestMapping("/deleteshiftrule")
	String deleteShiftRule(Model m) {
		// m.addAttribute("action", "queryshift");
		return "shift/create-shift-rule";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	String createShiftRuleByJson(@RequestBody String json, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		System.out.println("create:" + json);

		return shiftService.createShift(json);
		// return JsonResult.SUC.toString();
	}

	@RequestMapping(value = "/shiftdata", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	String getShiftJson(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");

		// return shiftService.createShift(json);
		return shiftService.findAllShift();
		// return
		// "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"id\":10000,\"name\":\"user-0\",\"createUserId\":\"Ů\",\"updateDate\":\"����-0\"}]}";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	String editShiftRuleByJson(@RequestBody String json, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		// shiftService.createShift(json);

		return "admin/admin";
	}

}
