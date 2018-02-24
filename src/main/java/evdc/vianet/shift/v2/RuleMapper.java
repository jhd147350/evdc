package evdc.vianet.shift.v2;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Repository("myTestRuleMapper")
public interface RuleMapper {
	
	@Select("select * from admin_shift_rule2")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name",property="name"),
		@Result(column="id",property="orders",//使用rule的id去order表中查找属于它的orders
		many=@Many(
				select="evdc.vianet.shift.v2.RuleMapper.selectShiftRuleOrderByRuleId",fetchType=FetchType.LAZY))
		
	})
	public List<ShiftRule> selectAllRules();
	
	//为了1对多的查询条件
	@Select("select * from admin_shift_order where ruleId = #{ruleId}")
	public ShiftRuleOrder selectShiftRuleOrderByRuleId(long ruleId);

}
