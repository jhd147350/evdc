package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.shift.entity.Staff;

public interface StaffMapper {

	@Insert("")
	int insertStaff(Staff staff);

	@Delete("")
	int deleteStaffById(long id);

	@Update("")
	int updateStaff(Staff staff);

	@Select("")
	List<Staff> selectStaffByScheduleId(long scheduleId);

}
