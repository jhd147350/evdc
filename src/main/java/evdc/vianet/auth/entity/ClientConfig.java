package evdc.vianet.auth.entity;

/**
 * @author jaden
 *
 * @date	2017年10月14日上午10:23:24
 */
public class ClientConfig {
	public static final String TABLE_NAME = "client_config";
	private long teamRoleId;
	private String ticketselectId;
	private long consoleMeanId;
	public ClientConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getTeamRoleId() {
		return teamRoleId;
	}
	public void setTeamRoleId(long teamRoleId) {
		this.teamRoleId = teamRoleId;
	}
	public String getTicketselectId() {
		return ticketselectId;
	}
	public void setTicketselectId(String ticketselectId) {
		this.ticketselectId = ticketselectId;
	}
	public long getConsoleMeanId() {
		return consoleMeanId;
	}
	public void setConsoleMeanId(long consoleMeanId) {
		this.consoleMeanId = consoleMeanId;
	}
	public static String getTableName() {
		return TABLE_NAME;
	}
	
}
