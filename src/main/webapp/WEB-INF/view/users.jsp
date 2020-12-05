<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.java.ft.models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<html lang="${param.language}">
<% ResourceBundle bundle = ResourceBundle.getBundle("content", new Locale((String) request.getAttribute("language"))); %>
<head>
    <title><fmt:message key="global.users"/></title>
    <meta name="description" content="<fmt:message key="meta.users"/>">
    <jsp:include page="../../includeStyles.jsp"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>

<main class="catalog-main main-content">
    <section class="goods container">
        <ul class="way">
            <li><a href="<%out.print(request.getContextPath());%>"><img
                    src="<%=request.getContextPath()%>/resources/img/icon-home.svg" width="14"
                    height="12" alt="Main page"/></a></li>
            <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="global.users"/></a></li>
        </ul>
        <h3 class="books-block-title"><fmt:message key="global.users"/>
            <span>
                <a href="?language=en" class="switch-language">EN</a>
                <a href="?language=ru" class="switch-language">RU</a>
            </span>
        </h3>
        <ul class="books-list users-list">
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                int type = (Integer) session.getAttribute("type");
                for (User user : users) {
                    out.print("<li class=\"good\"><p>" + user.getUserName() + "</p><p>" + user.getEmail() + "</p><p>" +
                            ((user.getSubscription() == null) ? bundle.getString("profile.getSubscription") : (bundle.getString("profile.subscriptionActive") + " " + user.getSubscription().getGiven().toString() + " " + bundle.getString("global.to") + " " + user.getSubscription().getExpires().toString())) +
                            "</p>");
                    if (type == 3) {
                        String librarianLink = ((user.getUserType().getId() == 1) ? "/makeLibrarian" : "/deleteLibrarian") + "?user=" + user.getId();
                        String librarianText = (user.getUserType().getId() == 1) ? bundle.getString("users.makeLibrarian") : bundle.getString("users.deleteLibrarian");
                        out.print("<p><a href=\"" + request.getContextPath() + librarianLink + "\">" + librarianText + "</a></p>");
                        String statusLink = ((user.getUserStatus().getId() == 1) ? "/block" : "/unblock") + "?user=" + user.getId();
                        String statusText = (user.getUserStatus().getId() == 1) ? bundle.getString("users.block") : bundle.getString("users.unblock");
                        out.print("<p><a href=\"" + request.getContextPath() + statusLink + "\">" + statusText + "</a></p>");
                    }
                    out.println("</li>");
                }
            %>
        </ul>
    </section>
</main>

<jsp:include page="../../footer.jsp"/>
</body>
</html>
