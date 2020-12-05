<%@ page import="com.epam.java.ft.models.Author" %>
<%@ page import="com.epam.java.ft.models.Book" %>
<%@ page import="com.epam.java.ft.models.Subscription" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<!DOCTYPE html>
<html lang="${param.language}">

<head>
    <title><fmt:message key="header.library"/> "<fmt:message key="header.title"/>"</title>
    <meta name="description" content="<fmt:message key="meta.index"/>">
    <jsp:include page="../../includeStyles.jsp"/>
</head>

<body>
<%
    ResourceBundle bundle = ResourceBundle.getBundle("content", new Locale((String) request.getAttribute("language")));
    Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
%>
<jsp:include page="../../header.jsp"/>

<main class="catalog-main main-content">
    <section class="goods container">
        <ul class="way">
            <li><a href="<%out.print(request.getContextPath());%>"><img
                    src="<%=request.getContextPath()%>/resources/img/icon-home.svg" width="14"
                    height="12" alt="Main page"/></a></li>
            <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="header.catalog"/></a></li>
        </ul>
        <h3 class="books-block-title"><fmt:message key="index.books"/>
            <span>
                <a href="?language=en" class="switch-language">EN</a>
                <a href="?language=ru" class="switch-language">RU</a>
            </span>
        </h3>
        <section class="order-block">
            <article class="filter-block">
                <p class="filter-title"><fmt:message key="index.filter"/></p>
                <form action="<%out.print(request.getContextPath());%>" class="filters">
                    <div>
                        <fieldset>
                            <legend class="filter-subtitle"><fmt:message key="index.authors"/>:</legend>
                            <input type="hidden" name="language"
                                   value="<%out.print(request.getAttribute("language"));%>">
                            <ul class="manufacturer-filter">
                                <%
                                    String language = (String) request.getAttribute("language");
                                    StringBuilder authorParameters = new StringBuilder();
                                    String queryString = request.getQueryString();
                                    List<Author> authors = (List<Author>) request.getAttribute("authors");
                                    for (Object obj : authors) {
                                        Author author = (Author) obj;
                                        String authorId = author.getId();
                                        out.print("<li><input type=\"checkbox\" class=\"visually-hidden\" name=\"" + authorId + "\" id=\"" + authorId + "\"><label" +
                                                " for=\"" + authorId + "\" tabindex=\"0\">" + author.getFullName() + "</label></li>");
                                        if (queryString != null && queryString.contains(author.getId())) {
                                            authorParameters.append("&").append(author.getId()).append("=on");
                                        }
                                    }
                                    String authorString = authorParameters.toString();
                                    if (!authorString.equals("")) {
                                        authorString = authorString.substring(0, authorString.length() - 1);
                                    }
                                %>
                            </ul>
                        </fieldset>
                    </div>
                    <input type="submit" value="<fmt:message key="index.show" />">
                </form>
            </article>
            <article class="books-block">
                <div class="sort-block">
                    <p><fmt:message key="index.sort"/></p>
                    <ul class="sort-filters">
                        <li class="sort-filter"><a
                                href="<%out.print(request.getContextPath());%>?language=<% out.print(language + authorString); %>&orderBy=title"><fmt:message
                                key="index.bookTitle"/></a></li>
                        <li class="sort-filter"><a
                                href="<%out.print(request.getContextPath());%>?language=<% out.print(language + authorString); %>&orderBy=author"><fmt:message
                                key="index.author"/></a></li>
                        <li class="sort-filter"><a
                                href="<%out.print(request.getContextPath());%>?language=<% out.print(language + authorString); %>&orderBy=edition"><fmt:message
                                key="index.edition"/></a></li>
                        <li class="sort-filter"><a
                                href="<%out.print(request.getContextPath());%>?language=<% out.print(language + authorString); %>&orderBy=editionDate"><fmt:message
                                key="index.editionDate"/></a></li>
                    </ul>
                    <%String orderBy = (String) request.getAttribute("orderBy");%>
                    <a href="<%out.print(request.getContextPath());%>?language=<% out.print(language + authorString); %>&orderBy=<% out.print(orderBy); %>&ascending=true">
                        <div class="arrow ascending <%if (request.getAttribute("ascending").equals("true")) {
                            out.print("selected");
                        }%>"></div>
                    </a>
                    <a href="<%out.print(request.getContextPath());%>?language=<% out.print(language + authorString); %>&orderBy=<% out.print(orderBy); %>&ascending=false">
                        <div class="arrow descending <%if (request.getAttribute("ascending").equals("false")) {
                            out.print("selected");
                        }%>"></div>
                    </a>
                </div>
                <ul class="books-list">
                    <%
                        List<Book> books = (List<Book>) request.getAttribute("books");
                        int currentPage = Integer.parseInt((String) request.getAttribute("page"));
                        int start = (currentPage - 1) * 8;
                        int size = books.size();
                        int booksOnPageCount = Math.min(start + 9, size);
                        Subscription subscription = (Subscription) session.getAttribute("subscription");

                        for (Object obj : books.subList(start, booksOnPageCount)) {
                            Book book = (Book) obj;
                            String text = "";
                            if (loggedIn != null && loggedIn) {
                                int status = (Integer) session.getAttribute("status");
                                if (status != 1) {
                                    text = "<span>" + bundle.getString("index.blocked") + "</span>";
                                } else {
                                    text = (subscription != null ? "<a href=\"order?orderType=Subscription&book_id=" + book.getId() + "\" class=\"buy-button\">" + bundle.getString("index.subscription") + "</a>\n" : "") +
                                            "<a href=\"order?orderType=Reading hall&book_id=" + book.getId() + "\" class=\"buy-button reading-hall-choose\">" + bundle.getString("index.readingHall") + "</a>\n";
                                }
                            } else {
                                text = "<span>" + bundle.getString("index.login") + "</span>";
                            }
                            out.print("<li class=\"good\" tabindex=\"0\">\n" +
                                    "                        <div class=\"book\">\n" +
                                    "                            <img src=\"" + request.getContextPath() + "/resources/img/books/" + book.getBookSrc() + "\" width=\"144\" height=\"225\" alt=\"" + book.getTitle() + "\"/>\n" +
                                    "                        </div>\n" +
                                    "                        <div class=\"buy\">\n" + text +
                                    "                        </div>\n" +
                                    "                        <b>" + book.getTitle() + "</b>\n" +
                                    "                        <p class=\"author\">" + book.getAuthor().getFullName() + "</p>\n" +
                                    "                        <p class=\"current-price\">" + book.getPrice() + " " + bundle.getString("hrn") + "/" + bundle.getString("hour") + "</p>\n" +
                                    "                    </li>");
                        }
                    %>
                </ul>
                <ul class="list-pages">
                    <%
                        int pagesCount = size / 9;
                        if ((size % 9) != 0) {
                            pagesCount++;
                        }
                        if (pagesCount > 1) {
                            for (int i = 0; i < pagesCount; i++) {
                                out.print("<li><a href=\"" + request.getContextPath() + "?language=" + language + "&orderBy=" + orderBy + authorString + "&ascending=" + request.getAttribute("ascending") + "&page=" + (i + 1));
                                if ((i + 1) == currentPage) {
                                    out.print("\" class=\"chosen");
                                }
                                out.print("\">" + (i + 1 + "</a></li>"));
                            }
                        }
                        if (currentPage != pagesCount) {
                            out.print("<li><a href = \"" + request.getContextPath() + "?language=" + language + "&orderBy="
                                    + orderBy + authorString + "&ascending=" + request.getAttribute("ascending") + "&page=" + (currentPage + 1) +
                                    "\" class=next>" + bundle.getString("index.next") + "</a></li> ");
                        }
                    %>
                </ul>
            </article>
        </section>
    </section>
