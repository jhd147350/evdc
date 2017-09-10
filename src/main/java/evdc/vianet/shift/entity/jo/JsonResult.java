package evdc.vianet.shift.entity.jo;

import org.json.JSONObject;

public class JsonResult {
	// static public int SAME_NAME = 1;
	private int code;
	private String info;

	static public JsonResult SAME_NAME;

	static public JsonResult SUC;

	static public JsonResult FAILED;

	static public long TEST_USERID = 1;
	static {
		SAME_NAME = new JsonResult();
		SAME_NAME.setCode(1);
		SAME_NAME.setInfo("same name");

		SUC = new JsonResult();
		SUC.setCode(200);
		SUC.setInfo("suc");

		FAILED = new JsonResult();
		FAILED.setCode(400);
		FAILED.setInfo("failed");
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		JSONObject result = new JSONObject();
		result.put("code", code);
		result.put("info", info);

		return result.toString();
	}

}
