package evdc.vianet.shift.entity.view;

import evdc.vianet.auth.entity.Team;

public class ViewTeamSchedule extends Team {
	public static final String VIEW_NAME = "view_team_schedule";
	private Long scheduleId;

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public String toString() {
		return super.toString() + " schedule:" + scheduleId;
	}

}
