<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CRUD AFIP - TESTE 1</title>
	
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="resources/js/script.js"></script>
</head>
<body>
	<h3><span class="label label-default">Alterar Cliente</span></h3><br />
	
	<form class="col-sm-12 form-horizontal" action="alteraCliente" method="post">
		<div class="col-sm-3"></div>
		<div class="col-sm-6 form-group">
			Nome: 	<input class="form-control" maxlength="100" name="nome" type="text" value="${cliente.nome}" />
			<form:errors path="cliente.nome"/><br /><br />
			
			CPF:    <input id="cpf" class="form-control" maxlength="11" name="cpf" type="text" value="${cliente.cpf}" />
			<form:errors path="cliente.cpf"/><br /><br />
			
			E-mail: <input class="form-control" maxlength="100"name="email" type="text" value= "${cliente.email}" />
			<form:errors path="cliente.email"/><br /><br />
			
			<input name="id" type="hidden" value= "${cliente.id}" />
			<input class="btn btn-default" type="submit" value="Alterar">
		</div>
		<div class="col-sm-3"></div>
	</form>
	<div id="lnk">
		<a href="lista">Clientes Cadastrados</a><br/>
		<a href="/teste1">Menu Principal</a>
	</div>
</body>
</html>