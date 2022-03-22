<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bms.Book"%>
<html>
	<head>
		<title>list</title>
	</head>
	<body>
		<h1 align="center">書籍管理システムweb版ver.1.0</h1>
		<hr align="center" size="5" color="blue" width="950"></hr>

		<table align="center" width="850">
			<tr>
				<td width="80">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td width="80">[<a href="<%=request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
				<td width="508" align="center" ><font size="5">書籍一覧</font></td>
				<td width="80">&nbsp;</td>
				<td width="80">&nbsp;</td>
			</tr>
		</table>

		<hr align="center" size="2" color="black" width="950"></hr>

		<table align="center">
			<tr>
				<td>
					<form action="<%=request.getContextPath()%>/search">
						isbn：<input type=text size="30" name="isbn"></input>
						title：<input type=text size="30" name="title"></input>
						価格：<input type=text size="30" name="price"></input>
						<input type="submit" name="search" value="検索"></input>
					</form>
				</td>
				<td>
					<form action="<%=request.getContextPath()%>/list">
						<input type="submit" name="searchall" value="全件表示"></input>
					</form>
				</td>
			</tr>
		</table>


		<table align="center">
			<tr>
				<th bgcolor="#6666ff" width="200">isbn</th>
				<th bgcolor="#6666ff" width="200">title</th>
				<th bgcolor="#6666ff" width="200">価格</th>
				<th bgcolor="#6666ff" width="250" colspan="2">変更/削除 変更点</th>
			</tr>
			<%
			ArrayList<Book> list =(ArrayList<Book>)request.getAttribute("book_list");
			if(list != null){
				for(int i=0;i<list.size();i++){
					Book books = (Book)list.get(i);
			%>
			<tr>
				<td align="center" width="200"><a href="<%=request.getContextPath() %>/detail?isbn=<%=books.getIsbn()%>&cmd=detail"><%=books.getIsbn()%></a></td>
				<td align="center" width="200"><%=books.getTitle()%></td>
				<td align="center" width="200"><%=books.getPrice()%></td>
				<td align="center" width="122">
					<a href="<%=request.getContextPath() %>/detail?isbn=<%=books.getIsbn()%>&cmd=update">変更</a>
				</td>
				<td align="left" width="122">
					<a href="<%=request.getContextPath()%>/delete?isbn=<%=books.getIsbn()%>">削除</a>
				</td>
			</tr>
			<%
				}
			}else{
			%>
			<tr>
				<td align="center" width="200">&nbsp;</td>
				<td align="center" width="200">&nbsp;</td>
				<td align="center" width="200">&nbsp;</td>
				<td align="center" width="250" colspan="2">&nbsp;</td>
			</tr>
			<%
			}
			%>
		</table>
		<br><br><br><br><br><br><br><br><br><br>

		<hr align="center" size="5" color="blue" width="950"></hr>

		<table border=0 align="center" width="950" summary="フッター表示">
			<tr><td>copyright (c) 2010 all rights reserved.</td></tr>
		</table>
	</body>
</html>