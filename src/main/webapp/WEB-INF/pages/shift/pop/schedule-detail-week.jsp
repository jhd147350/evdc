<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<thead>
	<tr>
		<c:forEach items="${scheduleResults}" var="result">
			<th colspan="${result.orderOfDay}">${result.date.week}(${result.date.month}月${result.date.day}日)</th>
		</c:forEach>
	</tr>
	<tr>
		<c:forEach items="${scheduleResults}" var="result">
			<c:forEach items="${result.times}" var="time">
				<th title="${time.startTime}-${time.endTime}">${time.info}</th>
			</c:forEach>
		</c:forEach>
	</tr>
</thead>
<tbody>
	<tr>
		<c:forEach items="${scheduleResults}" var="result">
			<c:forEach items="${result.staffsP}" var="staff">
				<td id="${staff.id}">${staff.name}</td>
			</c:forEach>
		</c:forEach>

	</tr>
	<tr>
		<c:forEach items="${scheduleResults}" var="result">
			<c:forEach items="${result.staffsS}" var="staff">
				<td id="${staff.id}">${staff.name}</td>
			</c:forEach>
		</c:forEach>
	</tr>
</tbody>