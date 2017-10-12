package evdc.vianet.emailticket.task;

import java.sql.Timestamp;

public class EmailAccount {
	static public final String TABLE_NAME="email_account";
	private long id;
	private String email;
	private String password;
	private Timestamp updateDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	

}
