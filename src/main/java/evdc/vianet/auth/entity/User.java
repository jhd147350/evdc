package evdc.vianet.auth.entity;

public class User {
	public static final String TABLE_NAME = "tier_user";

	/**
	 * user就是普通用户，只能对自己和客户提交的工单进行增删查改之类的操作<br>
	 * team是客户团队级别的管理员能对自己所属团队的工单进行增删查改操作，对自己组员进行增删改查操作，指定角色<br>
	 * admin最高管理员对所有团队，人员，工单进行增删改查，给其他人赋予admin和admin_team<br>
	 * admin > team > user
	 * 
	 * @author jia.haodong
	 *
	 */
	public enum Role {
		USER, TEAM, ADMIN
	}

	private long id;
	private String nickname;// 昵称
	private String role;
	private long teamId;
	private String phone;
	private String email;
	private String username;// 登录id，用户名
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", role=" + role + ", teamId=" + teamId + ", phone="
				+ phone + ", email=" + email + ", username=" + username + ", password=" + password + "]";
	}

}
