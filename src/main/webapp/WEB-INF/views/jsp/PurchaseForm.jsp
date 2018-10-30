<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Place Order </title>
        <style>
            tr {
                height: 20px;
            }
        </style>
    </head>
    <body>
        <a href = "/DvdStore"><button> Back </button></a>
        <table align = center>
            <c:forEach var = "dvd" items = "${selectedDvds}">
                <tr>
                    <th> Name: </th>
                    <td> ${dvd.name} </td>
                </tr>
                <tr>
                    <th> Language: </th>
                    <td> ${dvd.language} </td>
                </tr>
                <tr>
                    <th> Type: </th>
                    <td> ${dvd.type} </td>
                </tr>
                <tr>
                    <th> Price: </th>
                    <td> ${dvd.price} </td>
                </tr><tr/>
            </c:forEach>
        </table>
        <form action = "customer" method = "post" align = "center">
            Select Address: <select name = "id" required>
            <c:forEach var = "address" items = "${customer.address}">
                <option type = "radio" value = "${address.id}">${address}</option>
            </c:forEach></select>
            <button formaction = "/DvdStore"> Re-Select </button>
            <button formaction = "purchase"> Purchase </button>
        </form>
    </body>
</html>        
