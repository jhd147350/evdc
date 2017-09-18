package evdc.vianet.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共的常量
 * 
 * @author jhd147350
 *
 */
public class Constant {
	static public List<Severity> sev = new ArrayList<>();
	static public Map<Integer, String> sevMap = new HashMap<>();
	
	/**
	 * 周一到周日索引从1-7
	 */
	public static List<String> WEEK_ORDER_IN_CHINA=new ArrayList<>();
	
	static {
		sev.add(new Severity("Sev1", 1));
		sev.add(new Severity("Sev2", 2));
		sev.add(new Severity("Sev3", 3));
		sev.add(new Severity("Sev4", 4));

		sevMap.put(1, "Sev1");
		sevMap.put(2, "Sev2");
		sevMap.put(3, "Sev3");
		sevMap.put(4, "Sev4");
		
		WEEK_ORDER_IN_CHINA.add("一");
		WEEK_ORDER_IN_CHINA.add("二");
		WEEK_ORDER_IN_CHINA.add("三");
		WEEK_ORDER_IN_CHINA.add("四");
		WEEK_ORDER_IN_CHINA.add("五");
		WEEK_ORDER_IN_CHINA.add("六");
		WEEK_ORDER_IN_CHINA.add("日");

	}

}
