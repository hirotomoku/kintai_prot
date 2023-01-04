<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.DateFormat"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<%@ page import="beans.RecordBeans"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.lang.Object"%>

<%
int c1 = (Integer) session.getAttribute("c1");
@SuppressWarnings("unchecked")
Map<Integer,String> map = (HashMap<Integer,String>) session.getAttribute("callenderMap");
int aMonth = (Integer)session.getAttribute("aMonth");

int sumover = 0;
int sumnight = 0;
int sumholiday = 0;
int sumdeficiency = 0;


@SuppressWarnings("unchecked")
ArrayList<RecordBeans> list = (ArrayList<RecordBeans>) session.getAttribute("list");
for(RecordBeans all : list){
	sumover += all.getZangyou_zikan();
	sumnight += all.getShinya_zangyou();
	sumholiday += all.getKyuzitu_syukin();
	sumdeficiency += all.getHusoku_zikan();
}
%>
<%
Date date = new Date();
DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
String realtime = dateFormat.format(date);
%>
<%

String intime = " ";
intime = (String) session.getAttribute("zihun_s");
String outtime = "";
outtime = (String) session.getAttribute("zihun_t");
String d1 = (String) session.getAttribute("state1");
String d2 = (String) session.getAttribute("state2");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" href="top.css">
<title>Top</title>
</head>
<body class="bg-light-sbtle">
	<nav class="sticky-top navbar navbar-expand-lg navbar-dark bg-dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">GroupWare</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarNa">
	      <ul class="navbar-nav">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="#">Setting</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="#">Window</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="#" tabindex="-1" aria-disabled="true">Help</a>
	        </li>
	      </ul>
	    </div>
	   <div class="text-end align-bottom">
	    <a class="navbar-brand fs-5 align-bottom" href="#">
      	<img class = "rounded-circle" src="kulogo.png" alt="" width="30" height="30">
      	Hiroaki Takase
    	</a>
    	</div>
  	  </div>
	</nav>
	<form action="KintaiServlet" method="post">
	<div class="container mt-md-5">
	<div>
		<table class="table">
			<tr>
				<div class="timedisplay ">
					<p class = "<%=d1 != "disabled" ? "bg-white p-2 border border-secondary border-2 fs-4" : " p-2 border border-secondary border-2 fs-4 text-secondary bg-dark-subtle"%>">
					<%=intime%>
					</p>
					<p class = "<%=d2 != "disabled" ? "bg-white p-2 border border-secondary border-2 fs-4" : "bg-dark-subtle p-2 border border-secondary border-2 fs-4 text-secondary "%>">
					<%=outtime%>
					</p>
				</div>
			</tr>
			<tr>
				<div class="workbutton">
					<input type="submit" name ="button" class="btn btn-secondary btn-lg mt-1 mb-5 mr-5 ml-5<%=d1%>" value="出　勤" <%=d1%>>
					<input type="submit" name ="button" class="btn btn-secondary btn-lg mt-1 mb-5 mr-5 ml-5<%=d2%>" value="退　勤" <%=d2%>>
				</div>
			</tr>
		</table>
		<table class="monthtable">
			<tr>
				<div class="monthbutton">
					<input type="submit" value="前月" name="button" class="btn btn-secondary btn-sm">
					<input type="submit" value="次月" name="button" class="btn btn-secondary btn-sm">
				</div>
			</tr>
		</table>
		<table class="table table-striped mt-2 table-hover">
			<tr>
				<th>日付</th>
				<th>出勤時刻</th>
				<th>退勤時刻</th>
				<th>残業時間(分)</th>
				<th>深夜残業(分)</th>
				<th>休日出動(分)</th>
				<th>不足時間(分)</th>
			</tr>

			<%
			int z = 0;
			int flag = 0;
			String tempDay = null;

			for(Map.Entry<Integer,String> entry : map.entrySet()) {
				tempDay = entry.getValue().toString();
			%>
			<tr class="hyou">
				<td>
					<font <%=tempDay.equals("日") ? "color = \"#ff4040\"" : tempDay.equals("土") ? "color = \"#0083ff\"" : "" %>>
					<%=aMonth%>月<%=entry.getKey()%>日(<%=entry.getValue()%>)
					</font>
				</td>
				<%for(z = 0; z < list.size(); z++){ 
				  	if(entry.getKey() == list.get(z).getDay()){
				%>
					<td><%=list.get(z).getShukin_zikan() != null ? list.get(z).getShukin_zikan().substring(list.get(z).getShukin_zikan().length() - 5) : ""%></td>
					<td><%=list.get(z).getTaikin_zikan() != null ? list.get(z).getTaikin_zikan().substring(list.get(z).getTaikin_zikan().length() - 5) : ""%></td>
					<td><%=list.get(z).getZangyou_zikan()%></td>
					<td><%=list.get(z).getShinya_zangyou()%></td>
					<td><%=list.get(z).getKyuzitu_syukin()%></td>
					<td><%=list.get(z).getHusoku_zikan()%></td>
					
				<%	flag = 1;
					break;
				  	}
				}
				if(flag == 0){
				%>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				<%	
				}
				flag = 0;
				%>
			</tr>
			<%
			}
			%>
		</table>
	</form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
	
	</div>
	</div>
<footer class="footer fixed-bottom bg-dark">
      <p class="text-secondary fs-8 text-end align-bottom py-1">@2022 Lazy Dogs Co.,Ltd All Rights Reserved</p>
</footer>
</body>
</html>