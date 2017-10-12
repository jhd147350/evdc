package evdc.vianet.emailticket.task;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/emailticket")
public class EmialTicketController {

	@Autowired
	@Qualifier("emailTicketService")
	EmailTicketService service;

	@RequestMapping(value = "/data", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getEmailTicketData(HttpServletResponse response, Long page,Long limit) {
		response.setCharacterEncoding("utf-8");
		//System.out.println("data:--------" + service.getEmailTicketJson());
		return service.getEmailTicketJson(page,limit);

	}

	@RequestMapping("/main")
	public String getEmailTicketPage() {
		return "emailticket/main";

	}
	
	@RequestMapping("/detail")
	public String getEmailTicketDetailPage(Long id,Model m) {
		service.getEmailDetail(id, m);
		return "emailticket/emaildetail";

	}

}
