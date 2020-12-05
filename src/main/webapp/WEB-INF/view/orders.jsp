<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.java.ft.models.Book" %>
<%@ page import="com.epam.java.ft.models.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<%
    ResourceBundle bundle = ResourceBundle.getBundle("content", new Locale((String) request.getAttribute("language")));
%>
<html lang="${param.language}">
<head>
    <title><fmt:message key="global.orders"/></title>
    <meta name="description" content="<fmt:message key="meta.orders"/>">
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
            <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="global.orders"/></a></li>
        </ul>
        <h3 class="books-block-title"><fmt:message key="global.orders"/>
            <span>
                <a href="?language=en" class="switch-language">EN</a>
                <a href="?language=ru" class="switch-language">RU</a>
            </span>
        </h3>
        <ul class="books-list users-list">
            <%
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                for (Order order : orders) {
                    Book book = order.getBook();
                    out.println("<li class=\"good\">");
                    out.println("<div class=\"book\"><img src=\"" + request.getContextPath() + "/resources/img/books/" + book.getBookSrc() + "\" width=\"144\" height=\"225\" alt=\"" + order.getBook().getTitle() + "\" /></div>");
                    out.println("<div class=\"buy\">");
                    out.println((order.getFine() > 0) ? "<a href=\"fine?id=" + order.getId() + "\">" + bundle.getString("orders.payFine") + "</a>" :
                            ((order.getDeadline() == null) ? "<a href=" + request.getContextPath() + "/giveBook?id=" + order.getId() + "&bookId=" + order.getBook().getId() + "&orderType=" + order.getOrderType().getId() + ">" + bundle.getString("orders.giveBook") + "</a>"
                                    : "<a href=" + request.getContextPath() + "/return?id=" + order.getId() + "&bookId=" + order.getBook().getId() + ">" + bundle.getString("orders.takeBook") + "</a>"));
                    out.println("</div>");
                    out.println("<h3>" + order.getBook().getTitle() + "</h3>");
                    out.println("<b>" + order.getUser().getEmail() + "</b>");
                    out.println("<p class=\"author\">" + ((order.getDeadline() == null) ? bundle.getString("orders.userDidNotTakeBook") : order.getDeadline().toString()) + "</p>");
                    out.println("<p class=\"current-price\">" + bundle.getString("book.fine") + ": " + order.getFine() + "</p>");
                    out.println("</li>");
                }
            %>
        </ul>
    </section>
</main>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
