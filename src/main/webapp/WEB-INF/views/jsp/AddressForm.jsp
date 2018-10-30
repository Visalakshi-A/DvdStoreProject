<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Address </title>
        <style>
            tr {
                height: 50px;
                width: 45%;
            }
        </style>
    </head>
    <body>
        <form action = "profile" method = "get">
            <input type = submit value =  Back />
        </form>
        <br/><br/><br/>
        <form method = "post" autocomplete = "off">
            <input hidden name = "id" value = "${address.id}"/>
            <table align = "center">
                <caption><h3>
                    <c:if test = "${null == address}">Add New Address</c:if>
                    <c:if test = "${null != address}">Edit Address</c:if>
                </h3></caption>
                <tr>
                    <th> Address Line: </th>
                    <td><input type = "text" name = "addressLine" minlength = 5 maxlength = 30
                    value = "${address.addressLine}" required/></td>
                </tr>
                <tr>
                    <th> City: </th>
                    <td><input type = "text" name = "city" pattern = "[a-zA-Z\s]{3,20}"
                    value = "${address.city}" required/></td>
                </tr>
                <tr>
                    <th> State: </th>
                    <td><input type = "text" name = "state" pattern = "[a-zA-Z\s]{3,20}"
                    value = "${address.state}" required/></td>
                </tr>
                <tr>
                    <th> Country: </th>
                    <td><input type = "text" name = "country" pattern = "[a-zA-Z\s]{3,20}"
                    value = "${address.country}" required/></td>
                </tr>
                <tr>
                    <th> Zip Code: </th>
                    <td><input type = "text" name = "zipCode" pattern = "[0-9]+" maxlength = 6
                    value = "${address.zipCode}" required/></td>
                </tr>
                <tr><td/><td>
                    <c:if test = "${null != address}">
                        <input hidden name = "id" value = "${address.id}"/>
                        <button formaction = "update-address"> Update </button>
                    </c:if>
                    <c:if test = "${null == address}">
                        <button formaction = "add-address"> Add </button>
                    </c:if></td>
                </tr>
            </table>
        </form>
    </body>
</html>
