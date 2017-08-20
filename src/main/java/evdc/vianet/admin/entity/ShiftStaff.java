package evdc.vianet.admin.entity;

public class ShiftStaff {
	public static final String TABLE_NAME = "admin_shift_staff";
	private long id;
	private long shiftId;
	private int orderOfCircle;// 一个周期中的第几个
	private int orderOfDay;// 一天中的第几个
	private boolean isPrimary;// 主值班人
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
		return "ShiftStaff [id=" + id + ", shiftId=" + shiftId + ", orderOfCircle=" + orderOfCircle + ", orderOfDay="
				+ orderOfDay + ", isPrimary=" + isPrimary + ", userId=" + userId + "]";
	}

}
