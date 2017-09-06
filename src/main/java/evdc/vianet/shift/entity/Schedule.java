package evdc.vianet.shift.entity;

import java.sql.Timestamp;

public class Schedule {

	public static final String TABLE_NAME = "admin_shift_schedule";
	private long id;
	private long teamId;
	private Timestamp begainDate;
	private int circle;
	private long shiftId;
	private boolean enble;

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

	public Timestamp getBegainDate() {
		return begainDate;
	}

	public void setBegainDate(Timestamp begainDate) {
		this.begainDate = begainDate;
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

	public boolean isEnble() {
		return enble;
	}

	public void setEnble(boolean enble) {
		this.enble = enble;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", teamId=" + teamId + ", begainDate=" + begainDate + ", circle=" + circle
				+ ", shiftId=" + shiftId + ", enble=" + enble + "]";
	}

}
