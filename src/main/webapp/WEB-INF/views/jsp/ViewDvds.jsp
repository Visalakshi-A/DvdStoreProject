<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> DVDS </title>
    </head>
    <body>
        <form action = "/DvdStore" method = "get">
            <input type = "submit" value = "Back"/>
        </form><br/><br/>
        <c:if test = "${null == status}">
            <form method = "get">
                <div style = "float: left">
                <button style = "width: 250px;" formaction = "insert-form"> Insert New DVD </button></div>
                <div style = "float: right"> 
                <button style = "width: 250px;" formaction = "restore-display"> Show Deleted DVDs </button></div>
            </form>
            <h2 align = center> Active DVDs </h2>
            <div style = "float: right; margin-right: 100px;"><form method = "get">
                <input type = "text" name = "name" placeholder = "Search"/>
                <button formaction = "display-dvds-by-name">
                    <img src="<c:url value = '/resources/img/search.png'/>" height="15" width="15"/></button>
            </form></div>
            <c:if test = "${null == categories}">
                <jsp:forward page = "/dvd/categories"/>
            </c:if>
            <form method = "get">
                Filter: <select name = "category" style = "width: 200px">
                <c:forEach var = "category" items = "${categories}">
                    <option value = "${category.id}">${category.value}</option>
                </c:forEach>
                </select>
                <button formaction = "display-dvds-by-category"> Filter </button>
                <button formaction = "display"> Remove Filter </button>
            </form>
        </c:if>

        <c:if test = "${null != status}">
            <form action = "display" method = "get">
                <div style = "float: right">
                <input type = submit style = "width: 250px;" value = "Show Active DVDs" /></div>
            </form><br/>
            <h2 align = center> Inactive DVDs </h2>
        </c:if>

        <c:if test = "${null == dvds}">
            <jsp:forward page = "display"/>
        </c:if><br/>
        <div style = "overflow: scroll; height: 75%; width: 100%">
        <c:if test = "${empty dvds}">
            <p align = center> No DVD available </p>
        </c:if>
        <c:if test = "${not empty dvds}">
        <table align = "center" cellpadding = "10">
            <tr>
                <th>Name</th>
                <th>Language</th>
                <th>Type</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Rating</th>
                <th>Released</th>
                <th>Category</th>
            </tr>
            <%@ page import = "com.ideas2it.dvdstore.utils.DateUtils" %>
            <c:forEach var = "dvd" items = "${dvds}">
                <tr align = "center">
                    <td>${dvd.name}</td>
                    <td>${dvd.language}</td>
                    <td>${dvd.type}</td>
                    <td>${dvd.quantity}</td>
                    <td>${dvd.price}</td>
                    <td>${dvd.rating}</td>
                    <td>${DateUtils.getPeriodDiff(dvd.releaseDate)}</td>
                    <td>${dvd.categories}</td>

                    <form method = "post">
                        <input hidden name = "id" value = "${dvd.id}"/>
                        <c:if test = "${null == status}">
                            <td><button formaction = "update-form" 
                                onclick = "return confirm('Do you want to Update ${dvd.name}?')">
                                <img src="<c:url value = '/resources/img/edit.png' />" height="15" width="15"/></button></td>
                            <td><button formaction = "delete"
                                onclick = "return confirm('Do you want to Delete ${dvd.name}?')">
                                <img src="<c:url value = '/resources/img/delete.png'/>" height="15" width="15"/></button></td>
                        </c:if>
                        <c:if test = "${null != status}">
                            <td><button formaction = "restore"
                                onclick = "return confirm('Do you want to Restore ${dvd.name}?')">
                                Restore </button>
                            </td>
                        </c:if>
                    </form>
                </tr>
            </c:forEach>
        </table></c:if></div>
    </body>
    <c:if test = "${null != message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
</html>
