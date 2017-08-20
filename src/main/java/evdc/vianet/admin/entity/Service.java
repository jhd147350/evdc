package evdc.vianet.admin.entity;

/**
 * support的服务类型，还有该服务的工单会自动指派的组是什么
 * @author jhd147350
 *
 */
public class Service {
	public static final String TABLE_NAME = "admin_service";
	private long id;
	private String name;
	private long assignTeamId;

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

	public long getAssignTeamId() {
		return assignTeamId;
	}

	public void setAssignTeamId(long assignTeamId) {
		this.assignTeamId = assignTeamId;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", assignTeamId=" + assignTeamId + "]";
	}

}
