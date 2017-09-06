package evdc.vianet.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.auth.service.UserService;
import evdc.vianet.ticket.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	@Qualifier("ticketService")
	private TicketService service;// =new UserServiceImp();

	@RequestMapping("/create")
	public String createTicketPage() {
		return "console/createTicket";
		
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public String clickCreateTicketButton(@RequestBody String json) {
		//service.createTicket();
		System.out.println(json);
		return "console/createTicket";
		
	}
	@RequestMapping(value="/create1",method=RequestMethod.POST)
	@ResponseBody
	public String clickCreateTicketButton1( String name,String city) {
		//service.createTicket();
		System.out.println(name+":"+city);
		return "test";
		
	}
}
