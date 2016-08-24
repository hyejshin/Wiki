package com.board.controller;

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

/**
 * Servlet implementation class WriteAuthorityCheck
 * 글 쓰기 업데이트 권한 체크
 * 로그인한 상태에서만 글 등록 및 업데이트 가능
 */

@WebServlet("/AuthorityCheck.do")
public class AuthorityCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorityCheck() {
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
		
		String successUrl = "";
		String failUrl = "";
		String option = request.getParameter("option");
		
		if(option.equals("write")){
			successUrl = "/Board/write.jsp";
			failUrl = "BoardList.do";
		}else if(option.equals("update")){
			int idx = Integer.parseInt(request.getParameter("idx"));
			successUrl = "BoardUpdateView.do?idx="+idx;
			failUrl = "BoardView.do?idx="+idx;
		}
		
		
		HttpSession session = request.getSession();
    	MemberBean mb = (MemberBean)session.getAttribute("loginUser");
        
    	//로그인이 되어 있지 않으면 로그인 후 사용하라는 알림창을 띄우고 이전 화면으로 이동
        if(mb == null){
            response.setCharacterEncoding("EUC-KR");
            PrintWriter writer = response.getWriter();
            writer.println("<script type='text/javascript'>");
            writer.println("alert('로그인 후 시도해주세요.')");
            writer.println("window.location='"+ failUrl +"';");
            writer.println("</script>");
            writer.flush();
        }
        

    	RequestDispatcher rd = request.getRequestDispatcher(successUrl);
    	rd.forward(request, response);

    }

}
