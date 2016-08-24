package com.wiki.mail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wiki.mail.SMTPAuthenticator;



/**
 * Servlet implementation class SendMail
 */
@WebServlet("/SendMail.do")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 request.setCharacterEncoding("UTF-8");
	     response.setContentType("text/html;charset=UTF-8");
	     response.setCharacterEncoding("UTF-8");

		//String m_name = request.getParameter("name");
		String m_name = "Wiki";
        String m_email = "shinsy11@naver.com";
        String m_title = "[send from wiki] 작성하신 게시글이 수정되었습니다";
        String m_text = "안녕하세요! IBM 위키입니다. <br> 등록하신 게시글이 수정되었습니다.<br><br>";
        m_text += "변경된 게시글: <br>";
        m_text += "<a href='http://localhost:8090/Wiki/home.jsp'>확인하기<a>";

        try {
            String mail_from = m_name + "<" + m_email + ">";
            String mail_to = "customer<hyejungshin7@gmail.com>";
            String title = m_title;
            String contents = "보낸 사람 : " + m_name + "&lt;" + m_email + "&gt;<br><br>" + m_text;
        
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
            
            response.sendRedirect("request_complete.jsp");

        } catch (Exception e) {
            response.sendRedirect("request_failed.jsp");
        } finally {
        	
        }
	}


}
