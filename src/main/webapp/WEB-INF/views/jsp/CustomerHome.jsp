<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> ${user.name} </title>
    </head>
    <body>
        <h2 align = "center">DVD Store</h2>
        <form method = "get">
            <button formaction = "profile"> Profile - ${user.name} </button>
            <div style = "float: right">
                <button formaction = "/DvdStore/logout" formmethod = "post"> Log Out </button>
            </div>
        </form><br/>
        <form method = "post">
            Filter: <select name = "category" style = "width: 200px">
                <c:forEach var = "category" items = "${categories}">
                    <option value = "${category.id}">${category.value}</option>
                </c:forEach>
            </select>
            <button formaction = "dvds-by-category"> Filter </button>
            <button formaction = "/DvdStore"> Remove Filter </button>
            <div style = float:right;margin-right:100px>
            <input type = "text" name = "name" placeholder = "Enter Title"/>
            <button formaction = "dvds-by-name">
                <img src="<c:url value = '/resources/img/search.png'/>" alt = "Search" height="15" width="15"/>
            </button></div><br/>
        </form>
        <form action = "customer" method = "post">
        <div style = overflow:scroll;height:70%>
        <table width = 90% align = center cellpadding = 10>
            <tr>
                <th/>
                <th>Name</th>
                <th>Language</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Rating</th>
                <th>Released</th>
                <th>Category</th>
            </tr>
            <%@ page import = "com.ideas2it.dvdstore.utils.DateUtils" %>
            <c:forEach var = "dvd" items = "${dvds}">
                <tr align = "center">
                    <td><input type = "checkbox" name = "id" value = "${dvd.id}" 
                        <c:if test = "${0 == dvd.quantity}"> disabled </c:if> /></td>
                    <td>${dvd.name}</td>
                    <td>${dvd.language}</td>
                    <td>${dvd.type}</td>
                    <td>${dvd.quantity}</td>
                    <td>${dvd.price}</td>
                    <td>${dvd.rating}</td>
                    <td>${dvd.releaseDate}</td>
                    <td>${dvd.categories}</td>
                </tr>
            </c:forEach>
        </table>
        </div><br/>
            <center><button style = "width: 250px" formaction = "purchase-form" 
                onclick = "return checkDvdIsSelected();"> Purchase </button></center>
        </form>
    </body>
    <c:if test = "${not empty message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
    <script type = "text/javascript" src = "<c:url value = '/resources/js/DvdStore.js' />"></script>
</html>
<!--<c:if test = "${selectedDvds.contains(dvd)}"> checked </c:if>-->
