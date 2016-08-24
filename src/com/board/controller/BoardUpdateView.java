package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardImpl;
import DTO.BoardBean;

/**
 * Servlet implementation class UpdateView
 * 업데이트: 게시글 수정
 */

@WebServlet("/BoardUpdateView.do")
public class BoardUpdateView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateView() {
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
		
		BoardImpl bi = new BoardImpl();
        int idx = Integer.parseInt(request.getParameter("idx"));
       
        BoardBean bean = bi.bSelect(idx);
    
        
        // 수정중이기에 수정이 불가능하다는 메시지를 띄우고 이전페이지로 이동
        if(bean.getValid().equals("no")){
            response.setCharacterEncoding("EUC-KR");
            PrintWriter writer = response.getWriter();
            writer.println("<script type='text/javascript'>");
            writer.println("alert('수정중인 게시글 입니다. 나중에 다시 시도하세요');");
            String url = "BoardView.do?idx=" + idx;
            writer.println("window.location='" + url + "';");
            writer.println("</script>");
            writer.flush();
        }
        
        // 수정중임으로 접근이 불가능 하도록 valid를 'no'로 변경후 업데이트 시킨다.
        bi.bUpdateValid("no", idx);  
        
        request.setAttribute("Bean", bean);
       
        // 수정 화면 페이지에(update.jsp) 이전에 쓰여진 내용을 보여준다.
        RequestDispatcher rd = request.getRequestDispatcher("/Board/update.jsp");
        rd.forward(request, response);
    }
    
}