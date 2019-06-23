<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Blog Post</title>
</head>
<body>
<h2>Edit your blog post: </h2>
<form action="updateBlog" method="post">
<pre>
Id: <input type="text" name="id" value="${blog.id}" readonly/>
<textarea rows="5" cols="50" name="title">${blog.title}</textarea>
<textarea rows="5" cols="50" name="body" >${blog.body}</textarea>
</pre>
<input type="submit" value="save">
</form>
${msg}

<br>
</body>
</html>
</html>