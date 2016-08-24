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
 * 검색: 게시글을 검색한다
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
        
        // 검색 옵션에 전체(all)일 경우에는 전체 목록을 보여준다.
        if(option.equals("all")){
            list = bi.bList();
            request.setAttribute("List", list);
        } else{
        	list = bi.bSearch(option, keyword);
        }
        
        request.setAttribute("List", list);
        
        // 게시글을 보여주는 화면 (viewList.jsp)로 넘어간다.
        RequestDispatcher rd = request.getRequestDispatcher("/Board/viewList.jsp");
        rd.forward(request, response);
    }

}
