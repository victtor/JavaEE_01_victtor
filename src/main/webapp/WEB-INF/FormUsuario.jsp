<%@page import="software.JavaEE.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulário para inserção de Nome, Login e Senha de Usuário</title>

	<link rel="stylesheet" href="theme/bootstrap.css" media="screen">
    <link href="theme/bootstrap-lumen.css" rel="stylesheet">
</head>
<body>
	<% 
	   Usuario usuario = (Usuario)request.getAttribute("user");
	 %>
	<div class="fieldcontainer" style="text-align: center"> 
		<form action="usucontroller.do" method="post">
			Id  <input type="number" name="id"  placeholder="Id" value="<%= usuario.getId()%>" ><br><br/>
			Nome <input type="text" name="nome"  placeholder="nome" value="<%= usuario.getNome()%>"/><br><br/>
			Login <input type="text" name="login" placeholder="login"  value="<%= usuario.getLogin()%>"/><br><br/>
			Senha <input type="password" name="senha" placeholder="senha" value="<%= usuario.getSenha()%>"/><br><br/>
	        <input type="submit" value="salvar"/>
		</form>
	</div>	

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<script src="bootstrap/usebootstrap.js"></script>

</body>
</html>