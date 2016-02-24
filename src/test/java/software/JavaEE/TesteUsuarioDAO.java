package software.JavaEE;

import java.util.ArrayList;

import software.JavaEE.persistencia.entidade.Usuario;
import software.JavaEE.persistencia.jdbc.UsuarioDAO;
// classe para o CRUD
public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testeCadastrar();
		//testeAlterar();   
        //testeExcluir(); 
		testeSalvar();
		//testeBuscar();
		//testeBuscarAll();
		//testeAutenticar();
	}

	public static void testeAutenticar() {
		UsuarioDAO userDao = new UsuarioDAO();
		Usuario user = new Usuario();
		user.setLogin("mm");
		user.setSenha("4234243");
		
		userDao.authentic(user);
		System.out.println(userDao.authentic(user));
	}
	
	public static void testeBuscarAll() {
		UsuarioDAO userDao = new UsuarioDAO();
		// dando ao objeto user o retorno da busca
		ArrayList<Usuario> userList = userDao.searchAll();
		for(Usuario u: userList){
		System.out.println(u.toString());
	  }
	}
	
	public static void testeBuscar() {
		UsuarioDAO userDao = new UsuarioDAO();
		// dando ao objeto user o retorno da busca
		Usuario user = userDao.searchById(3);
		System.out.println(user.toString());
	}

	public static void testeSalvar(){
		Usuario user = new Usuario();
		user.setId(6);
		user.setNome("José da Silva");
		user.setLogin("josesilva");
		user.setSenha("dadadas");
		
		UsuarioDAO udao = new UsuarioDAO();
		udao.save(user);
		
		System.out.println("Salvo com sucesso!");
	}
	
	public static void testeExcluir(){
		Usuario user = new Usuario();
		user.setId(1);
		
		UsuarioDAO userDao = new UsuarioDAO();
		userDao.delete(user);
		
		System.out.println("Delete completo");
	}
	
	public static void testeAlterar(){
		// alterar o usuário
		Usuario user = new Usuario();
		user.setId(6);
		user.setNome("Victtor Motta Santtana");
		user.setLogin("victtormotta");
		user.setSenha("lovymetal");
		
		// Objeto de acesso a dados para alterar usuário no banco de dados
		UsuarioDAO userDao = new UsuarioDAO();
		userDao.update(user);
		
		System.out.println("Update completo");
	}
	
	public static void testeCadastrar(){
		       // criando o usuário
				Usuario user = new Usuario();
				user.setNome("Victor Mota Santana");
				user.setLogin("victtor");
				user.setSenha("123");
				
				// Objeto de acesso a dados para cadastrar usuário no banco de dados
				UsuarioDAO userDao = new UsuarioDAO();
				userDao.insert(user);
				
				System.out.println("Cadastro completo");
	}
}
