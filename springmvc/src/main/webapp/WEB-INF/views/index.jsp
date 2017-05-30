<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index</title>
</head>
<body>
	<h1>Hello World!</h1>${message }
	<a href="${context}/produtos">Listar Produtos</a>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="${context}/produtos/form">Cadastro de Produto</a>
	</sec:authorize>
	
	<a href="${context}/cliente/form">Cadastro de Cliente</a>
	<a href="${context }/logout">Logout</a>
	<br/><br/>
	<form method="POST" action="${context}/" enctype="multipart/form-data">
		<input type="file" name="file" />
		<input type="submit" value="Submit" />
	</form>
</body>
</html>