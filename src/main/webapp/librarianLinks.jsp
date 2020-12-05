<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="userLinks.jsp"/>
<a href="orders"><fmt:message key="profile.orders"/></a>
<a href="users"><fmt:message key="profile.users"/></a>