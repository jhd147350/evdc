package evdc.vianet.ticket.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import evdc.vianet.auth.entity.ClientConfig;
import evdc.vianet.auth.entity.Status;
import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.service.AuthorityService;
import evdc.vianet.auth.service.ClientConfigService;
import evdc.vianet.auth.service.TeamService;
import evdc.vianet.auth.service.UserRoleService;
import evdc.vianet.auth.service.UserService;
import evdc.vianet.ticket.entity.Ticket;
import evdc.vianet.ticket.entity.TicketAttachment;
import evdc.vianet.ticket.entity.view.TicketMessageView;
import evdc.vianet.ticket.entity.view.TicketView;
import evdc.vianet.ticket.service.TicketAttachmentService;
import evdc.vianet.ticket.service.TicketCommentService;
import evdc.vianet.ticket.service.TicketSerService;
import evdc.vianet.ticket.service.TicketService;
import evdc.vianet.ticket.service.TicketSubscribeTeamService;
import evdc.vianet.util.FtpServer;

/**

 * @ClassName: TicketController

 * @Description: TODO

 * @author: jaden

 * @date: 2017年10月18日 上午9:24:52


 */
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
	@Autowired
	@Qualifier("clientConfigService")
	ClientConfigService clientConfigService;
	@Autowired
	@Qualifier("teamService")
	TeamService teamService;
	@Autowired
	@Qualifier("ticketSubscribeTeamService")
	TicketSubscribeTeamService ticketSubscribeTeamService;
	@Autowired
	@Qualifier("ticketCommentService")
	TicketCommentService ticketCommentService;
	
	private User u ;
	//权限判断
	@RequestMapping("/ticketConsole")
	public String ticketConsole(Model m, HttpSession httpSession) {
		u = (User) httpSession.getAttribute("user");		
		ClientConfig clientConfig = clientConfigService.getMeansByTeamRoleId((teamService.findTeamById(u.getTeamId())).getRole());
		String[] selects = clientConfig.getTicketselectId().split(";");
		List<Authority> authoritys = new ArrayList<Authority>();
		for (String sele : selects) {
			Authority authority = authorityService.findAuthById(Integer.parseInt(sele));
			authoritys.add(authority);
		}
		m.addAttribute("ticketServices", ticketSerService.findAllTicketService());
		m.addAttribute("authoritys", authoritys);
		return "ticket/ticketConsole";
	}
	
	@RequestMapping(value="/findAllTicketsByAssignTeamAndKeyword",method=RequestMethod.POST)
	@ResponseBody
	public SearchTicketsAndCount findAllTicketsByAssignTeamAndKeyword(HttpSession httpSession, String page, String limit, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");	
		int limitint = Integer.parseInt(limit);
		int pageint = Integer.parseInt(page);
		int limit1 = (pageint - 1) * limitint;
		List<TicketView> ticketViews = ticketService.findAllTicketViewsByAssignTeamAndKeywordANDPageANDLimit(limit1, limitint, u.getTeamId(), service, status, severity, keyword);
		int count = ticketService.findAllTicketCountByAssignTeamAndKeyword(u.getTeamId(), service, status, severity, keyword);
		SearchTicketsAndCount andCount = new SearchTicketsAndCount();
		andCount.setCount(count);
		andCount.setTicketViewList(ticketViews);
		andCount.setCode(200);
		return andCount;	
	}
	@RequestMapping(value="/findAllTicketsBySubmitTeamAndKeyword",method=RequestMethod.POST)
	@ResponseBody
	public SearchTicketsAndCount findAllTicketsBySubmitTeamAndKeyword(HttpSession httpSession, String page, String limit, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");
		int limitint = Integer.parseInt(limit);
		int pageint = Integer.parseInt(page);
		int limit1 = (pageint - 1) * limitint;
		List<TicketView> ticketViews = ticketService.findAllTicketViewsBySubmitTeamAndKeywordANDPageANDLimit(limit1, limitint, u.getTeamId(), service, status, severity, keyword);
		int count = ticketService.findAllTicketCountBySubmitTeamAndKeyword(u.getTeamId(), service, status, severity, keyword);
		SearchTicketsAndCount andCount = new SearchTicketsAndCount();
		andCount.setCount(count);
		andCount.setTicketViewList(ticketViews);
		andCount.setCode(200);
		return andCount;	
	}
	@RequestMapping(value="/findAllTicketsBySubscribeTeamAndKeyword",method=RequestMethod.POST)
	@ResponseBody
	public SearchTicketsAndCount findAllTicketsBySubscribeTeamAndKeyword(HttpSession httpSession, String page, String limit, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");
		int limitint = Integer.parseInt(limit);
		int pageint = Integer.parseInt(page);
		int limit1 = (pageint - 1) * limitint;
		List<TicketView> ticketViews = ticketService.findAllTicketViewsBySubscribeTeamAndKeywordANDPageANDLimit(limit1, limitint, u.getTeamId(), service, status, severity, keyword);
		int count = ticketService.findAllTicketCountBySubscribeTeamAndKeyword(u.getTeamId(), service, status, severity, keyword);
		SearchTicketsAndCount andCount = new SearchTicketsAndCount();
		andCount.setCount(count);
		andCount.setTicketViewList(ticketViews);
		andCount.setCode(200);
		return andCount;	
	}
	@RequestMapping(value="/findAllTicketsByKeyword",method=RequestMethod.POST)
	@ResponseBody
	public SearchTicketsAndCount findAllTicketsByKeyword(HttpSession httpSession, String page, String limit, String keyword, String service, String severity, String status) {
		u = (User) httpSession.getAttribute("user");		
		int limitint = Integer.parseInt(limit);
		int pageint = Integer.parseInt(page);
		int limit1 = (pageint - 1) * limitint;
		List<TicketView> ticketViews = ticketService.findAllTicketViewsByKeywordANDPageANDLimit(limit1, limitint, service, status, severity, keyword);
		int count = ticketService.findAllTicketCountByKeyword(service, status, severity, keyword);
		SearchTicketsAndCount andCount = new SearchTicketsAndCount();
		andCount.setCount(count);
		andCount.setTicketViewList(ticketViews);
		andCount.setCode(200);
		return andCount;	
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
			         if(ftpServer.init()) {
				         System.out.println("开始上传:"+serFileName);
			        	 InputStream input = mpf.getInputStream();
			        	 ftpServer.execute(ftpServer.getMethod("UPLOAD"), serFileName, input, null);	 
			         }
			         status.setStatus(0);
		        	 status.setTicketFilePath(serFileName);
		        	 status.setFileName(fileName);
			     }	     

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;	
	}
	@RequestMapping(value="/deleteTicketFile",method=RequestMethod.POST)
	@ResponseBody
	public Status deleteTicketFile(String serFileName) {
		Status status = new Status();
		if(ftpServer.init()) {
			ftpServer.execute(ftpServer.getMethod("DELETE"), serFileName, null, null);
			System.out.println("删除成功"+serFileName);
		}
		status.setStatus(0);
		return status;		
	}
	
	/**
	
	 * @Title: createTicket
	
	 * @Description: TODO
		创建工单
	 * @param httpSession
	 * @param title
	 * @param description
	 * @param serviceType
	 * @param severity
	 * @param fileName
	 * @param serFileName
	 * @return
	
	 * @return: Status
	
	 */
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
	@RequestMapping(value="/ticketShowPage",method=RequestMethod.GET)
	public String ticketShowPage(HttpSession httpSession, Model m, String ticketId) {
		u = (User) httpSession.getAttribute("user");
		List<Ticket> tickets = ticketService.findAllTicketsByKeywordANDPageANDLimit(0, 1, ".*", ".*", ".*", ticketId);
		m.addAttribute("ticketServices", ticketSerService.findAllTicketService());
		m.addAttribute("ticket", tickets.get(0));
		String changeTicketStatus = "";
		String changeTicketStatusPath = "";
		switch (tickets.get(0).getStatus()) {
		case "New":
			changeTicketStatus = "受理";
			changeTicketStatusPath = "./changeTicketPage?method=ack&ticketId="+ticketId;
			break;
		case "In_Process":
			changeTicketStatus = "解决";
			changeTicketStatusPath = "./changeTicketPage?method=solve&ticketId="+ticketId;
			break;
		case "Resolved":
			changeTicketStatus = "关闭";
			break;
		case "Closed":
			changeTicketStatus = "重开";
			changeTicketStatusPath = "./changeTicketPage?method=reopen&ticketId="+ticketId;
			break;
		default:
			break;
		}
		List<TicketMessageView> ticketComments = new ArrayList<>();
		ticketComments.addAll(ticketCommentService.getCommentsByTicketIdAndScope(Long.parseLong(ticketId), "Client"));
		ticketComments.addAll(ticketCommentService.getCommentsByTicketIdAndScope(Long.parseLong(ticketId), "Shared"));
		ticketComments.addAll(ticketCommentService.getCommentsByTicketIdAndteamIdAndScope(Long.parseLong(ticketId), u.getTeamId(), "Internal"));
		//动态按钮
		m.addAttribute("changeTicketStatus", changeTicketStatus);
		//动态按钮路径
		m.addAttribute("changeTicketStatusPath", changeTicketStatusPath);
		//评论
		Collections.sort(ticketComments, new Comparator<TicketMessageView>() {
			@Override
			public int compare(TicketMessageView o1, TicketMessageView o2) {
				// TODO Auto-generated method stub
				return o1.getTimestamp().compareTo(o2.getTimestamp());
			}
		});
		m.addAttribute("ticketComments", ticketComments);
		//ticket附件
		List<TicketAttachment> ticketAttachments = ticketAttachmentService.getTicketAttachmentsByTicketIdAndMessageId(Long.parseLong(ticketId), 0);
		m.addAttribute("ticketAttachments", ticketAttachments);
		//comment附件
		for (TicketMessageView ticketComment : ticketComments) {
			m.addAttribute(ticketComment.getId()+"" , ticketAttachmentService.getTicketAttachmentsByTicketIdAndMessageId(Long.parseLong(ticketId), ticketComment.getId()));
		}
		return "ticket/ticketShow";	
	}
	@RequestMapping(value="/updateTicketService",method=RequestMethod.POST)
	@ResponseBody
	public Status updateTicketService(HttpSession httpSession, String ticketId, String serviceBefore, String serviceType){
		ticketService.changeTicketServiceId(Integer.parseInt(serviceType), Integer.parseInt(ticketId));
		Status status = new Status();
		status.setStatus(0);
		return status;
	}
	@RequestMapping(value="/updateTicketSeverity",method=RequestMethod.POST)
	@ResponseBody
	public Status updateTicketSev(HttpSession httpSession, String ticketId, String severityBefore, String severity){
		ticketService.changeTicketSeverity(severity, Integer.parseInt(ticketId));
		Status status = new Status();
		status.setStatus(0);
		return status;
	}
	@RequestMapping(value="/ticketSubcribePage",method=RequestMethod.GET)
	public String ticketSubcribePage(HttpSession httpSession, Model m, String ticketId) {
		List<Team> subscribeTeams = ticketSubscribeTeamService.getSubscribeTeamsByTicketId(Long.parseLong(ticketId));
		List<Team> nonSubscribeTeams = ticketSubscribeTeamService.getNonSubscribeTeamsByTicketId(Long.parseLong(ticketId));
		m.addAttribute("subscribeTeams", subscribeTeams);
		m.addAttribute("nonSubscribeTeams", nonSubscribeTeams);
		m.addAttribute("ticketId", ticketId);
		return "ticket/ticketSubscribe";
	}
	@RequestMapping(value="/ticketSubcribeTeamChange",method=RequestMethod.POST)
	@ResponseBody
	public Status ticketSubcribeTeamChange(HttpSession httpSession, @RequestParam(value = "addArray[]")String[] addArray, @RequestParam(value = "reduceArray[]")String[] reduceArray, String ticketId) {
		System.out.println("订阅");
		if(reduceArray != null) {
			int rl = reduceArray.length;
			for (int i = 1; i < rl; i++) {
				
				ticketSubscribeTeamService.deleteSubscribeTeamByTicketIdAndTeamId(Long.parseLong(ticketId), Long.parseLong(reduceArray[i]));
			}
		}
		if(addArray != null) {
			int al = addArray.length;
			for (int i = 1; i < al; i++) {
				ticketSubscribeTeamService.addSubscribeTeamByTicketIdAndTeamId(Long.parseLong(ticketId), Long.parseLong(addArray[i]));
			}
		}
		Status status = new Status();
		status.setStatus(0);
		return status;
	}
	@RequestMapping(value="/addCommment",method=RequestMethod.POST)
	@ResponseBody
	public Status addCommment(HttpSession httpSession, @RequestParam(value = "fileName[]")String[] fileName, @RequestParam(value = "serFileName[]")String[] serFileName, String ticketId, String comment, String scope) {
		u = (User) httpSession.getAttribute("user");
		
		long commentId = ticketCommentService.addTicketComment(Long.parseLong(ticketId), u.getId(), u.getTeamId(), comment, scope);
		for(int i = 0; i < fileName.length-1; i++){
			ticketAttachmentService.addTicketAttachmentService(Long.parseLong(ticketId), commentId, null, serFileName[i], fileName[i]);
		}
		Status status = new Status();
		status.setStatus(0);
		return status;
	}
	@RequestMapping(value="/ticketAttachmentDownload")
    public ResponseEntity<byte[]> ticketAttachmentDownload(HttpServletRequest request,
            String attachmentId, String attachmentName,
            Model model)throws Exception {
       //下载文件路径
       String path = request.getServletContext().getRealPath("/static/");
       File file = new File(path+"file/"+attachmentId);
       file.delete();
       file.createNewFile();
       System.out.println("文件目录"+path);
       if(ftpServer.init()) {
    	   ftpServer.execute(ftpServer.getMethod("DOWNLOAD"), attachmentId, null, path+"file/");
       }
       HttpHeaders headers = new HttpHeaders();  
       //下载显示的文件名，解决中文名称乱码问题  
       //String downloadFielName = new String(attachmentId.getBytes("UTF-8"),"iso-8859-1");
       //通知浏览器以attachment（下载方式）打开图片
       headers.setContentDispositionFormData("attachment", attachmentName);
       //headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachmentName + "\"");
       //application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
               headers, HttpStatus.OK);  
    }
    @RequestMapping(value="/changeTicketPage",method=RequestMethod.GET)
	public String changeTicketPage(HttpSession httpSession, Model m, String ticketId, String method ) {
		u = (User) httpSession.getAttribute("user");
		String setStatus = "";
		switch (method) {
		case "ack":
			setStatus = "In_Process";
			break;
		case "solve":
			setStatus = "Resolved";
			break;
		case "reopen":
			setStatus = "In_Process";
			break;
		default:
			break;
		}
		m.addAttribute("ticketId", ticketId);
		m.addAttribute("setStatus", setStatus);
		return "ticket/changeTicketPage";
	}
    @RequestMapping(value="/changeTicketStatus",method=RequestMethod.POST)
	@ResponseBody
	public Status changeTicketStatus(HttpSession httpSession, String ticketId, String setStatus, String comment ) {
		u = (User) httpSession.getAttribute("user");
		
		long commentId = ticketCommentService.addTicketComment(Long.parseLong(ticketId), u.getId(), u.getTeamId(), comment, "Shared");
		ticketService.changeTicketStatus(setStatus, Long.parseLong(ticketId));
		Status status = new Status();
		status.setStatus(0);
		return status;
    }
	class SearchTicketsAndCount{
		private int count;
		private String msg;
		private int code;
		private List<TicketView> ticketViewList;
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		
		public void setCount(int count) {
			this.count = count;
		}
		public int getCount() {
			return this.count;
		}
		public void setTicketViewList(List<TicketView> ticketViewList) {
			this.ticketViewList = ticketViewList;
		}
		public List<TicketView> getTicketViewList(){
			return this.ticketViewList;
		}
	}
	/*
	 *文件上传后返回数据
	 */
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
