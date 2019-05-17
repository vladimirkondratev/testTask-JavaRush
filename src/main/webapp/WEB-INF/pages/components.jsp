<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Components Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 4px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 5px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>

<h2>Component List</h2>
<c:url value="/search" var="searchAction"/>

<form:form action="${searchAction}" method="POST">
    <table class="center">
        <tr>
            <td width="150">Component's name:</td>
            <td><input size="30" type="text" name="name"/></td>
            <td><input type="submit" value="Search"/></td>
        </tr>
    </table>
</form:form>

<c:url value="/components" var="all"><c:param name="filterFlag" value="${1}"/></c:url>
<c:url value="/components" var="required"><c:param name="filterFlag" value="${0}"/></c:url>
<c:url value="/components" var="not_required"><c:param name="filterFlag" value="${-1}"/></c:url>
<table class="center">
    <tr>
        Filter
        <td><form:form action="${all}"><input type="submit" value="All  "/></form:form></td>
        <td><form:form action="${required}"><input type="submit" value="Required "/></form:form></td>
        <td><form:form action="${not_required}"><input type="submit" value="Not required"/></form:form></td>
    </tr>
</table>

<table class="tg">
    <tr>
        <th width="150">Name</th>
        <th width="20">Required</th>
        <th width="20">Quantity</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${componentList}" var="component">
        <tr>
            <td>${component.name}</td>
            <td>${component.required}</td>
            <td>${component.quantity}</td>
            <td><a href="components/edit/${component.id}">Edit</a></td>
            <td><a href="components/remove/${component.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>


<br/>
<div id="pagination">
    <c:url value="/components" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev.</a>
    </c:if>

    <c:forEach begin="1" end="${maxPage}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/components" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/components" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPage}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div>
<br/>
Number of computers you can assemble:  ${computers}
<br/>

<h3>Add a Component</h3>

<c:url var="addAction" value="/components/add"/>

<form:form modelAttribute="component" method="post" action="components/add">
    <table>
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
    <form:button>Add Component</form:button>
</form:form>
</body>
</html>
