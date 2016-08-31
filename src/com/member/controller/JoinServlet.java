package com.member.controller;

import javax.servlet.RequestDispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;


@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/joinForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		
		String user_name = request.getParameter("user_name");
		String user_id   = request.getParameter("user_id");
		String user_pw   = request.getParameter("user_pw");
		String user_email= request.getParameter("user_email");
		String admin     = request.getParameter("admin");
		
		//MemberBean으로부터 회원정보 가져옴
		MemberBean mb = new MemberBean();
		mb.setUser_name(user_name);
		mb.setUser_id(user_id);
		mb.setUser_pw(user_pw);
		mb.setUser_email(user_email);
		mb.setAdmin(Integer.parseInt(admin));
		
		MemberDAO mdao = MemberDAO.getInstance();
		int result = mdao.insertMember(mb);
		
		HttpSession session = request.getSession();
		
		if(result == 1){
			session.setAttribute("user_id", mb.getUser_id());
			request.setAttribute("message", "회원가입 성공!!!");
		}else{
			request.setAttribute("message", "회원가입 실패..8ㅅ8");
		}
		
		//로그인페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/loginForm.jsp");
		dispatcher.forward(request,response);
	}

}
