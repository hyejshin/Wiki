package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

@WebServlet("/withdrawMember.do")
public class WithdrawMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WithdrawMember() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/withdrawForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		int res;
		
		HttpSession session = request.getSession();
		MemberBean mb = (MemberBean)session.getAttribute("loginUser");
		
		String user_id = mb.getUser_id();
		//String user_id = (String)session.getAttribute("user_id");
		String msg ="";
		
		/*if(mb==null){
			msg = "alert('로그인이 필요합니다.');";
		}
		*/
		
		if(msg.equals("")){
			MemberDAO dao = MemberDAO.getInstance();
			res=dao.deleteMember(user_id);
			
			if(res==1){	
				msg ="alert('탈퇴 성공! 안녕히 가세요');";
				session.invalidate();
			}else if(res==-1){
				msg="alert('탈퇴 실패! 어서오세요');";
			}
		}

		response.setCharacterEncoding("EUC-KR");
        PrintWriter writer = response.getWriter();
        writer.println("<script type='text/javascript'>");
        writer.println(msg);
        writer.println("window.location='home.jsp';");
        writer.println("</script>");
        writer.flush();		

		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

}
