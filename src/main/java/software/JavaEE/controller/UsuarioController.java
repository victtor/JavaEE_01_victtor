package software.JavaEE.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import software.JavaEE.persistencia.entidade.Usuario;
import software.JavaEE.persistencia.jdbc.UsuarioDAO;
// http://localhost:8080/javaEE/usucontroller.do
@WebServlet("/usucontroller.do")
public class UsuarioController  extends HttpServlet {
		
		public UsuarioController(){
			System.out.println("Construtor...");
		}

		@Override
		public void init() throws ServletException {
			System.out.println("Init...");
	    	super.init();
		}

		/**
		 * Método doGet para receber valores pela url com o Http Request e transformá-los em objeto Usuarui e ser inserido no banco.
		 * Url exemplo ->  localhost:8080/javaEE/usucontroller.do?nome=ze&login=zeze&senha=123
		 */
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("Chamou doGet!"+ req);
			resp.setContentType("text/html");
			// passando dois parâmetros pelo doGet para a exclusão ou para a busca e listagem de Ids
			String acao = req.getParameter("acao");
			UsuarioDAO udao = new UsuarioDAO();
			Usuario user = new Usuario();

			if(acao.equals("excluir")){
			// se ação for excluir irá pegar o id para exclusão
			String id = req.getParameter("id");
			if (id!=null)
				user.setId(Integer.parseInt(id));
			
			udao = new UsuarioDAO();
			udao.delete(user);

			resp.sendRedirect("usucontroller.do?acao=lista");	
			} else if (acao.equals("lista")) {
				// cria array list do tipo usuario e o atribui o valor do retorno do método searchAll
               	List<Usuario> listaUser = udao.searchAll();
        		// seta um atributo ao request, no caso, a lista de usuários (Map)
               	req.setAttribute("users", listaUser);
                	
               	// constrói uma instância para o envio dos atributos
               	RequestDispatcher rqDisp = req.getRequestDispatcher("WEB-INF/listausu.jsp");
               	// redireciona o servlet para um JSP
               	rqDisp.forward(req, resp);
			} else if (acao.equals("alterar")) {
				// método para alterar dados, pega o id, retorna o 
				// usuario
				String id = req.getParameter("id");
				user = udao.searchById(Integer.parseInt(id));
				req.setAttribute("user", user);

				RequestDispatcher rqDisp = req.getRequestDispatcher("WEB-INF/FormUsuario.jsp");
				rqDisp.forward(req, resp);
			} else if (acao.equals("novo")) {
				// método para pegar o último id e adicionar +1
				Integer id = udao.getLastId();
				user.setId(id);
				user.setNome("");
				user.setLogin("");
				user.setSenha("");

				req.setAttribute("user", user);

				RequestDispatcher rqDisp = req.getRequestDispatcher("WEB-INF/FormUsuario.jsp");
				rqDisp.forward(req, resp);
			}
		}
		
		/**
		 * Método doPost para receber valores como chave e valor (key, values) com o Postman e com o Http Request transformá-los em objeto Usuario
		 * para ser adicionado no banco.
		 * Url exemplo ->  localhost:8080/javaEE/usucontroller.do
		 */
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
       
		Usuario user = new Usuario();
		if (id != null) {
			user.setId(Integer.parseInt(id.trim()));   	// limpando os espaços para poder ser um inteiro válido

		}
		user.setNome(nome);
		user.setLogin(login);
		user.setSenha(senha);

		UsuarioDAO udao = new UsuarioDAO();
		udao.save(user);
		resp.sendRedirect("usucontroller.do?acao=lista");
		}
		
		@Override
		public void destroy() {
		System.out.println("Destroy	...");
		super.destroy();
		}
		
}
