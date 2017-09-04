package evdc.vianet.shift.entity;

import java.sql.Timestamp;

public class Shift {
	public static final String TABLE_NAME = "admin_shift";
	private long id;
	private String name;
	private long createUserId;
	private Timestamp updateDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Shift [id=" + id + ", name=" + name + ", createUserId=" + createUserId + ", updateDate=" + updateDate
				+ "]";
	}

}
