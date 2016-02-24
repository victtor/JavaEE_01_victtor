package software.JavaEE.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import software.JavaEE.persistencia.entidade.Usuario;
import software.JavaEE.persistencia.jdbc.UsuarioDAO;

@WebServlet("/autenticador.do")
public class AutenticadorController extends HttpServlet{
	
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		 // não cria sessão no logOut
		 HttpSession sessao = req.getSession(false);
		 
		 if(sessao!=null){
			 // invalida a sessão
			 sessao.invalidate();
			 resp.sendRedirect("Login.html");
		 }
	}
	
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			    Usuario user = new Usuario();
				UsuarioDAO udao = new UsuarioDAO();

				String login = req.getParameter("login");
				String senha = req.getParameter("senha");
				user.setLogin(login);
				user.setSenha(senha); 
				// consultando user no banco
				user = udao.authentic(user);
				// verifica se user foi encontrado
				if(user!=null){
					// criando sessão para o usuário e definindo o tempo limite
					HttpSession sessao = req.getSession();
					sessao.setAttribute("user", user);
					sessao.setMaxInactiveInterval(60*5);
					// redireciona o user para a tela principal
					req.getRequestDispatcher("index.jsp").forward(req, resp);;
				} else{
					resp.getWriter().print("<script>window.alert('Não encontrado!'); "
																	+ "location.href='Login.html';</script>");
				}
				
		}
}
