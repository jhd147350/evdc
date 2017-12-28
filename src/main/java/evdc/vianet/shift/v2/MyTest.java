package evdc.vianet.shift.v2;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import evdc.vianet.test.SpringBaseTest;

public class MyTest extends SpringBaseTest {

	@Autowired
	@Qualifier("myTestRuleMapper")
	private RuleMapper mapper;

	@Test
	public void test() {
		List<ShiftRule> selectAllRules = mapper.selectAllRules();
		for (ShiftRule shiftRule : selectAllRules) {
			System.out.println(shiftRule.getName());
			if (shiftRule.getOrders() != null) {
				for (ShiftRuleOrder order : shiftRule.getOrders()) {
					System.out.println("--" + order.getNotes());
				}
			}
		}
	}
}
