<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<title>CRUD AFIP - TESTE 1</title>
	
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
</head>
<body>

	<c:choose>
	<c:when test="${!empty clientes}">
		<h3><span class="label label-default">Clientes Cadastrados</span></h3>
		<div class="panel panel-default">
		<div class="panel-body">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>Nome</th>
					<th>CPF</th>
					<th>Email</th>
					<th>A&ccedil;&atilde;o</th>
				</tr>
			</thead>
			<tbody>	
			<c:forEach items="${clientes}" var="cliente">
			<tr>
				<td>${cliente.nome}</td>
				<td>${cliente.cpf}</td>
				<td>${cliente.email}</td>
				<td>
					<c:if test="${remover eq true}"><a href="removeCliente?id= ${cliente.id}">Remover</a></c:if>
					<c:if test="${alterar eq true}"><a href="alteraCadastro?id= ${cliente.id}">Alterar</a></c:if>
				</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
	</c:when>
	
	<c:otherwise>
		<br />
		<h3>N&atilde;o existem clientes cadastrados</h3>
	</c:otherwise>
	</c:choose>
	<br/><br/>
	
	<div id="lnk">
		<a href="adiciona">Cadastrar novo Cliente</a><br />
		<a href="/teste1">Menu Principal</a>
	</div>
		
</body>
</html>