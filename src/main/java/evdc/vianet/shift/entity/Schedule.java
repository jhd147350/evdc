package evdc.vianet.shift.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Schedule {

	public static final String TABLE_NAME = "admin_shift_schedule";
	private long id;
	private long teamId;
	private Date beginDate;
	private int circle;
	private long shiftId;
	private boolean enable;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public int getCircle() {
		return circle;
	}

	public void setCircle(int circle) {
		this.circle = circle;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", teamId=" + teamId + ", begainDate=" + beginDate + ", circle=" + circle
				+ ", shiftId=" + shiftId + ", enble=" + enable + "]";
	}

}
