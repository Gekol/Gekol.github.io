<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.language}"/>
<fmt:setBundle basename="content"/>
<%String userName = (String) session.getAttribute("userName");%>

<div class="profile-block">
    <div class="profile">
        <div class="name">
            <a href="profile" class="profile-link">
                <svg class="profile-image" width="20" height="18" viewBox="0 0 20 18" fill="none"
                     xmlns="http://www.w3.org/2000/svg">
                    <path d="M17.881 14.166C15.737 13.431 13.625 12.5 12.674 11.776C12.017 11.275 11.904 10.158 12.157 9.361C13.275 8.458 14.021 6.884 14.021 5.083C14.021 2.276 12.221 0 10 0C7.77902 0 5.97901 2.276 5.97901 5.083C5.97901 6.884 6.72501 8.457 7.84201 9.36C8.09501 10.158 7.98302 11.275 7.32502 11.776C6.37502 12.5 4.26201 13.43 2.11801 14.166C-0.0259852 14.902 1.48343e-05 18 1.48343e-05 18H20C20 18 20.025 14.901 17.881 14.166Z"
                          fill="#C5C5C5"></path>
                </svg>
                <% out.println(userName); %>
            </a>
            <a href="logout" class="logout">
                <svg width="20" height="18" viewBox="0 0 20 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M20 9L11.125 0.5V6.5H5.125V11.5H11.125V17.5L20 9Z" fill="#C5C5C5"></path>
                    <path d="M2.10601 14.452V3.548C2.12401 3.172 2.27701 2.491 3.11601 2.491H8.14301V0.5H3.11601C0.938006 0.5 0.112006 2.31 0.0870056 3.527V14.473C0.112006 15.689 0.938006 17.5 3.11601 17.5H8.14301V15.51H3.11601C2.27701 15.51 2.12401 14.827 2.10601 14.452Z"
                          fill="#C5C5C5"></path>
                </svg>
            </a>
        </div>
    </div>
    <ul>
        <li tabindex="0"><a href="profile"><fmt:message key="header.orders"/></a></li>
        <li tabindex="0"><a href="profile"><fmt:message key="header.office"/></a></li>
    </ul>
</div>