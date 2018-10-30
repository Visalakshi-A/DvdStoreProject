<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> User Login </title>
        <style>
            tr {
                height: 50px;
            }
        </style>
    </head>
    <body>
        <br/><br/><br/><br/><br/><br/><br/>
        <form action = "login" method = "post">
            <table align = center>
                <caption> <h3> Login </h3> </caption>
                <tr>
                    <th> Name: </th>
                    <td> <input type = "text" name = "name" pattern = "[^+-/*?\s]{4,30}" 
                        title = "Minimum 4 letters. Cannot have +, -, /, *, ?"/> </td>
                </tr>
                <tr>
                    <th> Password: </th>
                    <td> <input type = password name = "password"/> </td>
                </tr>
                <tr> <td/>
                    <td> <input type = "submit" value = "Log In"/>
                </tr>
                <tr>
                    <td colspan = 2 align = center> Not an User? <a href = "signup-form"> Sign Up </a> </td>
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
</html>
