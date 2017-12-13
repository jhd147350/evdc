package evdc.vianet.ticket.entity;

import java.util.List;

public class SearchTicketsAndCount<T> {
	private int count;
	private String msg;
	private int code;
	private List<T> ticketViewList;
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
	public void setTicketViewList(List<T> ticketViewList) {
		this.ticketViewList = ticketViewList;
	}
	public List<T> getTicketViewList(){
		return this.ticketViewList;
	}
}
