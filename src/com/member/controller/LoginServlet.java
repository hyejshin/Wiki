package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;
import com.member.db.MemberBean;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/loginForm.jsp";
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("loginUser") != null){ //이미 로그인 된 사용자
			url="main.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/loginForm.jsp"; //회원 인증 실패할 경우 넘어갈 페이지
		
		//loginForm.jsp에서 입력한 아이디와 비밀번호를 가지고 와서 변수에 저장
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//MemberDAO에 있는 userCheck()에서 아이디와 비밀번호를 가지고 회원인증을 함
		MemberDAO mdao = MemberDAO.getInstance();
		int result = mdao.userCheck(user_id, user_pw);
		
		if(result == 1){
			MemberBean mb = mdao.getMember(user_id);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mb);
			request.setAttribute("message", "회원가입 성공!");
			url="home.jsp";
		}else if(result == 0){
			request.setAttribute("message", "비밀번호가 일치하지 않습니다!");
		}else if(result == -1){
			request.setAttribute("message", "존재하지 않는 회원입니다!");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
