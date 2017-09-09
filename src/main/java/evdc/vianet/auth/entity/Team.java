package evdc.vianet.auth.entity;

/**
 * @author jaden
 *
 * 2017年9月9日下午6:17:36
 */
public class Team {
	public static final String TABLE_NAME = "tier_team";
	private long id;
	private String name;
	private long role;
	private String companyName;
	private String code;
	private int	delete;
	private String description;

	
	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Team(long id, String name, long role, String companyName, String code, int delete, String description) {
		super();
		this.id = id;
		this.name = name;
		this.role = role;
		this.companyName = companyName;
		this.code = code;
		this.delete = delete;
		this.description = description;
	}


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


	public long getRole() {
		return role;
	}


	public void setRole(long role) {
		this.role = role;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getDelete() {
		return delete;
	}


	public void setDelete(int delete) {
		this.delete = delete;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public static String getTableName() {
		return TABLE_NAME;
	}


	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", role=" + role + ", companyName=" + companyName
				+ ", code=" + code + ", description=" + description + "]";
	}

}
