package evdc.vianet.emailticket.task;

public class EmailSender {
	
	private static String email="jia.haodong-bl@21vianet.com";
	
	private static String password="147350Jhd++++++";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExchangeMailbox mailbox =new ExchangeMailbox(email, password);
		mailbox.sendEmail("", "", "", "");

	}

}
