<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="basic.Calendar1"%>
<%@ page import="basic.Sumple"%>
<%@ page import="java.util.Date, java.text.DateFormat"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>
<%@ page import="common.Week"%>
<%@ page import="basic.Calendar1"%>
<%@ page import="beans.RecordBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
int c1 = (int) session.getAttribute("c1");
%>
<%
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
<%--
String sumover = Sumple.zangyou;
String sumnight = Sumple.sinya;
String sumholiday = Sumple.kyujitu;
String sumdeficiency = Sumple.husoku;
--%>
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
<title>TopMenu</title>
<link rel="stylesheet" href="Menu.css">
</head>
<body>
	<form action="KintaiServlet" method="post">
		<header>
			<div class="login">
				<p>
					社員ID<br>Ｓ０００１<br>名前<br>山田太郎
				</p>
			</div>
			<h1 class="title">株式会社マート広告代理店</h1>
		</header>
		<p class="toptime">
			<%=realtime%>
		</p>
		<table class="toptable">
			<tr>
				<div class="timedisplay">
					<p><%=intime%>
					</p>
					<p><%=outtime%>
					</p>
				</div>
			</tr>
			<tr>
				<div class="workbutton">
					<input type="hidden" name="id" value="S0001" /> <input
						type="submit" value="出勤" name="button" class="goworkbutton"
						<%=d1%>> <input type="submit" value="退勤" name="button"
						class="leavingworkbutton" <%=d2%>>
				</div>
			</tr>
		</table>
		<div class="application">
			<input type="radio" name="r" value="特記なし">特記なし <input
				type="radio" name="r" value="1日休">１日休 <input type="radio"
				name="r" value="午前休">午前休 <input type="radio" name="r"
				value="午後休">午後休<br>
			<button type="button">特記</button>
		</div>
		<table class="sumtable">
			<tr>
				<td rowspan="2">合計時間</td>
				<th>残業時間<br>（分）
				</th>
				<th>深夜残業時間<br>（分）
				</th>
				<th>休日出勤時間<br>（分）
				</th>
				<th>不足時間<br>（分）
				</th>
			</tr>
			<tr>
				<td><%=sumover%></td>
				<td><%=sumnight%></td>
				<td><%=sumholiday%></td>
				<td><%=sumdeficiency%></td>
			</tr>
		</table>
		<table class="monthtable">
			<tr>
				<div class="monthbutton">
					<input type="hidden" name="id" value="S0001" /> <input
						type="submit" value="先月" name="button" class="lastmonthbutton">
					<input type="submit" value="来月" name="button"
						class="nextmonthbutton">
				</div>
			</tr>
		</table>
		<table class="datatable">
			<tr>
				<th>日付</th>
				<th>出勤時刻</th>
				<th>退勤時刻</th>
				<th>残業時間<br>（分）
				</th>
				<th>深夜残業時間<br>（分）
				</th>
				<th>休日出動時間<br>（分）
				</th>
				<th>不足時間（分）</th>
			</tr>
			<%
			int month = c1;
			int changecount;
			String[] week;
			if (month == 2) {
				changecount = 28;
			} else if (month == 4 && month == 6 && month == 9 && month == 11) {
				changecount = 30;
			} else {
				changecount = 31;
			}

			if (month == 8) {
				week = Week.hati();
			} else if (month == 2 || month == 3 || month == 11) {
				week = Week.ni();
			} else if (month == 6) {
				week = Week.roku();
			} else if (month == 9 || month == 12) {
				week = Week.kyu();
			} else if (month == 4 || month == 7) {
				week = Week.yon();
			} else if (month == 10 || month == 1) {
				week = Week.iti();
			} else {
				week = Week.go();
			}
			%>
			<%
			int sum1;
			int sum2;
			int sum3;
			int sum4;
			int t = 0;
			int a;
			int i = 0;
			int m = 0;
			int flag = 0;

			for( i = 0;i < changecount ;i++) {
			%>
			<tr <%=week[t]== "日" ? "bgcolor = \"#ffb6c1\"" : week[t]== "土" ? "bgcolor = \"#ffffe0\"":""%>>
				<td><%=month%>/<%=i + 1%>(<%=week[t]%>)</td>
				<%for(int z = 0; z < list.size(); z++){ 
				  	if((i + 1) == list.get(z).getDay()){
				%>
					<td><%=list.get(z).getShukin_zikan()%></td>
					<td><%=list.get(z).getTaikin_zikan()%></td>
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
			t++;
			m++;
			%>
			<%
			}
			%>
		</table>
	</form>
	<div class="checkbutton">
		<button onclick="location.href='Confirmation.jsp'" type="button">確認</button>
	</div>
</body>
</html>