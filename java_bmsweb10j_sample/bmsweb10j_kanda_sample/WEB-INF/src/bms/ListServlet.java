package bms;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

public class ListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException{

		BookDAO objDao = new BookDAO();

		ArrayList<Book> list = objDao.selectAll();

		request.setAttribute("book_list", list);
		request.getRequestDispatcher("/view/list.jsp").forward(request, response);

	}
}
