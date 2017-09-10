package evdc.vianet.shift.entity.jo;

import java.util.List;

import evdc.vianet.shift.entity.view.ViewShift;

public class TableDataShift {
	private int code;
	private String msg;
	private int count;
	private List<ViewShift> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ViewShift> getData() {
		return data;
	}

	public void setData(List<ViewShift> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "TableDataShift [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}

}
