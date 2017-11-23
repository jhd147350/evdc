package evdc.vianet.auth;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.entity.UserRole;
import evdc.vianet.auth.service.AuthorityService;
import evdc.vianet.auth.service.UserRoleService;
import evdc.vianet.auth.service.UserService;
import evdc.vianet.ticket.entity.TicketChangeRecord;
import evdc.vianet.ticket.service.TicketChangeRecordService;

/**
 * @author jaden
 *
 * 2017年9月4日下午11:41:07
 */
@Component
public class EvdcAuthFilter extends AuthFilter {
	@Autowired
	@Qualifier("authorityService")
	private AuthorityService authorityService;
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	@Autowired
	@Qualifier("ticketChangeRecordService")
	private TicketChangeRecordService ticketChangeRecordService;
	@Override
	protected int haveAuth(User u, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String path = request.getRequestURL().toString();
		System.out.println(path);
		//权限判断
		UserRole userRole = userRoleService.findUserRoleById(u.getRole());
		List<Authority> authoritys = authorityService.findAuthoritysByPath(path.split("evdc")[1]);
		
		if(authoritys.size()>=1){
			System.out.println("权限值要求为："+authoritys.get(0).getAuthValue());
			if(userRole.getAuthValue()==1||(userRole.getAuthValue()&authoritys.get(0).getAuthValue())>0){
				TicketChangeRecord ticketChangeRecord  = new TicketChangeRecord();
				if(authoritys.get(0).getType()!=null){
					switch (authoritys.get(0).getType()) {
					case "ticketSubcribe":
						
						ticketChangeRecord.setTicketId(Long.parseLong(request.getParameter("ticketId")));
						ticketChangeRecord.setUserId(u.getId());
						ticketChangeRecord.setFiled(authoritys.get(0).getAuthName());
						ticketChangeRecord.setNewValue("+: "+(request.getParameterValues("addArray[]").toString())+"/n-: "+(request.getParameterValues("reduceArray[]").toString()));
						ticketChangeRecordService.addNewRecord(ticketChangeRecord);
						break;
					case "ticketComment":
						ticketChangeRecord.setTicketId(Long.parseLong(request.getParameter("ticketId")));
						ticketChangeRecord.setUserId(u.getId());
						ticketChangeRecord.setFiled(authoritys.get(0).getAuthName());
						ticketChangeRecord.setNewValue(request.getParameter("comment"));
						ticketChangeRecordService.addNewRecord(ticketChangeRecord);
						break;
					case "ticketStatusChange":
						ticketChangeRecord.setTicketId(Long.parseLong(request.getParameter("ticketId")));
						ticketChangeRecord.setUserId(u.getId());
						ticketChangeRecord.setFiled(authoritys.get(0).getAuthName()+": "+request.getParameter("setStatus"));
						ticketChangeRecord.setNewValue(request.getParameter("comment"));
						ticketChangeRecordService.addNewRecord(ticketChangeRecord);
						break;
					case "updateTicketService":
						ticketChangeRecord.setTicketId(Long.parseLong(request.getParameter("ticketId")));
						ticketChangeRecord.setUserId(u.getId());
						ticketChangeRecord.setFiled(authoritys.get(0).getAuthName()+": "+request.getParameter("serviceBefore"));
						ticketChangeRecord.setNewValue(request.getParameter("serviceType"));
						ticketChangeRecordService.addNewRecord(ticketChangeRecord);
						break;
					case "updateTicketSeverity":
						ticketChangeRecord.setTicketId(Long.parseLong(request.getParameter("ticketId")));
						ticketChangeRecord.setUserId(u.getId());
						ticketChangeRecord.setFiled(authoritys.get(0).getAuthName()+": "+request.getParameter("severityBefore"));
						ticketChangeRecord.setNewValue(request.getParameter("severity"));
						ticketChangeRecordService.addNewRecord(ticketChangeRecord);
						break;
					default:
						break;
					}
				}
				
				return 0;
			}else{
				return 2;
			}
		}else{
			return 0;
		}
		
	}
	
	

}
