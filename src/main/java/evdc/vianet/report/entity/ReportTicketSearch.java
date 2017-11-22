package evdc.vianet.report.entity;

public class ReportTicketSearch {
	public static final String TABLE_NAME = "report_ticket_search";
	private long id;
	private String name;
	private String sql;
	private String describe;
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
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
