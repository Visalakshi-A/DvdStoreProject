<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Category </title>
    </head>
    <body>
        <c:if test = "${null == dvds}">
        <form action = "/DvdStore" method = "get">
            <input type = "submit" value = "Back"/>
        </form>
        <!-- To retrieve the category list if list is empty -->
        <form action = "category" method = "get">
            <c:if test = "${null == status}">
                <h3 align = center> Active Categories </h3>
                <button style = "float:right" formaction = restore-display>
                 Show Deleted Categories </button>
            </c:if>
            <c:if test = "${false == status}">
                <h3 align = center> Inactive Categories </h3>
                <button style = "float:right" formaction = "display">
                Show Active Categories </button><br/>
            </c:if>
        </form>
        <c:if test = "${null == status}">
            <c:if test = "${null == category}">
                <p> Add new Category </p>
                <form action = "insert" method = "post" autocomplete = off>
                    <input type = text name = value />
                    <input type = submit value = Insert />
                </form>
            </c:if>
            <c:if test = "${null != category}">
                <center><p> Update the Category: </p>
                <form action = "update" method = "post" autocomplete = "off">
                    <input hidden name = "id" value = "${category.id}"/>
                    <input name = "value" pattern = "[a-zA-Z]{1,20}"
                        value = "${category.value}" title = "Text Only" required = "required"/>
                    <input type = "submit" value = "Update" />
                </form></center>
            </c:if>
        </c:if>
        <table align = "center" width = 60%> 
            <c:forEach var = "category" items = "${categories}"><tr height = "40px">
                <form action = "category" method = "post">
                    <input hidden name = "id" value = "${category.id}"/>
                    <input hidden name = "selectedCategory" value = "${category.value}"/>
                        <td width = 50%>${category.value}</td>
                    <c:if test = "${null == status}">
                        <td><button formaction = "update-form">
                            <img src="<c:url value = '/resources/img/edit.png' />" height="15" width="15"/></button></td>
                        <td><button formaction = "delete" 
                            onclick = "return confirm('Do you want to Delete${category}?')">
                            <img src="<c:url value = '/resources/img/delete.png' />" height="15" width="15"/></button></td>
                        <td><button formaction = "display-dvds-by-category">Show DVDS</button></td>
                    </c:if>
                    <c:if test = "${false == status}">
                        <td><button formaction = "restore"
                            onclick = "return confirm('Do you want to Restore${category}?')">
                            Restore</button></td>
                    </c:if>
                </form>
            </tr></c:forEach>
        </table>
        </c:if>
        <c:if test = "${null != dvds}">
        <button onclick = "window.history.back()"> Back </button><br/>
        <p>DVDs present in category <b>${value}</b></p>
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
                        <form action = "remove-dvd" method = "post">
                            <input hidden name = "dvdId" value = "${dvd.id}"/>
                            <input hidden name = "categoryId" value  = "${category.id}"/>
                            <td><button onclick = "return confirm('Do you want to Remove ${dvd.name} from ${category}?')">
                                Remove DVD</button></td>
                        </form>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
    <c:if test = "${null != message}">
        <script type = "text/javascript">
            alert("${message}");
        </script>
        <c:remove var = "message"/>
    </c:if>
</html>
