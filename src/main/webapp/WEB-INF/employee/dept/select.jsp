<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>select.jsp</title>
</head>
<body>

<hr>
<select>
	<c:forEach var="color" items="${requestScope.colors}">
		<option>${pageScope.color}</option>
	</c:forEach>
</select>
<hr>

</body>
</html>