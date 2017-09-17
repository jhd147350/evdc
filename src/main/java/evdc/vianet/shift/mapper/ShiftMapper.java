package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.shift.entity.Shift;
import evdc.vianet.shift.entity.view.ViewShift;

public interface ShiftMapper {
	@Insert("insert into " + Shift.TABLE_NAME
			+ " (name,createUserId) values(#{name},#{createUserId})")
	int insertShift(Shift s);

	@Delete("delete from " + Shift.TABLE_NAME + " where id=#{id}")
	int deleteShiftById(long id);

	@Update("update " + Shift.TABLE_NAME
			+ " set name=#{name}, ownerTeamId=#{s.ownerTeamId}, createUserId=#{s.createUserId} where id=#{s.id}")
	int updateShiftById(Shift s);

	@Select("select * from " + Shift.TABLE_NAME + " where teamId=#{teamId}")
	List<Shift> selectShiftByTeamId(long teamId);

	@Select("select * from " + ViewShift.VIEW_NAME)
	List<ViewShift> selectAllShiftView();
	
	@Select("select * from " + Shift.TABLE_NAME)
	List<Shift> selectAllShift();
	
	@Select("select id from " + Shift.TABLE_NAME+" where name=#{name}")
	Long selectShiftIdByName(String name);
	
	@Select("select * from " + Shift.TABLE_NAME+" where id=#{id}")
	Shift selectShiftById(long id);
}
