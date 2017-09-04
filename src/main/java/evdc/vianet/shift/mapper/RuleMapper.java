package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.shift.entity.Rule;

public interface RuleMapper {
	
	@Insert("")
	int insertRule(Rule rule);
	
	@Delete("")
	int deleteRuleById(long id);
	
	@Update("")
	int updateRule(Rule rule);
	
	@Select("")
	List<Rule> selectRuleByShiftId(long shiftId);

}
