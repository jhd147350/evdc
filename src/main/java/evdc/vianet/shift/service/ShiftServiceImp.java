package evdc.vianet.shift.service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.mapper.UserMapper;
import evdc.vianet.constant.Constant;
import evdc.vianet.constant.ScheduleException;
import evdc.vianet.shift.entity.Rule;
import evdc.vianet.shift.entity.Schedule;
import evdc.vianet.shift.entity.Shift;
import evdc.vianet.shift.entity.Staff;
import evdc.vianet.shift.entity.jo.JsonResult;
import evdc.vianet.shift.entity.jo.ScheduleResult;
import evdc.vianet.shift.entity.jo.ScheduleResult.MyDate;
import evdc.vianet.shift.entity.jo.ScheduleResult.MyStaff;
import evdc.vianet.shift.entity.jo.ScheduleResult.MyTimeDuration;
import evdc.vianet.shift.entity.jo.TableData;
import evdc.vianet.shift.entity.view.ViewOnDutyUser;
import evdc.vianet.shift.entity.view.ViewShift;
import evdc.vianet.shift.entity.view.ViewTeamSchedule;
import evdc.vianet.shift.mapper.RuleMapper;
import evdc.vianet.shift.mapper.ScheduleMapper;
import evdc.vianet.shift.mapper.ShiftMapper;
import evdc.vianet.shift.mapper.StaffMapper;

@Service("shiftService")
public class ShiftServiceImp implements ShiftService, ShiftServiceApi {

	@Autowired
	private ShiftMapper shiftMapper;

	@Autowired
	private RuleMapper ruleMapper;

	@Autowired
	private ScheduleMapper scheduleMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private StaffMapper staffMapper;

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
	public void getDetailSchedulePage(long teamId, Model m) {

		Schedule schedule = scheduleMapper.selectScheduleByTeamId(teamId);

		int circle = schedule.getCircle();

		Date beginDate = schedule.getBeginDate();

		List<Rule> rules = ruleMapper.selectRuleByShiftId(schedule.getShiftId());

		List<Staff> staffs = staffMapper.selectStaffByScheduleId(schedule.getId());

		List<ScheduleResult> results = new ArrayList<>();

		// Date today = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		// int dayOfWeek=c.get(Calendar.DAY_OF_WEEK);//周日是第一天

		LocalDate localDate = LocalDate.now();
		// 和周一差几天
		int mondayDiff = localDate.getDayOfWeek().getValue() - 1;

		// 将日期重置到这周的周一
		c.add(Calendar.DATE, -mondayDiff);
		// 一一周的数据为例子，TODO 这个数值以后可以根据传进来的参数来决定
		for (int i = 1; i <= 7; i++) {
			System.out.println("星期" + i + ":" + c.get(Calendar.DAY_OF_MONTH));

			// 和循环开始日期相差多少天
			int dayDiffToBegin = (int) ((c.getTimeInMillis() - beginDate.getTime()) / (1000 * 3600 * 24));
			if (dayDiffToBegin < 0) {
				break;// 跳出循环不计算这一天
			}
			// 计算这一天是circle的第几天根据开始日期和circle天数
			int dayOfCircle = dayDiffToBegin % circle + 1;

			ScheduleResult result = new ScheduleResult();
			// 有几条规则 就表名这一天分了几个班次
			result.setOrderOfDay(rules.size());
			MyDate date = result.new MyDate();
			date.setWeek("星期" + Constant.WEEK_ORDER_IN_CHINA.get(i - 1));
			date.setYear(c.get(Calendar.YEAR));
			date.setMonth(c.get(Calendar.MONTH) + 1);
			date.setDay(c.get(Calendar.DAY_OF_MONTH));
			result.setDate(date);
			List<MyTimeDuration> times = new ArrayList<>();
			List<MyStaff> myStaffsP = new ArrayList<>();
			List<MyStaff> myStaffsS = new ArrayList<>();

			for (int j = 0; j < rules.size(); j++) {
				MyTimeDuration time = result.new MyTimeDuration();
				time.setStartTime(rules.get(j).getStartTime() + "");
				time.setEndTime(rules.get(j).getEndTime() + "");
				times.add(time);
				int orderOfDay = j + 1;
				MyStaff myStaffP = result.new MyStaff();
				Staff staffP = getStaffByDayOfCircleAndOrderOfDay(dayOfCircle, orderOfDay, staffs, true);
				if (staffP != null) {
					myStaffP.setId(staffP.getUserId());
					myStaffP.setName(staffMapper.selectUserNameByUserId(staffP.getUserId()));
				} else {
					myStaffP.setId(0);
					myStaffP.setName("");
				}

				myStaffsP.add(myStaffP);

				MyStaff myStaffS = result.new MyStaff();
				Staff staffS = getStaffByDayOfCircleAndOrderOfDay(dayOfCircle, orderOfDay, staffs, false);
				if (staffS != null) {
					myStaffS.setId(staffS.getUserId());
					myStaffS.setName(staffMapper.selectUserNameByUserId(staffS.getUserId()));
				} else {
					myStaffS.setId(0);
					myStaffS.setName("");
				}

				myStaffsS.add(myStaffS);
			}

			result.setTimes(times);
			result.setStaffsS(myStaffsS);
			result.setStaffsP(myStaffsP);
			results.add(result);

			// 日期加一
			c.add(Calendar.DATE, 1);
		}
		m.addAttribute("scheduleResults", results);
	}

