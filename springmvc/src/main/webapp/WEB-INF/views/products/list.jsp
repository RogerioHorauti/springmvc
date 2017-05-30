<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
<c:set var="context" value="${pageContext.request.contextPath}" />

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Livros</title>
</head>
<body>
	<a href="${context }/">Home</a> 
	
	Seu carrinho ${shoppingCart.quantity}
	${sessionScope['scopedTarget.shoppingCart'].quantity}
	
	<a href="${context }/shopping">Sacola de compras</a> 
	
	${success}
	${message}
	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal" var="user"/>
		<div>
		Olá ${user.name}
		</div>
	</security:authorize>
	<a href="${context }/logout">Logout</a>
	<table>
		<tr>
			<td>Titulo</td>
			<td>Description</td>
			<td></td>
		</tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.title}</td>
				<td>${product.dscription}</td>
				<td><a href="${context }/produtos/${product.id}">Details</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>