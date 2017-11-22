package evdc.vianet.emailticket.task;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import evdc.vianet.shift.entity.jo.JsonResult;
import evdc.vianet.shift.entity.jo.TableData;
import evdc.vianet.ticket.entity.TicketMessage;
import evdc.vianet.ticket.service.TicketCommentService;
import evdc.vianet.ticket.service.TicketService;

@Service("emailTicketService")
public class EmailTicketService {

	@Autowired
	EmailMapper mapper;

	@Autowired
	@Qualifier("ticketService")
	TicketService ticketService;
	
	@Autowired
	@Qualifier("ticketCommentService")
	TicketCommentService ticketCommentService;

	public String getEmailJson(long page, long limit) {
		List<Email> emails = mapper.selectAllUnhandledEmail((page - 1) * limit, limit);
		long count = mapper.countAllUnhandledEmail();
		TableData<Email> jsonData = new TableData<>();
		jsonData.setCode(200);
		jsonData.setCount(count);
		jsonData.setMsg("this is suc msg");
		jsonData.setData(emails);
		JSONObject jo = new JSONObject(jsonData);
		// System.out.println(jo.toString());
		return jo.toString();

	}

	public String getEmailTicketJson(long page, long limit, String idorkey, String status, String service,
			String startdate, String enddate, String client) {
		// List<EmailTicket> emails = mapper.selectAllEmailTicket((page - 1) * limit,
		// limit);
		List<EmailTicket> emails = mapper.searchEmailTicket((page - 1) * limit, limit, idorkey, status, service,
				startdate, enddate, client);
		long count = mapper.countSearchEmailTicket(idorkey, status, service, startdate, enddate, client);
		TableData<EmailTicket> jsonData = new TableData<>();
		jsonData.setCode(200);
		jsonData.setCount(count);
		jsonData.setMsg("this is suc msg");
		jsonData.setData(emails);
		JSONObject jo = new JSONObject(jsonData);
		return jo.toString();

	}

	// 导出数据就不需要分页处理了
	public List<EmailTicket> getEmailTickets(String idorkey, String status, String service, String startdate,
			String enddate, String client) {
		// List<EmailTicket> emails = mapper.selectAllEmailTicket((page - 1) * limit,
		// limit);
		// (page - 1) * limit, limit
		List<EmailTicket> emails = mapper.searchEmailTicket(null, 0l, idorkey, status, service, startdate, enddate,
				client);
		return emails;

	}

	public void getEmailDetail(long id, Model m) {
		m.addAttribute("m", mapper.selectEmailById(id));
	}

	public void getEmailTicketDetail(long id, Model m) {

		m.addAttribute("emails", mapper.selectAllEmailsByTicketId(id));
		m.addAttribute("ticket", mapper.selectEmailTicketById(id));
	}

	@Transactional
	public String createEmailTicket(String json, long uid, long tid) throws ParseException {
		JSONObject jo = new JSONObject(json);
		String emailId = jo.getString("emailId");
		String title = jo.getString("title");
		String severity = jo.getString("severity");
		String service = jo.getString("service");
		String desc = jo.getString("desc");
		String timestamp = jo.getString("timestamp");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date temp = sdf.parse(timestamp);

		Timestamp ts = new Timestamp(temp.getTime());

		long ticketId = ticketService.createTicket("email", title, desc, service, severity, uid, tid);

		System.out.println("自动生成的id：" + ticketId);
		addNote2Email(desc, ticketId, emailId);
		
		ticketCommentService.addTicketComment(ticketId, uid, tid, desc, TicketMessage.Scope.Shared.toString());

		return JsonResult.SUC.toString();

	}

	public String mergeEmailTicket(String json) {
		JSONObject jo = new JSONObject(json);
		String emailId = jo.getString("emailId");
		String ticketId = jo.getString("ticketId");
		String note = jo.getString("note");
		long idL = Long.parseLong(ticketId);
		EmailTicket ticket = mapper.selectEmailTicketById(idL);
		if (ticket == null) {
			return JsonResult.FAILED.toString();
		}
		addNote2Email(note, idL, emailId);
		return JsonResult.SUC.toString();
	}

	private void addNote2Email(String note, long ticketId, String id) {
		long idL = Long.parseLong(id);
		mapper.updateNoteById(note, ticketId, idL);
	}

	public void deleteMail(Long ticketId) {
		mapper.updateDeleteById(true, ticketId);
	}

	public void resetMail(Long id) {
		mapper.updateTicketId0ById(id);
	}

	@Transactional
	public int deleteEmailTicket(Long id) {

		mapper.updateTicketId0ByTicketId(id);

		return mapper.deleteEmailTicketById(id);

	}

	public void closeEmailTicket(Long id) {
		mapper.updateStatusByTicketId("close", id);
	}

	public void reopenEmailTicket(Long id) {
		mapper.updateStatusByTicketId("open", id);
	}

}
