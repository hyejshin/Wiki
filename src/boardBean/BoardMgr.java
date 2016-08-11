package boardBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.PooledConnection;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import java.util.*;

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
	
	//테이블에 글제목, 등록자, 등록시간, 이미지, 내용을 저장하는 함수
	public boolean insertBoard(String title, String writer, String writeDate, String modifier, 
			String modifyDate, String category, String image, String explanation){
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			System.out.println("before execute");
			String mySQL = "INSERT INTO board (idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation)";
			mySQL +=  "VALUES(seq_idx.NEXTVAL, '" + title + "', '" + writer + "', '" + writeDate;
			mySQL +=  "', '"  + modifier + "', '"  + modifyDate + "', '" + category + "', '" + image + "', '" + explanation + "')";
			stmt.executeQuery(mySQL);
			
			System.out.println("after execute");
			stmt.close();
			conn.close();
			
			return true;
		}catch (Exception e){
			System.out.println("Exception: " + e);
			return false;
		}
	}
	
	public String nvl(String str){
		if(str == null)
			return "";
		else
			return str;
	}
	
	public Board getBoardInfo(int idx){
		Connection conn = null;
		Statement stmt = null;
		Board info = new Board();

		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			String mySQL = "SELECT * FROM board WHERE idx = " + idx;
			ResultSet result = stmt.executeQuery(mySQL);
			result.next();
				
			info.setTitle(result.getString("title"));
			info.setWriter(result.getString("writer"));
			info.setWriteDate(result.getString("writeDate"));
			info.setModifier(nvl(result.getString("modifier")));
			info.setModifyDate(nvl(result.getString("modifyDate")));
			info.setImage(result.getString("category"));
			info.setImage(result.getString("image"));
			info.setExplanation(result.getString("explanation"));
			
			stmt.close();
			conn.close();
		}catch (Exception e){
			System.out.println("Exception: " + e);
		}
		
		return info;
	}
	
	public Vector getBoardList(int idx){
		Connection conn = null;
		Statement stmt = null;
		Vector vecList = new Vector();
		
		try {
			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			//String mySQL = "INSERT INTO board (idx, title, writer, writeDate, image, explanation)";
			//stmt.executeQuery(mySQL);
			
			stmt.close();
			conn.close();
			
		}catch (Exception e){
			System.out.println("Exception: " + e);
		}
		return vecList;
	}
}
