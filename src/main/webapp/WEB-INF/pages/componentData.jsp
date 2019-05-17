<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edit Component</h1>

<c:url value="/edit" var="editAction"/>

<form:form modelAttribute="component" method="post" action="${editAction}">
    <table>
        <tr>
            <td></td>
            <td>
                <form:input path="id" readonly="true"/>
            </td>
        </tr>
        <tr>
            <td>Name</td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>Required</td>
            <td>
                Yes:<form:radiobutton path="required" value="true"/>
                No:<form:radiobutton path="required" value="false"/>
            </td>
        </tr>
        <tr>
            <td>Quantity</td>
            <td>
                <form:input path="quantity"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="Edit Component"/>
</form:form>

</body>
</html>
