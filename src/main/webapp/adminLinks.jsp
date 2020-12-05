<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:include page="librarianLinks.jsp"/>
<a href="add-book" class="addBook"><fmt:message key="profile.newBook"/></a>
<a href="books" class="addBook"><fmt:message key="profile.books"/></a>