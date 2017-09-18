package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.User;
import evdc.vianet.shift.entity.Schedule;
import evdc.vianet.shift.entity.Staff;

public interface StaffMapper {

	@Insert("insert into " + Staff.TABLE_NAME
			+ " (shiftId,scheduleId,orderOfCircle,orderOfDay,isPrimary,userId) values(#{shiftId},#{scheduleId},#{orderOfCircle},#{orderOfDay},#{isPrimary},#{userId})")
	int insertStaff(Staff staff);

	@Delete("")
	int deleteStaffById(long id);

	@Update("")
	int updateStaff(Staff staff);

	@Select("select * from "+Staff.TABLE_NAME+" where scheduleId=#{scheduleId}")
	List<Staff> selectStaffByScheduleId(long scheduleId);
	/*
	@Select("")
	List<Staff> selectStaffSByScheduleId(long scheduleId);
	*/
	
	@Select("select name from " + User.TABLE_NAME + " where `id`=#{userId}")
	String selectUserNameByUserId(long userId);

}
