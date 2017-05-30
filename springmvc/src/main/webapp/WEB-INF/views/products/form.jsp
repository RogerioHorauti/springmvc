<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="url" value="${pageContext.request.requestURL}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulario</title>

	<!-- Bootstrap -->
    <link href="../resources/css/bootstrap.css" rel="stylesheet">
	<link href="../resources/css/bootstrap-datepicker.css" rel="stylesheet">
	
	<script src="../resources/js/jquery.js"></script>
    <script src="../resources/js/bootstrap.js"></script>
	<script src="../resources/js/bootstrap-datepicker.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function () {
	      $('#releaseDate').datepicker({
	          format: "dd/mm/yyyy",
	          language: "pt-BR"
	      });
	    });
	</script>
	
</head>
<body>
	<a href="${context }/">Home</a><br/><br/>
	
	<spring:hasBindErrors name="product">	
		<c:forEach var="error" items="${errors.allErrors}">
			${error.code}<br/>
		</c:forEach>	
		<br/><br/>
	</spring:hasBindErrors>
	
	<form:form method="post" servletRelativeAction="/produtos" commandName="product" >	
		<div>
			<label for="title">Titulo</label>
			<form:input type="text" path="title" />
			<form:errors path="title"/>
		</div>
		<div>
			<label for="dscription">Descrição</label>
			<form:textarea rows="10" cols="20" path="dscription" ></form:textarea>
			<form:errors path="dscription"/>
		</div>
		<div>
			<label for="pages">Número de paginas</label>
			<form:input type="text" path="pages" />
			<form:errors path="pages"/>
		</div>
		<div>
			<label for="releaseDate">Data de lançamento</label>			
			<input type="text" name="releaseDate" id="releaseDate" class="form-control"/>
			<form:errors path="releaseDate"/>				
		</div>
		<div>
			<c:forEach items="${types}" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType}">${bookType}</label>
					<input type="text" name="prices[${status.index}].value"	id="price_${bookType}"/>
					<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
				</div>
			</c:forEach>
		</div>
		<div>
			<input type="submit" value="Enviar">
		</div>
	</form:form>
</body>
</html>