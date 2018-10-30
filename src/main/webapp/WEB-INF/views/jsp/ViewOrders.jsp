<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Orders </title>
    </head>
    <body>
        <form action = "/DvdStore" method = "get">
            <input type = "submit" value = "Back"/>
        </form>
        <h2 align = "center"> Orders </h2>
        <c:if test = "${null == orders}">
            <jsp:forward page = "/order/display"/>
        </c:if>
        <div style = "overflow: scroll; height: 600px">
        <table width = 90% align = center>
            <tr>
                <th> Id </th>
                <th> Customer Id </th>
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
                    <td> ${order.customerId} </td>
                    <td> ${order.dvd.name} </td>
                    <td> ${order.dvd.language} </td>
                    <td> ${order.dvd.type} </td>
                    <td> ${order.dvd.price} </td>
                    <td> ${order.orderDate} </td>
                    <td> <c:if test = "${order.status}">
                         <form action = "set-delivered" method = "post">
                            <input hidden name = "id" value = "${order.id}"/>
                            <input type = submit value = Delivered />
                         </c:if>
                         <c:if test = "${false == order.status}"> Delivered </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table></div>
    </body>
    <c:if test = "${null != message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
</html>