	/**
	 * 根据参数找到那个班次的员工是谁
	 * 
	 * @param dayOfCircle
	 * @param orderOfDay
	 * @param staffs
	 * @return
	 */
	private Staff getStaffByDayOfCircleAndOrderOfDay(int dayOfCircle, int orderOfDay, List<Staff> staffs,
			boolean isPrimary) {
		for (Staff s : staffs) {
			if (s.getOrderOfCircle() == dayOfCircle && s.getOrderOfDay() == orderOfDay && s.isPrimary() == isPrimary) {
				return s;
			}
		}
		return null;
	}

	@Override
	public String getScheduleTeam(boolean hasSchedule) {
		// TODO Auto-generated method stub
		// List<Team> teams = new ArrayList<>();
		TableData<ViewTeamSchedule> data = new TableData<>();
		List<ViewTeamSchedule> teamScheduleView = scheduleMapper.selectAllTeamScheduleView();
		List<ViewTeamSchedule> teamsView = new ArrayList<>();

		for (ViewTeamSchedule viewTeamSchedule : teamScheduleView) {
			System.out.println(viewTeamSchedule.toString());
			if (hasSchedule) {

				if (viewTeamSchedule.getScheduleId() != null) {
					teamsView.add(viewTeamSchedule);
				}
			} else {
				if (viewTeamSchedule.getScheduleId() == null) {
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

		// data.setData(teams);
		data.setData(teamsView);

		return new JSONObject(data).toString();

	}

	/**
	 * 值班计划和值班人员是一起写入数据库的
	 */
	@Override
	@Transactional
	public String createSchedule(String json) {
		// TODO Auto-generated method stub
		System.out.println(json);
		JSONObject jo = new JSONObject(json);
		long teamId = jo.getLong("teamId");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date beginDate;
		try {
			beginDate = new Date(sdf.parse(jo.getString("beginDate")).getTime());
		} catch (JSONException e) {
			e.printStackTrace();
			return JsonResult.FAILED.toString();
		} catch (ParseException e) {
			e.printStackTrace();
			return JsonResult.FAILED.toString();
		}

		int circle = jo.getInt("circle");
		long shiftId = jo.getLong("shiftId");
		// 值班计划
		Schedule schedule = new Schedule();
		schedule.setTeamId(teamId);
		schedule.setBeginDate(beginDate);
		schedule.setCircle(circle);
		schedule.setShiftId(shiftId);
		schedule.setEnable(true);
		// 插入值班计划
		scheduleMapper.insertSchedule(schedule);
		long scheduleId = scheduleMapper.selectScheduleIdByTeamId(teamId);
		// 值班人员
		List<Staff> staffs = new ArrayList<>();
		JSONArray staffsJA = jo.getJSONArray("staffs");
		for (int i = 0; i < staffsJA.length(); i++) {
			JSONObject staffJO = staffsJA.getJSONObject(i);
			long userId = staffJO.getLong("userId");
			int orderOfCircle = staffJO.getInt("orderOfCircle");
			int orderOfDay = staffJO.getInt("orderOfDay");
			boolean isPrimary = staffJO.getBoolean("isPrimary");
			Staff staff = new Staff();
			staff.setUserId(userId);
			staff.setOrderOfCircle(orderOfCircle);
			staff.setOrderOfDay(orderOfDay);
			staff.setPrimary(isPrimary);
			staff.setShiftId(shiftId);
			staff.setScheduleId(scheduleId);
			staffs.add(staff);
		}
		System.out.println(schedule.toString());
		for (Staff temp : staffs) {
			staffMapper.insertStaff(temp);
			System.out.println(temp.toString());
		}

		return JsonResult.SUC.toString();
	}

	@Override
	public List<ViewOnDutyUser> getOnDutyUsersByTeamId(long teamId, Calendar currentTime) throws ScheduleException {
		// int orderOfCircle=0;
		int orderOfDay = 0;

		Schedule schedule = scheduleMapper.selectScheduleByTeamId(teamId);
		// 改排班所用到的规则

		if (schedule == null) {
			throw new ScheduleException("所查团队还没进行过排班");
		}
		// rules应该是按order排序的，没个order的时间应当遵循从小到大，最后一个班可以跨天
		List<Rule> rules = ruleMapper.selectRuleByShiftId(schedule.getShiftId());

		if (rules == null) {
			throw new ScheduleException("所查团队使用的排班规则为空");
		}

		// -----
		Calendar c = Calendar.getInstance();
		if (currentTime != null) {
			c = currentTime;
		}
		// c.set(Calen, value);
		// Calendar cBefore = (Calendar) c.clone();
		// cBefore.add(Calendar.DATE, -1);
		// Calendar cAfter = (Calendar) c.clone();
		// cAfter.add(Calendar.DATE, 1);

		// -----

		int h = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		// 不计算秒了int s = c.get(Calendar.SECOND);

		// 当时 是这一天的第几分钟
		int currentMinute = h * 60 + m;

		boolean span = false;
		// 和第一个班次和最后一个班次的比较都是很重要的
		for (int i = 0; i < rules.size(); i++) {
			// boolean isAfterSpan = false;
			// 第一个班次
			if (i == 0) {
				Time startTime = rules.get(i).getStartTime();
				int startMinute = startTime.getHours() * 60 + startTime.getMinutes();
				Time endTime = rules.get(i).getEndTime();
				int endMinute = endTime.getHours() * 60 + endTime.getMinutes();
				// 8:00-20:00的班 如果当前时间是8:00 则当前属于8:00-20:00的班次，否则可能是上一天跨天的班次
				if (currentMinute < startMinute) {// TODO
					Rule lastRule = rules.get(rules.size() - 1);
					Time laststartTime = lastRule.getStartTime();
					int laststartMinute = laststartTime.getHours() * 60 + laststartTime.getMinutes();
					Time lastendTime = lastRule.getEndTime();
					int lastendMinute = lastendTime.getHours() * 60 + lastendTime.getMinutes();
					// 开始时间大于结束时间表名改班次跨天了，比如：20:00-8:00的班次
					if (laststartTime.getTime() > lastendTime.getTime()) {// 跨天
						// isAfterSpan = true;
						if (currentMinute < lastendMinute) {
							orderOfDay = lastRule.getOrder();
							System.out.println("orderOfDay：" + orderOfDay);
							span = true;
							break;
						}
					} else {// 不跨天

						if (currentMinute >= laststartMinute && currentMinute < lastendMinute) {
							orderOfDay = lastRule.getOrder();
							System.out.println("orderOfDay：" + orderOfDay);
							span = true;
							break;// 跳出循环
						}
					}

				} else if (currentMinute < endMinute) {// 20:00 属于8:00-20:00,20:00-8:00中的后者
					orderOfDay = rules.get(i).getOrder();
					System.out.println("orderOfDay：" + orderOfDay);
					break;// 跳出循环
				}
			} else if (i != 0 && i < (rules.size() - 1)) {
				Time startTime = rules.get(i).getStartTime();
				int startMinute = startTime.getHours() * 60 + startTime.getMinutes();
				Time endTime = rules.get(i).getEndTime();
				int endMinute = endTime.getHours() * 60 + endTime.getMinutes();
				// 8:00-20:00的班 如果当前时间是8:00 则当前属于8:00-20:00的班次，否则可能是上一天跨天的班次
				if (currentMinute >= startMinute && currentMinute < endMinute) {
					orderOfDay = rules.get(i).getOrder();
					System.out.println("orderOfDay：" + orderOfDay);
					break;// 跳出循环
				}
			}

			// 最后一个班次才可能跨天
			else if (i == (rules.size() - 1)) {
				Time startTime = rules.get(i).getStartTime();
				int startMinute = startTime.getHours() * 60 + startTime.getMinutes();
				Time endTime = rules.get(i).getEndTime();
				int endMinute = endTime.getHours() * 60 + endTime.getMinutes();

				// 开始时间大于结束时间表名改班次跨天了，比如：20:00-8:00的班次
				if (startTime.getTime() > endTime.getTime()) {// 跨天
					// isAfterSpan = true;
					if (currentMinute >= startMinute) {
						orderOfDay = rules.get(i).getOrder();
						System.out.println("orderOfDay：" + orderOfDay);
						break;

					}
				} else {// 不跨天

					if (currentMinute >= startMinute && currentMinute < endMinute) {
						orderOfDay = rules.get(i).getOrder();
						System.out.println("orderOfDay：" + orderOfDay);
						break;// 跳出循环
					}
				}

			}

		}

		// 先计算这个团队的班次循环周期是多少
		int circle = schedule.getCircle();

		Date beginDate = schedule.getBeginDate();

		// 和循环开始日期相差多少天
		int dayDiffToBegin = (int) ((c.getTimeInMillis() - beginDate.getTime()) / (1000 * 3600 * 24));

		// 计算这一天是circle的第几天根据开始日期和circle天数
		int orderOfCircle = dayDiffToBegin % circle + 1;
		if (span) {
			orderOfCircle--;
		}

		// TODO 计算 这一天是那个循环的第几天，那个次序

		// staffMapper.selectOnDutyUsersByTeamId(teamId, orderOfCircle, orderOfDay);
		return staffMapper.selectOnDutyUsersByTeamId(teamId, orderOfCircle, orderOfDay);
	}

	@Override
	public String deleteSchedule(long teamid) {
		int delete = scheduleMapper.deleteScheduleByTeamId(teamid);
		System.out.println("deleteSchedule:" + delete);

		return delete == 1 ? JsonResult.SUC.toString() : JsonResult.FAILED.toString();
	}

}
