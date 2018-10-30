<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>
            <c:if test = "${null != customer}">${customer.name}-Edit</c:if>
            <c:if test = "${null == customer}"> Register </c:if>
        </title>
        <style>
            tr {
                height: 50px;
                width: 45%;
            }
        </style>
    </head>
    <body>
        <c:if test = "${null == customer}">
            <br/><br/><br/>
            <h3 align = center> Customer Registration </h3><br/>
            <p align = center> User Name: ${user.name} </p>
        </c:if>
        <c:if test = "${null != customer}">
            <form action = "profile" method = "get">
                <input type = submit value =  Back />
            </form>
            <h3 align = center> Update Details </h3>
        </c:if>
        <form method = "post" align = "center">
            <input hidden name = "id" value = "${customer.id}"/>
            <table align = center>
                <tr>
                    <th> Name: </th>
                    <td><input type = "text" name = "name" pattern = "[a-zA-Z]{2,30}"
                    value = "${customer.name}" required/></td>
                </tr>
                <tr>
                    <th> Email-id: </th>
                    <td><input type = "text" name = "email" pattern = "[a-zA-Z0-9./_]{5,40}[@][a-zA-Z]{2,}[.][a-zA-Z]+"
                    value = "${customer.email}" required/></td>
                </tr>
                <tr>
                    <th> Contact Number: </th>
                    <td><input type = "text" name = "contactNumber" pattern = "[987]{1}[0-9]{9}"
                    value = "${customer.contactNumber}" required/></td>
                </tr>
                <c:if test = "${null == customer}">
                    <tr>
                        <th> Residential Address</th><td/><tr><th> Address Line: </th>
                        <td><input type = "text" name = "address[0].addressLine" minlength = 5 maxlength = 30 required/></td>
                    </tr>
                    <tr>
                        <th> City: </th>
                        <td><input type = "text" name = "address[0].city" pattern = "[a-zA-Z\s]{3,20}" required/></td>
                    </tr>
                    <tr>
                        <th> State: </th>
                        <td><input type = "text" name = "address[0].state" pattern = "[a-zA-Z\s]{3,20}" required/></td>
                    </tr>
                    <tr>
                        <th> Country: </th>
                        <td><input type = "text" name = "address[0].country" pattern = "[a-zA-Z\s]{3,20}" required/></td>
                    </tr>
                    <tr>
                        <th> Zip Code: </th>
                        <td><input type = "text" name = "address[0].zipCode" pattern = "[0-9]{6}" maxlength = 6 required/></td>
                    </tr>
                </c:if>
            </table>
            <center><br/>
            <c:if test = "${null == customer}">
                <button formaction = register> Register </button>
            </c:if>
            <c:if test = "${null != customer}">
                <button formaction = update> Update </button>
            </c:if></center>
        </form><br/>
    </body>
    <c:if test = "${not empty message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
</html>
