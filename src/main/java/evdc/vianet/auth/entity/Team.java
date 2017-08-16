package evdc.vianet.auth.entity;

public class Team {
	public static final String TABLE_NAME = "tier_user";
	private int id;
	private String name;
	private boolean isClient;
	private String companyName;
	private String code;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", isClient=" + isClient + ", companyName=" + companyName
				+ ", code=" + code + ", description=" + description + "]";
	}

}
