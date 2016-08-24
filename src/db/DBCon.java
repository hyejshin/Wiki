package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCon {
	// 데이터 베이스를 연결해 준다.
	public Connection connect(){
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 사용할 데이터베이스 계정 아이디와 비밀번호
			conn = DriverManager.getConnection(url, "wiki", "1234");
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	// 데이터 베이스를 닫아준다.
	public void close(PreparedStatement pstmt, Connection conn){
		try{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// 데이터 베이스를 닫아준다.
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
		try{
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
