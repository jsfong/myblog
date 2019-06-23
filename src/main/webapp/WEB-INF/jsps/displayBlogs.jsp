<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@page isELIgnored="false" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Blog Entry</title>
</head>
<body>
<h2>Blog: </h2>
<table>
<c:forEach items="${blogs}" var="blog">
<tr>
${blog.id}
<br>
${blog.title}
<br>
${blog.body}
<br>
<table>
<tr>
<td><a href="showEditBlog?id=${blog.id}">Edit</a></td>
<td><a href="deleteBlog?id=${blog.id}">Delete</a></td>
</tr>
</table>
<br>
<br>
<hr>
</tr>
</c:forEach>

</table>
</body>
</html>