package evdc.vianet.report.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.report.entity.ReportTicketSearch;

public interface ReportTicketSearchMapper {
	
	@Insert("insert into " + ReportTicketSearch.TABLE_NAME 
			+ " (`name`,`sql`,`describe`) values(#{r.name},#{r.sql},#{r.describe})")
	int insertReportTicketSearch(@Param("r") ReportTicketSearch r);
	@Select("select * from " + ReportTicketSearch.TABLE_NAME
			+ " where id=#{id}")
	ReportTicketSearch findReportTicketSearchById(@Param("id") long id);
	@Update("update " + ReportTicketSearch.TABLE_NAME
			+ " set `name`=#{r.name}, `sql`=#{r.sql}, `describe`=#{r.describe}")
	void updateReportTicketSearch(@Param("r") ReportTicketSearch r);
	@Select("select * from " + ReportTicketSearch.TABLE_NAME)
	List<ReportTicketSearch> findAllReportTicketSearchs();
}
