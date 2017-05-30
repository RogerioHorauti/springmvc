<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show</title>
</head>
<body>
	<a href="${context}/produtos">Listar Produtos</a>
	<a href="${context }/shopping">Sacola de compras</a>
	<br/>
	<h2>${product.title }</h2>
	<p>${product.dscription }</p>
	
	<form:form servletRelativeAction="/shopping" method="post">
		<input type="hidden" value="${product.id}" name="productId"/>	
		<c:forEach items="${product.prices}" var="price">
			[ ${price.bookType} - ${price.value} ]
			<input type="radio" name="bookType" id="${product.id}-${price.bookType}" value="${price.bookType}"
				${price.bookType.name()=='COMBO'?'checked':'' }>
		</c:forEach>
		<input type="submit" alt="Compre agora" title="Compre agora" value="comprar"/>
	</form:form>
	
	
	
</body>
</html>