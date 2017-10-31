package evdc.vianet.emailticket.task;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

public interface EmailMapper {

	// TODO 有的邮件body很大，超过mysql Text的存储范围可以使用MediumText
	// TODO 参考http://www.cnblogs.com/pureEve/p/6015000.html
	// http://www.cnblogs.com/usual2013blog/p/3747644.html
	@Insert("insert into " + Email.TABLE_NAME
			+ " (`subject`,`body`,`from`,`to`,`cc`,`cdate`,`note`,`ticketId`,`delete`,`emailUniqueId`,`fromInbox`) values(#{subject},#{body},#{from},#{to},#{cc},#{cdate},#{note},#{ticketId},#{delete},#{emailUniqueId},#{fromInbox})")
	int insertEmail(Email e);

	// TODO long 类型默认为0
	@Select("select * from " + Email.TABLE_NAME + " where ticketId=0 and `delete`=false limit #{arg0},#{arg1}")
	List<Email> selectAllUnhandledEmail(long rowsOffset, long limit);

	@Select("select * from " + Email.TABLE_NAME + " where ticketId=#{id}")
	List<Email> selectAllEmailsByTicketId(long id);

	@Select("SELECT count(*) FROM " + Email.TABLE_NAME + " where ticketId=0 and `delete`=false")
	long countAllUnhandledEmail();

	@Select("select * from " + Email.TABLE_NAME + " where id=#{id}")
	Email selectEmailById(long id);

	@Select("select * from " + EmailAccount.TABLE_NAME)
	List<EmailAccount> selectAllEmailAccount();

	// TODO 多个参数问题 要是用Available parameters are [arg0, arg1]
	@Update("update " + EmailAccount.TABLE_NAME + " set `updateDate`=#{arg0}  where `id`=#{arg1}")
	int updateTimeStampEmailAccountById(Timestamp t, long id);

	@Select("select updateDate from " + EmailAccount.TABLE_NAME + " where id=#{id}")
	Timestamp selectTimeStampFromEmailAccountById(long id);

	// ------------email_ticket-----------------

	// TODO 参考链接http://blog.csdn.net/u012325167/article/details/52403631
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into " + EmailTicket.TABLE_NAME
			+ " (`title`,`client`,`status`,`timestamp`,`service`) values(#{title},#{client},#{status},#{timestamp},#{service})")
	int insertEmailTicket(EmailTicket e);

	/**
	 * @deprecated
	 * @param rowsOffset
	 * @param limit
	 * @return
	 */
	@Select("select * from " + EmailTicket.TABLE_NAME + " limit #{arg0},#{arg1}")
	List<EmailTicket> selectAllEmailTicket(long rowsOffset, long limit);

	/**
	 * @deprecated
	 * @return
	 */
	@Select("SELECT count(*) FROM " + EmailTicket.TABLE_NAME)
	long countAllEmailTicket();

	@Select("select * from " + EmailTicket.TABLE_NAME + " where id=#{id}")
	EmailTicket selectEmailTicketById(long id);

	@Update("update " + Email.TABLE_NAME + " set `note`=#{arg0}, `ticketId`=#{arg1}  where `id`=#{arg2}")
	int updateNoteById(String note, long ticketId, long emailId);

	@Update("update " + Email.TABLE_NAME + " set `delete`=#{arg0}  where `id`=#{arg1}")
	int updateDeleteById(boolean delete, long id);

	@Update("update " + Email.TABLE_NAME + " set `ticketId`=0  where `id`=#{id}")
	int updateTicketId0ById(long id);

	@Delete("delete from " + EmailTicket.TABLE_NAME + " where id=#{id}")
	int deleteEmailTicketById(long id);

	@Update("update " + Email.TABLE_NAME + " set `ticketId`=0  where `ticketId`=#{id}")
	int updateTicketId0ByTicketId(long id);

	@Update("update " + EmailTicket.TABLE_NAME + " set `status`=#{arg0}  where `id`=#{arg1}")
	int updateStatusByTicketId(String status, long id);

	/**
	 * 替代之前的查询
	 * 
	 * @param rowsOffset
	 * @param limit
	 * @param idorkey
	 * @param status
	 * @param service
	 * @return
	 */
	@SelectProvider(type = EmailProvider.class, method = "searchEmailTicket")
	List<EmailTicket> searchEmailTicket(@Param("page") long rowsOffset, @Param("limit") long limit,
			@Param("idorkey") String idorkey, @Param("status") String status, @Param("service") String service);

	@SelectProvider(type = EmailProvider.class, method = "countSearchEmailTicket")
	long countSearchEmailTicket(@Param("idorkey") String idorkey, @Param("status") String status,
			@Param("service") String service);
}
