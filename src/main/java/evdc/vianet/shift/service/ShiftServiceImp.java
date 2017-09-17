package evdc.vianet.shift.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.mapper.UserMapper;
import evdc.vianet.shift.entity.Rule;
import evdc.vianet.shift.entity.Shift;
import evdc.vianet.shift.entity.Staff;
import evdc.vianet.shift.entity.jo.JsonResult;
import evdc.vianet.shift.entity.jo.TableData;
import evdc.vianet.shift.entity.view.ViewShift;
import evdc.vianet.shift.entity.view.ViewTeamSchedule;
import evdc.vianet.shift.mapper.RuleMapper;
import evdc.vianet.shift.mapper.ScheduleMapper;
import evdc.vianet.shift.mapper.ShiftMapper;
import evdc.vianet.shift.mapper.StaffMapper;

@Service("shiftService")
public class ShiftServiceImp implements ShiftService, ShiftServiceApi {

	@Autowired
	ShiftMapper shiftMapper;
	@Autowired
	RuleMapper ruleMapper;

	@Autowired
	ScheduleMapper scheduleMapper;

	@Autowired
	UserMapper userMapper;

	/**
	 * 接收的数据格式如下：<br>
	 * { "shiftname":"jhd", "createuserid":11, "rules":[{ "shiftname":"jhd",
	 * "starttime":"123", "endtime":"123", "order":1, "info":"info-test"}, {
	 * "shiftname":"jhd", "starttime":"123", "endtime":"123", "order":1,
	 * "info":"info-test"} ] }
	 * 
	 * @return
	 */
	@Override
	@Transactional // 支持事务
	public String createShift(String json) {
		System.out.println(json);
		JSONObject jo = new JSONObject(json);
		String shiftName = jo.getString("shiftname");
		long createUserId = jo.getLong("createuserid");
		JSONArray rules = jo.getJSONArray("rules");
		// Long selectShiftIdByName = shiftMapper.selectShiftIdByName(shiftName);
		if (shiftMapper.selectShiftIdByName(shiftName) != null) {
			return JsonResult.SAME_NAME.toString();
		}

		Shift shift = new Shift();
		shift.setName(shiftName);
		shift.setCreateUserId(createUserId);

		shiftMapper.insertShift(shift);
		System.out.println(shift.toString());
		long shiftId = shiftMapper.selectShiftIdByName(shiftName);

		List<Rule> ruleList = new ArrayList<>();
		// rules.length();
		for (int i = 0; i < rules.length(); i++) {
			JSONObject temp = rules.getJSONObject(i);
			// String startTime = temp.getString("starttime");
			// String endTime = temp.getString("endtime");
			int order = temp.getInt("order");
			String info = temp.getString("info");
			Rule r = new Rule();
			// SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
			Time startTime = Time.valueOf(temp.getString("starttime"));
			Time endTime = Time.valueOf(temp.getString("endtime"));
			r.setStartTime(startTime);
			r.setEndTime(endTime);
			r.setOrder(order);
			r.setInfo(info);
			r.setShiftId(shiftId);
			ruleList.add(r);
		}

		for (Rule rule : ruleList) {
			System.out.println(rule.toString());
			ruleMapper.insertRule(rule);
		}

		return JsonResult.SUC.toString();

	}

	/**
	 * { code: 0, msg: "", count: 1000, data: [] }
	 */
	@Override
	public String findAllShift() {
		List<ViewShift> allShift = shiftMapper.selectAllShiftView();
		TableData<ViewShift> dataShift = new TableData<ViewShift>();
		dataShift.setCode(200);
		dataShift.setCount(100);
		dataShift.setMsg("this is msg");
		dataShift.setData(allShift);
		JSONObject jo = new JSONObject(dataShift);
		System.out.println(jo.toString());
		return jo.toString();

	}

	@Override
	public void findShiftByTeamId(long teamId) {
		// TODO Auto-generated method stub

	}

	@Override
	public String deleteShift(long shiftid) {
		int delete = shiftMapper.deleteShiftById(shiftid);
		System.out.println("deleteShift:" + delete);

		return delete == 1 ? JsonResult.SUC.toString() : JsonResult.FAILED.toString();

	}

	@Override
	public void deleteShiftRule(String json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateShift(String json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadShiftAndRulesDataToModelByShiftId(long shiftId, Model m) {
		Shift shift = shiftMapper.selectShiftById(shiftId);
		m.addAttribute("shift", shift);
		m.addAttribute("rules", ruleMapper.selectRuleByShiftId(shiftId));
		String username = userMapper.findUserById(shift.getCreateUserId()).getName();
		m.addAttribute("username", username);

	}

	@Override
	public void getCreateSchedulePage(Long teamId, Model m) {

		List<User> users = userMapper.findAllUsersByTeamId(teamId);

		// List<ViewShift> selectAllShift = shiftMapper.selectAllShift();
		List<Shift> shifts = shiftMapper.selectAllShift();

		m.addAttribute("teamId", teamId);
		m.addAttribute("users", users);
		m.addAttribute("shifts", shifts);

	}

	@Override
	public void getDetailSchedulePage(String json, Model m) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getScheduleTeam(boolean hasSchedule) {
		// TODO Auto-generated method stub
		//List<Team> teams = new ArrayList<>();
		TableData<ViewTeamSchedule> data = new TableData<>();
		List<ViewTeamSchedule> teamScheduleView = scheduleMapper.selectAllTeamScheduleView();
		List<ViewTeamSchedule> teamsView=new ArrayList<>();

		for (ViewTeamSchedule viewTeamSchedule : teamScheduleView) {
			System.out.println(viewTeamSchedule.toString());
			if(hasSchedule) {
			
				if(viewTeamSchedule.getScheduleId()!=null) {
					teamsView.add(viewTeamSchedule);
				}
			}else {
				if(viewTeamSchedule.getScheduleId()==null) {
					teamsView.add(viewTeamSchedule);
				}
			}
			
		}

		data.setCount(100);
		if (hasSchedule) {
			data.setMsg("有排班");
			// teams = scheduleMapper.selectAllTeamWithSchedule();
		} else {
			data.setMsg("无排班");
			// teams = scheduleMapper.selectAllTeamNoSchedule();

		}

		data.setCode(200);

		//data.setData(teams);
		data.setData(teamsView);

		return new JSONObject(data).toString();

	}

}
