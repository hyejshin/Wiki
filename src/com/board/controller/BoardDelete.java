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

import DAO.BoardImpl;

import com.member.db.MemberBean;

/**
 * Servlet implementation class BoardDelete
 * 삭제: 게시글을 삭제한다
 */

@WebServlet("/BoardDelete.do")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDelete() {
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
		
		int idx = Integer.parseInt(request.getParameter("idx"));           
        
    	// 게시글 삭제 권한이 있는지 확인
        String url = "";
    	String message = "";
    	HttpSession session = request.getSession();
    	MemberBean mb = (MemberBean)session.getAttribute("loginUser");

    	if(mb == null){
    		message = "alert('권한이 없습니다. 로그인 후 시도하세요.');";
    	}else if(mb.getAdmin() == 0){
    		message = "alert('권한이 없습니다. 관리자에게 문의하세요.');";
    	}

    	// 관리자일 경우에만 게시글을 삭제한다.
    	if(message.equals("")){        	
            BoardImpl bi = new BoardImpl();
            bi.bDelete(idx);
            message = "alert('글이 삭제 되었습니다.');";
            url = "BoardList.do";
    	}else{
           	url = "BoardView.do?idx="+idx;
    	}
    	
        // 글이 삭제 되었다는 또는 권한이 없다는 알림 메시지를 띄어 준다.
        response.setCharacterEncoding("EUC-KR");
        PrintWriter writer = response.getWriter();
        writer.println("<script type='text/javascript'>");
        writer.println(message);
        writer.println("window.location='" + url + "';");
        writer.println("</script>");
        writer.flush();
        
        // 게시글 목록을 보여주는 서블릿으로 이동
        RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
        rd.forward(request, response);
    }

}
