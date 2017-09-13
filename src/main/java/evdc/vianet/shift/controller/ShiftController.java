package evdc.vianet.shift.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.shift.entity.Shift;
import evdc.vianet.shift.entity.jo.TableData;
import evdc.vianet.shift.service.ShiftService;

/**
 * 值班管理控制器
 * 
 * @author jhd147350
 *
 */
@Controller
@RequestMapping("/shift")
public class ShiftController {

	@Autowired
	@Qualifier("shiftService")
	private ShiftService shiftService;

	@RequestMapping("/rule")
	String rulePage(Model m) {
		m.addAttribute("action", "rule");
		return "admin/admin";
	}
	
	@ResponseBody
	@RequestMapping(value = "/rule/delete", method = RequestMethod.DELETE)
	String deleteShift(Long shiftid) {
		return shiftService.deleteShift(shiftid);
	}

	@RequestMapping("/rule/create")
	String createShiftRule() {
		// m.addAttribute("action", "queryshift");
		return "shift/pop/rule-create";
	}

	@RequestMapping("/rule/detail")
	String detailShiftRule(Long shiftid, HttpServletRequest request, Model m) {
		System.out.println(request.getRequestURI() + ":" + request.getQueryString());
		System.out.println(shiftid);
		shiftService.loadShiftAndRulesDataToModelByShiftId(shiftid, m);
		return "shift/pop/rule-detail";
	}
	
	@RequestMapping("/schedule")
	String queryShift(Model m) {
		m.addAttribute("action", "schedule");
		return "admin/admin";
	}

	
	@RequestMapping("/schedule/create")
	String createSchedulePop(Long teamid, Model m) {
		//m.addAttribute("action", "rule");
		shiftService.getCreateSchedulePage("", m);
		return "shift/pop/schedule-create";
	}
	
	@RequestMapping("/schedule/detail")
	String detailSchedulePop(Long teamid, Model m) {
		//m.addAttribute("action", "rule");
		shiftService.getCreateSchedulePage("", m);
		return "shift/pop/schedule-detail";
	}
	
	@RequestMapping("/schedule/edit")
	String editSchedulePop(Long teamid, Model m) {
		//m.addAttribute("action", "rule");
		shiftService.getCreateSchedulePage("", m);
		return "shift/pop/schedule-edit";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	String createShiftRuleByJson(@RequestBody String json, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		System.out.println("create:" + json);

		//TODO iframe 和 父页面传值问题需要解决
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

	@RequestMapping(value = "/teamschedule", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	String getTeamSchedule(Boolean schedule, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");

		List<Team> teams1 = new ArrayList<>();
		List<Team> teams2 = new ArrayList<>();

		Team t1 = new Team();
		t1.setId(1);
		t1.setName("有排班");

		Team t2 = new Team();
		t2.setId(2);
		t2.setName("无排班");

		teams1.add(t1);
		teams2.add(t2);
		TableData<Team> data = new TableData<>();
		data.setCode(200);
		data.setCount(100);
		if (schedule) {
			data.setMsg("有排班");
			data.setData(teams1);

		} else {
			data.setMsg("无排班");
			data.setData(teams2);
		}
		return new JSONObject(data).toString();
		// return
		// "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"id\":10000,\"name\":\"user-0\",\"createUserId\":\"Ů\",\"updateDate\":\"����-0\"}]}";

	}

}
