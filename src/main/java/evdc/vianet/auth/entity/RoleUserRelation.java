package evdc.vianet.auth.entity;

/**
 * @author jaden
 *
 * 2017年9月5日上午12:05:30
 */
public class RoleUserRelation {
	public static final String TABLE_NAME = "auth_role_user_relation";
	private long userId;
	private long userRoleId;
	
	public RoleUserRelation() {
		super();
	}
	public RoleUserRelation(long userId, long userRoleId) {
		super();
		this.userId = userId;
		this.userRoleId = userRoleId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}
	public static String getTableName() {
		return TABLE_NAME;
	}
	
}
