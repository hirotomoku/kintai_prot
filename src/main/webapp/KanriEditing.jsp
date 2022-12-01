<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="basic.Week"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KanriEdit</title>
<link rel="stylesheet" href="Menu.css">
</head>
<body>
		<header>
			<div class="login">
				<p>
					社員ID<br>S０００１<br>名前<br>山田太郎
				</p>
			</div>
			<h1 class="title">従業員データ編集画面</h1>
		</header>
		<!-- <p class="toptime"><%--<%=realtime--%>ログイン時の時刻を表示させる場所
		</p>
		<table class="toptable">
			<tr>
				<div class="timedisplay">
					<p><%--<%=intime--%>09:00
					</p>
					<p><%--<%=outtime--%>18:00
					</p>
				</div>
			</tr>
			<tr>
				<div class="workbutton">
					<input type="hidden" name="id" value="S0001" /> <input
						type="submit" value="出勤" name="gowork" class="goworkbutton">
					<input type="submit" value="退勤" name="leavingwork"
						class="leavingworkbutton">
				</div>
			</tr>
		</table>
		<div class="application">
			<input type="radio" name="r" value="特記なし">特記なし <input
				type="radio" name="r" value="1日休">１日休 <input type="radio"
				name="r" value="午前休">午前休 <input type="radio" name="r"
				value="午後休">午後休<br>
			<button type="button">特記</button>
		</div>-->
		<div class="editbutton"><button>編集</button></div>
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
				<td>
					<%--<%=sumover--%>00
				</td>
				<td>
					<%--<%=sumnight--%>00
				</td>
				<td>
					<%--<%=sumholiday--%>00
				</td>
				<td>
					<%--<%=sumdeficiency--%>00
				</td>
			</tr>
		</table>
		<table class="monthtable">
			<tr>
				<div class="monthbutton">
					<input type="hidden" name="id" value="S0001" /> <input
						type="submit" value="先月" name="lastmonth" class="lastmonthbutton">
					<input type="submit" value="来月" name="nextmonth"
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
				<th>休日出勤時間<br>（分）
				</th>
				<th>不足時間（分）</th>
			</tr>
				<%
				int i;
				int t = 0;
				int a;
				for (i = 0; i < 31; i++) {
					t++;
				%>
				<tr class="holidaycollor[a]">
				<td>10/<%=i + 1%>(<%=Week.infoarray[t]%>)
				</td>
				<td>09:00</td>
				<td>18:00</td>
				<td>00</td>
				<td>00</td>
				<td>00</td>
				<td>00</td>
				</tr>
				<%
				}
				%>
		</table>
		<div class="back"><button>戻る</button></div>
</body>
</html>