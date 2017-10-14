package evdc.vianet.emailticket.task;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmailMapper {

	// TODO 有的邮件body很大，超过mysql Text的存储范围可以使用MediumText
	// TODO 参考http://www.cnblogs.com/pureEve/p/6015000.html
	// http://www.cnblogs.com/usual2013blog/p/3747644.html
	@Insert("insert into " + Email.TABLE_NAME
			+ " (`subject`,`body`,`from`,`to`,`cc`,`cdate`,`note`,`ticketId`,`delete`,`emailUniqueId`,`fromInbox`) values(#{subject},#{body},#{from},#{to},#{cc},#{cdate},#{note},#{ticketId},#{delete},#{emailUniqueId},#{fromInbox})")
	int insertEmail(Email e);

	// TODO long 类型默认为0
	@Select("select * from " + Email.TABLE_NAME + " where ticketId=0 limit #{arg0},#{arg1}")
	List<Email> selectAllUnhandledEmail(long page, long limit);

	@Select("SELECT count(*) FROM " + Email.TABLE_NAME + " where ticketId=0")
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

}
