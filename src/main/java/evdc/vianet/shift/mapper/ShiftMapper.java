package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.shift.entity.Shift;

public interface ShiftMapper {
	@Insert("insert into " + Shift.TABLE_NAME
			+ " (name,ownerTeamId,createUserId) values(#{s.name},#{s.ownerTeamId},#{s.createUserId}))")
	int insertShift(Shift s);

	@Delete("delete from" + Shift.TABLE_NAME + " where id=#{id}")
	int deleteShiftById(long id);

	@Update("update " + Shift.TABLE_NAME
			+ " set name=#{name}, ownerTeamId=#{s.ownerTeamId}, createUserId=#{s.createUserId} where id=#{s.id}")
	int updateShiftById(Shift s);

	@Select("select * from " + Shift.TABLE_NAME + " where teamId=#{teamId}")
	List<Shift> selectShiftByTeamId(long teamId);

	@Select("select * from " + Shift.TABLE_NAME)
	List<Shift> selectAllShift();
}
