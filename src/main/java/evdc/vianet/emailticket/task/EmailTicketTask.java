package evdc.vianet.emailticket.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
public class EmailTicketTask {
	private final String filename = "emailticket.properties";

	@Autowired
	EmailMapper mapper;

	/**
	 * 定时去获取邮箱的新邮件然后写入数据库
	 */
	// http://www.cnblogs.com/liaojie970/p/5913272.html
	// @Scheduled(cron = "0/30 * * * * ? ")//每隔30s
	@Scheduled(cron = "0 0/5 * * * ? ") // 每隔五分钟
	public void email2Ticket() {
		 
		System.out.println("任务执行时间：" + LocalTime.now().toString());
		List<EmailAccount> accounts = mapper.selectAllEmailAccount();
		for (EmailAccount a : accounts) {

			try {
				ExchangeMailbox mailbox = new ExchangeMailbox(a.getEmail(), a.getPassword());
				Date d = a.getUpdateDate();
				mailbox.setLatestDate(d);
				List<Email> inboxMail = mailbox.getInboxMail();
				List<Email> sentMail = mailbox.getSentMail();
				inboxMail.addAll(sentMail);
				Collections.sort(inboxMail);
				//insertEmails(mailbox.getInboxMail());
				//insertEmails(mailbox.getSentMail());
				insertEmails(inboxMail);
				// TODO 临时解决方案这里时间+1一秒 https://github.com/OfficeDev/ews-java-api/issues/630
				Timestamp t = new Timestamp(mailbox.getLatestDate().getTime() + 1000l);
				// 在将最新的时间更新回去，这样就会每次都能拿到之前未拿到的邮件
				mapper.updateTimeStampEmailAccountById(t, a.getId());
			} catch (Exception e) {
				System.err.println("err 任务出错");
				e.printStackTrace();
				
			}

		}
		// 测试 mapper.updateTimeStampEmailAccountById(new Timestamp(System.currentTimeMillis()), 1);

	}

	private void insertEmails(List<Email> emails) {
		for (Email email : emails) {
			try {
				
			
			mapper.insertEmail(email);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.err.println(email.toString());
			}
		}
	}

	/**
	 * @deprecated
	 * @return
	 */
	private String readDate() {
		Properties p = new Properties();
		String fromdate = null;
		try {
			p.load(new FileInputStream(filename));
			fromdate = p.getProperty("fromdate", System.currentTimeMillis() + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fromdate;
	}

	/**
	 * @deprecated
	 * @return
	 */
	private String saveDate(String date) {
		File file = new File(filename);

		Properties p = new Properties();
		try {
			// load the previous data
			FileInputStream input = new FileInputStream(file);
			p.load(input);
			input.close();
			// overwrite the previous data
			FileOutputStream output = new FileOutputStream(file);
			p.put("fromdate", date);
			p.store(output, "DATE INFO");
			output.close();
			System.out.println("Your info has been saved!");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return date;
	}

}
