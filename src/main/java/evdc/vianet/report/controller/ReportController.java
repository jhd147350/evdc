package evdc.vianet.report.controller;

import java.io.File;
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

import evdc.vianet.ticket.entity.view.TicketChangeRecordView;
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
	
	@RequestMapping(value="/ticketReportPage",method=RequestMethod.GET)
	public String ticketReportPage(Model m) {
		
		return "ticket/ticketReportPage";
	}
	
	@RequestMapping(value="/ticketReport",method=RequestMethod.POST)
	@ResponseBody
	public List<TicketChangeRecordView> ticketReport(Model m, String ticketId ) {
		List<TicketChangeRecordView> ticketChangeRecordViews = ticketChangeRecordService.getAllViewRecordsByTicketId(Long.parseLong(ticketId));
		return ticketChangeRecordViews;
	}
	
	@RequestMapping(value="/exportTicketReport", method=RequestMethod.GET)
    public ResponseEntity<byte[]> exportTicketReport(HttpServletRequest request,
    		String ticketId)throws Exception {
	    	List<TicketChangeRecordView> ticketChangeRecordViews = ticketChangeRecordService.getAllViewRecordsByTicketId(Long.parseLong(ticketId));
	    	String path = request.getServletContext().getRealPath("/static/");
	    	File xlsFile = new File(path+"file/report/jxl.xls");
	    	xlsFile.createNewFile();
	        // 创建一个工作簿
	        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
	        // 创建一个工作表
	        WritableSheet sheet = workbook.createSheet("sheet1", 0);
	        
	        sheet.addCell(new Label(0, 0, "ticketId"));
	        sheet.addCell(new Label(1, 0, "操作"));
	        sheet.addCell(new Label(2, 0, "值 "));
	        sheet.addCell(new Label(3, 0, "操作人 "));
	        sheet.addCell(new Label(4, 0, "时间 "));
	        int cols = ticketChangeRecordViews.size();
	        for (int row = 0; row < cols; row++){ 
	        	TicketChangeRecordView ticketChangeRecordView = ticketChangeRecordViews.get(row);
	        	sheet.addCell(new Label(0, row+1, ticketChangeRecordView.getTicketId()+""));
		        sheet.addCell(new Label(1, row+1, ticketChangeRecordView.getFiled()));
		        sheet.addCell(new Label(2, row+1, ticketChangeRecordView.getNewValue()));
		        sheet.addCell(new Label(3, row+1, ticketChangeRecordView.getName()));
		        sheet.addCell(new Label(4, row+1, ticketChangeRecordView.getTimestamp().toLocaleString()));
	        }
	        workbook.write();
	        workbook.close();
	    	HttpHeaders headers = new HttpHeaders();  
	    	//下载显示的文件名，解决中文名称乱码问题  
	    	//String downloadFielName = new String(attachmentId.getBytes("UTF-8"),"iso-8859-1");
	    	//通知浏览器以attachment（下载方式）打开图片
	    	headers.setContentDispositionFormData("attachment", "ticket" + ticketId + "report.xls");
	    	//headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"ticket" + ticketId + "report.xls\"");
	    	//application/octet-stream ： 二进制流数据（最常见的文件下载）。
	    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(xlsFile),    
	    			headers, HttpStatus.OK);        
    }
}
