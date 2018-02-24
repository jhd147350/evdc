package evdc.vianet.shift.v2;

import java.util.List;

public class ShiftRule {

	public static String TABLE_NAME = "admin_shift2_rule";
	private long id;
	private String name;
	private List<ShiftRuleOrder> orders;

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

	public List<ShiftRuleOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ShiftRuleOrder> orders) {
		this.orders = orders;
	}

}
