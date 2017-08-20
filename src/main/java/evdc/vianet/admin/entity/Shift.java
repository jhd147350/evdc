package evdc.vianet.admin.entity;

/**
 * 排班的规则<br>
 * 要想给某个团队排班，必须要创建一个排班规则，然后按这个规则去排班 
 * 
 * @see ShiftRule
 * @author jhd147350
 *
 */
public class Shift {
	public static final String TABLE_NAME = "admin_shift";
	private long id;
	private String name;

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

	@Override
	public String toString() {
		return "Shift [id=" + id + ", name=" + name + "]";
	}

}
