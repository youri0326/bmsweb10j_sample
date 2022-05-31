package bms;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 書籍管理システムにおける書籍検索機能に関する処理をおこなうサーブレットクラス
 *
 * @author KandaITSchool
 *
 */
public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// 文字コードを設定する
			request.setCharacterEncoding("UTF-8");

			// ① isbn,title,price等の入力パラメータを取得する
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			// ②BookDAOをインスタンス化する
			BookDAO objDao = new BookDAO();

			// ③書籍を検索するメソッドを呼び出し、Bookオブジェクトのリストを取得する
			ArrayList<Book> bookList = objDao.search(isbn, title, price);

			// ③④で取得したリストをリクエストスコープに"book_list"という名前で格納する
			request.setAttribute("book_list", bookList);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "menu";

		} finally {
			// ⑤ エラーの有無でフォワード先を呼び分ける
			if (error.equals("")) {
				// エラーが無い場合はlist.jspにフォワード
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			} else {
				// エラーが有る場合はerror.jspにフォワードする
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
