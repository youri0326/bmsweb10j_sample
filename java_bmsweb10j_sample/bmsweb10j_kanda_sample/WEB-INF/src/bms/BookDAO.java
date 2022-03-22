package bms;

import java.sql.*;
import java.util.*;

public class BookDAO{

	private static String RDB_DRIVE="com.mysql.jdbc.Driver";
	private static String URL="jdbc:mysql://localhost/mybookdb";
	private static String USER="root";
	private static String PASSWD="root123";

	public static Connection getConnection()
	{
		try{
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

	public ArrayList<Book> selectAll()	{

		ArrayList<Book> list = new ArrayList<Book>();
		String sql = "SELECT * FROM bookinfo ORDER BY ISBN";

		Connection con = null;
		Statement  smt = null;
		try{
			con = BookDAO.getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while(rs.next()){
				Book books = new Book();
				books.setIsbn(rs.getString("isbn"));
				books.setTitle(rs.getString("title"));
				books.setPrice(rs.getInt("price"));

				list.add(books);
			}

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;

	}

}
