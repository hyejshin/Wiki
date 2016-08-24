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
	
	/* 1. int userCheck(String user_id, String user_pw) : member_info 테이블에서 ID와 PW를 비교해서 해당 아이디가 존재하지 않으면 -1, 아이디는 일치하고, 암호는 다르면 0, 모두 일치하면 1 리턴
	 * 2. MemberBean getMember(String user_id) : member_info 테이블에서 입력값인 아이디로 회원정보를 찾아옴.	
	 * 3. int confirmID(String user_id) : 회원 가입시 아이디 중복확인할 때 사용하는 메소드. 아이디가 존재하면 1 없으면 -1 리턴
	 * 4. void insertMember(MemberBean mb) : 매개변수로 받은 회원 정보를 테이블에 삽입함.
	 * 5. void updateMember(MemberBean mb) : 매개변수로 받은 회원 정보 중에 아이디로 member_info 테이블을 검색해서 회원 정보 수정 
	 */
	
	//회원 인증 처리
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
			
			if(rs.next()){ //아이디 일치 : rs.next() = true
				if(rs.getString("user_pw") != null && rs.getString("user_pw").equals(user_pw)){
					result = 1; //비밀번호 일치
				}else{
					result = 0; //비밀번호 불일치
				}
			}else{
				result = -1; //아이디 불일치
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
	
	//ID로 회원정보 가져오기.
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
	
	/* ID 중복확인 해주는 역할*/
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
				result = 1; //ID 이미 존재
			} else{
				result = -1; //일치하는 ID 없음
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
	
	/* 회원가입을 통해서 얻은 회원정보를 member_info 테이블에 삽입*/
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
	
	//회원정보 수정하는 메소드
	public int updateMember(MemberBean mb){
		int result = -1;
		DBCon db = new DBCon();
		String sql = "UPDATE member_info SET user_pw=?, user_email=?, admin=? WHERE user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb.getUser_pw()); 	//PW 바꾸기
			pstmt.setString(2, mb.getUser_email());	//이메일 바꾸기
			pstmt.setInt(3,mb.getAdmin());			//회원등급 바꾸기
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
			
			if(rs.next()){ //admin일치 : rs.next() = true
					result = 1; //비밀번호 일치
			}else{
				result = -1; //아이디 불일치
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
