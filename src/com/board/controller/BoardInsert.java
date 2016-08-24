package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardImpl;
import DTO.BoardBean;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardInsert
 */

//추가: 게시글 등록
@WebServlet("/BoardInsert.do")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsert() {
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
		
		String savePath = request.getServletContext().getRealPath("file_save");
    	
    	//파일 크기 15MB로 제한
    	int sizeLimit = 1024*1024*15;
    	
    	MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "EUC-KR", new DefaultFileRenamePolicy());
   	 	
    	//작성한 내용을 bean객체에 넣어 준다.
    	BoardBean bean = new BoardBean();
        bean.setTitle(multi.getParameter("title"));
        bean.setWriter(multi.getParameter("writer"));
        bean.setWriteDate(multi.getParameter("writeDate"));
        bean.setModifier(multi.getParameter("modifier"));
        bean.setModifyDate(multi.getParameter("modifyDate"));
        bean.setCategory(multi.getParameter("category"));
        bean.setExplanation(multi.getParameter("explanation"));    
        bean.setImage(multi.getFilesystemName("file"));
        bean.setWriterEmail(multi.getParameter("writerEmail"));
        
        BoardImpl bi = new BoardImpl();
        bi.bInsert(bean); // 글 쓰기 화면(write.jsp)에서 받은 내용을 데이터 베이스에 추가시킨다. 
       
        // 추가가 되었으면 글 목록을 보여주는 화면으로 이동
        RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
        rd.forward(request, response);
    }
}
