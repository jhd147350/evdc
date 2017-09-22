package evdc.vianet.shift.entity.view;

import evdc.vianet.auth.entity.User;

public class ViewOnDutyUser extends User {
	public static final String VIEW_NAME="view_on_duty_user"; 
	private int orderOfCircle;
	private int orderOfDay;
	private boolean isPrimary;

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

}
