package evdc.vianet.shift.v2;

import java.sql.Date;

public class Schedule {
	public static final String TABLE_NAME = "admin_shift2_schedule";

	private long id;
	private long uId;
	private long replaceUId;
	private Date startDate;
	private Date endDate;
	private boolean isPrimary;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}

	public long getReplaceUId() {
		return replaceUId;
	}

	public void setReplaceUId(long replaceUId) {
		this.replaceUId = replaceUId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

}
