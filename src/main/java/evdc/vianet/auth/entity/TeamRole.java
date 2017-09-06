package evdc.vianet.auth.entity;

/**
 * @author jaden
 *
 * 2017年9月5日上午12:00:42
 */
public class TeamRole {
	public static final String TABLE_NAME = "auth_team_role";
	private long id;
	private String roleName;
	private long teamId;
	private long authValue;
	private int delete;
	
	public TeamRole() {
		super();
	}
	public TeamRole(long id, String roleName, long teamId, long authValue, int delete) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.teamId = teamId;
		this.authValue = authValue;
		this.delete = delete;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	public long getAuthValue() {
		return authValue;
	}
	public void setAuthValue(long authValue) {
		this.authValue = authValue;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public static String getTableName() {
		return TABLE_NAME;
	}
	
}
