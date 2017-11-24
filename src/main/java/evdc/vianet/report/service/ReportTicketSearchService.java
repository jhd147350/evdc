package evdc.vianet.report.service;

import java.util.List;

import evdc.vianet.auth.entity.Status;
import evdc.vianet.report.entity.ReportTicketSearch;

public interface ReportTicketSearchService {
	public Status addReportTicketSearch(ReportTicketSearch r);
	public Status updateReportTicketSearch(ReportTicketSearch r);
	public List<ReportTicketSearch> getAllReportTicketSearchs();
	public ReportTicketSearch getReportTicketSearchById(long id);
	public int deldetReportTicketSearchById(long id); 
}
