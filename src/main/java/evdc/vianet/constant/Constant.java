package evdc.vianet.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预定义的常量
 * 
 * @author jhd147350
 *
 */
public class Constant {
	static public List<Severity> sev = new ArrayList<>();
	static public Map<Integer, String> sevMap = new HashMap<>();
	static {
		sev.add(new Severity("Sev1", 1));
		sev.add(new Severity("Sev2", 2));
		sev.add(new Severity("Sev3", 3));
		sev.add(new Severity("Sev4", 4));

		sevMap.put(1, "Sev1");
		sevMap.put(2, "Sev2");
		sevMap.put(3, "Sev3");
		sevMap.put(4, "Sev4");

	}

}
