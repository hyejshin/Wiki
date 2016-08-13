package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCon;
import DTO.BoardBean;

public class BoardImpl implements Board{
	public ArrayList<BoardBean> bList() {
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBCon db = new DBCon();
       
        try {   
            conn = db.connect();
            String sql = "select idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation from board order by idx";
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
   
    public BoardBean bSelect(int idx) {
       
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBCon db = new DBCon();       
        BoardBean bean = new BoardBean();

        try {   
            conn = db.connect();
            String sql = "select idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation";
            sql += " from board where idx = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,idx);
            rs = pstmt.executeQuery(); 
           
            rs.next();           
            bean.setIdx(rs.getInt("idx"));
            bean.setTitle(rs.getString("title"));
            bean.setWriter(rs.getString("writer"));
            bean.setWriteDate(rs.getString("writeDate"));
            bean.setModifier(nvl(rs.getString("modifier")));
            bean.setModifyDate(nvl(rs.getString("modifyDate")));
            bean.setImage(nvl(rs.getString("category")));
            bean.setImage(rs.getString("image"));
            bean.setExplanation(nvl(rs.getString("explanation")));
            
        } catch(SQLException se) {
            se.printStackTrace();       
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            db.close(rs, pstmt, conn);
        }
        return bean;
    } 
   
    public int bDelete(int bseq) {

        int res=0;
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
 
    public int bUpdate(BoardBean bean) {
        int res=0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
            conn = db.connect();
            String sql = "update board set title=?, writer=?, writeDate=?, modifier=?, modifyDate=?, category=?, image=?, explanation=? where idx=?";
            pstmt = conn.prepareStatement(sql);     
            pstmt.setString(1, bean.getTitle());
            pstmt.setString(2, bean.getWriter());
            pstmt.setString(3, bean.getModifier());
            pstmt.setString(4, bean.getModifyDate());
            pstmt.setString(5, bean.getCategory());
            pstmt.setString(6, bean.getImage());
            pstmt.setString(7, bean.getExplanation());         
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
 
    public int bInsert(BoardBean bean) {

        int res=0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
        	conn = db.connect();
            String sql = "insert into board (idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation)";
            sql += "values(seq_idx.nextval,?,?,sysdate,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, bean.getTitle());
            pstmt.setString(2, bean.getWriter());
            pstmt.setString(3, bean.getModifier());
            pstmt.setString(4, bean.getModifyDate());
            pstmt.setString(5, bean.getCategory());
            pstmt.setString(6, bean.getImage());
            pstmt.setString(7, bean.getExplanation());
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
    
    public String nvl(String str){
    	if(str == null)
    		return "";
    	else
    		return str;
    }
}