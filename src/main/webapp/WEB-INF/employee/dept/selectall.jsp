<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selectall.jsp</title>
</head>
<body>

<hr>
<select>
	<c:forEach var="tzs" items="${requestScope.tzs}">
		<option>${pageScope.tzs}</option>
	</c:forEach>
</select>
<hr>

<table bgcolor="pink" border="2" bordercolor="blue" cellpadding="2">
	<c:forEach var="tzs" items="${requestScope.tzs}" varStatus="status">
		<tr>
			<td>${status.index}</td>
			<td>${pageScope.tzs}</td>
		</tr>
	</c:forEach>
</table>
<hr>

<c:set var="col" value="5"/>
<table border="1" style="border-collapse: collapse;">
	<c:forEach var="tzs" items="${tzs}" varStatus="status">
		<c:if test="${status.index % col == 0}"><tr></c:if>
			<td>${tzs}</td>
		<c:if test="${status.index % col == (col-1)}"></tr></c:if>
	</c:forEach>
</table>
<hr>


</body>
</html>