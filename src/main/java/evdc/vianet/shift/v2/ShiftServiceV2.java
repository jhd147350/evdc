package evdc.vianet.shift.v2;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import evdc.vianet.constant.ScheduleException;
import evdc.vianet.shift.entity.view.ViewOnDutyUser;
import evdc.vianet.shift.service.ShiftService;

@Service("shiftServiceV2")
public class ShiftServiceV2 implements ShiftService{

	@Override
	public String createShift(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSchedule(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getCreateSchedulePage(Long teamId, Model m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDetailSchedulePage(long teamId, boolean isWeekView, int nextWeek, String date, Model m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getScheduleTeam(boolean hasSchedule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteShift(long shiftid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSchedule(long teamid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShiftRule(String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateShift(String json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadShiftAndRulesDataToModelByShiftId(long shiftId, Model m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findAllShift() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findShiftByTeamId(long teamId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ViewOnDutyUser> getOnDutyUsersByTeamId(long teamId, Calendar currentTime) throws ScheduleException {
		// TODO Auto-generated method stub
		return null;
	}

}
