package com.member.controller;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.db.MemberDAO;

@WebServlet("/idCheck.do")
public class idCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public idCheckServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id=request.getParameter("user_id"); //회원가입시 입력한 아이디 불러옴
		MemberDAO mdao=MemberDAO.getInstance();
		int result = mdao.confirmID(user_id); //DAO 불러옴
		
		request.setAttribute("user_id", user_id);
		request.setAttribute("result", result);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("member/idcheck.jsp"); 
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
