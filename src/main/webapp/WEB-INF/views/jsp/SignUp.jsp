<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> User Sign Up </title>
        <style>
            tr {
                height: 50px;
            }
        </style>
    </head>
    <body>
        <br/><br/><br/><br/><br/><br/><br/>
        <form action = "signup" method = "post">
            <table align = center>
                <caption> <h3> Sign Up </h3> </caption>
                <tr> <td style = "color: red" id = "error-message"> </td> </tr>
                <tr>
                    <th> Name: </th>
                    <td> <input type = "text" name = "name" pattern = "[^+-/*%?\s]{5,30}" title = "Cannot have +, -, /, *, %, ?"/> </td>
                </tr>
                <tr>
                    <th> Password: </th>
                    <td> <input id = "password" type = "password" name = "password"
                        onchange = "checkPassword();" required/> </td>
                </tr>
                <tr>
                    <th> Confirm Password: </th>
                    <td> <input id = "confirm-password" type = "password" name = "confirm-password"
                        onchange = "checkPassword();" required/> </td>
                </tr>
                <tr> <td/>
                    <td> <input id = "submit" type = "submit" value = "Sign Up" /> </td>
                </tr>
                <tr>
                    <td colspan = 2 align = center> Already a User? <a href = "/DvdStore"> Log In </a> </td>
                </tr>
            </table>
        </form>
    </body>
    <c:if test = "${not empty message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
    <script type = "text/javascript" src = "<c:url value = '/resources/js/DvdStore.js' />"> </script>
</html>
