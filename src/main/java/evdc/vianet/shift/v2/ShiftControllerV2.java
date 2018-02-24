package evdc.vianet.shift.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/shiftv2")
public class ShiftControllerV2 {
	
	@Autowired
	@Qualifier("shiftServiceV2")
	private ShiftServiceV2 shiftService;
	
	@RequestMapping("")
	String check() {
		// m.addAttribute("action", "queryshift");
		return "shiftv2/schedule";
	}
	
	@ResponseBody
	@RequestMapping("/test")
	String check1() {
		// m.addAttribute("action", "queryshift");
		return "shift/pop/rule-create";
	}

}
