<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>
            <c:if test = "${null == dvd}"> Add New DVD </c:if>
            <c:if test = "${null != dvd}"> Update DVD </c:if>
        </title>
        <style>
            th {
                text-align: right;
                margin-right: 50px;
                padding: 0 15px 0 0;
                width: 40%;
            }
            tr {
                height: 30px;
            }
        </style>
    </head>
    <body>
        <form method = "get">
            <button formaction = display> Back </button>
        </form>
        <form:form method = "post" autocomplete = "off" onsubmit = "return validate();">
            <h3 align = center>
            <c:if test = "${null != dvd}">
                Update DVD Details <br/><br/>
                <form:hidden path = "id" value = "${dvd.id}" />
            </c:if>
            <c:if test = "${null == dvd}">
                Enter DVD Details <br/><br/>
            </c:if></h3>
            <table align = center width = 80% colspacing = 15>
            <tr>
                <th>Name:</th>
                <td><form:input path = "name" value = "${dvd.name}" required = "required"/></td>
            </tr>
            <tr>
                <th>Language:</th>
                <td><form:input path = "language" type = "text" value = "${dvd.language}"
                    pattern = "[a-zA-Z]{3,15}" title = "Must be text only" required = "required"/></td>
            </tr>
            <tr>
                <th>Type:</th>
                <td><label>
                        <c:choose><c:when test = "${dvd.type.equals('Normal')}">
                            <form:radiobutton path = "type"  value = "Normal"
                                checked = "checked" />Normal
                        </c:when>
                        <c:otherwise>
                            <form:radiobutton path = "type"  value = "Normal" />Normal
                        </c:otherwise></c:choose></label>
                    <label>
                        <c:choose><c:when test = "${dvd.type.equals('HD')}">
                            <form:radiobutton path = "type"  value = "HD"
                                checked = "checked" />HD
                        </c:when>
                        <c:otherwise>
                            <form:radiobutton path = "type"  value = "HD" />HD
                        </c:otherwise></c:choose></label>
                    <label>
                        <c:choose><c:when test = "${dvd.type.equals('Full HD')}">
                            <form:radiobutton path = "type"  value = "Full HD"
                                checked = "checked" />Full HD
                        </c:when>
                        <c:otherwise>
                            <form:radiobutton path = "type"  value = "Full HD" />Full HD
                        </c:otherwise></c:choose></label>
                </td>
            </tr>
            <tr>
                <th>Quantity:</th>
                <td><form:input path = "quantity" type = "number" min = "0" 
                    value = "${dvd.quantity}" required = "required"/></td>
            </tr>
            <tr>
                <th>Price:</th>
                <td><form:input path = "price" type = "number" step = "0.01" min = "10" 
                    value = "${dvd.price}" required = "required"/></td>
            </tr>
            <tr>
                <th>Rating:</th>
                <td><form:input path = "rating" type = "number" step = "0.1" min = "0" max = "10"
                    value = "${dvd.rating}" required = "required"/></td>
            </tr>
            <tr>
                <th>Release Date:</th>
                <td><form:input id = "releaseDate" path = "releaseDate" type = "date" 
                    value = "${dvd.releaseDate}" required = "required"/></td>
            </tr>
            <tr><td/><td style = "color: red" id = "dateDiv"></td></tr>
            <tr>
                <th>Category:</th>
                <c:forEach var = "category" items = "${categories}">
                    <td><label>
                            <input type = "checkbox" name = "category" value = "${category.id}"
                                <c:if test = "${dvd.categories.contains(category)}"> checked </c:if> />
                            ${category.value}
                        </label></td></tr><tr><td/>
                            <!--<c:forEach var = "dvdCategory" items = "${dvd.categories}">
                                <c:if test = "${dvdCategory.id == category.id}">checked = "checked"</c:if>
                            </c:forEach>/>-->
                </c:forEach>
                <td style = "color: red" id = "categoryDiv"></td></tr>
                <tr><td/>
                <c:if test = "${null != dvd}">
                <td><button formaction = "update"> Update </button></td></tr>
                </c:if>
                <c:if test = "${null == dvd}">
                <td><button formaction = "insert"> Insert </button></td></tr>
                </c:if>
            </table>
        </form:form>
    </body>
    <c:if test = "${null != message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
    <script type = "text/javascript" src = "<c:url value = '/resources/js/DvdStore.js' />"></script>
</html>
