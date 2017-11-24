package evdc.vianet.report.controller;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;

import evdc.vianet.auth.entity.Status;
import evdc.vianet.report.entity.ReportTicketSearch;
import evdc.vianet.report.service.ReportTicketSearchService;
import evdc.vianet.report.service.ReportTicketService;
import evdc.vianet.ticket.entity.view.TicketView;
import evdc.vianet.ticket.service.TicketChangeRecordService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
	@Qualifier("ticketChangeRecordService")
	private TicketChangeRecordService ticketChangeRecordService;
	@Autowired
	@Qualifier("reportTicketSearchService")
	private ReportTicketSearchService reportTicketSearchService;
	@Autowired
	@Qualifier("reportTicketService")
	private ReportTicketService reportTicketService;
	
	@RequestMapping(value="/ticketReportPage",method=RequestMethod.GET)
	public String ticketReportPage(Model m) {
		m.addAttribute("reportSearchs", reportTicketSearchService.getAllReportTicketSearchs());
		return "report/ticketReportPage";
	}
	
	@RequestMapping(value="/ticketReport",method=RequestMethod.POST)
	@ResponseBody
	public TicketViewsAndCount ticketReport(Model m, String searchId, String page, String limit ) {
		int limitint = Integer.parseInt(limit);
		int pageint = Integer.parseInt(page);
		int limit1 = (pageint - 1) * limitint;
		ReportTicketSearch reportTicketSearch = reportTicketSearchService.getReportTicketSearchById(Integer.parseInt(searchId));
		int count = reportTicketService.getCountBySql(reportTicketSearch.getSql()); 
		TicketViewsAndCount andCount = new TicketViewsAndCount();
		andCount.setCount(count);
		andCount.setTicketViewList(reportTicketService.getTicketViewsBySql(limit1, limitint, reportTicketSearch.getSql()));
		andCount.setCode(200);
		return andCount;
	}
	
	@RequestMapping(value="/exportTicketReport", method=RequestMethod.GET)
    public ResponseEntity<byte[]> exportTicketReport(HttpServletRequest request,
    		String searchId)throws Exception {
    	ReportTicketSearch reportTicketSearch = reportTicketSearchService.getReportTicketSearchById(Integer.parseInt(searchId));

    	List<TicketView> ticketViews = reportTicketService.getTicketViewsBySql(0, 0, reportTicketSearch.getSql());
	    String path = request.getServletContext().getRealPath("/static/");
	    File xlsFile = new File(path+"file/report/jxl.xls");
	    xlsFile.createNewFile();
	    // 创建一个工作簿
	        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
	        // 创建一个工作表
	        WritableSheet sheet = workbook.createSheet("sheet1", 0);
	        Field[] fields = TicketView.class.getDeclaredFields();
	        int fl = fields.length;
	        for(int i = 1; i < fl; i++){
	        	sheet.addCell(new Label(i-1, 0, fields[i].getName()));
	        }
	        /*sheet.addCell(new Label(0, 0, "ticketId"));
	        sheet.addCell(new Label(1, 0, "操作"));
	        sheet.addCell(new Label(2, 0, "值 "));
	        sheet.addCell(new Label(3, 0, "操作人 "));
	        sheet.addCell(new Label(4, 0, "时间 "));*/
	        int cols = ticketViews.size();
	        Field.setAccessible(fields,   true); 
	        for (int row = 0; row < cols; row++){ 
	        	TicketView ticketView = ticketViews.get(row);
	        	String[]   name   =   new   String[fl]; 
                Object[]   value   =   new   Object[fl]; 
	        	for(int i = 1; i < fl; i++){
	        		name[i]   =   fields[i].getName(); 
	        		if(fields[i].get(ticketView)!=null){
	        			value[i] = fields[i].get(ticketView);
	        		}else{
	        			value[i] = "";
                    	System.out.println(name[i]);
                    }
                    sheet.addCell(new Label(i-1, row+1, (String)value[i].toString()));
                    
	        	}
	        	/*sheet.addCell(new Label(0, row+1, ticketChangeRecordView.getTicketId()+""));
		        sheet.addCell(new Label(1, row+1, ticketChangeRecordView.getFiled()));
		        sheet.addCell(new Label(2, row+1, ticketChangeRecordView.getNewValue()));
		        sheet.addCell(new Label(3, row+1, ticketChangeRecordView.getName()));
		        sheet.addCell(new Label(4, row+1, ticketChangeRecordView.getTimestamp().toLocaleString()));*/
	        }
	        workbook.write();
	        workbook.close();
	    	HttpHeaders headers = new HttpHeaders();  
	    	//下载显示的文件名，解决中文名称乱码问题  
	    	//String downloadFielName = new String(attachmentId.getBytes("UTF-8"),"iso-8859-1");
	    	//通知浏览器以attachment（下载方式）打开图片
	    	headers.setContentDispositionFormData("attachment", "ticket" + reportTicketSearch.getName() + "report.xls");
	    	//headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ticket" + ticketId + "report.xls\"");
	    	//application/octet-stream ： 二进制流数据（最常见的文件下载）。
	    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(xlsFile),    
	    			headers, HttpStatus.OK);        
    }
    @RequestMapping(value="/reportTicketSearchAddPage",method=RequestMethod.GET)
    public String reportTicketSearchAddPage(){
    	return "report/reportTicketSearchAddPage";
    }
    @RequestMapping(value="/reportTicketSearchAdd",method=RequestMethod.POST)
    @ResponseBody
    public Status reportTicketSearchAdd(String title, String sql, String describe){
    	ReportTicketSearch r = new ReportTicketSearch();
    	r.setName(title);
    	r.setSql(sql);
    	r.setDescribe(describe);
    	reportTicketSearchService.addReportTicketSearch(r);
    	Status status = new Status();
    	status.setStatus(0);
    	return status;
    }
    @RequestMapping(value="/reportTicketSearchEditPage",method=RequestMethod.GET)
    public String reportTicketSearchEditPage(Model m, String id){
    	ReportTicketSearch reportTicketSearch = reportTicketSearchService.getReportTicketSearchById(Integer.parseInt(id));
    	m.addAttribute("reportTicketSearch", reportTicketSearch);
    	return "report/reportTicketSearchEditPage";
    }
    
    @RequestMapping(value="/reportTicketSearchEdit",method=RequestMethod.POST)
    @ResponseBody
    public Status reportTicketSearchEdit(String id, String title, String sql, String describe){
    	ReportTicketSearch r = new ReportTicketSearch();
    	r.setId(Integer.parseInt(id));
    	r.setName(title);
    	r.setSql(sql);
    	r.setDescribe(describe);
    	reportTicketSearchService.updateReportTicketSearch(r);
    	Status status = new Status();
    	status.setStatus(0);
    	return status;
    }
    @RequestMapping(value="/reportTicketSearchDelete",method=RequestMethod.POST)
    @ResponseBody
    public Status reportTicketSearchDelete(String id){
    	reportTicketSearchService.deldetReportTicketSearchById(Integer.parseInt(id));
    	Status status = new Status();
    	status.setStatus(0);
    	return status;
    }
}
class TicketViewsAndCount{
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
	public List<TicketView> getTicketViewList() {
		return ticketViewList;
	}
	public void setTicketViewList(List<TicketView> ticketViewList) {
		this.ticketViewList = ticketViewList;
	}
	
}