<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<%
    boolean loggedIn;
    if (session == null || session.getAttribute("loggedIn") == null) {
        loggedIn = false;
    } else {
        loggedIn = (Boolean) session.getAttribute("loggedIn");
    }
    String language = (String) request.getAttribute("language");
%>
<header class="header-block">
    <div class="menu-content">
        <div class="menu-block container">
            <div class="title-block">
                <p class="title"><fmt:message key="header.title"/></p>
                <div class="search-block">
                    <form class="search" method="GET" action="<%out.print(request.getContextPath());%>">
                        <label for="search">
                            <input type="hidden" name="language"
                                   value="<% out.print(language); %>">
                            <input type="text" placeholder="<fmt:message key="header.search" />" name="book"
                                   autocomplete="false" id="search"
                                   class="searchField">
                        </label>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="about">
            <div class="description">
                <p><fmt:message key="header.library"/></p>
                <p><fmt:message key="header.literature"/></p>
                <p><fmt:message key="header.time"/></p>
            </div>
            <div class="contact-info">
                <p class="phone"><img src="<%=request.getContextPath()%>/resources/img/icon-phone.svg" width="19"
                                      height="19" alt="Phone"/> <a
                        href="tel:78125550555">+380 (67) 617-17-10</a></p>
                <p class="city"><fmt:message key="header.address"/></p>
            </div>
            <%
                String fileName = "buttons.jsp";
                if (loggedIn) {
                    fileName = "loggedIn.jsp";
                }
            %>
            <jsp:include page="<%= fileName %>"/>
        </div>
    </div>

    <div class="navigation-content container">
        <nav class="navigation">
            <ul>
                <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="header.main"/></a></li>
                <li><a href="company"><fmt:message key="header.company"/></a></li>
                <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="header.catalog"/></a></li>
                <li><a href="news"><fmt:message key="header.news"/></a></li>
                <li><a href="specialOffers"><fmt:message key="header.specialOffers"/></a></li>
                <li><a href="delivery"><fmt:message key="header.delivery"/></a></li>
                <li><a href="contacts"><fmt:message key="header.contacts"/></a></li>
            </ul>
        </nav>
    </div>

</header>