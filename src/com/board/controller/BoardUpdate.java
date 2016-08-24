package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wiki.mail.SMTPAuthenticator;

import DAO.BoardImpl;
import DTO.BoardBean;

import com.member.db.MemberBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class Update
 */

//������Ʈ��: �Խñ� ���� ȭ��
@WebServlet("/BoardUpdate.do")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		
		String savePath = request.getServletContext().getRealPath("file_save");
    	
    	//���� ũ�� 15MB�� ����
    	int sizeLimit = 1024*1024*15;
    	
    	MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "EUC-KR", new DefaultFileRenamePolicy());

    	//�ۼ��� ������ bean��ü�� �־� �ش�.
    	
    	BoardBean bean = new BoardBean();
    	bean.setIdx(Integer.parseInt(multi.getParameter("idx")));
        bean.setTitle(multi.getParameter("title"));
        bean.setWriter(multi.getParameter("writer"));
        bean.setWriteDate(multi.getParameter("writeDate"));
        bean.setModifier(multi.getParameter("modifier"));
        bean.setModifyDate(multi.getParameter("modifyDate"));
        bean.setCategory(multi.getParameter("category"));
        bean.setExplanation(multi.getParameter("explanation"));
        bean.setImage(multi.getFilesystemName("file"));
        bean.setWriterEmail(multi.getParameter("writerEmail"));
       
        //������ �Ϸ� �Ǿ������� �ٽ� ������ �����ϵ��� valid ���� 'yes'�� �������ش�.
        bean.setValid("yes");
        
        BoardImpl bi = new BoardImpl();
        bi.bUpdate(bean);

        
        
        // ������ �Ϸ�ǰ� ������ ����� ����� ���� �ƴ� ��� �Խñ��� ����� ȸ������ ���Ϸ� �˸���
        HttpSession session = request.getSession();
    	MemberBean mb = (MemberBean)session.getAttribute("loginUser");
    	String writerEmail = bean.getWriterEmail();
    	
    	if(mb != null && !writerEmail.equals(mb.getUser_email())){
	        String m_name = "Wiki";
	        String m_email = "shinsy11@naver.com";
	        String m_title = "[send from wiki] �ۼ��Ͻ� �Խñ��� �����Ǿ����ϴ�";
	        String m_text = "�ȳ��ϼ���! IBM ��Ű�Դϴ�. <br> ����Ͻ� �Խñ��� �����Ǿ����ϴ�.<br><br>";
	        m_text += "����� �Խñ�: "+ bean.getTitle() +"<br>";
	        m_text += "<a href='http://localhost:8090/Wiki/home.jsp'>Ȯ���ϱ�<a>";
	
	        try {
	            String mail_from = m_name + "<" + m_email + ">";
	            String mail_to = "customer<"+ bean.getWriterEmail() +">";
	            String title = m_title;
	            String contents = "���� ��� : " + m_name + "&lt;" + m_email + "&gt;<br><br>" + m_text;
	        
	            mail_from = new String(mail_from.getBytes("UTF-8"), "UTF-8");
	            mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");
	
	            Properties props = new Properties();
	            props.put("mail.transport.protocol", "smtp");
	            props.put("mail.smtp.host", "smtp.naver.com");
	            props.put("mail.smtp.port", "465");
	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.socketFactory.port", "465");
	            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	            props.put("mail.smtp.socketFactory.fallback", "false");
	            props.put("mail.smtp.auth", "true");
	
	            Authenticator auth = new SMTPAuthenticator();
	            Session sess = Session.getDefaultInstance(props, auth);
	            MimeMessage msg = new MimeMessage(sess);
	
	            msg.setFrom(new InternetAddress(mail_from));
	            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
	            msg.setSubject(title, "UTF-8");
	            msg.setContent(contents, "text/html; charset=UTF-8");
	            msg.setHeader("Content-type", "text/html; charset=UTF-8");
	            Transport.send(msg);
	            
	            //response.sendRedirect("request_complete.jsp");
	            System.out.println("���� �߼� ����");
	         
	        } catch (Exception e) {
	            //response.sendRedirect("request_failed.jsp");
	        	System.out.println("���� �߼� ����");
	        } finally {
	        	
	        }
    	}
        
        
        // ���� �Ϸ� �� ������ �Ǿ��ٴ� �˸�â�� ��� �ִ� ��ũ��Ʈ
        response.setCharacterEncoding("EUC-KR");
        PrintWriter writer = response.getWriter();
        writer.println("<script type='text/javascript'>");
        writer.println("alert('���� �Ǿ����ϴ�.');");
        String url = "BoardView.do?idx=" + bean.getIdx();
        writer.println("window.location='"+ url +"';");
        writer.println("</script>");
        writer.flush();
        
        // �Խñ� ����� �����ִ� �������� �̵�
        RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
        rd.forward(request, response);
    }

}
