package evdc.vianet.shift.entity;

import java.sql.Timestamp;

public class Cover {
	public static final String TABLE_NAME = "admin_shift_staff_cover";
	private long id;
	private long oldUserId;
	private long scheduleId;
	private Timestamp coverDate;
	private long newUserId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOldUserId() {
		return oldUserId;
	}

	public void setOldUserId(long oldUserId) {
		this.oldUserId = oldUserId;
	}

	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Timestamp getCoverDate() {
		return coverDate;
	}

	public void setCoverDate(Timestamp coverDate) {
		this.coverDate = coverDate;
	}

	public long getNewUserId() {
		return newUserId;
	}

	public void setNewUserId(long newUserId) {
		this.newUserId = newUserId;
	}

	@Override
	public String toString() {
		return "Cover [id=" + id + ", oldUserId=" + oldUserId + ", scheduleId=" + scheduleId + ", coverDate="
				+ coverDate + ", newUserId=" + newUserId + "]";
	}

}
