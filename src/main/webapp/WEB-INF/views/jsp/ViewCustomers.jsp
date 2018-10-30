<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Customers </title>
    </head>
    <body>
        <form action = "/DvdStore" method = "get">
            <input type = "submit" value = "Back"/>
        </form><br/><br/>
        <h2 align = "center"> Customers </h2>
        <table width = 90% align = center>
            <tr>
                <th> Id </th>
                <th> Name </th>
                <th> Email </th>
                <th> Contact Number </th>
                <th> Address </th><th/>
                <th> Orders </th>
            </tr>
            <c:forEach var = "customer" items = "${customers}">
                <tr align = center height = 50px>
                    <td> ${customer.id} </td>
                    <td> ${customer.name} </td>
                    <td> ${customer.email} </td>
                    <td> ${customer.contactNumber} </td>
                    <td> ${customer.address.size()} </td>
                    <form action = "display-customer-address" method = "post">
                        <input hidden name = "id" value = "${customer.id}"/>
                        <td><input type = submit value = View /></td>
                    </form>
                    <td> ${customer.orders.size()} </td>
                    <form action = "display-customer-orders" method = "post">
                        <input hidden name = "id" value = "${customer.id}"/>
                        <td><input type = submit value = View /></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
