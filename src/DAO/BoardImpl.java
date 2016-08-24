package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCon;
import DTO.BoardBean;

public class BoardImpl implements Board{
	// �Խñ� bean ����Ʈ�� ��ȯ �Ѵ�. 
	public ArrayList<BoardBean> bList() {
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
       
        // ������ ���̽� ����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBCon db = new DBCon();
       
        try {   
        	// ��� ���� �ε��� ������� �ҷ��´�.
            conn = db.connect();
            String sql = "select idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation from board order by idx desc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()){               
                // bean�� select�� �� �ֱ�    
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
   
	// Ư�� �ε����� �Խñ��� bean�� ��� ��ȯ�Ѵ�.
    public BoardBean bSelect(int idx) {
       
    	// ������ ���̽� ����
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
           
            // bean�� select�� �� �ֱ�  
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
   
    // Ư�� �ε��� ���� �����Ѵ�.
    public int bDelete(int bseq) {

    	// ���������� ���� �Ǿ����� Ȯ���ϴ� ����
        int res=0;
        
        // bean�� select�� �� �ֱ�  
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
 
    // �Խñ��� ������Ʈ ���ش�.
    public int bUpdate(BoardBean bean) {
    	// ���������� ���� �Ǿ����� Ȯ���ϴ� ����
        int res=0;
        
        // ������ ���̽� ����
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
        	// bean�� �ִ� ���� ������ �����ͺ��̽��� ������Ʈ �����ش�.
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
 
    // �Խñ��� valid�� ������Ʈ ���ش�.
    public int bUpdateValid(String value, int idx) {
    	// ���������� ���� �Ǿ����� Ȯ���ϴ� ����
        int res=0;
        
        // ������ ���̽� ����
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
        	// bean�� �ִ� ���� ������ �����ͺ��̽��� ������Ʈ �����ش�.
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
    
    // �� ��� (�߰�)
    public int bInsert(BoardBean bean) {

    	// ���������� ���� ��� �Ǿ����� Ȯ���ϴ� ����
        int res=0;
        // ������ ���̽� ����
        Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
        	// �ڹٺ� ����Ǿ� �ִ� ������ �����ͺ��̽��� ������Ʈ ���ش�.
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
    
    // �Խñ� �˻�
    public ArrayList<BoardBean> bSearch(String option, String keyword) {
        ArrayList<BoardBean> list = new ArrayList<BoardBean>();
        // ������ ���̽� ����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DBCon db = new DBCon();
       
        try {   
        	// �˻����(����, �ۼ���, ī�װ�)�� ������ �Խñ��� �˻��Ѵ�.
            conn = db.connect();
            String sql = "select idx, title, writer, writeDate, modifier, modifyDate, category, image, explanation ";
            sql += "from board where " + option + " like ? order by idx desc";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+keyword+"%");
            rs = pstmt.executeQuery();

            while(rs.next()){               
                // bean�� select�� �� �ֱ�    
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
    
    // ������ ���̽����� ���� �������� �� ���� ���� ���(null)null�� ��µ��� �ʵ��� null ���̸� �� ��Ʈ���� ��ȯ�Ѵ�.
    public String nvl(String str){
    	if(str == null)
    		return "";
    	else
    		return str;
    }
}