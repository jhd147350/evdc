package evdc.vianet.shift.entity;

import java.sql.Time;

public class Rule {
	public static final String TABLE_NAME = "admin_shift_rule";
	private long id;
	private long shiftId;
	private String info;
	private int order;
	private Time startTime;
	private Time endTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Rule [id=" + id + ", shiftId=" + shiftId + ", info=" + info + ", order=" + order + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}
