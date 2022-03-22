package tw.com.huafan.project.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtils {

	private final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	private final String JDBC_URL="jdbc:mysql://127.0.0.1:3306/project?serverTimezone=UTC&useSSL=false& useUnicode=true&characterEncoding=UTF-8";
	private final String JDBC_USER="root";
	private final String JDBC_PASS="Password123";
	
	private Connection conn;
	private DBUtils() {
		
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DBUtils getDB() {
		
		return new DBUtils();
	}
	
	public Connection getConnection() {
		
		try {
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void close(Connection conn) throws SQLException {
		
		if (conn != null) {
			
			conn.close();
		}
	}
	
	public void close(PreparedStatement ps) {
		
		if (ps != null) {
			
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(Statement st) throws SQLException {
		
		if (st != null) {
			
			st.close();
		}
	}

	public void close(ResultSet rs) throws SQLException {
		
		if (rs != null) {
			
			rs.close();
		}
	}
	
	
}
