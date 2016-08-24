package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardImpl;


/**
 * Servlet implementation class BoardUpdateCancel
 */
@WebServlet("/BoardUpdateCancel.do")
public class BoardUpdateCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateCancel() {
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
		
        //������ �Ϸ� �Ǿ������� �ٽ� ������ �����ϵ��� valid ���� 'yes'�� �������ش�.
        BoardImpl bi = new BoardImpl();
        bi.bUpdateValid("yes", idx);        

        request.setAttribute("idx", idx);
        
        // �Խñ� ����� �����ִ� �������� �̵�
        RequestDispatcher rd = request.getRequestDispatcher("BoardView.do");
        rd.forward(request, response);
    }

}
