package evdc.vianet.emailticket.task;

import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.response.FindItemResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponseCollection;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.AttachmentCollection;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemAttachment;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.property.complex.MimeContent;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;
import microsoft.exchange.webservices.data.search.filter.SearchFilter.IsGreaterThan;

public class ExchangeMailbox {
	private String email;
	private String password;
	private String uri = "https://mail.21vianet.com/EWS/Exchange.asmx";

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private ExchangeService es;
	private ExchangeCredentials ec;

	private int pageSize = 100;// 100个足够了
	private ItemView itemView;
	// private ItemView sentView;

	private IsGreaterThan isGreaterThan;// 搜索条件

	private Date latestDate;
	
	private Date searchDate;

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date d) {
		this.latestDate = d;
		this.searchDate=(Date) d.clone();
	}

	PropertySet ps;// 属性集

	// private FindItemsResults<Item> inboxResults;// 收件箱结果集

	// private FindItemsResults<Item> sentResults;// 发件箱结果集

	// Folder inbox;
	// Folder sent;
	// List<FolderId> folders;
	// ItemView all;

	public ExchangeMailbox(String email, String password) throws Exception {
		this.email = email;
		this.password = password;
		es = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ec = new WebCredentials(email, password);
		es.setCredentials(ec);
		es.setUrl(new URI(uri));

		itemView = new ItemView(pageSize);
		// sentView = new ItemView(pageSize);

		// inboxResults = es.findItems(WellKnownFolderName.Inbox, isGreaterThan,
		// inboxView);
		// sentResults = es.findItems(WellKnownFolderName.SentItems, isGreaterThan,
		// sentView);
		// inbox = Folder.bind(es, WellKnownFolderName.Inbox);

		// sent = Folder.bind(es, WellKnownFolderName.SentItems);
		// folders = new ArrayList<>();
		// folders.add(inbox.getId());
		// folders.add(sent.getId());
		// all = new ItemView(100);

		ps = new PropertySet(BasePropertySet.FirstClassProperties, ItemSchema.MimeContent, ItemSchema.HasAttachments,
				ItemSchema.Attachments);
		// es.loadPropertiesForItems(null, ps);
		// es.loadPropertiesForItems(sentResults, ps);
		// ArrayList<Item> items = inboxResults.getItems();
		// System.out.println(inboxResults.getTotalCount());
		// System.out.println(sentResults.getTotalCount());

	}

	/**
	 * 一次性获取多个文件夹下面的item
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	private String getResult1(String date) throws Exception {

		Date startDate = formatter.parse("2017-09-26 8:00:00");
		isGreaterThan = new SearchFilter.IsGreaterThan(ItemSchema.DateTimeReceived, startDate);

		/*
		 * ServiceResponseCollection<FindItemResponse<Item>> my = es.findItems(folders,
		 * isGreaterThan, null, all, null, null); for (FindItemResponse<Item>
		 * findItemResponse : my) { // 处理所有邮件
		 * System.out.println(findItemResponse.getResults().getTotalCount()); }
		 */
		return null;
	}

	private List<Email> getResultItems(WellKnownFolderName folderName) {
		//latestDate = d;
		if (folderName == WellKnownFolderName.SentItems) {
			// TODO https://github.com/OfficeDev/ews-java-api/issues/630 大于的效果不对
			isGreaterThan = new SearchFilter.IsGreaterThan(ItemSchema.DateTimeSent, searchDate);
		} else {
			isGreaterThan = new SearchFilter.IsGreaterThan(ItemSchema.DateTimeReceived, searchDate);
		}

		FindItemsResults<Item> findItems;
		List<Email> emails = new ArrayList<>();
		try {
			findItems = es.findItems(folderName, isGreaterThan, itemView);
			ArrayList<Item> emailItems = findItems.getItems();

			if (emailItems.size() <= 0) {
				System.out.println("log：未查询到新邮件");
				return emails;
			}
			es.loadPropertiesForItems(findItems, ps);

			for (Item item : emailItems) {
				if (item instanceof EmailMessage) {
					EmailMessage email = (EmailMessage) item;
					Email e = new Email();
					e.setEmailUniqueId(email.getId().getUniqueId());
					e.setSubject(email.getSubject());
					// 发件是按发送时间 收件时按接收时间计算
					if (folderName == WellKnownFolderName.SentItems) {
						e.setCdate(new Timestamp(email.getDateTimeSent().getTime()));
						e.setFromInbox(false);

						if (latestDate.before(email.getDateTimeSent())) {
							latestDate = email.getDateTimeSent();
						}
					} else {
						e.setCdate(new Timestamp(email.getDateTimeReceived().getTime()));
						e.setFromInbox(true);
						if (latestDate.before(email.getDateTimeReceived())) {
							latestDate = email.getDateTimeReceived();
						}
					}

					e.setFrom((email.getFrom().toString()));
					List<EmailAddress> toRecipients = email.getToRecipients().getItems();
					StringBuffer toStr = new StringBuffer();
					for (EmailAddress temp : toRecipients) {
						toStr.append(temp.toString() + "; ");
					}
					e.setTo(toStr.toString());
					StringBuffer ccStr = new StringBuffer();
					List<EmailAddress> ccRecipients = email.getCcRecipients().getItems();
					for (EmailAddress temp : ccRecipients) {
						ccStr.append(temp.toString() + "; ");
					}
					e.setCc(ccStr.toString());

					MessageBody body = email.getBody();
					e.setBody(body.toString());

					List<Attachment> items = email.getAttachments().getItems();
					// System.out.println(email.getHasAttachments());
					// System.out.println("附件数量：" + items.size());
					for (Attachment temp : items) {
						if (temp instanceof FileAttachment) {
							// System.out.println("file :" + temp.getClass());
							// TODO 附件和内嵌图片先不处理
							/*
							 * FileAttachment fa = (FileAttachment) temp; File tempZip = new
							 * File("F:\\temp\\" + email.getId() + "\\" + fa.getName()); if
							 * (!tempZip.exists()) { tempZip.getParentFile().mkdir();
							 * tempZip.createNewFile(); } fa.load(tempZip.getPath());
							 */
						} else if (temp instanceof ItemAttachment) {
							System.out.println("item     :" + temp.getClass());
						}
						/*
						 * System.out.println("class    :" + temp.getClass());
						 * System.out.println("cid      :" + temp.getContentId());
						 * System.out.println("id       :" + temp.getId());
						 * System.out.println("ctype    :" + temp.getContentType());
						 * System.out.println("isInline :" + temp.getIsInline());
						 */
					}

					if (email.getHasAttachments() || email.getAttachments().getItems().size() > 0) {
						// System.err.println("有附件哦");
					}
					emails.add(e);
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return emails;
	}

	public List<Email> getInboxMail() {
		
		List<Email> emails = getResultItems(WellKnownFolderName.Inbox);

		return emails;

	}

	public List<Email> getSentMail() {
		List<Email> emails = getResultItems(WellKnownFolderName.SentItems);

		return emails;
	}

}
