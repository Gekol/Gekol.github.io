<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.java.ft.models.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.HashMap" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<html lang="${param.language}">
<%
    ResourceBundle bundle = ResourceBundle.getBundle("content", new Locale((String) request.getAttribute("language")));
%>
<head>
    <title><fmt:message key="books.books"/></title>
    <meta name="description" content="<fmt:message key="meta.books"/>">
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
            <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="books.books"/></a></li>
        </ul>
        <h3 class="books-block-title"><% bundle.getString("books.books"); %>
            <span>
                <a href="?language=en" class="switch-language">EN</a>
                <a href="?language=ru" class="switch-language">RU</a>
            </span>
        </h3>
        <ul class="books-list users-list">
            <%
                List<Book> books = (List<Book>) request.getAttribute("books");
                HashMap<String, Integer> bookCounts = (HashMap<String, Integer>) request.getAttribute("bookCounts");
                for (Book book : books) {
                    out.println("<li class=\"good\">");
                    out.println("<div class=\"book\"><img src=\"" + request.getContextPath() + "/resources/img/books/" +
                            book.getBookSrc() + "\" width=\"144\" height=\"225\" alt=\"" +
                            book.getTitle() + "\" /></div>");
                    out.println("<div class=\"buy\">");
                    out.println("<a href=\"books?delete=" + book.getId() + "\">" + bundle.getString("book.delete") +
                            "</a>");
                    out.println("<a href=\"edit?id=" + book.getId() + "\">" + bundle.getString("book.edit") + "</a>");
                    Integer count = bookCounts.get(book.getTitle());
                    out.println("<p>" + ((count == null) ? 0 : count) + "</p>");
                    out.println("</div>");
                    out.println("<b>" + book.getTitle() + "</b>");
                    out.println("<p class=\"author\">" + book.getAuthor().getFullName() + "</p>");
                    out.println("<p class=\"current-price\">" + book.getPrice() + " " + bundle.getString("hrn") + "/" +
                            bundle.getString("hour") + "</p>");
                    out.println("</li>");
                }
            %>
        </ul>
    </section>
</main>
<jsp:include page="../../footer.jsp"/>
</body>
</html>
