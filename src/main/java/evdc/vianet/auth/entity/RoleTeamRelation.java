package evdc.vianet.auth.entity;

/**
 * @author jaden
 *
 * 2017年9月5日上午12:06:42
 */
public class RoleTeamRelation {
	public static final String TABLE_NAME = "auth_role_team_relation";
	private long teamId;
	private long teamRoleId;
	
	public RoleTeamRelation() {
		super();
	}
	public RoleTeamRelation(long teamId, long teamRoleId) {
		super();
		this.teamId = teamId;
		this.teamRoleId = teamRoleId;
	}
	public long getTeamId() {
		return teamId;
	}
	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}
	public long getTeamRoleId() {
		return teamRoleId;
	}
	public void setTeamRoleId(long teamRoleId) {
		this.teamRoleId = teamRoleId;
	}
	public static String getTableName() {
		return TABLE_NAME;
	}
	
}
