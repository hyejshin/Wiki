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
		
		if(session.getAttribute("loginUser") != null){ //�̹� �α��� �� �����
			url="main.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/loginForm.jsp"; //ȸ�� ���� ������ ��� �Ѿ ������
		
		//loginForm.jsp���� �Է��� ���̵�� ��й�ȣ�� ������ �ͼ� ������ ����
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//MemberDAO�� �ִ� userCheck()���� ���̵�� ��й�ȣ�� ������ ȸ�������� ��
		MemberDAO mdao = MemberDAO.getInstance();
		int result = mdao.userCheck(user_id, user_pw);
		
		if(result == 1){
			MemberBean mb = mdao.getMember(user_id);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mb);
			request.setAttribute("message", "ȸ������ ����!");
			url="home.jsp";
		}else if(result == 0){
			request.setAttribute("message", "��й�ȣ�� ��ġ���� �ʽ��ϴ�!");
		}else if(result == -1){
			request.setAttribute("message", "�������� �ʴ� ȸ���Դϴ�!");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
