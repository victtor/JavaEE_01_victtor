<%@page import="software.JavaEE.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de usuários</title>
</head>
<body>
<%
   // atribuindo o valor do atributo request Users a um arraylist, fazendo um cast de Object para Array
	List<Usuario> list = (List<Usuario>)request.getAttribute("users");
	out.print("<table border=1>");
	out.print("<tr <th> id </th> <tr <th> nome </th> </tr>");
	for(Usuario u: list){
		out.print("<tr>");
		out.println("<td>"+u.getId()+"</td><td> "+u.getNome() + "</td");
		out.print("/tr");
	}
	out.print("</table>");
%>
</body>
</html>