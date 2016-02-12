package software.JavaEE.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {
        
		
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaweb", "postgres", "post");
		} catch (SQLException e) {
			// relançando a exception
			throw new RuntimeException(e);
		}
		
	}

}
