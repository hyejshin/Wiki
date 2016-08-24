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

//업데이트뷰: 게시글 수정 화면
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
    	
    	//파일 크기 15MB로 제한
    	int sizeLimit = 1024*1024*15;
    	
    	MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "EUC-KR", new DefaultFileRenamePolicy());

    	//작성한 내용을 bean객체에 넣어 준다.
    	
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
       
        //수정이 완료 되었음으로 다시 수정이 가능하도록 valid 값을 'yes'로 변경해준다.
        bean.setValid("yes");
        
        BoardImpl bi = new BoardImpl();
        bi.bUpdate(bean);

        
        
        // 수정이 완료되고 수정한 사람이 등록한 글이 아닐 경우 게시글을 등록한 회원에게 메일로 알리기
        HttpSession session = request.getSession();
    	MemberBean mb = (MemberBean)session.getAttribute("loginUser");
    	String writerEmail = bean.getWriterEmail();
    	
    	if(mb != null && !writerEmail.equals(mb.getUser_email())){
	        String m_name = "Wiki";
	        String m_email = "shinsy11@naver.com";
	        String m_title = "[send from wiki] 작성하신 게시글이 수정되었습니다";
	        String m_text = "안녕하세요! IBM 위키입니다. <br> 등록하신 게시글이 수정되었습니다.<br><br>";
	        m_text += "변경된 게시글: "+ bean.getTitle() +"<br>";
	        m_text += "<a href='http://localhost:8090/Wiki/home.jsp'>확인하기<a>";
	
	        try {
	            String mail_from = m_name + "<" + m_email + ">";
	            String mail_to = "customer<"+ bean.getWriterEmail() +">";
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
	            
	            //response.sendRedirect("request_complete.jsp");
	            System.out.println("메일 발송 성공");
	         
	        } catch (Exception e) {
	            //response.sendRedirect("request_failed.jsp");
	        	System.out.println("메일 발송 실패");
	        } finally {
	        	
	        }
    	}
        
        
        // 수정 완료 후 수정이 되었다는 알림창을 띄어 주는 스크립트
        response.setCharacterEncoding("EUC-KR");
        PrintWriter writer = response.getWriter();
        writer.println("<script type='text/javascript'>");
        writer.println("alert('수정 되었습니다.');");
        String url = "BoardView.do?idx=" + bean.getIdx();
        writer.println("window.location='"+ url +"';");
        writer.println("</script>");
        writer.flush();
        
        // 게시글 목록을 보여주는 서블릿으로 이동
        RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
        rd.forward(request, response);
    }

}
