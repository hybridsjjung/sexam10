<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dog.jsp</title>
</head>
<body>

<hr>
<h1>dogs</h1>
<hr>

<c:forEach var="dog" items="${requestScope.dogs}">
	<img alt="${pageScope.dog}" src="${pageScope.dog}">
</c:forEach>

</body>
</html>