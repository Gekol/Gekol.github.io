<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.java.ft.models.Author" %>
<%@ page import="com.epam.java.ft.models.Edition" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    List<Edition> editions = (List<Edition>) request.getAttribute("editions");
%>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<html lang="${param.language}">
<head>
    <title><fmt:message key="book.edit"/> <fmt:message key="book"/></title>
    <jsp:include page="../../includeStyles.jsp"/>
</head>
<body>
<jsp:include page="../../header.jsp"/>
<main class="catalog-main main-content">
    <section class="item-added-block edit-form">
        <div class="item-added">
            <form action="<%out.print(request.getContextPath());%>/edit" method="post">
                <h1 class="form__title"><fmt:message key="profile.editBook"/></h1>
                <input type="hidden" name="id" id="id" value="<% out.print(request.getAttribute("book")); %>"/>
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
                    <input type="submit" value="<fmt:message key="global.submit"/>"
                           class="form__input form__input__submit">
                </div>
            </form>
        </div>
    </section>
</main>
<jsp:include page="../../footer.jsp"/>
</body>
</html>