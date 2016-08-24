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
 * ������Ʈ: �Խñ� ����
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
    
        
        // �������̱⿡ ������ �Ұ����ϴٴ� �޽����� ���� ������������ �̵�
        if(bean.getValid().equals("no")){
            response.setCharacterEncoding("EUC-KR");
            PrintWriter writer = response.getWriter();
            writer.println("<script type='text/javascript'>");
            writer.println("alert('�������� �Խñ� �Դϴ�. ���߿� �ٽ� �õ��ϼ���');");
            String url = "BoardView.do?idx=" + idx;
            writer.println("window.location='" + url + "';");
            writer.println("</script>");
            writer.flush();
        }
        
        // ������������ ������ �Ұ��� �ϵ��� valid�� 'no'�� ������ ������Ʈ ��Ų��.
        bi.bUpdateValid("no", idx);  
        
        request.setAttribute("Bean", bean);
       
        // ���� ȭ�� ��������(update.jsp) ������ ������ ������ �����ش�.
        RequestDispatcher rd = request.getRequestDispatcher("/Board/update.jsp");
        rd.forward(request, response);
    }
    
}