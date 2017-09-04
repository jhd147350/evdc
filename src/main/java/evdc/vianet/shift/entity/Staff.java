package evdc.vianet.shift.entity;

public class Staff {
	public static final String TABLE_NAME = "admin_shift_staff";
	private long id;
	private long shiftId;
	private long scheduleId;
	private int orderOfCircle;
	private int orderOfDay;
	private boolean isPrimary;
	private long userId;

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

	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getOrderOfCircle() {
		return orderOfCircle;
	}

	public void setOrderOfCircle(int orderOfCircle) {
		this.orderOfCircle = orderOfCircle;
	}

	public int getOrderOfDay() {
		return orderOfDay;
	}

	public void setOrderOfDay(int orderOfDay) {
		this.orderOfDay = orderOfDay;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", shiftId=" + shiftId + ", scheduleId=" + scheduleId + ", orderOfCircle="
				+ orderOfCircle + ", orderOfDay=" + orderOfDay + ", isPrimary=" + isPrimary + ", userId=" + userId
				+ "]";
	}

}
