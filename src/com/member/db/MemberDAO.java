package com.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import db.DBCon;

public class MemberDAO {
	private MemberDAO(){
		
	}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance(){
		return instance;
	}
	
	/* 1. int userCheck(String user_id, String user_pw) : member_info ���̺��� ID�� PW�� ���ؼ� �ش� ���̵� �������� ������ -1, ���̵�� ��ġ�ϰ�, ��ȣ�� �ٸ��� 0, ��� ��ġ�ϸ� 1 ����
	 * 2. MemberBean getMember(String user_id) : member_info ���̺��� �Է°��� ���̵�� ȸ�������� ã�ƿ�.	
	 * 3. int confirmID(String user_id) : ȸ�� ���Խ� ���̵� �ߺ�Ȯ���� �� ����ϴ� �޼ҵ�. ���̵� �����ϸ� 1 ������ -1 ����
	 * 4. void insertMember(MemberBean mb) : �Ű������� ���� ȸ�� ������ ���̺� ������.
	 * 5. void updateMember(MemberBean mb) : �Ű������� ���� ȸ�� ���� �߿� ���̵�� member_info ���̺��� �˻��ؼ� ȸ�� ���� ���� 
	 */
	
	//ȸ�� ���� ó��
	public int userCheck(String user_id, String user_pw){
		int result = -1;
		DBCon db = new DBCon();
		String sql = "SELECT user_pw FROM member_info WHERE user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn 	= db.connect();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs		= pstmt.executeQuery();
			
			if(rs.next()){ //���̵� ��ġ : rs.next() = true
				if(rs.getString("user_pw") != null && rs.getString("user_pw").equals(user_pw)){
					result = 1; //��й�ȣ ��ġ
				}else{
					result = 0; //��й�ȣ ����ġ
				}
			}else{
				result = -1; //���̵� ����ġ
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//ID�� ȸ������ ��������.
	public MemberBean getMember(String user_id){
		DBCon db = new DBCon();
		MemberBean mb = null;
		String sql = "SELECT * FROM member_info WHERE user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				mb = new MemberBean();
				mb.setUser_id(rs.getString("user_id"));
				mb.setUser_pw(rs.getString("user_pw"));
				mb.setUser_name(rs.getString("user_name"));
				mb.setUser_email(rs.getString("user_email"));
				mb.setAdmin(rs.getInt("admin"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return mb;
	}
	
	/* ID �ߺ�Ȯ�� ���ִ� ����*/
	public int confirmID(String user_id){
		int result = -1;
		DBCon db = new DBCon();
		String sql = "SELECT user_id FROM member_info WHERE user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = 1; //ID �̹� ����
			} else{
				result = -1; //��ġ�ϴ� ID ����
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/* ȸ�������� ���ؼ� ���� ȸ�������� member_info ���̺� ����*/
	public int insertMember(MemberBean mb){
		int result = -1;
		DBCon db = new DBCon();
		String sql = "INSERT INTO member_info VALUES(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn	= db.connect();
			pstmt	= conn.prepareStatement(sql);
			pstmt.setString(1, mb.getUser_name());
			pstmt.setString(2, mb.getUser_id());
			pstmt.setString(3, mb.getUser_pw());
			pstmt.setString(4, mb.getUser_email());
			pstmt.setInt(5, mb.getAdmin());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//ȸ������ �����ϴ� �޼ҵ�
	public int updateMember(MemberBean mb){
		int result = -1;
		DBCon db = new DBCon();
		String sql = "UPDATE member_info SET user_pw=?, user_email=?, admin=? WHERE user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb.getUser_pw()); 	//PW �ٲٱ�
			pstmt.setString(2, mb.getUser_email());	//�̸��� �ٲٱ�
			pstmt.setInt(3,mb.getAdmin());			//ȸ����� �ٲٱ�
			pstmt.setString(4, mb.getUser_id());
			
			result = pstmt.executeUpdate();			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int adminCheck(String user_id){
		int result = -1;
		DBCon db = new DBCon();
		String sql = "SELECT admin FROM member_info WHERE user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = db.connect();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs		= pstmt.executeQuery();
			
			if(rs.next()){ //admin��ġ : rs.next() = true
					result = 1; //��й�ȣ ��ġ
			}else{
				result = -1; //���̵� ����ġ
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int deleteMember(String user_id){
		int result = -1;
		DBCon db = new DBCon();
		
		String sql = "DELETE FROM member_info WHERE user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try{
			conn = db.connect();
			pstmt 	= conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			result=pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
