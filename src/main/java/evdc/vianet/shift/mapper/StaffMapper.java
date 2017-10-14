package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.User;
import evdc.vianet.shift.entity.Schedule;
import evdc.vianet.shift.entity.Staff;
import evdc.vianet.shift.entity.view.ViewOnDutyUser;

public interface StaffMapper {

	@Insert("insert into " + Staff.TABLE_NAME
			+ " (shiftId,scheduleId,orderOfCircle,orderOfDay,isPrimary,userId) values(#{shiftId},#{scheduleId},#{orderOfCircle},#{orderOfDay},#{isPrimary},#{userId})")
	int insertStaff(Staff staff);

	@Delete("")
	int deleteStaffById(long id);

	@Update("")
	int updateStaff(Staff staff);

	@Select("select * from " + Staff.TABLE_NAME + " where scheduleId=#{scheduleId}")
	List<Staff> selectStaffByScheduleId(long scheduleId);
	/*
	 * @Select("") List<Staff> selectStaffSByScheduleId(long scheduleId);
	 */

	@Select("select name from " + User.TABLE_NAME + " where `id`=#{userId}")
	String selectUserNameByUserId(long userId);

	//TODO 多个参数问题 http://www.cnblogs.com/EasonJim/p/7056256.html 
	@Select("SELECT * FROM " + ViewOnDutyUser.VIEW_NAME
			+ " where teamId=#{arg0} and orderOfCircle=#{arg1} and orderOfDay=#{arg2};")
	List<ViewOnDutyUser> selectOnDutyUsersByTeamId(long teamId, int orderOfCircle, int orderOfDay);

}
