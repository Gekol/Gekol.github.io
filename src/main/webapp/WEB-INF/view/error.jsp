<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ErrorPage Handling</title>
</head>
<body>
<h1>ErrorPage information</h1>
Code: <% out.println(request.getAttribute("code")); %>
</body>
</html>
