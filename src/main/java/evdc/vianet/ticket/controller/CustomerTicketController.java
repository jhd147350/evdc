package evdc.vianet.ticket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.auth.entity.User;
import evdc.vianet.ticket.entity.SearchTicketsAndCount;
import evdc.vianet.ticket.entity.view.CustomerTicketView;

import evdc.vianet.ticket.service.CustomerTicketService;
import evdc.vianet.ticket.service.TicketSerService;


/**

 * @ClassName: CustomerTicketController

 * @Description: TODO

 * @author: jaden

 * @date: 2017年12月13日 上午10:54:31


 */
@Controller
@RequestMapping("/customerTicket")
public class CustomerTicketController {
	@Autowired
	@Qualifier("ticketSerService")
	private TicketSerService ticketSerService;// =new UserServiceImp();
	@Autowired
	@Qualifier("customerTicketService")
	private CustomerTicketService customerTicketService;
	private User u ;
	
	/**
	
	 * @Title: customerTicketConsole
	
	 * @Description: TODO
	显示customer工单控制台
	 * @param m
	 * @param httpSession
	 * @return
	
	 * @return: String
	
	 */
	@RequestMapping("/customerTicketConsole")
	public String customerTicketConsole(Model m, HttpSession httpSession) {	
	
		m.addAttribute("ticketServices", ticketSerService.findAllTicketService());
		return "ticket/customerTicketConsole";
	}
	
	/**
	
	 * @Title: findAllTicketsBySubmitTeamAndKeyword
	
	 * @Description: TODO
	根据客户组获取客户工单
	 * @param httpSession
	 * @param page
	 * @param limit
	 * @param keyword
	 * @param service
	 * @param severity
	 * @param status
	 * @return
	
	 * @return: SearchTicketsAndCount
	
	 */
	@RequestMapping(value="/findTicketByCustomerTeamAndKeyword",method=RequestMethod.POST)
	@ResponseBody
	public SearchTicketsAndCount<CustomerTicketView> findTicketByCustomerTeamAndKeyword(HttpSession httpSession, String page, String limit, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");
		int limitint = Integer.parseInt(limit);
		int pageint = Integer.parseInt(page);
		int limit1 = (pageint - 1) * limitint;
		List<CustomerTicketView> customerTicketViews = customerTicketService.findAllTicketViewsByCustomerTeamAndKeywordANDPageANDLimit(limit1, limitint, u.getTeamId(), service, status, severity, keyword);
		int count = customerTicketService.findAllCustomerTicketCountByCustomerTeamAndKeyword(u.getTeamId(), service, status, severity, keyword);
		SearchTicketsAndCount<CustomerTicketView> andCount = new SearchTicketsAndCount<CustomerTicketView>();
		andCount.setCount(count);
		andCount.setTicketViewList(customerTicketViews);
		andCount.setCode(200);
		return andCount;	
	}
}
