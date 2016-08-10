package boardBean;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.PooledConnection;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class BoardMgr {
	private OracleConnectionPoolDataSource ocpds = null;
	private PooledConnection pool = null;
	
	public BoardMgr() {
		try{
			ocpds = new OracleConnectionPoolDataSource();
			ocpds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			ocpds.setUser("wiki"); //DB계정
			ocpds.setPassword("1234"); //DB비번
			pool = ocpds.getPooledConnection(); //연결시도
		} catch(Exception e) {
			System.out.println("Error: DB Connection failed"); //연결실패
		}
	}
	
	public boolean insertBoard(String title, String writer, String writeDate, String image, String explanation){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String mySQL = "INSERT INTO board (idx, title, writer, writeDate, image, explanation)";
			mySQL +=  "VALUES(seq_idx.NEXTVAL, '" + title + "', '" + writer;
			mySQL += "', '" + writeDate + "', '" + image + "', '" + explanation + "')";
			stmt.executeQuery(mySQL);
			
			stmt.close();
			conn.close();
			
			return true;
		}catch (Exception e){
			System.out.println("Exception: " + e);
			return false;
		}
	}
}
