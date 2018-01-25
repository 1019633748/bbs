<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>home</title>
</head>
<body>
	<c:forEach items="${sections }" var="obj">
		<h3><a href="${pageContext.request.contextPath}/section/get/section/${obj.id }">${obj.sectionName }</a></h3>
		<c:forEach items="${obj.titles }" var="subObj">
		<h5><a href="${pageContext.request.contextPath }/content/get/content/${subObj.id}/1">${subObj.name }</a> ${subObj.author } <fmt:formatDate value="${subObj.createDate }" pattern="yyyy-MM-dd"/></h5>
		</c:forEach>
	</c:forEach>
</body>
</html>