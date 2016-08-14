package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class BoardServlet
 */
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
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
		String actionMode = request.getParameter("actionMode");
		
		// 서블릿 합치기 작업
        if (actionMode.equals("INSERT")){
            BoardBean bean = new BoardBean();
            
            bean.setTitle(request.getParameter("title"));
            bean.setWriter(request.getParameter("writer"));
            bean.setWriteDate(request.getParameter("writeDate"));
            bean.setModifier(request.getParameter("modifier"));
            bean.setModifyDate(request.getParameter("modifyDate"));
            bean.setCategory(request.getParameter("category"));
            bean.setImage(request.getParameter("image"));
            bean.setExplanation(request.getParameter("explanation"));
           
            BoardImpl bi = new BoardImpl();
            bi.bInsert(bean);
           
            RequestDispatcher rd = request.getRequestDispatcher("/BoardServlet?actionMode=LIST");
            rd.forward(request, response);
 
        } else if (actionMode.equals("UPDATEVIEW")){
        	
        	BoardImpl bi = new BoardImpl();
            int idx = Integer.parseInt(request.getParameter("idx"));
           
            BoardBean bean = bi.bSelect(idx);
            request.setAttribute("Bean", bean);
           
            RequestDispatcher rd = request.getRequestDispatcher("/Board/update.jsp");
            rd.forward(request, response);
            
        } else if (actionMode.equals("UPDATE")){
            BoardBean bean = new BoardBean();
            
            bean.setIdx(Integer.parseInt(request.getParameter("idx")));
            bean.setTitle(request.getParameter("title"));
            bean.setWriter(request.getParameter("writer"));
            bean.setWriteDate(request.getParameter("writeDate"));
            bean.setModifier(request.getParameter("modifier"));
            bean.setModifyDate(request.getParameter("modifyDate"));
            bean.setCategory(request.getParameter("category"));
            bean.setImage(request.getParameter("image"));
            bean.setExplanation(request.getParameter("explanation"));
           
            System.out.println("수정직전");
            BoardImpl bi = new BoardImpl();
            bi.bUpdate(bean);
            System.out.println("수정햇음");
 
            System.out.println("수정완료");
            response.setCharacterEncoding("EUC-KR");
            PrintWriter writer = response.getWriter();
            writer.println("<script type='text/javascript'>");
            writer.println("alert('수정 되었습니다.');");
            String url = "/Wiki/BoardServlet?actionMode=VIEW&idx=" + bean.getIdx();
            writer.println("window.location='" + url + "';");
            writer.println("</script>");
            writer.flush();
            
            RequestDispatcher rd = request.getRequestDispatcher("/BoardServlet?actionMode=LIST");
            rd.forward(request, response);
           
        } else if (actionMode.equals("DELETE")){
            int idx = Integer.parseInt(request.getParameter("idx"));
            BoardImpl bi = new BoardImpl();
            bi.bDelete(idx);
       
            response.setCharacterEncoding("EUC-KR");
            PrintWriter writer = response.getWriter();
            writer.println("<script type='text/javascript'>");
            writer.println("alert('글이 삭제 되었습니다.');");
            writer.println("window.location='/Wiki/BoardServlet?actionMode=LIST';");
            writer.println("</script>");
            writer.flush();
            
            RequestDispatcher rd = request.getRequestDispatcher("/BoardServlet?actionMode=LIST");
            rd.forward(request, response);
       
        } else if (actionMode.equals("VIEW")){
           
            BoardImpl bi = new BoardImpl();
            int idx = Integer.parseInt(request.getParameter("idx"));
           
            BoardBean bean = bi.bSelect(idx);
            request.setAttribute("Bean", bean);
           
            RequestDispatcher rd = request.getRequestDispatcher("/Board/view.jsp");
            rd.forward(request, response);
           
        } else if (actionMode.equals("LIST")){
           
            BoardImpl bi = new BoardImpl();
 
            ArrayList<BoardBean> list = bi.bList();
            request.setAttribute("List", list);
            
            RequestDispatcher rd = request.getRequestDispatcher("/Board/viewList.jsp");
            rd.forward(request, response); 
            
        }  else if (actionMode.equals("SEARCH")){
        	String option = request.getParameter("option");
        	String keyword = request.getParameter("keyword");
        	
            BoardImpl bi = new BoardImpl();
 
            if(option.equals("all")){
	            ArrayList<BoardBean> list = bi.bList();
	            request.setAttribute("List", list);
            }else{
            	ArrayList<BoardBean> list = bi.bSearch(option, keyword);
	            request.setAttribute("List", list);
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("/Board/viewList.jsp");
            rd.forward(request, response);          
        }
	}
}
