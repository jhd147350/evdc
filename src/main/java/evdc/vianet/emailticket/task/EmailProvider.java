package evdc.vianet.emailticket.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class EmailProvider {
	public String searchEmailTicket(Map<String, Object> param) {

		// select * from " + EmailTicket.TABLE_NAME + " limit #{arg0},#{arg1}
		String sql = new SQL() {
			{
				SELECT("* ");
				FROM(EmailTicket.TABLE_NAME);

				String idorkey = (String) param.get("idorkey");
				if (idorkey != null && idorkey.length() != 0) {
					if (isNum(idorkey)) {
						WHERE("id =" + Long.parseLong(idorkey));
					} else {
						WHERE("title like '%" + idorkey + "%'");
					}
				}
				String status = (String) param.get("status");
				if (status != null && !status.equals("all")) {
					WHERE("status = '" + status + "'");
				}
				String service = (String) param.get("service");
				if (service != null && !service.equals("all")) {
					WHERE("service = '" + service + "'");
				}
			}
		}.toString();
		return sql + " \nlimit " + param.get("page") + "," + param.get("limit");
	}
	
	//TODO 这个方法和searchEmailTicket 大部分内容一样，只不过是为了计数
	public String countSearchEmailTicket(Map<String, Object> param) {

		// select * from " + EmailTicket.TABLE_NAME + " limit #{arg0},#{arg1}
		String sql = new SQL() {
			{
				SELECT("count(*) ");
				FROM(EmailTicket.TABLE_NAME);

				String idorkey = (String) param.get("idorkey");
				if (idorkey != null && idorkey.length() != 0) {
					if (isNum(idorkey)) {
						WHERE("id =" + Long.parseLong(idorkey));
					} else {
						WHERE("title like '%" + idorkey + "%'");
					}
				}
				String status = (String) param.get("status");
				if (status != null && !status.equals("all")) {
					WHERE("status = '" + status + "'");
				}
				String service = (String) param.get("service");
				if (service != null && !service.equals("all")) {
					WHERE("service = '" + service + "'");
				}
			}
		}.toString();
		return sql;
	}

	private boolean isNum(String s) {

		try {
			Long.parseLong(s);
			return true;
		} catch (Exception e) {
			System.err.println("不是数字");
			return false;
		}
	}

	public static void main(String[] args) {

		java.lang.reflect.Method m;

		Map<String, Object> param = new HashMap<String, Object>();
		;
		EmailProvider ep = new EmailProvider();

		param.put("limit", 6);
		param.put("page", 5);
		param.put("idorkey", "asd");
		param.put("status", "st");
		// param.put("service", "se");
		System.out.println(ep.searchEmailTicket(param));
	}

}
