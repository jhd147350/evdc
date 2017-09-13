package evdc.vianet.shift.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import evdc.vianet.shift.entity.Rule;
import evdc.vianet.shift.entity.Shift;

/**
 * 所有排班相关api，只返回json数据<br>
 * 只有组或最大管理员可以创建，只有本组或最大管理员才可以删除修改本组的值班规则
 * 
 * @author jhd147350
 *
 */


public interface ShiftServiceApi {

	

}
