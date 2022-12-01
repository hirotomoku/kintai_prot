<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="basic.Calendar1"%>
<%
	Calendar1 c1 =(Calendar1)request.getAttribute("c1");	
%>
<!DOCTYPE html>
<html lang = "ja">
<head>
	<meta charset="UTF-8">
	<title><%=c1.getGengou() %>年<%=c1.getMonth() %>月カレンダー</title>
	<link rel= "stylesheet" href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
	<link href= "https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c" rel= "stylesheet">
	<link rel= "stylesheet" href= "css/main.css">
</head>
<body>
	<div id = "container">
		<h1><%= c1.getGengou() %>年<%=c1.getMonth() %>月カレンダー</h1>
		<p>
			<a href="?year=<%=c1.getYear()%>&month=<%=c1.getMonth()-1%>">前月</a>
			<a href="?year=<%=c1.getYear()%>&month=<%=c1.getMonth()+1%>">翌月</a>
		</p>
		<table>
			<tr>
				<th>日</th>
				<th>月</th>
				<th>火</th>
				<th>水</th>
				<th>木</th>
				<th>金</th>
				<th>土</th>
			</tr>
			<%for(String[] row:c1.getData()){ %>
			<tr>
				<%for(String col:row){ %>
					<%if (col.startsWith("*")){ %>
						<td class = "today"><%=col.substring(1) %></td>
					<%}else{ %>
						<td><%=col %></td>
					<%} %>
				<%} %>
			</tr>
			<%} %>
		</table>
	</div><!-- end container -->

</body>
</html>