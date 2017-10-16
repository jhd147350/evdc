package evdc.vianet.auth.entity;

/**
 * @author jaden
 *
 * 2017年10月11日下午5:06:46
 */
public class ConsoleList {
	public static final String TABLE_NAME = "console_list";
	private long id;
	private String listname;
	private long value;
	private long father;
	private String path;
	public ConsoleList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ConsoleList(long id, String listname, long value, long father, String path) {
		super();
		this.id = id;
		this.listname = listname;
		this.value = value;
		this.father = father;
		this.path = path;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getListname() {
		return listname;
	}
	public void setListname(String listname) {
		this.listname = listname;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public long getFather() {
		return father;
	}
	public void setFather(long father) {
		this.father = father;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public static String getTableName() {
		return TABLE_NAME;
	}
	
}
