package com.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardImpl;
import DTO.BoardBean;

/**
 * Servlet implementation class BoardSearch
 * �˻�: �Խñ��� �˻��Ѵ�
 */

@WebServlet("/BoardSearch.do")
public class BoardSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearch() {
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
		
		String option = request.getParameter("option");
    	String keyword = request.getParameter("keyword");
    	
        BoardImpl bi = new BoardImpl();
        ArrayList<BoardBean> list;
        
        // �˻� �ɼǿ� ��ü(all)�� ��쿡�� ��ü ����� �����ش�.
        if(option.equals("all")){
            list = bi.bList();
            request.setAttribute("List", list);
        } else{
        	list = bi.bSearch(option, keyword);
        }
        
        request.setAttribute("List", list);
        
        // �Խñ��� �����ִ� ȭ�� (viewList.jsp)�� �Ѿ��.
        RequestDispatcher rd = request.getRequestDispatcher("/Board/viewList.jsp");
        rd.forward(request, response);
    }

}
