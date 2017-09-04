package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.shift.entity.Schedule;

public interface ScheduleMapper {
	
	@Insert("")
	int insertSchedule(Schedule schedule);
	
	@Delete("")
	int deleteScheduleById(long id);
	
	@Update("")
	int updateSchedule(Schedule schedule);
	
	@Select("")
	List<Schedule> selectScheduleByTeamId(long teamId);
	
	@Select("")
	Schedule selectScheduleById(long Id);
	
	@Select("")
	List<Schedule> selectAllSchedule();

}