</main>
<jsp:include page="../../footer.jsp"/>
<section class="item-added-block visually-hidden">
    <form class="item-added">
        <div class="message-block">
            <img src="<%=request.getContextPath()%>/resources/img/icon-mark.png" alt="Purchase successful"
                 width="66" height="66">
            <p class="added"><fmt:message key="index.orderCreated"/></p>
        </div>
        <button class="modal-close" type="button" aria-label="<% bundle.getString("global.close"); %>"></button>
    </form>
</section>
<section class="item-added-block register-form visually-hidden">
    <div class="item-added">
        <form action="<%out.print(request.getContextPath());%>/" method="post">
            <h1 class="form__title"><fmt:message key="header.registration"/></h1>
            <div class="form__block">
                <label for="first_name" class="form__label"><fmt:message key="name"/>: </label>
                <input type="text" id="first_name" name="first_name" class="form__input">
            </div>
            <div class="form__block">
                <label for="last_name" class="form__label"><fmt:message key="surname"/>: </label>
                <input type="text" id="last_name" name="last_name" class="form__input">
            </div>
            <div class="form__block">
                <label for="email" class="form__label"><fmt:message key="global.email"/>: </label>
                <input type="email" id="email" name="email" class="form__input" required>
            </div>
            <div class="form__block">
                <label for="password" class="form__label"><fmt:message key="global.password"/>: </label>
                <input type="password" id="password" name="password" class="form__input" required>
            </div>
            <div class="form__block">
                <input type="submit" value="<fmt:message key="global.submit" />"
                       class="form__input form__input__submit">
            </div>
        </form>
        <button class="modal-close" type="button" aria-label="<fmt:message key="global.close" />"></button>
    </div>
</section>
<section class="item-added-block login-form visually-hidden">
    <div class="item-added">
        <form action="<%out.print(request.getContextPath());%>/" method="post">
            <h1 class="form__title"><fmt:message key="header.logIn"/></h1>
            <div class="form__block">
                <label for="emailField" class="form__label"><fmt:message key="global.email"/>: </label>
                <input type="email" id="emailField" name="email" class="form__input" required>
            </div>
            <div class="form__block">
                <label for="passwordField" class="form__label"><fmt:message key="global.password"/>: </label>
                <input type="password" id="passwordField" name="password" class="form__input" required>
            </div>
            <div class="form__block">
                <input type="submit" value="<fmt:message key="global.submit" />"
                       class="form__input form__input__submit">
            </div>
        </form>
        <button class="modal-close" type="button" aria-label="<fmt:message key="global.close" />"></button>
    </div>
</section>
<script src="<%=request.getContextPath()%>/resources/js/popups.js"></script>
</body>

</html>