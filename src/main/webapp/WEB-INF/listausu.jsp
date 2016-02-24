<%@page import="software.JavaEE.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de usuários</title>
<!-- script para pegar como parâmetro o id do for e setar como parâmetro
para acao excluir o id, depois de confirmação do usuário -->
<script type="text/javascript">
	function confirmarExclusao(id){
		if (window.confirm("Tem certeza que deseja excuir?")) {
			location.href="usucontroller.do?acao=excluir&id="+id;
		};
	}
</script>
</head>
<body>
<%
   // atribuindo o valor do atributo request Users a um arraylist, fazendo um cast de Object para Array
	List<Usuario> list = (List<Usuario>)request.getAttribute("users");
%>	
	<table border=1>
		<tr >
			<th> id </th>
			<th> nome </th> 
			<th> ação </th>
		</tr>
<%	for(Usuario u: list){ %>
		<tr>
			<td><%out.print(u.getId() ); %></td>
			<td><%=u.getNome() %></td>
			<td><a href="usucontroller.do?acao=alterar&id=<%=u.getId() %>">alterar | </a> <a href="javascript:confirmarExclusao(<%=u.getId()%>)"> excluir </a></td>
		</tr>
 <%	} %>
 </table>
 <br></br><a href="usucontroller.do?acao=novo" > novo cadastro </a>

</body>
</html>