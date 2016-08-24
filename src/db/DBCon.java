package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCon {
	// ������ ���̽��� ������ �ش�.
	public Connection connect(){
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ����� �����ͺ��̽� ���� ���̵�� ��й�ȣ
			conn = DriverManager.getConnection(url, "wiki", "1234");
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	// ������ ���̽��� �ݾ��ش�.
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
	
	// ������ ���̽��� �ݾ��ش�.
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
