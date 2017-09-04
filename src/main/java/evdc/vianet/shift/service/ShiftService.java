package evdc.vianet.shift.service;

import evdc.vianet.shift.entity.Shift;

/**
 * 所有排班相关业务<br>
 * 只有组或最大管理员可以创建，只有本组或最大管理员才可以删除修改本组的值班规则
 * 
 * @author jhd147350
 *
 */
public interface ShiftService {

	void createShift(Shift s);

	void deleteShift(long id);

	void updateShiftName(Shift s);

	void findShiftById(long id);
	
	void findAllShift();

	void findShiftByTeamId(long teamId);

}
