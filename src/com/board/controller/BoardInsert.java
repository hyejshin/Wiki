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

//�߰�: �Խñ� ���
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
    	
    	//���� ũ�� 15MB�� ����
    	int sizeLimit = 1024*1024*15;
    	
    	MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "EUC-KR", new DefaultFileRenamePolicy());
   	 	
    	//�ۼ��� ������ bean��ü�� �־� �ش�.
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
        bi.bInsert(bean); // �� ���� ȭ��(write.jsp)���� ���� ������ ������ ���̽��� �߰���Ų��. 
       
        // �߰��� �Ǿ����� �� ����� �����ִ� ȭ������ �̵�
        RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
        rd.forward(request, response);
    }
}
