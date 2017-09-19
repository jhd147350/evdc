package evdc.vianet.ticket.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.Status;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.service.AuthorityService;
import evdc.vianet.auth.service.UserRoleService;
import evdc.vianet.auth.service.UserService;
import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.service.TicketAttachmentService;
import evdc.vianet.ticket.service.TicketSerService;
import evdc.vianet.ticket.service.TicketService;
import evdc.vianet.util.FtpServer;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	@Qualifier("ticketService")
	private TicketService ticketService;// =new UserServiceImp();
	@Autowired
	@Qualifier("authorityService")
	private AuthorityService authorityService;
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	@Autowired
	@Qualifier("ticketSerService")
	private TicketSerService ticketSerService;
	@Autowired
	@Qualifier("ftpServer")
	private FtpServer ftpServer;
	@Autowired
	@Qualifier("ticketAttachmentService")
	TicketAttachmentService ticketAttachmentService;
	
	private User u ;
	
	@RequestMapping("/ticketConsole")
	public String ticketConsole(Model m, HttpSession httpSession) {
		u = (User) httpSession.getAttribute("user");
		List<Authority> ticketFindAuthoritys = authorityService.findAuthoritysByType("ticketFind");
		List<Authority> authoritys = new ArrayList<Authority>();
		for (Authority authority : ticketFindAuthoritys) {
			if(userRoleService.findUserRoleById(u.getRole()).getAuthValue()%authority.getAuthValue()==0) {
				authoritys.add(authority);
			}
		}
		
		m.addAttribute("ticketServices", ticketSerService.findAllTicketService());
		List<Ticket> tickets;
		
		int methods = authoritys.size();
		if(methods==1) {
			tickets = ticketService.findAllTicketsBySubmitTeamAndKeyword(u.getTeamId(), ".*", ".*", ".*", "");
		}else {
			tickets = ticketService.findAllTicketsBySubscibeTeamAndKeyword(u.getTeamId(), ".*", ".*", ".*", "");
		}
		m.addAttribute("authoritys", authoritys);
		m.addAttribute("tickets", tickets);
		return "ticket/ticketConsole";
	}
	
	@RequestMapping(value="/findAllTicketsByAssignTeamAndKeyword",method=RequestMethod.POST)
	@ResponseBody
	public List<SearchTicket> findAllTicketsByAssignTeamAndKeyword(HttpSession httpSession, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");
		List<SearchTicket> searchTickets = new ArrayList<SearchTicket>();		
		List<Ticket> tickets = ticketService.findAllTicketsByAssignTeamAndKeyword(u.getTeamId(), service, status, severity, keyword);
		
		for (Ticket ticket : tickets) {
			SearchTicket search = new SearchTicket();
			search.setId(ticket.getId());
			search.setTitle(ticket.getTitle());
			search.setSeverity(ticket.getSeverity());
			search.setService(ticketSerService.findTicketServiceById(ticket.getServiceId()).getName());
			
			search.setStatus(ticket.getStatus());
			search.setUpdateDate(ticket.getUpdateDate());
			search.setSubmitUser(userService.findUserById(ticket.getSubmitUserId()).getName());
			search.setUpdateUser(userService.findUserById(ticket.getUpdateUserId()).getName());
			search.setAssignUser(userService.findUserById(ticket.getAssignUserId()).getName());
			searchTickets.add(search);
		}
		return searchTickets;	
	}
	@RequestMapping(value="/findAllTicketsBySubmitTeamAndKeyword",method=RequestMethod.POST)
	@ResponseBody
	public List<SearchTicket> findAllTicketsBySubmitTeamAndKeyword(HttpSession httpSession, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");
		List<SearchTicket> searchTickets = new ArrayList<SearchTicket>();		
		List<Ticket> tickets = ticketService.findAllTicketsBySubmitTeamAndKeyword(u.getTeamId(), service, status, severity, keyword);
		
		for (Ticket ticket : tickets) {
			SearchTicket search = new SearchTicket();
			search.setId(ticket.getId());
			search.setTitle(ticket.getTitle());
			search.setSeverity(ticket.getSeverity());
			search.setService(ticketSerService.findTicketServiceById(ticket.getServiceId()).getName());
			
			search.setStatus(ticket.getStatus());
			search.setUpdateDate(ticket.getUpdateDate());
			search.setSubmitUser(userService.findUserById(ticket.getSubmitUserId()).getName());
			search.setUpdateUser(userService.findUserById(ticket.getUpdateUserId()).getName());
			search.setAssignUser(userService.findUserById(ticket.getAssignUserId()).getName());
			searchTickets.add(search);
		}
		return searchTickets;	
	}
	
	
	@RequestMapping(value="/findAllTicketsBySubscibeTeamAndKeyword",method=RequestMethod.POST)
	@ResponseBody
	public List<SearchTicket> findAllTicketsBySubscibeTeamAndKeyword(HttpSession httpSession, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");
		List<SearchTicket> searchTickets = new ArrayList<SearchTicket>();		
		List<Ticket> tickets = ticketService.findAllTicketsBySubscibeTeamAndKeyword(u.getTeamId(), service, status, severity, keyword);
		
		for (Ticket ticket : tickets) {
			SearchTicket search = new SearchTicket();
			search.setId(ticket.getId());
			search.setTitle(ticket.getTitle());
			search.setSeverity(ticket.getSeverity());
			search.setService(ticketSerService.findTicketServiceById(ticket.getServiceId()).getName());
			
			search.setStatus(ticket.getStatus());
			search.setUpdateDate(ticket.getUpdateDate());
			search.setSubmitUser(userService.findUserById(ticket.getSubmitUserId()).getName());
			search.setUpdateUser(userService.findUserById(ticket.getUpdateUserId()).getName());
			search.setAssignUser(userService.findUserById(ticket.getAssignUserId()).getName());
			searchTickets.add(search);
		}
		return searchTickets;	
	}
	@RequestMapping(value="/findAllTicketsByKeyword",method=RequestMethod.POST)
	@ResponseBody
	public List<SearchTicket> findAllTicketsByKeyword(HttpSession httpSession, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");
		List<SearchTicket> searchTickets = new ArrayList<SearchTicket>();		
		List<Ticket> tickets = ticketService.findAllTicketsByKeyword(service, status, severity, keyword);
		
		for (Ticket ticket : tickets) {
			SearchTicket search = new SearchTicket();
			search.setId(ticket.getId());
			search.setTitle(ticket.getTitle());
			search.setSeverity(ticket.getSeverity());
			search.setService(ticketSerService.findTicketServiceById(ticket.getServiceId()).getName());
			
			search.setStatus(ticket.getStatus());
			search.setUpdateDate(ticket.getUpdateDate());
			search.setSubmitUser(userService.findUserById(ticket.getSubmitUserId()).getName());
			search.setUpdateUser(userService.findUserById(ticket.getUpdateUserId()).getName());
			search.setAssignUser(userService.findUserById(ticket.getAssignUserId()).getName());
			searchTickets.add(search);
		}
		return searchTickets;	
	}
	
	@RequestMapping(value="/ticketCreatePage",method=RequestMethod.GET)
	
	public String ticketCreatePage(Model m) {
		m.addAttribute("ticketServices", ticketSerService.findAllTicketService());
		return "ticket/ticketCreate";
		
	}
	@RequestMapping(value="/uploadTicketFile",method=RequestMethod.POST)
	@ResponseBody
	public FileUploadStatus uploadTicketFile(Model m,  MultipartHttpServletRequest request ) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		
		FileUploadStatus status = new FileUploadStatus();
		try {  
				CommonsMultipartFile multipartFile = null;
				Iterator<String> itr =  request.getFileNames();
				while(itr.hasNext()){
			         String str = itr.next();
			         multipartFile = (CommonsMultipartFile)request.getFile(str);
			         String fileName = multipartFile.getOriginalFilename();   //原文件名
			         String serFileName = df.format(new Date())+fileName;
			         MultipartFile mpf = request.getFile(str);
			         /*if(ftpServer.init()) {
				         System.out.println("开始上传:"+serFileName);
			        	 InputStream input = mpf.getInputStream();
			        	 ftpServer.execute(ftpServer.getMethod("UPLOAD"), serFileName, input, null);
			        	 
			         }*/
			         status.setStatus(0);
		        	 status.setTicketFilePath(serFileName);
		        	 status.setFileName(fileName);
			     }	     

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return status;	
	}
	@RequestMapping(value="/deleteTicketFile",method=RequestMethod.POST)
	@ResponseBody
	public Status deleteTicketFile(String serFileName) {
		Status status = new Status();
		/*if(ftpServer.init()) {
			ftpServer.execute(ftpServer.getMethod("DELETE"), serFileName, null, null);
			System.out.println("删除成功"+serFileName);
		}*/
		status.setStatus(0);
		return status;		
	}
	
	@RequestMapping(value="/createTicket",method=RequestMethod.POST)
	@ResponseBody
	public Status createTicket(HttpSession httpSession, String title, String description, String serviceType, String severity, @RequestParam(value = "fileName[]")String[] fileName, @RequestParam(value = "serFileName[]")String[] serFileName) {
		u = (User) httpSession.getAttribute("user");
		long ticketId = ticketService.createTicket("web", title, description, serviceType, severity, u.getId(), u.getTeamId());
		for(int i = 0; i < fileName.length-1; i++){
			ticketAttachmentService.addTicketAttachmentService(ticketId, 0, null, serFileName[i], fileName[i]);
		}
		Status status = new Status();
		status.setStatus(0);
		return status;		
	}
	
	class SearchTicket{
		private long id;
		private String title;
		private String severity;
		private String status;
		private String service;
		private String submitUser;
		private String assignUser;
		private String updateUser;
		private Timestamp updateDate;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getSeverity() {
			return severity;
		}
		public void setSeverity(String severity) {
			this.severity = severity;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getService() {
			return service;
		}
		public void setService(String service) {
			this.service = service;
		}
		public String getSubmitUser() {
			return submitUser;
		}
		public void setSubmitUser(String submitUser) {
			this.submitUser = submitUser;
		}
		public String getAssignUser() {
			return assignUser;
		}
		public void setAssignUser(String assignUser) {
			this.assignUser = assignUser;
		}
		public String getUpdateUser() {
			return updateUser;
		}
		public void setUpdateUser(String updateUser) {
			this.updateUser = updateUser;
		}
		public Timestamp getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Timestamp updateDate) {
			this.updateDate = updateDate;
		}
		
	}
	class FileUploadStatus{
		private int status;
		private String	ticketFilePath;
		private String fileName;
		public FileUploadStatus() {
			super();
			// TODO Auto-generated constructor stub
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int i) {
			this.status = i;
		}
		public String getTicketFilePath() {
			return ticketFilePath;
		}
		public void setTicketFilePath(String ticketFilePath) {
			this.ticketFilePath = ticketFilePath;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
	}
}
