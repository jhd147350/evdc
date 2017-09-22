package evdc.vianet.shift.entity.jo;

import java.util.List;

public class ScheduleResult {
	public class MyTimeDuration {
		private String startTime;
		private String endTime;
		private String info;

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}
		

	}

	public class MyStaff {
		private long id;
		private String name;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public class MyDate {
		/**
		 * 年
		 */
		private int year;
		/**
		 * 月
		 */
		private int month;
		/**
		 * 这个月第几号
		 */
		private int day;
		/**
		 * 周几<br>
		 * 中文字符串：例如一、二、三、四、五、六、日
		 */
		private String week;

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public String getWeek() {
			return week;
		}

		public void setWeek(String week) {
			this.week = week;
		}
	}

	private MyDate date;
	private int orderOfDay;
	private List<MyTimeDuration> times;
	private List<MyStaff> staffsP;
	private List<MyStaff> staffsS;

	public MyDate getDate() {
		return date;
	}

	public void setDate(MyDate date) {
		this.date = date;
	}

	public int getOrderOfDay() {
		return orderOfDay;
	}

	public void setOrderOfDay(int orderOfDay) {
		this.orderOfDay = orderOfDay;
	}

	public List<MyTimeDuration> getTimes() {
		return times;
	}

	public void setTimes(List<MyTimeDuration> times) {
		this.times = times;
	}

	public List<MyStaff> getStaffsP() {
		return staffsP;
	}

	public void setStaffsP(List<MyStaff> staffsP) {
		this.staffsP = staffsP;
	}

	public List<MyStaff> getStaffsS() {
		return staffsS;
	}

	public void setStaffsS(List<MyStaff> staffsS) {
		this.staffsS = staffsS;
	}

}
