package evdc.vianet.shift.service;



/**
 * 所有排班相关业务<br>
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

	/**
	 * 直接删除一个值班规则和所有关联的详细规则
	 * @param json
	 */
	void deleteShift(String json);
	
	void deleteShiftRule(String json);

	/**
	 * edit更新或删除值班规则或详细规则
	 * @param json
	 */
	void updateShift(String json);

	void findShiftById(long id);
	
	String findAllShift();

	void findShiftByTeamId(long teamId);

}
