<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>门店最低销量</td>
			<td>门店最高销量</td>
			<td>预警新客人数</td>
			<td>创建人</td>
			<td>创建时间</td>
			<td>修改人</td>
			<td>修改时间</td>
		</tr>
		<c:forEach items="${settingList }" var="setting">
			<tr>
				<td>${setting.storeNumMin}万</td>
				<td>${setting.storeNumMax}万</td>
				<td>${setting.warningNewNum}</td>
				<td>${setting.createUserName}</td>
				<td>${setting.createDate}</td>
				<td>${setting.updateUserName}</td>
				<td>${setting.updateDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>