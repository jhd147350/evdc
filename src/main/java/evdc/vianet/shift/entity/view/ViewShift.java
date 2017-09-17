package evdc.vianet.shift.entity.view;

import java.sql.Timestamp;

public class ViewShift {
	public static final String VIEW_NAME = "view_shift";
	private long id;
	private String name;
	private String createUser;
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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "ViewShift [id=" + id + ", name=" + name + ", createUser=" + createUser + ", updateDate=" + updateDate
				+ "]";
	}

}
