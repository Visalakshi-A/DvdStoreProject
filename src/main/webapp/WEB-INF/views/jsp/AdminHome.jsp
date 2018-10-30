<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Admin </title>
    </head>
    <body>
        <form method = "get">
            <div style = "float: right">
                <button formaction = "logout" formmethod = "post">Log Out</button>
            </div>
        <br/><br/><br/><br/><br/><br/>
        <center>
            <button style = "width: 250px;height: 40px;" formaction = "dvd/display">DVD</button>
        <br/><br/><br/>
            <button style = "width: 250px;height: 40px;" formaction = "customer/display">Customer</button>
        <br/><br/><br/>
            <button style = "width: 250px;height: 40px;" formaction = "order/display">Orders</button>
        <br/><br/><br/>
            <button style = "width: 250px;height: 40px;" formaction = "category/display">Category</button>
        <br/><br/><br/>
        </center>
    </body>
    <c:if test = "${null != message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
</html>
