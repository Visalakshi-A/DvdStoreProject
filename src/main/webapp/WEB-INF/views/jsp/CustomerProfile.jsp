<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> ${user.name} </title>
        <style>
            tr {
                height: 30px;
            }
        </style>
    </head>
    <body>
        <a href = "/DvdStore"><button> Back </button></a>
        <form method = "get">
            <div style = "float: right">
                <button formaction = "update-details-form"> Edit my Details </button>
                <button formaction = "add-address-form"> Add Address </button>
            </div>
        </form>
        <table align = "center" width = 60%>
            <tr> <th>Name: </th> <td>${customer.name}</td> </tr>
            <tr> <th>Email-id: </th> <td>${customer.email}</td> </tr>
            <tr> <th>Contact Number: </th> <td>${customer.contactNumber}</td> </tr>
        </table>
        <center><button onclick = "view('address')"> View My Address </button></center><br/>
        <div id = "address" style = "display: none">
        <table align = "center" width = 60%>
            <tr>
                <th> Address Line </th>
                <th> City </th>
                <th> State </th>
                <th> Country </th>
                <th> Zip Code </th>
            </tr>
            <c:forEach var = "address" items = "${customer.address}">
                <tr align = center height = 50px>
                    <td> ${address.addressLine} </td>
                    <td> ${address.city} </td>
                    <td> ${address.state} </td>
                    <td> ${address.country} </td>
                    <td> ${address.zipCode} </td>
                    <td><form method = "post">
                        <input hidden name = "id" value = "${address.id}">
                        <button formaction = "update-address-form"> Update </button>
                        <button formaction = "delete-address" 
                            onclick = "return confirm('Do you want to Delete Address?')" > Delete </button>
                    </form></td>
                </tr>
            </c:forEach>
        </table>
        </div>
        <center><button onclick = "view('orders')"> View My Orders </button></center>
        <div id = "orders" style = "display: none">
        <c:if test = "${empty customer.orders}">
            <p align = "center"> No Orders yet </p>
        </c:if>
        <c:if test = "${not empty customer.orders}">
        <table align = "center" width = 50%>
            <tr>
                <th> DVD Name </th>
                <th> Language </th>
                <th> Type </th>
                <th> Price </th>
                <th> Address </th>
                <th> Order Date </th>
            </tr>
            <c:forEach var = "order" items = "${customer.orders}">
                <tr align = center height = 50px>
                    <td> ${order.dvd.name} </td>
                    <td> ${order.dvd.language} </td>
                    <td> ${order.dvd.type} </td>
                    <td> ${order.dvd.price} </td>
                    <td> ${order.address.addressLine} </td>
                    <td> ${order.orderDate} </td>
                    <td><form method = "post">
                        <input hidden name = "id" value = "${order.id}" /> 
                        <c:if test = "${order.status}">
                            <button formaction = "cancel-order" 
                                onclick = "return confirm('Do you really want to cancel Order?')"> Cancel </button>
                        </c:if></form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
        </div>
    </body>
    <c:if test = "${not empty message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
    <script type = "text/javascript" src = "<c:url value = '/resources/js/DvdStore.js' />"></script>
</html>
