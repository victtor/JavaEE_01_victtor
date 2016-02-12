package software.JavaEE.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import software.JavaEE.persistencia.entidade.Usuario;

public class UsuarioDAO {

	public void cadastrar(Usuario user) {
		// classe para conexão com o banco
		Connection con = ConexaoFactory.getConnection();
		// string para inserção
		String sql = "insert into usuario(nome, login, senha) values (?,  ?,  ?)";
		// prepare statement para o sql
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, user.getNome());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getSenha());
            // executa no banco 
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void alterar(Usuario user) {
		Connection con = ConexaoFactory.getConnection();
		// string para inserção
		String sql = "update usuario set nome = ?, login = ?, senha = ? where id =  ?";
		// prepare statement para o sql
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, user.getNome());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getSenha());
			ps.setInt(4, user.getId());
            // executa no banco 
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void excluir(Usuario user) {
		Connection con = ConexaoFactory.getConnection();
		// string para inserção
		String sql = "delete from usuario where id = ?";
		// prepare statement para o sql
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1 , user.getId());
            // executa no banco 
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
