package software.JavaEE;

import software.JavaEE.persistencia.entidade.Usuario;
import software.JavaEE.persistencia.jdbc.UsuarioDAO;
// classe para o CRUD
public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testeCadastrar();
		//testeAlterar();   
        //testeExcluir(); 
	}

	public static void testeExcluir(){
		Usuario user = new Usuario();
		user.setId(1);
		
		UsuarioDAO userDao = new UsuarioDAO();
		userDao.excluir(user);
		
		System.out.println("Delete completo");
	}
	
	public static void testeAlterar(){
		// alterar o usuário
		Usuario user = new Usuario();
		user.setId(1);
		user.setNome("Victtor Motta Santtana");
		user.setLogin("victtormotta");
		user.setSenha("lovymetal");
		
		// Objeto de acesso a dados para alterar usuário no banco de dados
		UsuarioDAO userDao = new UsuarioDAO();
		userDao.alterar(user);
		
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
				userDao.cadastrar(user);
				
				System.out.println("Cadastro completo");
	}
}
