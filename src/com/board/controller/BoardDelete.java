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
 * ����: �Խñ��� �����Ѵ�
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
        
    	// �Խñ� ���� ������ �ִ��� Ȯ��
        String url = "";
    	String message = "";
    	HttpSession session = request.getSession();
    	MemberBean mb = (MemberBean)session.getAttribute("loginUser");

    	if(mb == null){
    		message = "alert('������ �����ϴ�. �α��� �� �õ��ϼ���.');";
    	}else if(mb.getAdmin() == 0){
    		message = "alert('������ �����ϴ�. �����ڿ��� �����ϼ���.');";
    	}

    	// �������� ��쿡�� �Խñ��� �����Ѵ�.
    	if(message.equals("")){        	
            BoardImpl bi = new BoardImpl();
            bi.bDelete(idx);
            message = "alert('���� ���� �Ǿ����ϴ�.');";
            url = "BoardList.do";
    	}else{
           	url = "BoardView.do?idx="+idx;
    	}
    	
        // ���� ���� �Ǿ��ٴ� �Ǵ� ������ ���ٴ� �˸� �޽����� ��� �ش�.
        response.setCharacterEncoding("EUC-KR");
        PrintWriter writer = response.getWriter();
        writer.println("<script type='text/javascript'>");
        writer.println(message);
        writer.println("window.location='" + url + "';");
        writer.println("</script>");
        writer.flush();
        
        // �Խñ� ����� �����ִ� �������� �̵�
        RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
        rd.forward(request, response);
    }

}
