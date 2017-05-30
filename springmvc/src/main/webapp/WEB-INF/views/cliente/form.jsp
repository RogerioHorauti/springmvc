<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<c:set var="context" value="${pageContext.request.contextPath}" />    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Cliente</title>
</head>
<body>
	${message }
	<a href="/">Home</a>
	<form:form action="${context }/cliente" commandName="cliente">	
		<form:input type="text" path="name"/>
		<form:errors path="name"/><br/>		
		<form:input type="text" path="email"/>
		<form:errors path="email"/><br/>		
		<input type="submit" value="Enviar"/>		
	</form:form>
	
</body>
</html>