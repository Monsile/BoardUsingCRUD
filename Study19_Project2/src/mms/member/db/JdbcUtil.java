package mms.member.db;

//DB관련 공통 기능 클래스
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	static{
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		//디비 작업에 필요한 Connection 객체를 생성해 주는 메소드
		Connection con = null;
		try{
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			con = DriverManager.getConnection(url, "javalink","javalink");
			con.setAutoCommit(false);//Tranasaction시작을 의미하고, commit은 하지 않겠다.
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con){
		try{
			con.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt){
		try{
			stmt.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs){
		try{
			rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//transaction 처리 메소드
	public static void commit(Connection con){
		try{
			con.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void rollback(Connection con){
		try{
			con.rollback();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
