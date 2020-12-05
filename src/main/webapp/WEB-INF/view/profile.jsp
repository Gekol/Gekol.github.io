<%@ page import="com.epam.java.ft.models.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<%
    ResourceBundle bundle = ResourceBundle.getBundle("content", new Locale((String) request.getAttribute("language")));
    String email = (String) session.getAttribute("email");
    String userName = (String) session.getAttribute("userName");
    Integer type = (Integer) session.getAttribute("type");
    Subscription subscription = (Subscription) request.getAttribute("subscription");
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    List<Edition> editions = (List<Edition>) request.getAttribute("editions");
    int fine = (Integer) request.getAttribute("fine");
%>
<!DOCTYPE html>
<html lang="${param.language}">

<head>
    <title><fmt:message key="profile"/></title>
    <meta name="description" content="<fmt:message key="meta.profile"/>">
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
            <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="header.office"/></a></li>
        </ul>
        <h3 class="books-block-title"><fmt:message key="header.office"/>
            <span>
                <a href="?language=en" class="switch-language">EN</a>
                <a href="?language=ru" class="switch-language">RU</a>
            </span>
        </h3>
        <h1 class="userName">
            <span><% out.print(userName); %></span>
            <span><%
                if (subscription == null) {
                    out.print("<a href=\"?createSubscription=true\" class='create-subscription'>" + bundle.getString("profile.getSubscription") + "</a>");
                } else {
                    out.print("<span class='create-subscription'>" + bundle.getString("profile.subscriptionActive") + " " + subscription.getGiven() + " " + bundle.getString("global.to") + " " + subscription.getExpires() + "</span>");
                }
            %></span>
        </h1>
        <div>
            <div><fmt:message key="global.email"/>: <% out.print(email); %></div>
            <% if (orders.size() == 0) {
                out.println("<div>" + bundle.getString("profile.noSubscription") + "</div>");
            } else {
                out.println("<div>" + bundle.getString("profile.overallFine") + ": " + fine + "</div>");
            }%>
            <div>
                <%
                    String fileName = "../../userLinks.jsp";
                    if (type > 1) {
                        fileName = "../../librarianLinks.jsp";
                    }
                    if (type == 3) {
                        fileName = "../../adminLinks.jsp";
                    }
                %>
                <jsp:include page="<%= fileName %>"/>
            </div>
        </div>
        <ul class="books-list orders-list">
            <%
                for (Order order : orders) {
                    Book book = order.getBook();
                    out.println("<li class=\"good\">");
                    out.println("<div class=\"book\"><img src=\"" + request.getContextPath() + "/resources/img/books/" + book.getBookSrc() + "\" width=\"144\" height=\"225\" alt=\"" + order.getBook().getTitle() + "\" /></div>");
                    out.println("<div class=\"buy\">");
                    out.println((order.getFine() > 0) ? "<span>" + bundle.getString("profile.overdue") + "</span>" : "<span>" + bundle.getString("profile.timeLeft") + "</span>");
                    out.println("</div>");
                    out.println("<b>" + book.getTitle() + "</b>");
                    out.println("<p class=\"author\">" + ((order.getDeadline() == null) ? bundle.getString("index.bookNotTaken") : order.getDeadline().toString()) + "</p>");
                    out.println("<p class=\"current-price\">" + bundle.getString("book.fine") + ": " + order.getFine() + "</p>");
                    out.println("</li>");
                }
            %>
        </ul>
    </section>
</main>
<jsp:include page="../../footer.jsp"/>
<section class="item-added-block book-form visually-hidden">
    <div class="item-added">
        <form action="<%out.print(request.getContextPath());%>/profile" method="post">
            <h1 class="form__title"><fmt:message key="profile.newBook"/></h1>
            <div class="form__block">
                <label for="title_en" class="form__label"><fmt:message key="book.englishTitle"/></label>
                <input type="text" id="title_en" name="title_en" class="form__input" required>
            </div>
            <div class="form__block">
                <label for="title_ru" class="form__label"><fmt:message key="book.russianTitle"/></label>
                <input type="text" id="title_ru" name="title_ru" class="form__input" required>
            </div>
            <div class="form__block">
                <label for="price" class="form__label"><fmt:message key="book.price"/></label>
                <input type="number" name="price" id="price" placeholder="10" class="form__input" required>
            </div>
            <div class="form__block">
                <label for="fine" class="form__label"><fmt:message key="book.fine"/></label>
                <input type="number" name="fine" id="fine" placeholder="10" class="form__input" required>
            </div>
            <div class="form__block">
                <label for="author" class="form__label"><fmt:message key="index.author"/></label>
                <select name="author" id="author" class="form__input" required>
                    <% for (Author author : authors) {
                        out.println("<option value=\"" + author.getId() + "\">" + author.getFullName() + "</option>");
                    }
                    %>
                </select>
            </div>
            <div class="form__block">
                <label for="edition" class="form__label"><fmt:message key="index.author"/></label>
                <select name="edition" id="edition" class="form__input" required>
                    <% for (Edition edition : editions) {
                        out.println("<option value=\"" + edition.getId() + "\">" + edition.getTitle() + "</option>");
                    }
                    %>
                </select>
            </div>
            <div class="form__block">
                <input type="submit" value="<fmt:message key="global.submit"/>" class="form__input form__input__submit">
            </div>
        </form>
        <button class="modal-close" type="button" aria-label="<fmt:message key="global.close" />"></button>
    </div>
</section>
<script src="<%=request.getContextPath()%>/resources/js/popups.js"></script>
</body>

</html>