package evdc.vianet.shift.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import evdc.vianet.shift.entity.Rule;
import evdc.vianet.shift.entity.Shift;

/**
 * 所有排班相关页面<br>
 * 只有组或最大管理员可以创建，只有本组或最大管理员才可以删除修改本组的值班规则
 * 
 * @author jhd147350
 *
 */


public interface ShiftService {

	/**
	 * 创建一个值班规则
	 * @param json
	 */
	String createShift(String json);
	
	void getCreateSchedulePage(String json, Model m);

	/**
	 * 直接删除一个值班规则和所有关联的详细规则
	 * @param json
	 */
	String deleteShift(long shiftid);
	
	void deleteShiftRule(String json);

	/**
	 * edit更新或删除值班规则或详细规则
	 * @param json
	 */
	void updateShift(String json);
	
	void loadShiftAndRulesDataToModelByShiftId(long shiftId, Model m);
	
	String findAllShift();

	void findShiftByTeamId(long teamId);

}
