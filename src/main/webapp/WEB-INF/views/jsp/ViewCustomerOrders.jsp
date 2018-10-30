<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Customer Orders </title>
    </head>
    <body>
        <form action = "display" method = "get">
            <input type = submit value = Back />
        </form>
        <c:if test = "${empty orders}">
            <h2 align = center> No Orders yet!! </h2>
        </c:if>
        <c:if test = "${not empty orders}">
            <h2 align = "center"> Customer order </h2><br/>
            <table width = 90% align = center>
            <tr>
                <th> Id </th>
                <th> DVD Name </th>
                <th> Language </th>
                <th> Type </th>
                <th> Price </th>
                <th> Order Date </th>
                <th> Status </th>   
            </tr>
            <c:forEach var = "order" items = "${orders}">
                <tr align = center height = 50px>
                    <td> ${order.id} </td>
                    <td> ${order.dvd.name} </td>
                    <td> ${order.dvd.language} </td>
                    <td> ${order.dvd.type} </td>
                    <td> ${order.dvd.price} </td>
                    <td> ${order.orderDate} </td>
                    <td> <c:if test = "${order.status}"> Yet to Deliver </c:if>
                         <c:if test = "${false == order.status}"> Delivered </c:if></td>
                </tr>
            </c:forEach>
        </c:if>
    </body>
</html>
