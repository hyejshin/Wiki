package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		MemberDAO mdao = MemberDAO.getInstance();
		
		MemberBean mb = mdao.getMember(user_id);
		request.setAttribute("mb", mb); //회원 정보 저장
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberUpdate.jsp"); //정보 수정하러 jsp로 갑니다...
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		
		String user_id   = request.getParameter("user_id"); 	
		String user_pw   = request.getParameter("user_pw");
		String user_email= request.getParameter("user_email");
		String admin     = request.getParameter("admin");
		
		MemberBean mb = new MemberBean();
		mb.setUser_id(user_id);
		mb.setUser_pw(user_pw);
		mb.setUser_email(user_email);
		mb.setAdmin(Integer.parseInt(admin));
		
		MemberDAO mdao = MemberDAO.getInstance();
		mdao.updateMember(mb);
		
		response.sendRedirect("login.do");
	}

}
