package evdc.vianet.shift.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import evdc.vianet.auth.entity.User;
import evdc.vianet.constant.ScheduleException;
import evdc.vianet.shift.entity.Rule;
import evdc.vianet.shift.entity.Shift;
import evdc.vianet.shift.entity.view.ViewOnDutyUser;

/**
 * 所有排班相关页面<br>
 * 只有组或最大管理员可以创建，只有本组或最大管理员才可以删除修改本组的值班规则
 * 
 * @author jhd147350
 *
 */


public interface ShiftService {

	/**
	 * 创建一个排班规则
	 * @param json
	 */
	String createShift(String json);
	
	/**
	 * 创建一个排班计划，schedule和staff
	 * @param json
	 */
	String createSchedule(String json);
	
	void getCreateSchedulePage(Long teamId, Model m);
	/**
	 * 
	 * @param teamId 团队id
	 * @param isWeekView 是表示按周查询，否表示按月查询
	 * @param date 查询所在的周或月的一天即可
	 * @param m 传给视图的数据
	 */
	void getDetailSchedulePage(long teamId, boolean isWeekView, int nextWeek, String date, Model m);
	
	String getScheduleTeam(boolean hasSchedule);

	/**
	 * 直接删除一个值班规则和所有关联的详细规则
	 * @param json
	 */
	String deleteShift(long shiftid);
	
	/**
	 * 直接删除一个团队的值班计划和所有关联的值班人员
	 * @param json
	 */
	String deleteSchedule(long teamid);
	
	void deleteShiftRule(String json);

	/**
	 * edit更新或删除值班规则或详细规则
	 * @param json
	 */
	void updateShift(String json);
	
	void loadShiftAndRulesDataToModelByShiftId(long shiftId, Model m);
	
	String findAllShift();

	void findShiftByTeamId(long teamId);
	
	/**
	 * 
	 * @param teamId
	 * @return 指定团队的（主/次）值班人
	 */
	List<ViewOnDutyUser> getOnDutyUsersByTeamId(long teamId, Calendar currentTime) throws ScheduleException;
	

}
