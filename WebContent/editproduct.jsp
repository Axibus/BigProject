<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="edit" method="post">
		ProductId:  <span name="id">${p.getId() }</span>
		name:*<input type ="text" name="name" value="${p.getName() }" />
		ProductTypeId:*<input type ="text" name="pid" value="${p.getProTypeId() }" />
		Price:*<input type ="text" name="price" value="${p.getPrice() }" />
		<input type ="submit"/>
	
	</form>
</body>
</html>