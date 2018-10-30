<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Customer Address </title>
    </head>
    <body>
        <form action = "display" method = "get">
            <input type = submit value = Back />
        </form>
        <c:if test = "${empty customerAddress}">
            <p align = center> Nothing to Show!! </p>
        </c:if>
        <c:if test = "${not empty customerAddress}">
            <h2 align = "center"> Customer Address </h2><br/>
            <table width = 90% align = center>
            <tr>
                <th> Address Line </th>
                <th> City </th>
                <th> State </th>
                <th> Country </th>
                <th> Zip Code </th>
            </tr>
            <c:forEach var = "address" items = "${customerAddress}">
                <tr align = center height = 50px>
                    <td> ${address.addressLine} </td>
                    <td> ${address.city} </td>
                    <td> ${address.state} </td>
                    <td> ${address.country} </td>
                    <td> ${address.zipCode} </td>
                </tr>
            </c:forEach>
        </c:if>
    </body>
</html>
