<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Error Page</title>
    </head>
    <body>
        <font color="red">Error: Sorry a problem occured. Requested Action could not be performed.</font><br>

        Error code: <%= response.getStatus() %><br>
        Go to <a href="/DvdStore">Home Page</a>
    </body>
</html>
