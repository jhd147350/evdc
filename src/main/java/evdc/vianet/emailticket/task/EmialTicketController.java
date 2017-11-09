package evdc.vianet.emailticket.task;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.shift.entity.jo.JsonResult;
import evdc.vianet.ticket.entity.TicketSer;
import evdc.vianet.ticket.service.TicketSerService;
import evdc.vianet.ticket.service.TicketSerServiceImp;

@Controller
@RequestMapping("/emailticket")
public class EmialTicketController {

	@Autowired
	@Qualifier("emailTicketService")
	EmailTicketService service;

	@Autowired
	@Qualifier("ticketSerService")
	TicketSerService tss;

	@RequestMapping(value = "/emaildata", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getEmailData(HttpServletResponse response, Long page, Long limit) {
		response.setCharacterEncoding("utf-8");
		// System.out.println("data:--------" + service.getEmailTicketJson());
		return service.getEmailJson(page, limit);

	}

	@RequestMapping(value = "/ticketdata", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getEmailTicketData(HttpServletResponse response, Long page, Long limit, String status, String service,
			String idorkey, String startdate, String enddate, String client) {
		response.setCharacterEncoding("utf-8");
		// System.out.println("data:--------" + service.getEmailTicketJson());

		System.out.println(status);
		System.out.println(service);
		System.out.println(idorkey);

		System.out.println(startdate);
		System.out.println(enddate);

		return this.service.getEmailTicketJson(page, limit, idorkey, status, service, startdate, enddate, client);

	}

	@RequestMapping("/main")
	public String getEmailTicketPage() {
		return "emailticket/main";

	}

	@RequestMapping("/console")
	public String getEmailTicketConsolePage(Model m) {
		List<TicketSer> allServices = tss.findAllTicketService();
		m.addAttribute("services", allServices);
		return "emailticket/console";

	}

	@RequestMapping("/detail")
	public String getEmailDetailPage(Long id, Model m) {
		service.getEmailDetail(id, m);
		m.addAttribute("emailId", id);

		List<TicketSer> allServices = tss.findAllTicketService();
		m.addAttribute("services", allServices);
		return "emailticket/emaildetail";

	}

	@RequestMapping("/ticketdetail")
	public String getEmailTicketDetailPage(Long id, Model m) {
		service.getEmailTicketDetail(id, m);
		return "emailticket/emailticketdetail";

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	String createEmailTicket(@RequestBody String json, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");

		System.out.println(json);

		try {
			return service.createEmailTicket(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "err";
	}

	@RequestMapping(value = "/merge", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	String mergeEmailTicket(@RequestBody String json, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		System.out.println(json);
		return service.mergeEmailTicket(json);
	}

	// JNOTE http://blog.csdn.net/dxmgood/article/details/52881642 produces =
	// "application/json; charset=utf-8" 会把使返回数据直接就是json对象，所以无需在ajax函数中转换var data =
	// JSON.parse(result);
	// TODO produces = "application/json; charset=utf-8"
	// 会把使返回数据直接就是json对象，所以无需在ajax函数中转换var data = JSON.parse(result);
	// @RequestMapping(value = "/deletemail", method = RequestMethod.DELETE,
	// produces = "application/json; charset=utf-8")
	@RequestMapping(value = "/deletemail", method = RequestMethod.DELETE)
	@ResponseBody
	String deleteEmail(Long id, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		service.deleteMail(id);
		return JsonResult.SUC.toString();
	}

	@RequestMapping(value = "/deleteticket", method = RequestMethod.DELETE)
	@ResponseBody
	String deleteEmailTicket(Long id, Model m) {
		int i = service.deleteEmailTicket(id);
		if (i <= 0) {
			return JsonResult.FAILED.toString();
		}
		return JsonResult.SUC.toString();
	}

	@RequestMapping(value = "/reset", method = RequestMethod.DELETE)
	@ResponseBody
	String resetEmail(Long id, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		service.resetMail(id);
		return JsonResult.SUC.toString();
	}

	@RequestMapping(value = "/closeticket", method = RequestMethod.DELETE)
	@ResponseBody
	String closeEmailTicket(Long id) {
		service.closeEmailTicket(id);
		return JsonResult.SUC.toString();
	}

	@RequestMapping(value = "/reopenticket", method = RequestMethod.DELETE)
	@ResponseBody
	String reopenEmailTicket(Long id) {
		service.reopenEmailTicket(id);
		return JsonResult.SUC.toString();
	}

	@RequestMapping("/export")
	public void exportProject(HttpServletResponse response, String status, String service, String idorkey,
			String startdate, String enddate, String client) {
		String[] excelHeader = { "ID#id", "标题#title", "客户#client", "状态#status", "服务#service", "时间#timestamp" };
		List<EmailTicket> projectList = this.service.getEmailTickets(idorkey, status, service, startdate, enddate, client);

		try {
			ExportExcelUtil.export(response, "EvDC邮件工单" + LocalDate.now().toString(), excelHeader, projectList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
