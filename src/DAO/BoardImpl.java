package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCon;
import DTO.BoardBean;

public class BoardImpl implements Board{
	// 게시글 bean 리스트를 반환 한다. 
	public ArrayList<BoardBean> bList() {
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
       
        // 데이터 베이스 연결
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBCon db = new DBCon();
       
        try {   
        	// 모든 값을 인덱스 순서대로 불러온다.
            conn = db.connect();
            String sql = "select idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation from board order by idx desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){               
                // bean에 select된 값 넣기    
                BoardBean bean = new BoardBean();
                bean.setIdx(rs.getInt("idx"));
                bean.setTitle(rs.getString("title"));
                bean.setWriter(rs.getString("writer"));
                bean.setWriteDate(rs.getString("writeDate"));
                bean.setModifier(nvl(rs.getString("modifier")));
                bean.setModifyDate(nvl(rs.getString("modifyDate")));
                bean.setCategory(nvl(rs.getString("category")));
                bean.setImage(rs.getString("image"));
                bean.setExplanation(nvl(rs.getString("explanation")));
                list.add(bean);
            }   
        } catch(SQLException se) {
            se.printStackTrace();   
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            db.close(rs, pstmt, conn);
        }
        return list;
    }
   
	// 특정 인덱스의 게시글을 bean에 담아 반환한다.
    public BoardBean bSelect(int idx) {
       
    	// 데이터 베이스 연결
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBCon db = new DBCon();       
        BoardBean bean = new BoardBean();

        try {   
            conn = db.connect();
            String sql = "select idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation, valid, writerEmail";
            sql += " from board where idx = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,idx);
            rs = pstmt.executeQuery(); 
           
            // bean에 select된 값 넣기  
            rs.next();           
            bean.setIdx(rs.getInt("idx"));
            bean.setTitle(rs.getString("title"));
            bean.setWriter(rs.getString("writer"));
            bean.setWriteDate(rs.getString("writeDate"));
            bean.setModifier(nvl(rs.getString("modifier")));
            bean.setModifyDate(nvl(rs.getString("modifyDate")));
            bean.setCategory(nvl(rs.getString("category")));
            bean.setImage(rs.getString("image"));
            bean.setExplanation(nvl(rs.getString("explanation")));
            bean.setValid(rs.getString("valid"));
            bean.setWriterEmail(rs.getString("writerEmail"));
            
        } catch(SQLException se) {
            se.printStackTrace();       
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            db.close(rs, pstmt, conn);
        }
        return bean;
    } 
   
    // 특정 인덱스 글을 삭제한다.
    public int bDelete(int bseq) {

    	// 성공적으로 삭제 되었는지 확인하는 변수
        int res=0;
        
        // bean에 select된 값 넣기  
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
            conn = db.connect();
            String sql = "delete from board where idx=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,bseq);
            pstmt.executeUpdate();

        }catch(SQLException se){
            se.printStackTrace();   
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close(pstmt, conn);
        }
        return res;
    }
 
    // 게시글을 업데이트 해준다.
    public int bUpdate(BoardBean bean) {
    	// 성공적으로 삭제 되었는지 확인하는 변수
        int res=0;
        
        // 데이터 베이스 연결
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
        	// bean에 있는 값을 가져와 데이터베이스에 업데이트 시켜준다.
            conn = db.connect();
            String sql = "update board set title=?, writer=?, writeDate=?, modifier=?, modifyDate=?, category=?, image=?, explanation=?, valid=? where idx=?";
            pstmt = conn.prepareStatement(sql);     
            pstmt.setString(1, bean.getTitle());
            pstmt.setString(2, bean.getWriter());
            pstmt.setString(3, bean.getWriteDate());
            pstmt.setString(4, bean.getModifier());
            pstmt.setString(5, bean.getModifyDate());
            pstmt.setString(6, bean.getCategory());
            pstmt.setString(7, bean.getImage());
            pstmt.setString(8, bean.getExplanation());
            pstmt.setString(9, bean.getValid());
            pstmt.setInt(10, bean.getIdx());
            res=pstmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();   
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close(pstmt, conn);
        }
        return res;
    }
 
    // 게시글을 valid를 업데이트 해준다.
    public int bUpdateValid(String value, int idx) {
    	// 성공적으로 삭제 되었는지 확인하는 변수
        int res=0;
        
        // 데이터 베이스 연결
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
        	// bean에 있는 값을 가져와 데이터베이스에 업데이트 시켜준다.
            conn = db.connect();
            String sql = "update board set valid=? where idx=?";
            pstmt = conn.prepareStatement(sql);     
            pstmt.setString(1, value);
            pstmt.setInt(2, idx);
            res=pstmt.executeUpdate();
        }catch(SQLException se){
            se.printStackTrace();   
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close(pstmt, conn);
        }
        return res;
    }
    
    // 글 등록 (추가)
    public int bInsert(BoardBean bean) {

    	// 성공적으로 글이 등록 되었는지 확인하는 변수
        int res=0;
        // 데이터 베이스 연결
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
        	// 자바빈에 저장되어 있는 내용을 데이터베이스에 업데이트 해준다.
        	conn = db.connect();
            String sql = "insert into board (idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation, writerEmail)";
            sql += "values(seq_idx.nextval,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, bean.getTitle());
            pstmt.setString(2, bean.getWriter());
            pstmt.setString(3, bean.getWriteDate());
            pstmt.setString(4, bean.getModifier());
            pstmt.setString(5, bean.getModifyDate());
            pstmt.setString(6, bean.getCategory());
            pstmt.setString(7, bean.getImage());
            pstmt.setString(8, bean.getExplanation());
            pstmt.setString(9, bean.getWriterEmail());
            res = pstmt.executeUpdate();

        }catch(SQLException se){
            se.printStackTrace();   
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close(pstmt, conn);
        }
        return res;
    }
    
    // 게시글 검색
    public ArrayList<BoardBean> bSearch(String option, String keyword) {
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
        // 데이터 베이스 연결
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBCon db = new DBCon();
       
        try {   
        	// 검색방법(제목, 작성자, 카테고리)를 가지고 게시글을 검색한다.
            conn = db.connect();
            String sql = "select idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation ";
            sql += "from board where " + option + " like ? order by idx desc";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+keyword+"%");
            rs = pstmt.executeQuery();

            while(rs.next()){               
                // bean에 select된 값 넣기    
                BoardBean bean = new BoardBean();
                bean.setIdx(rs.getInt("idx"));
                bean.setTitle(rs.getString("title"));
                bean.setWriter(rs.getString("writer"));
                bean.setWriteDate(rs.getString("writeDate"));
                bean.setModifier(nvl(rs.getString("modifier")));
                bean.setModifyDate(nvl(rs.getString("modifyDate")));
                bean.setCategory(nvl(rs.getString("category")));
                bean.setImage(rs.getString("image"));
                bean.setExplanation(nvl(rs.getString("explanation")));
                list.add(bean);
            }   
        } catch(SQLException se) {
            se.printStackTrace();   
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            db.close(rs, pstmt, conn);
        }
        return list;
    }
    
    // 데이터 베이스에서 값을 가져왔을 떄 값이 없을 경우(null)null이 출력되지 않도록 null 값이면 빈 스트링을 반환한다.
    public String nvl(String str){
    	if(str == null)
    		return "";
    	else
    		return str;
    }
}