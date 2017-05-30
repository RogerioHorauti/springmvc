<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
  
<c:set var="context" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Finalizar compra</title>
</head>
<body>
	<table>
		<c:forEach items="${shoppingCart.list}" var="item">
		<tr>
			<td >
				<img src="" alt="${item.product.title}"/>
			</td>
			<td >
				${item.product.title} - ${item.bookType}
			</td>
			<td >
				${item.price}
			</td>
			<td >
				<input type="text" readonly="readonly" value="${shoppingCart.getQuantity(item)}">
			</td>
				<td >
				${shoppingCart.getTotal(item)}
			</td>
		</tr>
		</c:forEach>	
		<tr>
			<td>
				<form:form servletRelativeAction="/payment" method="post">
					<input type="submit" name="checkout" value="Finalizar compra " id="checkout" />
				</form:form>
			</td>
			<td >
				${shoppingCart.total}
			</td>
			<td></td>
		</tr>
	</table>
	
	
</body>
</html>