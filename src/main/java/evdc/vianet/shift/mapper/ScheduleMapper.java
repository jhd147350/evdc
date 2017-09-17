package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.shift.entity.Schedule;
import evdc.vianet.shift.entity.view.ViewShift;
import evdc.vianet.shift.entity.view.ViewTeamSchedule;

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
	
	/**
	 * @deprecated
	 * @return
	 */
	@Select("select * from tier_team, admin_shift_schedule where tier_team.id = `admin_shift_schedule`.teamId;")
	List<Team> selectAllTeamWithSchedule();
	/**
	 * @deprecated
	 * @return
	 */
	@Select("select * from tier_team, admin_shift_schedule where tier_team.id != `admin_shift_schedule`.teamId;")
	List<Team> selectAllTeamNoSchedule();
	
	@Select("select * from " + ViewTeamSchedule.VIEW_NAME)
	List<ViewTeamSchedule> selectAllTeamScheduleView();

}
