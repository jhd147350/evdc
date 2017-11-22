package evdc.vianet.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.Status;
import evdc.vianet.report.entity.ReportTicketSearch;
import evdc.vianet.report.mapper.ReportTicketSearchMapper;

@Service("reportTicketSearchService")
public class ReportTicketSearchServiceImp implements ReportTicketSearchService {
	@Autowired
	private ReportTicketSearchMapper reportTicketSearchMapper;
	@Override
	public Status addReportTicketSearch(ReportTicketSearch r) {
		// TODO Auto-generated method stub
		Status status = new Status();
		reportTicketSearchMapper.insertReportTicketSearch(r);
		status.setStatus(0);
		return status;
	}

	@Override
	public Status updateReportTicketSearch(ReportTicketSearch r) {
		// TODO Auto-generated method stub
		Status status = new Status();
		reportTicketSearchMapper.updateReportTicketSearch(r);
		status.setStatus(0);
		return status;
	}

	@Override
	public List<ReportTicketSearch> getAllReportTicketSearchs() {
		// TODO Auto-generated method stub
		return reportTicketSearchMapper.findAllReportTicketSearchs();
	}

	@Override
	public ReportTicketSearch getReportTicketSearchById(long id) {
		// TODO Auto-generated method stub
		return reportTicketSearchMapper.findReportTicketSearchById(id);
	}

}
