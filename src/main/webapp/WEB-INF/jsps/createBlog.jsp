<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Blog Post</title>
</head>
<body>
<h2>Enter your blog post: </h2>
<form action="saveBlog" method="post">
<pre>
<textarea rows="5" cols="50" name="title">Blog Title</textarea>
<textarea rows="20" cols="50" name="body">What's in your mind.</textarea>
</pre>
<input type="submit" value="save">
</form>
${msg}
</body>
</html>