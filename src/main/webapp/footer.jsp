<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>

<footer class="downside">
    <div class="downside-navigation-content">
        <div class="downside-navigation container">
            <div class="address">
                <a href="<% out.println(request.getContextPath()); %>" class="title big"><fmt:message
                        key="header.title"/></a>
                <p class="address-info"><fmt:message key="header.address"/></p>
                <p class="phone-number"><a href="tel:78125550555">+380 (67) 617-17-10</a></p>
            </div>
            <div class="links">
                <ul class="white-links">
                    <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="header.company"/></a></li>
                    <li><a href="news"><fmt:message key="header.news"/></a></li>
                    <li><a href="<%out.print(request.getContextPath());%>"><fmt:message key="header.catalog"/></a></li>
                    <li><a href="delivery"><fmt:message key="header.delivery"/></a></li>
                    <li><a href="contacts"><fmt:message key="header.contacts"/></a></li>
                </ul>
                <ul class="yellow-links">
                    <li><a href="materials"><fmt:message key="footer.materials"/></a></li>
                    <li><a href="technique"><fmt:message key="footer.technique"/></a></li>
                    <li><a href="tool"><fmt:message key="footer.tool"/></a></li>
                    <li><a href="specialOffers"><fmt:message key="header.specialOffers"/></a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="downside-contacts-content">
        <div class="downside-contacts container">
            <div class="rights-reserved">
                <p>© 2010–2020 <fmt:message key="header.library"/> «<fmt:message key="header.title"/>»</p>
                <p><fmt:message key="footer.rights"/></p>
            </div>
            <div class="networks">
                <div class="network" tabindex="0">
                    <a href="vk.com" class="icon vk"></a>
                </div>
                <div class="network" tabindex="0">
                    <a href="facebook.com" class="icon fb"></a>
                </div>
                <div class="network" tabindex="0">
                    <a href="instagram.com" class="icon insta"></a>
                </div>
            </div>
            <div class="feedback">
                <p><fmt:message key="footer.feedback"/>:<a href="https://mail@htmlacademy.ru" class="yellow-link"
                                                           tabindex="0">mail@worm.ru</a></p>
                <p><fmt:message key="footer.developer"/> — <a href="https://htmlacademy.ru/intensive/htmlcss"
                                                              class="yellow-link">hsokolovskyi.com</a></p>
            </div>
        </div>
    </div>
</footer>