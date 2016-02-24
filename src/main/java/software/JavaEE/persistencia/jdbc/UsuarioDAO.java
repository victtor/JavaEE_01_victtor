package software.JavaEE.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import software.JavaEE.persistencia.entidade.Usuario;

public class UsuarioDAO {

	// classe para conexão com o banco
    Connection con = ConexaoFactory.getConnection();
	
	public void insert(Usuario user) {
		// string para inserção
		String sql = "insert into usuario(nome, login, senha) values (?,  ?,  md5(?))";
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

	public void update(Usuario user) {
		// string para inserção
		String sql = "update usuario set nome = ?, login = ?, senha = md5(?) where id =  ?";
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

	public void delete(Usuario user) {
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
	
	// método genérico para cadastrar e alterar
    public void save(Usuario user){
	 	if( user.getId() != null && user.getId() != getLastId()){
	 		update(user);
	 	}
	 	else{
	 		insert(user);
	 	}
	 }

    /**
     * Busca de um registro no banco de dados pelo id do usuário;
     * @param id -> É um inteiro q representa o número do Id do usuário a ser buscado;
     * @return -> Um objeto usuário quando econtrado valor no resultset ou NULL quando não encontra;
     */
    public Usuario searchById(Integer id){
    		// objeto para retornar os dados vindo do resultSet
    		Usuario userReturn = new Usuario();
    		String sql = "select * from usuario where id=?";
    		
    		try (PreparedStatement ps =  con.prepareStatement(sql)){
    			ps.setInt(1, id);
    			ResultSet rs = ps.executeQuery();
    			// se existir resultados
    		    if ( rs.next() ){
    				userReturn.setId(rs.getInt("id"));
    				userReturn.setNome(rs.getString("nome"));
    				userReturn.setLogin(rs.getString("login"));
    				userReturn.setSenha(rs.getString("senha"));
    				
    				return userReturn;
    			}
    	
    			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
    		return null;
    		
    }
    
    /**
     * Realiza a busca de vários registros da tabela de todos os usuários no banco de dados
     * @return Um Arraylist de objetos usuários resultados da consulta no banco de dados
     */
    public ArrayList<Usuario> searchAll(){
		// objeto para retornar os dados vindo do resultSet
		ArrayList<Usuario> arrayUser = new ArrayList<>();
		String sql = "select * from usuario";
		
		try (PreparedStatement ps =  con.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			// se existir resultados
		    while ( rs.next() ){
				Usuario userReturn = new Usuario();
		    	userReturn.setId(rs.getInt("id"));
				userReturn.setNome(rs.getString("nome"));
				userReturn.setLogin(rs.getString("login"));
				userReturn.setSenha(rs.getString("senha"));
				// adiciona os usuarios na lista
				arrayUser.add(userReturn);
				
			}
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// retorna o array com os usuarios
		return arrayUser;		
}
    
    public Usuario authentic(Usuario usuConsulta){
    	String sql = "select * from usuario where login=? and senha=md5(?)";
    	
    	try (PreparedStatement ps = con.prepareStatement(sql)){
    		ps.setString(1, usuConsulta.getLogin());
    		ps.setString(2, usuConsulta.getSenha());
    		ResultSet rs = ps.executeQuery();
    		
    		if( rs.next() ){
    			Usuario userBanco = new Usuario();
    			userBanco.setId(rs.getInt("id"));
    			userBanco.setNome(rs.getString("nome"));
    			userBanco.setLogin(rs.getString("login"));
    			userBanco.setSenha(rs.getString("senha"));
    			
    			return userBanco;
    		}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }

    public Integer getLastId(){
    		// objeto para retornar os dados vindo do resultSet
    		Integer lastId = null;
    		String sql = "select max(id) from usuario";
    		
    		try (PreparedStatement ps =  con.prepareStatement(sql)){
    			ResultSet rs = ps.executeQuery();
    			// se existir resultados
    		    if ( rs.next() ){
    				lastId = rs.getInt(1);	
    				lastId++;
    				return lastId;
    			}
    	
    			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
    		return null;
    		
    }
    
}
