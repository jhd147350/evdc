package evdc.vianet.auth.entity;


/**
 * @author jaden
 *
 * 2017年9月8日上午9:35:05
 */
public class TeamRole {
	public static final String TABLE_NAME = "auth_team_role";
	private long id;
	private String roleName;
	private long authValue;
	private int delete;
	private String describe;
	public TeamRole() {
		super();
	}
	public TeamRole(long id, String roleName, long authValue, int delete, String describe) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.authValue = authValue;
		this.delete = delete;
		this.describe = describe;
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
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public static String getTableName() {
		return TABLE_NAME;
	}
	
}
