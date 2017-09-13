package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.shift.entity.Rule;

public interface RuleMapper {
	
	//order是mysql系统关键字，所以要加上``否则会报错
	@Insert("insert into " + Rule.TABLE_NAME
			+ " (shiftId,info,`order`,startTime,endTime) values(#{shiftId},#{info},#{order},#{startTime},#{endTime})")
	int insertRule(Rule rule);
	
	@Delete("")
	int deleteRuleById(long id);
	
	@Update("")
	int updateRule(Rule rule);
	
	@Select("select * from "+Rule.TABLE_NAME+" where shiftId=#{shiftId}")
	List<Rule> selectRuleByShiftId(long shiftId);

}
