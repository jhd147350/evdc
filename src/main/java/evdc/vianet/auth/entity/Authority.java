package evdc.vianet.auth.entity;


/**
 * @author jaden
 *
 * 2017年9月8日上午9:33:28
 */
public class Authority {
	public static final String TABLE_NAME = "auth_authority";
	private long id;
	private String authName;
	private String path;
	private long authValue;
	private String describe;
	public Authority() {
		super();
	}
	public Authority(long id, String authName, String path, long authValue, String describe) {
		super();
		this.id = id;
		this.authName = authName;
		this.path = path;
		this.authValue = authValue;
		this.describe = describe;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getAuthValue() {
		return authValue;
	}
	public void setAuthValue(long authValue) {
		this.authValue = authValue;
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
