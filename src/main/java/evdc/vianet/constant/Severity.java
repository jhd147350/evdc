package evdc.vianet.constant;

/**
 * 严重等级
 * 
 * @author jhd147350
 *
 */
public class Severity {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 值
	 */
	private int value;
	
	

	public Severity(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Severity [name=" + name + ", value=" + value + "]";
	}

}
