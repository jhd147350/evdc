package evdc.vianet.emailticket.task;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class EmailProvider {
	public String searchEmailTicket(Map<String, Object> param) {

		//为null表示 查询 数量 计数 不分页， 为0表示导出报表所用，不分页
		Long limit = (Long) param.getOrDefault("limit", null);
		String idorkey = (String) param.get("idorkey");
		String status = (String) param.getOrDefault("status", null);
		String service = (String) param.getOrDefault("service", null);
		String startdate = (String) param.getOrDefault("startdate", null);
		String enddate = (String) param.getOrDefault("enddate", null);
		String client = (String) param.getOrDefault("client", null);

		String sql = new SQL() {
			{
				if (limit == null) {
					SELECT("count(*) ");
				} else {
					SELECT("* ");
				}

				FROM(EmailTicket.TABLE_NAME);

				if (notNull(idorkey)) {
					if (isNum(idorkey)) {
						WHERE("id =" + Long.parseLong(idorkey));
					} else {
						WHERE("title like '%" + idorkey + "%'");
					}
				}

				if (notNull(status)) {
					WHERE("status = '" + status + "'");
				}

				if (notNull(service)) {
					WHERE("service = '" + service + "'");
				}

				if (notNull(startdate)) {
					WHERE("`timestamp` > '" + startdate + "'");
				}

				if (notNull(enddate)) {
					WHERE("`timestamp` <= '" + enddate + "'");
				}

				if (notNull(client)) {
					WHERE("`client` like '%" + client + "%'");
				}
			}
		}.toString();

		if (limit == null || limit == 0l) {
			return sql;
		} else {
			return sql + "\norder by id desc" + " \nlimit " + param.get("page") + "," + param.get("limit");
		}

	}
	
	/**
	 * 
	 * @param param
	 * @return
	 * 合并计数到查询中
	 */
	@Deprecated
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

	private boolean notNull(String s) {
		if (s == null || s.equals("")) {
			return false;
		}
		return true;
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
